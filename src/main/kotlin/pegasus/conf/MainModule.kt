package pegasus.conf

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import pegasus.web.WebApplication
import pegasus.web.resources.ConfResource

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