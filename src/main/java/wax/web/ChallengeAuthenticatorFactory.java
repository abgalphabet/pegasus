package wax.web;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.MemoryRealm;
import org.restlet.security.Role;
import org.restlet.security.User;

public class ChallengeAuthenticatorFactory {

    /*
     * Define role names
     */
    public static final String ROLE_ADMIN = "admin";

    public static final String ROLE_ANYONE = "anyone";

    public static final String ROLE_DEV = "cellroledev";

    public static final String ROLE_OWNER = "cellroleowner";

    public static final String ROLE_USER = "cellroleuser";


	private ChallengeAuthenticator create(Application application, Restlet next) {

        ChallengeAuthenticator apiGuard = new ChallengeAuthenticator(application.getContext(), ChallengeScheme.HTTP_BASIC, "realm");

        // Create in-memory users and roles.
        MemoryRealm realm = new MemoryRealm();
        User owner = new User("owner", "owner");
        realm.getUsers().add(owner);
        realm.map(owner, Role.get(application, ROLE_OWNER));
        realm.map(owner, Role.get(application, ROLE_USER));
        realm.map(owner, Role.get(application, ROLE_DEV));
        User admin = new User("admin", "admin");
        realm.getUsers().add(admin);
        realm.map(admin, Role.get(application, ROLE_ADMIN));
        realm.map(admin, Role.get(application, ROLE_OWNER));
        realm.map(admin, Role.get(application, ROLE_USER));
        realm.map(admin, Role.get(application, ROLE_DEV));
        User user = new User("user", "user");
        realm.getUsers().add(user);
        realm.map(user, Role.get(application, ROLE_USER));

        // Verifier : to check authentication
        apiGuard.setVerifier(realm.getVerifier());
        // Enroler : add authorization roles
        apiGuard.setEnroler(realm.getEnroler());

        // You can also create your own authentication/authorization system by
        // creating classes extending SecretVerifier or LocalVerifier (for
        // authentication) and Enroler (for authorization) and set these to the
        // guard.

        apiGuard.setNext(next);

        // In case of anonymous access supported by the API.
        apiGuard.setOptional(true);

        return apiGuard;
    }

}
