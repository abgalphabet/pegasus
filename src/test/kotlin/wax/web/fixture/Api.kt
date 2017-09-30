package wax.web.fixture

import org.apache.http.HttpHost
import org.apache.http.client.fluent.Request

class Api(val path: String) {

    init {
    }

    fun get() : String {
        return Request.Get(path)
            .execute()
            .returnContent()
            .asString()

    }
}