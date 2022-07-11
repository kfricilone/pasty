/*
 * Copyright (c)  Kyle Fricilone (https://kfricilone.me)
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package me.kfricilone.pasty

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.runCatching
import com.github.michaelbull.retry.policy.limitAttempts
import com.github.michaelbull.retry.retry
import me.kfricilone.pasty.keygen.Keygen
import me.kfricilone.pasty.storage.Storage
import java.lang.IllegalStateException

/**
 * Created by Kyle Fricilone on Nov 05, 2021.
 */
public class Service(
    private val keygen: Keygen,
    private val storage: Storage
) {
    public suspend fun post(doc: SaveDocumentRequest): SaveDocumentResponse {
        val key = generateKey()
        storage.save(key, doc.text)
        return SaveDocumentResponse(
            if (doc.lang != "none") {
                "/doc/$key.${doc.lang}"
            } else {
                "/doc/$key"
            }
        )
    }

    public suspend fun get(doc: GetDocument, model: MutableMap<String, Any>): Result<Unit, Throwable> = runCatching {
        val parts = doc.key.split('.', limit = 2)
        model["code"] = storage.load(parts.first())
        model["key"] = parts.first()
        if (parts.size == 1) {
            model["lang"] = "language-none"
        } else if (parts.size > 1) {
            model["lang"] = "language-${parts.last()}"
        }
    }

    public suspend fun edit(doc: EditDocument, model: MutableMap<String, Any>): Result<Unit, Throwable> =
        runCatching { model["doc"] = storage.load(doc.key) }

    private suspend fun generateKey() = retry(limitAttempts(RETRY_LIMIT)) {
        val key = keygen.createKey()
        when (runCatching { storage.load(key) }) {
            is Err -> key
            else -> throw IllegalStateException("Could not find unused key in $RETRY_LIMIT attempt(s)")
        }
    }

    private companion object {
        private const val RETRY_LIMIT = 10
    }
}
