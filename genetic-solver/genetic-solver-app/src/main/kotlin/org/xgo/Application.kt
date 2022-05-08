package org.xgo

import com.google.inject.AbstractModule
import com.google.inject.Guice
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.xgo.plugins.*

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.module() {
    Guice.createInjector(MainModule(this), DomainModule(), AdaptersModule())
}

class MainModule(private val application: Application) : AbstractModule() {
    override fun configure() {
        bind(Application::class.java).toInstance(application)
    }
}
