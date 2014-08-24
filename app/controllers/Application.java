package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.data.DynamicForm;
import play.data.Form;
import play.Play;
import com.avaje.ebean.Ebean;
import models.*;
import auth0.*;


public class Application extends Controller {

	public static Result login() {		//Login page
	
		String clientId = Play.application().configuration().getConfig("auth0").getString("client_id");
		
		String domain = Play.application().configuration().getConfig("auth0").getString("domain");
		
		String callbackUrl = Play.application().configuration().getConfig("auth0").getString("callback_url");
	
		String state = new NonceGenerator().generateNonce();
		
		DynamicForm requestData = Form.form().bindFromRequest();
		
		String error = requestData.get("error"); 
		
		session("state", state);
	
		return ok(views.html.Application.login.render(clientId, callbackUrl, domain, state, error));
	}
	
	public static User retrieveUser(String email) {
	
		return Ebean.find(User.class)
			.where()
			.eq("email", email)
			.findList()
			.get(0);
	}
	
	public static Result logout() {
	
		session().clear();
		
		return redirect("/");
	}
	
	public static Result authenticate() {
	
		try {
		
			boolean scs = new Auth0ServletCallback().doGet();
			
			if(scs) {
			
				Auth0Principal ap = new Auth0Principal(session("idToken"));
				
				String email = ap.getMail();
				String fName = "";
				String lName = "";
				
				try {
					String[] tt = ap.getName().split(" ", 2);
					fName = tt[0];
					lName = tt[1];	
				} catch(Exception ex) {
					
				}
			
				User user = null;
			
				try {
				
					user = retrieveUser(email);
				} catch (IndexOutOfBoundsException ex) {
				
					user = new User(fName, lName, email);
					Ebean.save(user);
				}
				
				user = retrieveUser(email);
				
				session("user.name", user.getFirstName() + " " + user.getLastName());
				session("user.id", user.getId() + "");
			
				return redirect("/product/list");
				
			} else {
			
				return redirect("/");
			}	
		
		} catch (Exception ex) {
		
			return redirect("/" + "?msg=" + ex + "[" + ex.getMessage() + "]");
		}
		
	}
	
}