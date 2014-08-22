package controllers;

import java.util.Date;

import play.mvc.Controller;
import play.mvc.Result;

import models.Product;

public class ProductController extends Controller {

	public static Result create() {

		return redirect("/product/list");
	}

	public static Result delete(Long id) {
	

		return redirect("/product/list");	
	}
	
	public static Result update(Long id) {
	
		
		return redirect("/product/list");
	}
	
	public static Result list() {
	
		return ok(views.html.Product.list.render());
	}
	
	public static Result show(Long id) {
	
		return ok(views.html.Product.show.render(new Product( "Sohan", "Des", new Date(), new Date() )));
	}
	
	public static Result edit(Long id) {
		Product prod = new Product( "Sohan", "Des", new Date(), new Date() );
		prod.setId(4l);
		return ok(views.html.Product.edit.render( prod, "TESTID__"));
	}
	
	public static Result crt() {
	
		return ok(views.html.Product.create.render(new Product( "Sohan", "Des", new Date(), new Date() ), "TESTID__"));
	}
	
}