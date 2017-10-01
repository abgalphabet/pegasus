package pegasus.web.fixture

import org.apache.http.HttpHost

class Apis(val authority: HttpHost) {
    val conf: Api

    init {
        conf = Api(endpoint("/v1/conf"))
    }

    private fun endpoint(path: String): String {
        return "${authority.toURI()}/${path}"
    }
}