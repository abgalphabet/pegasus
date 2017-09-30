package wax.web

import com.google.inject.Guice
import com.google.inject.Injector
import com.google.inject.util.Modules
import org.apache.http.HttpHost
import org.restlet.Component
import org.restlet.data.Protocol
import org.restlet.ext.guice.RestletGuice
import wax.conf.MainModule
import wax.conf.TestModule

class TestApp {
    private val injector: Injector
    private val component: Component
    lateinit var httphost: HttpHost


    init {
        injector = Guice.createInjector(RestletGuice.Module(Modules.override(MainModule()).with(TestModule())))

        // Attach application to http://localhost:9001/v1
        component = Component()
        component.servers.add(Protocol.HTTP, 0)
        component.defaultHost.attach("/v1", injector.getInstance(WebApplication::class.java))
    }

    fun start() {
        component.start()
        val port = component.servers[0].actualPort
        httphost = HttpHost.create("http://localhost:${port}")
    }

    fun stop() {
        component.stop()
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            TestApp().start()
        }
    }


}