package wax.conf

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import wax.web.WebApplication
import wax.web.resources.ConfResource

class MainModule : AbstractModule() {

    override fun configure() {
        bind(WebApplication::class.java)
        bind(ConfResource::class.java)
    }

    @Provides @Singleton
    fun provideConfig(): Config {
        return ConfigFactory.load("application.prod.conf")
    }

}