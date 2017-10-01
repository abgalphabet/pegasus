package wax.web.resources

import com.typesafe.config.Config
import org.restlet.ext.json.JsonRepresentation
import org.restlet.representation.Representation
import org.restlet.resource.Get
import org.restlet.resource.ResourceException
import org.restlet.resource.ServerResource
import javax.inject.Inject

class ConfResource @Inject
constructor(val conf: Config): ServerResource() {

    @Get
    fun onGet() : Representation {
        val principals = clientInfo.principals
        val principal = principals.first()

        if (!isInRole("SUPERUSER"))
            throw ResourceException(403, "Unauthorized!")

        return JsonRepresentation(conf.getString("app.env"))
    }

}