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

import com.github.michaelbull.result.fold
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.UserHashedTableAuth
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.basic
import io.ktor.server.cio.EngineMain
import io.ktor.server.plugins.calllogging.CallLogging
import io.ktor.server.plugins.conditionalheaders.ConditionalHeaders
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.defaultheaders.DefaultHeaders
import io.ktor.server.request.receive
import io.ktor.server.resources.Resources
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import io.ktor.server.thymeleaf.Thymeleaf
import io.ktor.server.thymeleaf.ThymeleafContent
import io.ktor.server.webjars.Webjars
import io.ktor.util.getDigestFunction
import org.koin.core.logger.Level
import org.koin.core.qualifier.named
import org.koin.environmentProperties
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver

private val digest = getDigestFunction("SHA-256") { "pasty${it.length}" }

public fun main(args: Array<String>): Unit = EngineMain.main(args)

public fun Application.module() {
    val users = System.getenv("AUTH_USERS")
    val table = mutableMapOf<String, ByteArray>()
    users?.split(",")?.forEach { user ->
        user.split(":").also {
            table[it[0]] = digest(it[1])
        }
    }

    val userTable = UserHashedTableAuth(digest, table)

    install(DefaultHeaders)
    install(ConditionalHeaders)
    install(Webjars)
    install(Resources)
    install(CallLogging) {
        disableDefaultColors()
    }
    install(ContentNegotiation) {
        json()
        ignoreType<ThymeleafContent>()
    }
    install(Authentication) {
        basic {
            realm = "Access to the '/post' path"
            validate { userTable.authenticate(it) }
        }
    }
    install(Thymeleaf) {
        setTemplateResolver(
            ClassLoaderTemplateResolver().apply {
                prefix = "templates/"
                suffix = ".html"
                characterEncoding = "utf-8"
            }
        )
    }
    install(Koin) {
        slf4jLogger(Level.ERROR)
        environmentProperties()
        modules(pastyModule)
    }

    routing {
        index()
        getDocument()
        editDocument()
        when {
            table.isEmpty() -> postDocument()
            else -> authenticate { postDocument() }
        }
    }
}

private fun Route.index() {
    val langs by inject<Langs>(named("langs"))
    get<Index> {
        call.respond(ThymeleafContent("pasty", mapOf("langs" to langs.langs)))
    }
}

private fun Route.postDocument() {
    val service by inject<Service>()
    post<PostDocument> {
        call.respond(service.post(call.receive()))
    }
}

private fun Route.getDocument() {
    val service by inject<Service>()
    val model: MutableMap<String, Any> = mutableMapOf()
    get<GetDocument> {
        service
            .get(it, model)
            .onSuccess {
                call.respond(ThymeleafContent("pasty", model))
            }.onFailure {
                call.respondRedirect("/")
            }
    }
}

private fun Route.editDocument() {
    val service by inject<Service>()
    val langs by inject<Langs>(named("langs"))
    val model: MutableMap<String, Any> = mutableMapOf("langs" to langs.langs)
    get<EditDocument> {
        service
            .edit(it, model)
            .onSuccess {
                call.respond(ThymeleafContent("pasty", model))
            }.onFailure {
                call.respondRedirect("/")
            }
    }
}
