package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result login() {		//Login page
	
		
		return ok(views.html.Application.login.render());
	}
	
	public static Result logout() {
	
		return redirect("/");
	}
	
	public static Result authenticate() {
	
		return redirect("/product/list");
	}
	
}