package pegasus.conf

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

class TestModule : AbstractModule() {
    override fun configure() {
    }

    @Provides
    @Singleton
    fun provideConfig(): Config {
        return ConfigFactory.load("application.local.conf")
    }
}