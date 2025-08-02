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

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * Created by Kyle Fricilone on Oct 26, 2021.
 */
private object Documents : LongIdTable() {
    val key = text("key")
    val value = text("value")
}

public class PostgresStorage(
    connection: String
) : Storage {

    private val db = Database.connect("jdbc:$connection", "org.postgresql.Driver")

    init {
        transaction(db) {
            SchemaUtils.create(Documents)
        }
    }

    override suspend fun load(key: String): String = transaction(db) {
        Documents.selectAll().where { Documents.key eq key }.single()[Documents.value]
    }

    override suspend fun save(key: String, value: String) {
        transaction(db) {
            Documents.insert {
                it[Documents.key] = key
                it[Documents.value] = value
            }
        }
    }

    public companion object {
        public const val POSTGRES_CONNECTION_KEY: String = "POSTGRES_CONNECTION"
    }
}
