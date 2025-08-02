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

import aws.sdk.kotlin.runtime.auth.credentials.StaticCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.GetObjectRequest
import aws.sdk.kotlin.services.s3.putObject
import aws.smithy.kotlin.runtime.content.ByteStream
import aws.smithy.kotlin.runtime.content.decodeToString
import aws.smithy.kotlin.runtime.net.url.Url

/**
 * Created by Kyle Fricilone on Oct 26, 2021.
 */
public class S3Storage(
    endpoint: String?,
    region: String,
    access: String,
    secret: String,
    private val bucket: String
) : Storage {

    private val client: S3Client = S3Client {
        endpoint?.let {
            endpointUrl = Url.parse(it)
        }
        credentialsProvider = StaticCredentialsProvider {
            accessKeyId = access
            secretAccessKey = secret
        }
        this.region = region
    }

    override suspend fun load(key: String): String = client.getObject(
        GetObjectRequest {
            bucket = this@S3Storage.bucket
            this.key = key
        }
    ) {
        return@getObject it.body!!.decodeToString()
    }

    override suspend fun save(key: String, value: String) {
        client.putObject {
            bucket = this@S3Storage.bucket
            this.key = key
            body = ByteStream.fromString(value)
        }
    }

    public companion object {
        public const val S3_ENDPOINT_KEY: String = "S3_ENDPOINT"
        public const val S3_BUCKET_KEY: String = "S3_BUCKET"
        public const val S3_REGION_KEY: String = "S3_REGION"
        public const val S3_ACCESS_KEY: String = "S3_ACCESS"
        public const val S3_SECRET_KEY: String = "S3_SECRET"
    }
}
