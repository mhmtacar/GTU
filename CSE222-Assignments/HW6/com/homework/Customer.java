package com.homework;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Customer class
 * 
 * @author mehmet_acar
 */

public class Customer extends User{
	
	private ArrayList<String>id=new ArrayList<String>();
	private ArrayList<String>product_name=new ArrayList<String>();
	private ArrayList<String>product_category_tree=new ArrayList<String>();
	private LinkedList<String>price=new LinkedList<String>();
	private LinkedList<String>discounted_price=new LinkedList<String>();
	private LinkedList<Integer>percentage_discount=new LinkedList<Integer>();
	private LinkedList<String>description=new LinkedList<String>();
	
	public static ArrayList<String>ordered_id=new ArrayList<String>();
	public static ArrayList<String>ordered_product_name=new ArrayList<String>();
	public static ArrayList<String>ordered_product_category_tree=new ArrayList<String>();
	public static LinkedList<String>ordered_price=new LinkedList<String>();
	public static LinkedList<String>ordered_discounted_price=new LinkedList<String>();
	public static LinkedList<Integer>ordered_percentage_discount=new LinkedList<Integer>();
	public static LinkedList<String>ordered_description=new LinkedList<String>();
	
	private File file=new File("orders.txt");
	public static FileWriter fw;
	private File file2=new File("customers.txt");
	public FileWriter fw2;
	
    
	/**
     * Constructor of Customer
     * @param id ID of the Customer
     * @param password Password of the Customer
     * @throws IOException
     */
	public Customer(String id,String password) throws IOException {
		
		super(id,password);
		
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
		
		fw2.write(id + ", " + password + "\n");
	}
	
	
	/**
	 * Displays products.
	 * @param trader_name determines whose products will display
	 */
	public void display_products(String trader_name) {
		
		for(int index=0;index<Trader.names.size();index++) {
			if(Trader.names.get(index).equals(trader_name)) {
				System.out.println("ID: " + Trader.id.get(index));
				System.out.println("Product name: " + Trader.product_name.get(index));
				System.out.println("Product category tree: " + Trader.product_category_tree.get(index));
				System.out.println("Price: " + Trader.price.get(index));
				System.out.println("Discounted price: " + Trader.discounted_price.get(index));
				System.out.println("Description: " + Trader.description.get(index));
			}
			System.out.println();
		}
		
	}
	
	
	/**
	 * Search products according to product name.
	 * @param search_product_name determines returning results
	 * @return searching product name
	 * @throws Exception
	 */
	public String search(String search_product_name) throws Exception {
		
		boolean product_name_find=false;
		
		if(!this.product_name.contains(search_product_name)) {
		
		for(int i=0;i<Trader.product_name.size();i++) {
			if(Trader.product_name.get(i).equals(search_product_name)) {
			    this.id.add(Trader.id.get(i));
			    this.product_name.add(Trader.product_name.get(i));
			    this.product_category_tree.add(Trader.product_category_tree.get(i));
			    this.price.add(Trader.price.get(i));
			    this.discounted_price.add(Trader.discounted_price.get(i));
			    int num=(Integer.parseInt(Trader.price.get(i)))/100;
			    int per_discount=(Integer.parseInt(Trader.discounted_price.get(i)))/num;
			    this.percentage_discount.add(per_discount);
			    this.description.add(Trader.description.get(i));
			    product_name_find=true;
			}
		}
		
		}
		
		
		if(product_name_find || this.product_name.contains(search_product_name)) {
			return search_product_name;
		}
		
		
		throw new Exception("Any trader in the system does not have this product");
		
	}
	
	
	/**
	 * Sorts products according to their prices.
	 */
	public void sort_price() {
		int n = price.size();
		
		 for (int fill = 0; fill < n - 1; fill++) {
		 // Invariant: table[0 . . . fill - 1] is sorted.
		 int posMax = fill;
		 for (int next = fill + 1; next < n; next++) {
		 // Invariant: table[posMin] is the smallest item in
		 // table[fill . . . next - 1].
		 if (Integer.parseInt(price.get(next))>Integer.parseInt(price.get(posMax))) {
		 posMax = next;
		 }
		 }
		 // assert: table[posMin] is the smallest item in
		 // table[fill . . . n - 1].
		 // Exchange table[fill] and table[posMin].
		 String temp = price.get(fill);
		 price.set(fill,price.get(posMax));
		 price.set(posMax,temp);
		 String temp2 = id.get(fill);
		 id.set(fill,id.get(posMax));
		 id.set(posMax,temp2);
		 String temp3 = product_name.get(fill);
		 product_name.set(fill,product_name.get(posMax));
		 product_name.set(posMax,temp3);
		 String temp4 = product_category_tree.get(fill);
		 product_category_tree.set(fill,product_category_tree.get(posMax));
		 product_category_tree.set(posMax,temp4);
		 String temp5 = discounted_price.get(fill);
		 discounted_price.set(fill,discounted_price.get(posMax));
		 discounted_price.set(posMax,temp5);
		 int temp6 = percentage_discount.get(fill);
		 percentage_discount.set(fill,percentage_discount.get(posMax));
		 percentage_discount.set(posMax,temp6);
		 String temp7 = description.get(fill);
		 description.set(fill,description.get(posMax));
		 description.set(posMax,temp7);
		 
		 // assert: table[fill] is the smallest item in
		 // table[fill . . . n - 1].
		 }
		 
		 System.out.println("Sorting according to price");
		 
		 for(int i=0;i<price.size();i++) {
		 System.out.println((i+1) + "- Product " + (i+1) + "'s price is " + price.get(i));
		 }
		 
		 System.out.println();
		 
		 // assert: table[0 . . . n - 1] is sorted.
	}
	
	
	/**
	 * Sorts products according to their percentage discount.
	 */
	public void sort_percentage_discount() {
		int pass=1;
		boolean exchanges=false;
		
		do {
			exchanges=false;
			for(int i=0;i<percentage_discount.size()-pass;i++) {
				if(percentage_discount.get(i)<percentage_discount.get(i+1)) {
					int temp=percentage_discount.get(i);
					percentage_discount.set(i,percentage_discount.get(i+1));
					percentage_discount.set(i+1,temp);
					String temp2=id.get(i);
					id.set(i,id.get(i+1));
					id.set(i+1,temp2);
					String temp3=product_name.get(i);
					product_name.set(i,product_name.get(i+1));
					product_name.set(i+1,temp3);
					String temp4=product_category_tree.get(i);
					product_category_tree.set(i,product_category_tree.get(i+1));
					product_category_tree.set(i+1,temp4);
					String temp5=price.get(i);
					price.set(i,price.get(i+1));
					price.set(i+1,temp5);
					String temp6=discounted_price.get(i);
					discounted_price.set(i,discounted_price.get(i+1));
					discounted_price.set(i+1,temp6);
					String temp7=description.get(i);
					description.set(i,description.get(i+1));
					description.set(i+1,temp7);
					exchanges=true;
				}
			}
			pass++;
		}while(exchanges);
		
		System.out.println("Sorting according to percentage discount");
		for(int i=0;i<percentage_discount.size();i++) {
			 System.out.println((i+1) + "- Product " + (i+1) + " has % " + percentage_discount.get(i) + " discount");
			 }
		System.out.println();
	}
	
	
	/**
	 * Sorts products according to their names.
	 */
	public void sort_product_name() {
		quickSort(product_name, 0, product_name.size() - 1);
		for(int i=0;i<product_name.size();i++) {
			System.out.println(product_name.get(i));
		}
	}
	
	
	/**
	 * Helper quickSort function
	 * @param name names of the products
	 * @param first starting point
	 * @param last end point
	 */
	private static void quickSort(ArrayList<String>name,int first,int last) {
		
		if (first < last) { // There is data to be sorted.
			 // Partition the table.
			 int pivIndex = partition(name, first, last);
			 // Sort the left half.
			 quickSort(name, first, pivIndex - 1);
			 // Sort the right half.
			 quickSort(name, pivIndex + 1, last);
			 }
	}
	
	
	/**
	 * Applies partition in quickSort algorithm.
	 * @param name names of the products
	 * @param first starting point
	 * @param last end point
	 * @return down
	 */
	private static int partition(ArrayList<String>name,int first,int last) {
		// Select the first item as the pivot value.
		 String pivot = name.get(first);
		 int up = first;
		 int down = last;
		 do {
		 /* Invariant:
		 All items in table[first . . . up - 1] <= pivot
		 All items in table[down + 1 . . . last] > pivot
		 */
		 while ((up < last) && (pivot.compareTo(name.get(up)) >= 0)) {
		 up++; 
		 }
		 // assert: up equals last or table[up] > pivot.
		 while (pivot.compareTo(name.get(down)) < 0) {
		 down--;
		 }
		 // assert: down equals first or table[down] <= pivot.
		 if (up < down) { // if up is to the left of down.
		 // Exchange table[up] and table[down].
		 String temp=name.get(up);
		 name.set(up, name.get(down));
		 name.set(down, temp);
		 }
		 } while (up < down); // Repeat while up is left of down.
		 // Exchange table[first] and table[down] thus putting the
		 // pivot value where it belongs.
		 String temp=name.get(first);
		 name.set(first, name.get(down));
		 name.set(down, temp);
		 // Return the index of the pivot value.
		 return down;
	}
	
	
	/**
	 * Writes order to "orders.txt" file.
	 * @throws IOException
	 */
	public static void write_order() throws IOException {
		fw.write(ordered_id.get(ordered_id.size()-1) + ", " + ordered_product_name.get(ordered_product_name.size()-1) + ", " + ordered_product_category_tree.get(ordered_product_category_tree.size()-1) + ", " + ordered_price.get(ordered_price.size()-1) + ", " + ordered_discounted_price.get(ordered_discounted_price.size()-1) + ", " + ordered_description.get(ordered_description.size()-1) + "\n");
	}
	
	
	/**
	 * Adds order to customer.
	 * @param index selected product index
	 * @throws IOException
	 */
	public void add_order(int index) throws IOException {
		ordered_id.add(id.get(index));
		ordered_product_name.add(product_name.get(index));
		ordered_product_category_tree.add(product_category_tree.get(index));
		ordered_price.add(price.get(index));
		ordered_discounted_price.add(discounted_price.get(index));
		ordered_description.add(description.get(index));
		System.out.println("Product which has " + id.get(index) + " id is added to order");
	}
	
	
}

