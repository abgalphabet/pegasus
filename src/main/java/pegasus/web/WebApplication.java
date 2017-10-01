package pegasus.web;

import com.typesafe.config.Config;
import org.restlet.Application;
import org.restlet.Request;
import org.restlet.Response;
import org.restlet.Restlet;
import org.restlet.data.ClientInfo;
import org.restlet.data.Reference;
import org.restlet.ext.guice.FinderFactory;
import org.restlet.routing.Router;
import org.restlet.security.Authorizer;
import org.restlet.security.CertificateAuthenticator;
import org.restlet.security.Enroler;
import org.restlet.security.Role;
import pegasus.web.resources.ConfResource;
import pegasus.web.resources.ConfResource;

import javax.inject.Inject;

import static java.util.Arrays.asList;

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

        Router apiRouter = router;

        CertificateAuthenticator guard = new CertificateAuthenticator(this.getContext());
        guard.setEnroler(new Enroler() {
            @Override
            public void enrole(ClientInfo clientInfo) {
                if (clientInfo.getPrincipals().get(0).getName().startsWith("CN=Arthur"))
                    clientInfo.setRoles(asList(new Role(getApplication(), "SUPERUSER")));
                else
                    clientInfo.setRoles(asList(new Role(getApplication(), "USER")));

            }
        });

//        Authorizer authorizer = new Authorizer() {
//            @Override
//            protected boolean authorize(Request request, Response response) {
//                Reference resourceRef = request.getResourceRef();
//
//                return false;
//            }
//        };

//        guard.setNext(authorizer);
//        authorizer.setNext(apiRouter);

        guard.setNext(apiRouter);
        return guard;
    }

}
