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

import kotlinx.serialization.Serializable
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.eq
import org.litote.kmongo.reactivestreams.KMongo

/**
 * Created by Kyle Fricilone on Oct 26, 2021.
 */

@Serializable
private data class Document(
    val key: String,
    val value: String
)

public class MongoStorage(
    connection: String,
    database: String
) : Storage {

    private val client = KMongo.createClient(connection).coroutine
    private val db = client.getDatabase(database)
    private val collection = db.getCollection<Document>()

    override suspend fun load(key: String): String = collection.findOne(Document::key eq key)!!.value

    override suspend fun save(key: String, value: String) {
        collection.find(Document::key eq key).first()
        collection.insertOne(Document(key, value))
    }

    public companion object {
        public const val MONGO_DATABASE_KEY: String = "MONGO_DATABASE"
        public const val MONGO_CONNECTION_KEY: String = "MONGO_CONNECTION"
    }
}
