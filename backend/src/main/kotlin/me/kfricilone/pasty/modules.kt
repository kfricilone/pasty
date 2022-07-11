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

import me.kfricilone.pasty.keygen.Keygens
import me.kfricilone.pasty.keygen.PhoneticKeygen
import me.kfricilone.pasty.keygen.RandomKeygen
import me.kfricilone.pasty.storage.FileStorage
import me.kfricilone.pasty.storage.MongoStorage
import me.kfricilone.pasty.storage.PostgresStorage
import me.kfricilone.pasty.storage.RedisStorage
import me.kfricilone.pasty.storage.S3Storage
import me.kfricilone.pasty.storage.Storages
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Created by Kyle Fricilone on Nov 05, 2021.
 */
public val pastyModule: Module = module {
    single {
        val len = getProperty(Keygens.LenKey, Keygens.DefaultLen).toInt()
        when (Keygens.valueOf(getProperty(Keygens.Key, Keygens.Default))) {
            Keygens.RANDOM -> RandomKeygen(len)
            Keygens.PHONETIC -> PhoneticKeygen(len)
        }
    }

    single {
        when (Storages.valueOf(getProperty(Storages.Key, Storages.Default))) {
            Storages.FILE -> FileStorage()
            Storages.S3 -> S3Storage(
                getPropertyOrNull(S3Storage.S3EndpointKey),
                getProperty(S3Storage.S3RegionKey),
                getProperty(S3Storage.S3AccessKey),
                getProperty(S3Storage.S3SecretKey),
                getProperty(S3Storage.S3BucketKey)
            )
            Storages.MONGO -> MongoStorage(
                getProperty(MongoStorage.MongoConnectionKey),
                getProperty(MongoStorage.MongoDatabaseKey)
            )
            Storages.POSTGRES -> PostgresStorage(getProperty(PostgresStorage.PostgresConnectionKey))
            Storages.REDIS -> RedisStorage(getProperty(RedisStorage.RedisConnectionKey))
        }
    }

    single(named("langs")) {
        Langs.valueOf(getProperty(Langs.Key, Langs.Default))
    }

    single { Service(get(), get()) }
}
