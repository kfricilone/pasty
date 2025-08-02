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

package me.kfricilone.pasty.storage

import io.lettuce.core.RedisClient
import io.lettuce.core.api.coroutines

/**
 * Created by Kyle Fricilone on Oct 26, 2021.
 */
public class RedisStorage(
    connection: String,
) : Storage {

    private val client = RedisClient.create(connection)
    private val commands = client.connect().coroutines()

    override suspend fun load(key: String): String = commands.get(key)!!

    override suspend fun save(
        key: String,
        value: String
    ) {
        commands.set(key, value)
    }

    public companion object {
        public const val REDIS_CONNECTION_KEY: String = "REDIS_CONNECTION"
    }
}
