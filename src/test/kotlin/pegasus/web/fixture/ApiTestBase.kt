package pegasus.web.fixture

import pegasus.web.TestApp

open class ApiTestBase {
    val apis: Apis
        get() = __APIS

    companion object {
        private val app: TestApp = TestApp()
        private val __APIS: Apis

        init {
            app.start()
            __APIS = Apis(app.httphost)
        }
    }



}