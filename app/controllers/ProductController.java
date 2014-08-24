package controllers;

import java.util.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import play.data.Form;
import play.data.DynamicForm;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.Ebean;

import models.*;
import auth0.*;

public class ProductController extends Controller {

	public static Result create() {
	
		authorize();
	
		Product prod = bindProduct();
		
		prod.setUserId(parseLong(session("user.id")));
		
		prod.setCreatedDate(new Date());
		prod.setLastModifiedDate(prod.getCreatedDate());
		
		Ebean.save(prod);
		
		flash("msg", "The item has been created successfully.");

		return redirect("/product/list");
	}

	public static Result delete(Long id) {
	
		authorize();

		Product prod = retrieveProduct(id);

		Ebean.delete(prod);

		flash("msg", "Product " + id + "deleted Successfully.");
		
		return redirect("/product/list");	
	}
	
	public static void authorize() {
		if( new Auth0Filter().authorize() ) {
			return;
		}
		redirect("/");
	}
	
	public static Product retrieveProduct(long id) {
	
		Product prod = Ebean.find(Product.class)
		.where()
		.eq("id", id)
		.eq("userId", parseLong(session("user.id")))
		.findList()
		.get(0);
		
		return prod;
	}

	private static Product bindProduct() {
		Product prod = new Product();		
		return bindProduct(prod);
	}
	
	private static Product bindProduct(Product prod) {
	
		DynamicForm requestData = Form.form().bindFromRequest();
		String name = requestData.get("name");
		String description = requestData.get("description");
		
		prod.setName(name);
		prod.setDescription(description);
		return prod;
	}
	
	public static Result update(Long id) {
	
		authorize();

		Product prod = retrieveProduct(id);
		
		bindProduct(prod);
		
		prod.setLastModifiedDate(new Date());
		
		Ebean.update(prod);
		
		flash("msg", "Product " + id + "updated Successfully.");
		
		return redirect("/product/list");
	}
	
	public static Result list() {
	
		authorize();

		long page = requestLong("page");
		long size = requestLong("size");
		
		size = Math.min(size, 50l);
		size = size <= 0l ? 20l : size;
		
		long offset = ( page - 1l ) * size;
			
		List<Product> prods = Ebean.find(Product.class)
			.where()
			.eq("userId", parseLong(session("user.id")))
			.setFirstRow((int) offset)
			.setMaxRows((int) size)
			.findList();
			
		String u_name = session("user.name");
	
		return ok(views.html.Product.list.render(prods, u_name));
	}
	
	static long requestLong(String key) {
	
		try {
		
			DynamicForm requestData = Form.form().bindFromRequest();
			
			long l = Long.parseLong(requestData.get(key));
			
		//	System.out.println("###[[parse : " + l + " => " + session("user.id"));
			
			return l;
			
		} catch(NullPointerException | NumberFormatException ex) {
		
			return 0;
		}
		
	}
	
	static long parseLong(String key) {
	
		try {
		
			DynamicForm requestData = Form.form().bindFromRequest();
			
			long l = Long.parseLong(key);
			
		//	System.out.println("###[[parse : " + l + " => " + session("user.id"));
			
			return l;
			
		} catch(NullPointerException | NumberFormatException ex) {
		
			return 0;
		}
		
	}
	
	public static Result show(Long id) {
	
		authorize();
	
		Product prod = retrieveProduct(id);
		
		String u_name = session("user.name");
		
		return ok(views.html.Product.show.render(prod, u_name));
	}
	
	public static Result edit(Long id) {
	
		authorize();

		Product prod = retrieveProduct(id);
		
		String u_name = session("user.name");
		
		return ok(views.html.Product.edit.render( prod, u_name));
	}
	
	public static Result crt() {
	
		authorize();
	
		String u_name = session("user.name");
	
		return ok(views.html.Product.create.render(u_name));
	}
	
}