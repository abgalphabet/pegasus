package wax.web;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.guice.RestletGuice;
import sun.applet.Main;
import wax.conf.MainModule;

public class MainApp {

    public static void main(String[] args) throws Exception {
        new MainApp().init();
    }

    private void init() throws Exception {
        Injector injector = Guice.createInjector(new RestletGuice.Module(new MainModule()));

        // Attach application to http://localhost:9001/v1
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9001);
        c.getDefaultHost().attach("/v1", injector.getInstance(WebApplication.class));
        c.start();
    }



}