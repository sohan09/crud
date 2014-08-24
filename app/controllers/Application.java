package controllers;

import play.mvc.Controller;
import play.mvc.Result;

import com.avaje.ebean.Ebean;

import models.*;

public class Application extends Controller {

	public static Result login() {		//Login page

		String email = "sohan09014@gmail.com";
		String fName = "Sohan";
		String lName = "Nohemy";
	
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

		return ok(views.html.Application.login.render());
		//return ok(Long.parseLong(session("user.id")) + "");
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
	
		return redirect("/product/list");
	}
	
}