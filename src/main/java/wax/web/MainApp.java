package wax.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.restlet.Component;
import org.restlet.Server;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import sun.applet.Main;
import wax.conf.MainModule;

import java.net.URL;

public class MainApp {

    public static void main(String[] args) throws Exception {
        new MainApp().init();
    }

    private void init() throws Exception {
        Injector injector = Guice.createInjector(new RestletGuice.Module(new MainModule()));

        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9080);
        Server https = c.getServers().add(Protocol.HTTPS, 9443);

        URL keyfile = MainApp.class.getResource("/pki/keystore.p12");
//        URL trustfile = MainApp.class.getResource("/pki/truststore.jks");
        URL trustfile = MainApp.class.getResource("/pki/truststore.p12");
//        URL trustfile = MainApp.class.getResource("/pki/trust-arthur-only.jks");

        https.getContext().getParameters().add("keyStorePath", keyfile.getPath());
        https.getContext().getParameters().add("keyStorePassword", "passw0rd");
        https.getContext().getParameters().add("keyPassword", "passw0rd");
        https.getContext().getParameters().add("keyStoreType", "PKCS12");

        https.getContext().getParameters().add("trustStorePath", trustfile.getPath());
        https.getContext().getParameters().add("trustStorePassword", "passw0rd");
        https.getContext().getParameters().add("trustStoreType", "PKCS12");

        https.getContext().getParameters().add("needClientAuthentication", "true");

        c.getDefaultHost().attach("/v1", injector.getInstance(WebApplication.class));
        c.start();
    }



}