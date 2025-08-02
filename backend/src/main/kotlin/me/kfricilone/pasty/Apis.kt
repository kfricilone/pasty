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

import io.ktor.resources.Resource
import kotlinx.serialization.Serializable

/**
 * Created by Kyle Fricilone on Nov 05, 2021.
 */
@Resource("/")
public class Index

@Resource("/doc")
public class PostDocument

@Resource("/doc/{key}")
public data class GetDocument(
    val key: String
)

@Resource("/doc/{key}/edit")
public data class EditDocument(
    val key: String
)

@Serializable
public data class SaveDocumentRequest(
    val lang: String,
    val text: String
)

@Serializable
public data class SaveDocumentResponse(
    val redirect: String
)
