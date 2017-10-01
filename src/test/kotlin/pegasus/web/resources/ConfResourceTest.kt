package pegasus.web.resources

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import pegasus.web.fixture.ApiTestBase

class ConfResourceTest : ApiTestBase() {

    @Test
    fun testGet() {
        val response = apis.conf.get()
        assertThat(response).isEqualTo("LOCAL")
    }
}