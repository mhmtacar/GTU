import com.homework.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception,IOException {
	    
		int run_choice;
		
		while(true) {
		System.out.println("\nWould you like to run program with driver or menu");
		System.out.println("1-Run with driver");
		System.out.println("2-Run with menu");
		System.out.printf("Enter your choice:");
		Scanner scanner=new Scanner(System.in);
		run_choice=scanner.nextInt();
		if(run_choice==1 || run_choice==2) 
			break;
		else 
			System.out.println("Wrong choice");
		}
		
		if(run_choice==1) {
		User [] traders=new Trader[100];
		int trader_num=0;
		User [] customers=new Customer[100];
		int customer_num=0;
		new FileWriter("products.txt", false).close();
		new FileWriter("traders.txt", false).close();
		new FileWriter("orders.txt", false).close();
		new FileWriter("customers.txt", false).close();
		traders[0]=new Trader("11111111","111111","Alisha");
		trader_num++;
		
		((Trader)traders[0]).load_product();
        
        System.out.println("\nCurrent products infos");
		((Trader)traders[0]).print_id();
		((Trader)traders[0]).print_product_name();
		((Trader)traders[0]).print_price();
		((Trader)traders[0]).print_discounted_price();
		((Trader)traders[0]).print_description();
		System.out.println();
		
		
		((Trader)traders[0]).add_product("SRTEH2FVYZABCDEF","Alisha Solid Man's T-Shirt","Clothing >> Man's Clothing","200","100","Black T-Shirt");
		((Trader)traders[0]).add_product("SRTEH2FVYZPRSTUV","Alisha Solid Man's T-Shirt","Clothing >> Man's Clothing","250","200","Red T-Shirt");
		
		System.out.println("\nCurrent products infos");
		((Trader)traders[0]).print_id();
		((Trader)traders[0]).print_product_name();
		((Trader)traders[0]).print_price();
		((Trader)traders[0]).print_discounted_price();
		((Trader)traders[0]).print_description();
		System.out.println();
		
		
		String removed_product_id=null;

		try {
			removed_product_id=((Trader)traders[0]).remove_product("ABCDEFG");
			System.out.println("Removed product id:" + removed_product_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			removed_product_id=((Trader)traders[0]).remove_product("SRTEH2FF9KEDEFGF");
			System.out.println("Removed product id:" + removed_product_id);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		System.out.println("\nCurrent products infos");
		((Trader)traders[0]).print_id();
		((Trader)traders[0]).print_product_name();
		((Trader)traders[0]).print_price();
		((Trader)traders[0]).print_discounted_price();
		((Trader)traders[0]).print_description();
		System.out.println();
		

		try {
			((Trader)traders[0]).edit_product("PRSTVYZ","Alisha Solid Women's Cycling Shorts","Clothing >> Women's Clothing","599","267","White Short");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			((Trader)traders[0]).edit_product("SRTEH2F6HUZMQ6SJ","Alisha Solid Man's Cycling Shorts","Clothing >> Man's Clothing","799","367","Green Short");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		
		System.out.println("\nCurrent products infos");
		((Trader)traders[0]).print_id();
		((Trader)traders[0]).print_product_name();
		((Trader)traders[0]).print_price();
		((Trader)traders[0]).print_discounted_price();
		((Trader)traders[0]).print_description();
		System.out.println();
		
	
		customers[0]=new Customer("22222222","222222");
		customer_num++;
		
		
		try {
		String searched_product_name=((Customer)customers[0]).search("Alisha Solid Women's T-Shirt");
		System.out.println(searched_product_name + " product name is found");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		try {
		String searched_product_name=((Customer)customers[0]).search("Alisha Solid Man's T-Shirt");
		System.out.println(searched_product_name + " product name is found");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

		
		System.out.println();
		((Customer)customers[0]).sort_price();
		((Customer)customers[0]).sort_percentage_discount();
		System.out.println();

		((Customer)customers[0]).add_order(0);
		((Trader)traders[0]).meet_order();
		((Customer)customers[0]).add_order(1);
		((Trader)traders[0]).cancel_order();
		
		System.out.println("\n\nDisplay all products\n");
		((Customer)customers[0]).display_products("Alisha");
		
		for(int i=0;i<trader_num;i++) {
			((Trader)traders[i]).fw.close();
			((Trader)traders[i]).fw2.close();
		}
		for(int j=0;j<customer_num;j++) {
			((Customer)customers[j]).fw.close();
			((Customer)customers[j]).fw2.close();
		}
		
		}
		
		else if(run_choice==2) {

			   boolean exit_program=false;
			   User [] traders=new Trader[100];
			   int trader_index=0;
			   User [] customers=new Customer[100];
			   int customer_index=0;
			   new FileWriter("products.txt", false).close();
			   new FileWriter("traders.txt", false).close();
			   new FileWriter("orders.txt", false).close();
			   new FileWriter("customers.txt", false).close();
			   int i,j,k,l;
			   
			   String file="e-commerce-samples.csv";
			   int column_counter=0;
			   BufferedReader reader=null;
			   String line="";
			   Queue<String>name=new LinkedList<String>();
			   
			   try {
					reader=new BufferedReader(new FileReader(file));
				    while((line=reader.readLine())!=null) {
				    	String[] row = line.split(";");
				    	for(String data : row) {
				    		if(column_counter==6) {
				    			name.add(data);
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
				    	
			   
			   
			   while(exit_program==false) {
				   System.out.println("\n1-Authenticate");
				   System.out.println("2-Login");
				   System.out.println("3-Exit");
				   Scanner scan=new Scanner(System.in);
				   System.out.printf("Enter your choice:");
				   int choice=scan.nextInt();
				   
					if(choice==1) {
						
						  int proper_entry1=0;
						  String trader_name = null,id= null,password = null;
						  
						  while(proper_entry1==0) {
							  
						  System.out.println("\n1-Trader");
						  System.out.println("2-Customer");
						  System.out.println("3-Back to main menu");
						  System.out.printf("Enter your choice:");
						  
						  try {
						  
						  int reg_choice=scan.nextInt();
						  
						  if(reg_choice==1 || reg_choice==2) {
							  Scanner scan2=new Scanner(System.in);
							  boolean name_before_authenticated=false;
							  if(reg_choice==1) {
							   while(true) {
							   System.out.printf("\nEnter your name:");
							   trader_name=scan2.nextLine();
							   name_before_authenticated=false;
							   if(name.contains(trader_name)) {
								   for(i=0;i<trader_index;i++) {
									   if(((Trader)traders[i]).getName().equals(trader_name)) {
										   name_before_authenticated=true;
									   }
								   }
								   if(name_before_authenticated)
									   continue;
								   else
								   break;
							   }
							   }
							  }
							  while(true) {
							   System.out.printf("Enter your id:");
							   id=scan2.nextLine();
							   boolean id_before_authenticated=false;
							   for(i=0;i<trader_index;i++) {
								   if(traders[i].getID().equals(id)) {
									   id_before_authenticated=true;
								   }
							   }
							   for(i=0;i<customer_index;i++) {
								   if(customers[i].getID().equals(id)) {
									   id_before_authenticated=true;
								   }
							   }
							   if(id_before_authenticated)
								   continue;
							   if(id.length()==8) {
								   break;
							   }
							  }
							  while(true) {
							   System.out.printf("Enter your password:");
							   password=scan2.nextLine();
							   boolean password_before_authenticated=false;
							   for(i=0;i<trader_index;i++) {
								   if(traders[i].getPW().equals(password)) {
									   password_before_authenticated=true;
								   }
							   }
							   for(i=0;i<customer_index;i++) {
								   if(customers[i].getPW().equals(password)) {
									   password_before_authenticated=true;
								   }
							   }
							   if(password_before_authenticated)
								   continue;
							   if(password.length()==6) {
								   break;
							   }
							  }
						 }
						
						  if(reg_choice==1) {
							  traders[trader_index]=new Trader(id,password,trader_name);
							  ((Trader)traders[trader_index]).load_product();
							  trader_index++;
							  proper_entry1++;
						  }
						  
						  else if(reg_choice==2) {
							  customers[customer_index]=new Customer(id,password);
							  customer_index++;
							  proper_entry1++;
						  }
						  
						  else if(reg_choice==3) {
							  proper_entry1++;
						  }
						  
						  else {
							  throw new Exception();
						  }
						  
						  }
						  catch(Exception e) {
							  System.out.println("Wrong choice");
						  }
						 }
						}
					
					else if(choice==2) {
						Scanner scanner=new Scanner(System.in);
						System.out.printf("\nEnter your id:");
						String id=scanner.nextLine();
						System.out.printf("Enter your password:");
						String password=scanner.nextLine();
						int counter1=0,counter2=0;
						int log_trader_index=0;
						int log_customer_index=0;
						
						for(i=0;i<trader_index;i++) {
							if(traders[i].getID().equals(id) && traders[i].getPW().equals(password)) {
								counter1++;
								log_trader_index=i;
								break;
							}
						}
						for(i=0;i<customer_index;i++) {
							if(customers[i].getID().equals(id) && customers[i].getPW().equals(password)) {
								counter2++;
								log_customer_index=i;
								break;
							}
						}
						if(counter1==0 && counter2==0)
							System.out.println("Wrong id or password");
						else {
							if(counter1==1) {
								boolean exit_loop1=false;
								   
								   while(exit_loop1==false) {
									   
								   System.out.println("\n1-Add products");
								   System.out.println("2-Remove products");
								   System.out.println("3-Edit products");
								   System.out.println("4-Meet orders");
								   System.out.println("5-Cancel orders");
								   System.out.println("6-Back to main menu");
								   
								   
								   int trader_ch;
								   Scanner s1=new Scanner(System.in);
								   System.out.printf("Enter your choice:");
								   
								   try {
								   trader_ch=s1.nextInt();
								   
								   if(trader_ch==1) {
									   String id2,name2,category_tree,price,discounted_price,description;
									   Scanner s2=new Scanner(System.in);
									   System.out.printf("\n");
									   System.out.printf("Enter product id:");
									   id2=s2.nextLine();
									   System.out.printf("Enter product name:");
									   name2=s2.nextLine();
									   System.out.printf("Enter product category tree:");
									   category_tree=s2.nextLine();
									   System.out.printf("Enter product price:");
									   price=s2.nextLine();
									   System.out.printf("Enter product discounted price:");
									   discounted_price=s2.nextLine();
									   System.out.printf("Enter product description:");
									   description=s2.nextLine();
									   ((Trader)traders[log_trader_index]).add_product(id2, name2, category_tree, price, discounted_price, description);
								   }
								   
								   else if(trader_ch==2) {
									   String id2,removed_product_id=null;
									   Scanner s3=new Scanner(System.in);
									   System.out.printf("Enter product id:");
									   id2=s3.nextLine();
									   try {
											removed_product_id=((Trader)traders[log_trader_index]).remove_product(id2);
											 System.out.println("Removed product id:" + removed_product_id + "\n");
										} catch (Exception e) {
											System.out.println(e.getMessage());
										}
									   
								   }
								   
								   else if(trader_ch==3) {
									   String id3,name3,category_tree2,price2,discounted_price2,description2;
									   Scanner s4=new Scanner(System.in);
									   System.out.printf("\n");
									   System.out.printf("Enter wanted to edit product id:");
									   id3=s4.nextLine();
									   System.out.printf("Enter new product name:");
									   name3=s4.nextLine();
									   System.out.printf("Enter new product category tree:");
									   category_tree2=s4.nextLine();
									   System.out.printf("Enter new product price:");
									   price2=s4.nextLine();
									   System.out.printf("Enter new product discounted price:");
									   discounted_price2=s4.nextLine();
									   System.out.printf("Enter new product description:");
									   description2=s4.nextLine();
									   try {
										   ((Trader)traders[log_trader_index]).edit_product(id3, name3, category_tree2, price2, discounted_price2, description2);
										} catch (Exception e) {
											System.out.println(e.getMessage());
										}

								   }
								   
								   else if(trader_ch==4) {
									   ((Trader)traders[log_trader_index]).meet_order();
								   }
								   
								   else if(trader_ch==5) {
									   ((Trader)traders[log_trader_index]).cancel_order();
								   }
								   
								   else if(trader_ch==6) {
									   exit_loop1=true;
								   }
								   
								   else {
									   throw new Exception();
								   }
								   
								   
								  }
								   catch(Exception e) {
									   System.out.println("Wrong choice");
								   }
								}
								   
							}
							
							else if(counter2==1) {
								
								boolean exit_loop2=false;
								   
								   while(exit_loop2==false) {
								   System.out.println("\n1-Search product with name and sort according to price or percentage discount");
								   System.out.println("2-All the products of a trader");
								   System.out.println("3-Give order");
								   System.out.println("4-Back to main menu");
								   
								   int ch;
								   Scanner s5=new Scanner(System.in);
								   System.out.printf("Enter your choice:");
								   
								   try {
									   ch=s5.nextInt();
									   if(ch==1) {
										   String product_name;
										   Scanner s6=new Scanner(System.in);
										   System.out.printf("\n");
										   System.out.printf("Enter searching product name:");
										   product_name=s6.nextLine();
										   //try {
												String searched_product_name=((Customer)customers[log_customer_index]).search(product_name);
												System.out.println(searched_product_name + " product name is found");
												//}
												//catch(Exception e) {
													//System.out.println(e.getMessage());
												//}
										   while(true) {
										   System.out.println("\n1-Sort product according to price");
										   System.out.println("2-Sort product according to percentage discount");
										   int sort_ch;
										   System.out.printf("Enter sorting choice:");
										   sort_ch=s6.nextInt();
										   if(sort_ch==1) {
											   ((Customer)customers[log_customer_index]).sort_price();
											   break;
										   }
										   else if(sort_ch==2) {
											   ((Customer)customers[log_customer_index]).sort_percentage_discount();
											   break;
										   }
										   }
									   }
									   else if(ch==2) {
										   String trader_name;
										   Scanner s7=new Scanner(System.in);
										   System.out.printf("\n");
										   System.out.printf("Enter trader name:");
										   trader_name=s7.nextLine();
										   ((Customer)customers[log_customer_index]).display_products(trader_name);
									   }
									   else if(ch==3) {
										   int order_product_num;
										   Scanner s8=new Scanner(System.in);
										   System.out.printf("\n");
										   System.out.println("Which product num do you want to order from sorted products");
										   order_product_num=s8.nextInt();
										   ((Customer)customers[log_customer_index]).add_order(order_product_num-1);
									   }
									   else if(ch==4) {
										   exit_loop2=true;
									   }
									   
									   else {
										   throw new Exception();
									   }
									   
								   }
								   catch(Exception e) {
									   System.out.println("Wrong choice");
								   }
								   
								   }
							}
						}
					}
					else if(choice==3){
						 exit_program=true;
					}
					else {
						System.out.println("Wrong choice");
					}
			   }
			   
			   for(i=0;i<trader_index;i++) {
					((Trader)traders[i]).fw.close();
					((Trader)traders[i]).fw2.close();
				}
				for(j=0;j<customer_index;j++) {
					((Customer)customers[j]).fw.close();
					((Customer)customers[j]).fw2.close();
				}
		}
		
	}

}
