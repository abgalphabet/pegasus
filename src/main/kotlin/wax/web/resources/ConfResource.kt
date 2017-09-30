package wax.web.resources

import com.typesafe.config.Config
import org.restlet.ext.json.JsonRepresentation
import org.restlet.representation.Representation
import org.restlet.resource.Get
import org.restlet.resource.ServerResource
import javax.inject.Inject

class ConfResource @Inject
constructor(val conf: Config): ServerResource() {

    @Get
    fun onGet() : Representation {
        return JsonRepresentation(conf.getString("app.env"))
    }

}