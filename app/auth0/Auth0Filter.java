package auth0;

import java.io.IOException;

import play.mvc.*;
import play.data.*;
import play.Play;
import static play.mvc.Controller.*;

public class Auth0Filter {

    private String onFailRedirectTo;

    public void init() {
        onFailRedirectTo = Play.application().configuration().getConfig("auth0").getString("redirect_on_authentication_error");

        if (onFailRedirectTo == null) {
            throw new IllegalArgumentException("auth0.redirect_on_authentication_error parameter of " + this.getClass().getName() + " cannot be null");
        }
    }

    protected Tokens loadTokens() {

        return new Tokens((String) session("idToken"),
                (String) session("accessToken"));
    }

    protected void onSuccess(Tokens tokens) throws IOException {
		
    }

    protected void onReject() throws IOException {
		
    }

    public void doFilter() throws IOException {

        Tokens tokens = loadTokens();

        // Reject if not accessToken or idToken are found
        if (tokens.getAccessToken() == null || tokens.getIdToken() == null) {
            onReject();
            return;
        }

        onSuccess(tokens);
    }

    public void destroy() {
    }
}