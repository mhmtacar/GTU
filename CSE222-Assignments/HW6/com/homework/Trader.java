package com.homework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Trader class
 * 
 * @author mehmet_acar
 */

public class Trader extends User{
	
	private String name;
	public static ArrayList<String>id=new ArrayList<String>();
	public static ArrayList<String>product_name=new ArrayList<String>();
	public static ArrayList<String>product_category_tree=new ArrayList<String>();
	public static LinkedList<String>price=new LinkedList<String>();
	public static LinkedList<String> discounted_price=new LinkedList<String>();
	public static LinkedList<String>description=new LinkedList<String>();
	public static ArrayList<String>names=new ArrayList<String>();
	private File file=new File("products.txt");
	public FileWriter fw;
	private File file2=new File("traders.txt");
	public FileWriter fw2;
	
	
	/**
     * Constructor of Trader
     * @param id ID of the Trader
     * @param password Password of the Trader
     * @param name Name of the trader
     * @throws IOException
     */
	public Trader(String id,String password,String name) throws IOException {
		
		super(id,password);
		this.name=name;
		
		try {
			fw=new FileWriter(file,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fw2=new FileWriter(file2,true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fw2.write(name + ", " + id + ", " + password + "\n");
		
	}
	
	
	/**
     * Getter for name
     * @return name of the Trader
     */
	public String getName() {
		return name;
	}
	
	
	/**
     * Loads traders' products.
     */
	public void load_product() {
		
		int column_counter=0;
		String file="e-commerce-samples.csv";
		BufferedReader reader=null;
		String line="";
		String id=null;
		String product_name=null;
		String product_category_tree=null;
		String price=null;
		String discounted_price=null;
		String description=null;
		
		try {
			reader=new BufferedReader(new FileReader(file));
		    while((line=reader.readLine())!=null) {
		    	String[] row = line.split(";");
		    	for(String data : row) {
		    		if(column_counter==0) {
		    			id=data;
		    		}
		    		
		    		else if(column_counter==1) {
		    			product_name=data;
		    		}
		    		
		    		else if(column_counter==2) {
		    			product_category_tree=data;
		    		}
		    		
		    		else if(column_counter==3) {
		    			price=data;
		    		}
		    		
		    		else if(column_counter==4) {
		    			discounted_price=data;
		    		}
		    		
		    		else if(column_counter==5) {
		    			description=data;
		    		}
		    		
		    		else if(column_counter==6) {
		    			
		    			if(data.equals(this.name)) {
		    				this.id.add(id);
		    				this.product_name.add(product_name);
		    				this.product_category_tree.add(product_category_tree);
		    				this.price.add(price);
		    				this.discounted_price.add(discounted_price);
		    				this.description.add(description);
		    				this.names.add(this.name);
		    				fw.write(this.name + ", " + id + ", " + product_name + ", " + product_category_tree + ", " + price + ", " + discounted_price + ", " + description + "\n");
		    			}
		    		}
		    		column_counter++;
		    	}
		    	column_counter=0;
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
     * Add product to traders' products
     * @param id ID of the product
     * @param product_name name of the product
     * @param product_category_tree category tree of product
     * @param price price of the product
     * @param discounted_price discounted price of the product
     * @param description description of the product
     * @throws IOException
     */
	public void add_product(String id,String product_name,String product_category_tree,String price,String discounted_price,String description) throws IOException {
		this.id.add(id);
		this.product_name.add(product_name);
		this.product_category_tree.add(product_category_tree);
		this.price.add(price);
		this.discounted_price.add(discounted_price);
		this.description.add(description);
		this.names.add(this.name);
		System.out.println("Product which has " + id + " id is added");
		fw.write(this.name + ", " + id + ", " + product_name + ", " + price + ", " + discounted_price + ", " + description + "\n");
	}
	
	
	/**
     * Remove product from traders' products
     * @param id ID of the product
     * @return removed_product_id
     * @throws Exception
     */
	public String remove_product(String id) throws Exception {
		
		int count=0;
		
		int index=this.id.indexOf(id);
		
		if(index!=-1) {
		String removed_product_id=this.id.get(index);
		this.id.remove(index);
		this.product_name.remove(index);
		this.product_category_tree.remove(index);
		this.price.remove(index);
		this.discounted_price.remove(index);
		this.description.remove(index);
		this.names.remove(index);
		return removed_product_id;
		}
		
		throw new Exception("Trader does not have this product. So, Trader can not remove this.");
		
	}
	
	
	/**
     * Edit traders' product
     * @param id ID of the product
     * @param product_name name of the product
     * @param product_category_tree category tree of product
     * @param price price of the product
     * @param discounted_price discounted price of the product
     * @param description description of the product
     * @throws Exception
     */
	public void edit_product(String id,String product_name,String product_category_tree,String price,String discounted_price,String description) throws Exception {
		
		int index=this.id.indexOf(id);
		
		if(index!=-1) {
		this.product_name.set(index, product_name);
		this.product_category_tree.set(index, product_category_tree);
		this.price.set(index, price);
		this.discounted_price.set(index, discounted_price);
		this.description.set(index, description);
		System.out.println("Edited product id: " + id);
		}
		
		else {
		throw new Exception("Trader does not have this product. So, Trader can not edit this.");
		}
		
	}
	
	
	/**
     * Meets order
     * @throws Exception
     */
	public void meet_order() throws Exception {
		remove_product(Customer.ordered_id.get(Customer.ordered_id.size()-1));
		Customer.write_order();
		System.out.println("Last order which has " + Customer.ordered_id.get(Customer.ordered_id.size()-1) + " product id is meeted by trader");
	}
	
	
	/**
     * Cancels order.
     */
	public void cancel_order() {
		String removed_product_id=Customer.ordered_id.remove(Customer.ordered_id.size()-1);
		Customer.ordered_product_name.remove(Customer.ordered_product_name.size()-1);
		Customer.ordered_product_category_tree.remove(Customer.ordered_product_category_tree.size()-1);
		Customer.ordered_price.remove(Customer.ordered_price.size()-1);
		Customer.ordered_discounted_price.remove(Customer.ordered_discounted_price.size()-1);
		Customer.ordered_description.remove(Customer.ordered_description.size()-1);
		System.out.println("Last order which has " + removed_product_id + " product id is canceled by trader");
	}
	
	
	/**
     * Prints id's of products.
     */
	public void print_id() {
		System.out.println("id");
		for(int i=0;i<id.size();i++) {
			System.out.println("Product " + (i+1) + " id: " + id.get(i));
		}
		System.out.println();
	}
	
	
	/**
     * Prints names of products.
     */
	public void print_product_name() {
		System.out.println("product_name");
		for(int i=0;i<product_name.size();i++) {
			System.out.println("Product " + (i+1) + " name: " + product_name.get(i));
		}
		System.out.println();
	}
	
	
	/**
     * Prints category trees of products.
     */
	public void print_product_category_tree() {
		System.out.println("product_category_tree");
		for(int i=0;i<product_category_tree.size();i++) {
			System.out.println("Product " + (i+1) + " category_tree: " + product_category_tree.get(i));
		}
		System.out.println();
	}
	
	
	/**
     * Prints prices of products.
     */
	public void print_price() {
		System.out.println("price");
		for(int i=0;i<price.size();i++) {
			System.out.println("Product " + (i+1) + " price: " + price.get(i));
		}
		System.out.println();
	}
	
	
	/**
     * Prints discounted prices of products.
     */
	public void print_discounted_price() {
		System.out.println("discounted_price");
		for(int i=0;i<discounted_price.size();i++) {
			System.out.println("Product " + (i+1) + " discounted_price: " + discounted_price.get(i));
		}
		System.out.println();
	}
	
	
	/**
     * Prints descriptions of products.
     */
	public void print_description() {
		System.out.println("description\n");
		for(int i=0;i<description.size();i++) {
			System.out.println("Product " + (i+1) + " description: " + description.get(i) + "\n");
		}
		System.out.println();
	}
	
	
}

