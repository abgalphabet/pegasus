package wax.web;

import com.typesafe.config.Config;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.routing.Router;
import wax.web.resources.ConfResource;

import javax.inject.Inject;

public class WebApplication extends Application {

    private final Config conf;
    private final FinderFactory finder;

    @Inject
    public WebApplication(Config conf, FinderFactory finder) {
        this.conf = conf;
        this.finder = finder;
        setName(conf.getString("app.name"));
    }


    public Restlet createInboundRoot() {
        // Router for the API's resources
        Router router = new Router(getContext());

        router.attach("/conf", finder.finder(ConfResource.class));
        router.attach("/conf", finder.finder(ConfResource.class));

        Router apiRouter = router;

        return apiRouter;
    }

}
