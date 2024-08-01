import com.homework.*;
import java.util.Scanner; 

public class Main {

	public static void main(String[] args) {
		
		boolean exit_program=false;
		int admin_index=0;
		int employee_index=0;
		int customer_index=0;
		int branch_num=4;
		Person [] admins=new Administrator[100];
	    Person [] employees=new Employee[100];
	    Person [] customers=new Customer[100];
	    
	    int i,j,k,l;
	    BranchProduct [][] product=new BranchProduct[100][5];
	    
	    for(i=0;i<4;i++) {
	    	for(j=0;j<5;j++) {
	    		if(j==0)
	    		  product[i][j]=new BranchProduct("Office Chairs",7,5);
	    		else if(j==1)
	    		  product[i][j]=new BranchProduct("Office Desks",5,4);
	    		else if(j==2)
	    		  product[i][j]=new BranchProduct("Meeting Tables",10,4);
	    		else if(j==3)
		    	  product[i][j]=new BranchProduct("Bookcases",12,1);
	    		else
	    		  product[i][j]=new BranchProduct("Office Cabinets",12,1);
	    	}
	    }
	    
		while(exit_program==false) {
			
		System.out.println("\n1-Registration");
		System.out.println("2-Login");
		System.out.println("3-Exit");
		Scanner scan=new Scanner(System.in);
		System.out.printf("Enter your choice:");
		int choice=scan.nextInt();
		
		if(choice==1) {
			
		  int proper_entry1=0;
		  String name = null,surname = null,e_mail = null,password = null;
		  
		  while(proper_entry1==0) {
			  
		  System.out.println("\n1-Administrator");
		  System.out.println("2-Branch Employee");
		  System.out.println("3-Customer");
		  System.out.println("4-Back to main menu");
		  System.out.printf("Enter your choice:");
		  try {
		  
		  int reg_choice=scan.nextInt();
		  
		  if(reg_choice==1 || reg_choice==2 || reg_choice==3) {
			   Scanner scan2=new Scanner(System.in);
			   System.out.printf("\nEnter your name:");
			   name=scan2.nextLine();
			   System.out.printf("Enter your surname:");
			   surname=scan2.nextLine();
			   System.out.printf("Enter your e_mail:");
			   e_mail=scan2.nextLine();
			   System.out.printf("Enter your password:");
			   password=scan2.nextLine();
		  }
		
		  if(reg_choice==1) {
			  admins[admin_index]=new Administrator(name,surname,e_mail,password);
			  admin_index++;
			  proper_entry1++;
		  }
		  
		  else if(reg_choice==2) {
			  employees[employee_index]=new Employee(name,surname,e_mail,password);
			  employee_index++;
			  proper_entry1++;
		  }
		  
		  else if(reg_choice==3) {
			  customers[customer_index]=new Customer(name,surname,e_mail,password);
			  customers[customer_index].showInfos();
			  customer_index++;
			  proper_entry1++;
		  }
		  
		  else if(reg_choice==4) {
			  proper_entry1++;
		  }
		  
		  else {
			  throw Exception();
		  }
		  
		  }
		  catch(Exception e) {
			  System.out.println("Wrong choice");
		  }
		 }
		}
		
		else if(choice==2) {
			Scanner scanner=new Scanner(System.in);
			System.out.printf("\nEnter your e-mail:");
			String e_mail=scanner.nextLine();
			System.out.printf("Enter your password:");
			String password=scanner.nextLine();
			int counter1=0,counter2=0,counter3=0;
			int log_customer_index=0;
			
			for(i=0;i<admin_index;i++) {
				if(admins[i].getE_mail().equals(e_mail) && admins[i].getPassword().equals(password)) {
					counter1++;
					break;
				}
			}
			for(i=0;i<employee_index;i++) {
				if(employees[i].getE_mail().equals(e_mail) && employees[i].getPassword().equals(password)) {
					counter2++;
					break;
				}
			}
			for(i=0;i<customer_index;i++) {
				if(customers[i].getE_mail().equals(e_mail) && customers[i].getPassword().equals(password)) {
					counter3++;
					log_customer_index=i;
					break;
				}
			}
			
			if(counter1==0 && counter2==0 && counter3==0)
				System.out.println("Wrong e-mail or password");
			
			else {
				
			   if(counter1==1) {
				   
				   boolean exit_loop1=false;
				   
				   while(exit_loop1==false) {
					   
				   System.out.println("\n1-Add branch");
				   System.out.println("2-Remove branch");
				   System.out.println("3-Add branch employee");
				   System.out.println("4-Remove branch employee");
				   System.out.println("5-Any products that need to be supplied");
				   System.out.println("6-Back to main menu");
				   
				   int admin_ch;
				   Scanner s1=new Scanner(System.in);
				   System.out.printf("Enter your choice:");
				   
				   try {
				   admin_ch=s1.nextInt();
				   
				   if(admin_ch==1) {
					   branch_num++;
					   
					   for(i=0;i<5;i++) {
						   if(i==0)
					    	  product[branch_num-1][i]=new BranchProduct("Office Chairs",7,5);
					       else if(i==1)
					    	  product[branch_num-1][i]=new BranchProduct("Office Desks",5,4);
					       else if(i==2)
					    	  product[branch_num-1][i]=new BranchProduct("Meeting Tables",10,4);
					       else if(i==3)
						      product[branch_num-1][i]=new BranchProduct("Bookcases",12,1);
					       else
					    	  product[branch_num-1][i]=new BranchProduct("Office Cabinets",12,1);
					   }
					   
					   System.out.println("\nNew branch is added.");
					   
				   }
				   
				   else if(admin_ch==2) {
					   if(branch_num>0) {
					   branch_num--;
					   System.out.println("\nThe branch which was created last is removed.");
					   }
					   else
					   System.out.println("\nThere are not any branches. You can not remove branch.");
				   }
				   
				   else if(admin_ch==3) {
					   String name2,surname2,e_mail2,password2;
					   Scanner s2=new Scanner(System.in);
					   System.out.printf("\n");
					   System.out.printf("Enter employee name:");
					   name2=s2.nextLine();
					   System.out.printf("Enter employee surname:");
					   surname2=s2.nextLine();
					   System.out.printf("Enter employee e_mail:");
					   e_mail2=s2.nextLine();
					   System.out.printf("Enter employee password:");
					   password2=s2.nextLine();
					   employees[employee_index]=new Employee(name2,surname2,e_mail2,password2);
					   employee_index++;
				   }
				   
				   else if(admin_ch==4) {
					   if(employee_index>0) {
					   employee_index--;
					   System.out.println("\nThe employee which was created last is removed.");
					   }
					   else
					   System.out.println("\nThere are not any employees. You can not remove employee.");
				   }
				   
				   else if(admin_ch==5) {
					   for(i=0;i<branch_num;i++) {
						   for(j=0;j<5;j++) {
							   for(k=0;k<product[i][j].getModel_num();k++) {
								   for(l=0;l<product[i][j].getColor_num();l++) {
									   if(product[i][j].get_ProductNum(k, l)==0)
										   System.out.printf("In %d. branch %s Model %d Color %d product is need to be supplied\n", 
												   i+1, product[i][j].getName() , k+1 ,l+1);
								   }
							   }
						   }
					   }
				   }
				   
				   else if(admin_ch==6) {
					   exit_loop1=true;
				   }
				   
				   else
					   throw Exception();
				   }
				   catch(Exception e) {
					   System.out.println("Wrong choice");
				   }
				 }
			   }
			   
			   else if(counter2==1) {
				   
				   boolean exit_loop2=false;
				   
				   while(exit_loop2==false) {
				   System.out.println("\n1-Inquire products in stock");
				   System.out.println("2-Add products");
				   System.out.println("3-Remove products");
				   System.out.println("4-Back to main menu");
				   
				   int ch;
				   Scanner s3=new Scanner(System.in);
				   System.out.printf("Enter your choice:");
				   
				   try {
					   ch=s3.nextInt();
				   
					   if(ch==1) {
						   int proper_entry2=0,proper_entry3=0,proper_entry4=0;
						   int product_ch = 0;
						   int model_ch = 0;
						   int color_ch = 0;
						   int shop_choice;
						   int product_amount;
						   int branch_choice;
						   
						   while(proper_entry2==0) {
							   
						   System.out.println("\nWhich furniture do you want to inquire");
						   System.out.println("1-Office Chairs");
						   System.out.println("2-Office Desks");
						   System.out.println("3-Meeting Tables");
						   System.out.println("4-Bookcases");
						   System.out.println("5-Office Cabinets");
						   System.out.printf("Enter your choice:");
						   
						   try {
						   product_ch=s3.nextInt();
						   
						   if(product_ch>=1 && product_ch<=5)
							  proper_entry2++;
						   else
							   throw Exception();
						   }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					   }
						   
						   while(proper_entry3==0) {
							   
						   System.out.println("\nWhich model do you want to inquire?");
						   
						   for(i=0;i<product[0][product_ch-1].getModel_num();i++) {
						   System.out.printf("%d- Model %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
						   model_ch=s3.nextInt();
						   
						   if(model_ch>=1 && model_ch<=product[0][product_ch-1].getModel_num())
							   proper_entry3++;
						   else
							   throw Exception();
					      }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
						  }
						   
						   while(proper_entry4==0) {
							   
						   System.out.println("\nWhich color do you want to inquire?");
						   
						   for(i=0;i<product[0][product_ch-1].getColor_num();i++) {
							   System.out.printf("%d- Color %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
						   color_ch=s3.nextInt();
						   
						   if(color_ch>=1 && color_ch<=product[0][product_ch-1].getColor_num())
							   proper_entry4++;
						   else
							   throw Exception();
						   }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					     }
						   System.out.printf("\n");

                           if(branch_num==0)
                              System.out.println("You can not inquire this product.Because there are not any branches.");
						   
						   for(i=0;i<branch_num;i++) {     
							     System.out.println(i+1 + ". branch has " + product[i][product_ch-1].get_ProductNum(model_ch-1, color_ch-1) + " " +
							     product[i][product_ch-1].getName() + " which is Model " + model_ch + " and Color " + color_ch);
							        
						   }
					   }
					   
					   else if(ch==2 || ch==3) {
						   int proper_entry5=0,proper_entry6=0,proper_entry7=0,proper_entry8=0;
						   int product_ch = 0;
						   int model_ch = 0;
						   int color_ch = 0;
						   int shop_choice;
						   int product_amount;
						   int branch_choice = 0;
						   
						   while(proper_entry5==0) {
							   
						   System.out.println("\nWhich furniture do you want to select?");
						   System.out.println("1-Office Chairs");
						   System.out.println("2-Office Desks");
						   System.out.println("3-Meeting Tables");
						   System.out.println("4-Bookcases");
						   System.out.println("5-Office Cabinets");
						   System.out.printf("Enter your choice:");
						   
						   try {
						   product_ch=s3.nextInt();
						   
						   if(product_ch>=1 && product_ch<=5)
							   proper_entry5++;
						   else
							   throw Exception();
					      }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					   }
							
						   while(proper_entry6==0) {
							   
						   System.out.println("\nWhich model do you want to select?");
						   
						   for(i=0;i<product[0][product_ch-1].getModel_num();i++) {
						   System.out.printf("%d- Model %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
						   model_ch=s3.nextInt();
						   
						   if(model_ch>=1 && model_ch<=product[0][product_ch-1].getModel_num())
							   proper_entry6++;
						   else
							   throw Exception();
					      }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					   }
						   
						   while(proper_entry7==0) {
							   
						   System.out.println("\nWhich color do you want to select?");
						   
						   for(i=0;i<product[0][product_ch-1].getColor_num();i++) {
							   System.out.printf("%d- Color %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
						   color_ch=s3.nextInt();
						   
						   if(color_ch>=1 && color_ch<=product[0][product_ch-1].getColor_num())
							   proper_entry7++;
						   else
							   throw Exception();
					       }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					     }
						   
						   while(proper_entry8==0) {
							   
						   if(ch==2){
                               if(branch_num==0){
                                  System.out.println("\nYou can not add product to any branch.Because there are not any branches.");
                                  break;
                              }
                                else
						          System.out.println("\nWhich branch would you like to add products to?");
						  }
                                                   
						   else if(ch==3){
                                if(branch_num==0){
                                   System.out.println("\nYou can not remove product from any branch.Because there are not any branches.");
                                   break;
                               }
                                else
							       System.out.println("\nFrom which branch do you want to remove products?");
                           }
						   
						   for(i=0;i<branch_num;i++) {
							   System.out.printf("%d- Branch %d\n",i+1,i+1);
						   }
					
						   System.out.printf("Enter your choice:");
						   
						   try {
						   branch_choice=s3.nextInt();
						   
						   if(branch_choice>=1 && branch_choice<=branch_num)
							   proper_entry8++;
						   else
							   throw Exception();
						   }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					   }
						   
						   if(ch==2 && branch_num>0) {
							 System.out.printf("\nHow many product do you want to add:");
						     product_amount=s3.nextInt();
						     product[branch_choice-1][product_ch-1].add_product(model_ch-1, color_ch-1, product_amount);
						     System.out.println("Adding product to selected branch completed successfully.");
						   }
						   
						   else if(ch==3 && branch_num>0) {
							  System.out.printf("\nHow many product do you want to remove:");
							  product_amount=s3.nextInt();
							  
							  if(product[branch_choice-1][product_ch-1].get_ProductNum(model_ch-1,color_ch-1)-product_amount<0) {
								   System.out.println("The amount of product you want to remove from this branch is incorrect.");
							   }
							  
							  else {
								  product[branch_choice-1][product_ch-1].remove_product(model_ch-1,color_ch-1,product_amount);
								  System.out.println("Removing product from selected branch compeleted successfully.");
							  }
							  
						   }
						   
					   }
					   
					   else if(ch==4) {
						   exit_loop2=true;
					   }
					   
					   else
						   throw Exception();
				   
				     }
				     catch(Exception e) {
				    	 System.out.println("Wrong choice");
				     }
				   }
				   
			   }
			   
			   else if(counter3==1) {
				   
			   boolean exit_loop3=false;
			   
			   while(exit_loop3==false) {
				   
			   System.out.println("\n1-See list of product");
			   System.out.println("2-Search product,see which store a product is in and shopping");
			   System.out.println("3-Look at your previous orders");
			   System.out.println("4-Back to main menu");
			   
			   int ch;
			   Scanner s4=new Scanner(System.in);
			   System.out.printf("Enter your choice:");
			   
			   try {
			   ch=s4.nextInt();
			   
			   if(ch==1) {
				   System.out.println("\n1-Office Chairs");
				   System.out.println("2-Office Desks");
				   System.out.println("3-Meeting Tables");
				   System.out.println("4-Bookcases");
				   System.out.println("5-Office Cabinets");
			   }
			   
			   else if(ch==2) {
				   int proper_entry9=0,proper_entry10=0,proper_entry11=0,proper_entry12=0,proper_entry13=0;
				   int product_ch = 0;
				   int model_ch = 0;
				   int color_ch = 0;
				   int shop_choice = 0;
				   int product_amount;
				   int branch_choice = 0;
				   Scanner s5=new Scanner(System.in);
				   
				   while(proper_entry9==0) {
					   
				   System.out.println("\nWhich product do you want to see?");
				   System.out.println("1-Office Chairs");
				   System.out.println("2-Office Desks");
				   System.out.println("3-Meeting Tables");
				   System.out.println("4-Bookcases");
				   System.out.println("5-Office Cabinets");
				   System.out.printf("Enter your choice:");
				   
				   try {
				   product_ch=s5.nextInt();
				   
				   if(product_ch>=1 && product_ch<=5)
					   proper_entry9++;
				   else
					   throw Exception();
				   }  
				   
				   catch(Exception e) {
					   System.out.println("Wrong choice");
				   }
			     }
				   
				   while(proper_entry10==0) {
					   
				   System.out.println("\nWhich model do you want to see?");
				   
				   for(i=0;i<product[0][product_ch-1].getModel_num();i++) {
				   System.out.printf("%d- Model %d\n",i+1,i+1);
				   }
				   
				   System.out.printf("Enter your choice:");
				   
				   try {
				   model_ch=s5.nextInt();
				   
				   if(model_ch>=1 && model_ch<=product[0][product_ch-1].getModel_num())
					   proper_entry10++;
				   else
					   throw Exception();
				   }
				   
				   catch(Exception e) {
					   System.out.println("Wrong choice");
				   }
			   }
				   
				   while(proper_entry11==0) {
					   
				   System.out.println("\nWhich color do you want to see?");
				   
				   for(i=0;i<product[0][product_ch-1].getColor_num();i++) {
					   System.out.printf("%d- Color %d\n",i+1,i+1);
				   }
				   
				   System.out.printf("Enter your choice:");
				   
				   try {
				   color_ch=s5.nextInt();
				   
				   if(color_ch>=1 && color_ch<=product[0][product_ch-1].getColor_num())
					   proper_entry11++;
				   else
					   throw Exception();
				   }
				   
				   catch(Exception e) {
					   System.out.println("Wrong choice");
				   }
			   }
						   
				   System.out.printf("\n");
				   
				   for(i=0;i<branch_num;i++) {
					        if(product[i][product_ch-1].get_ProductNum(model_ch-1, color_ch-1)>0) {
					        	System.out.println(i+1 + ". branch has " + product[i][product_ch-1].get_ProductNum(model_ch-1, color_ch-1) + " proper " + 
					            product[i][product_ch-1].getName() + " which is Model " + model_ch + " and Color " + color_ch);
					        }
					   }

					if(branch_num==0)
						System.out.println("This product is not in any branches. Because there are not any branches.");
				   
				   while(proper_entry12==0 && branch_num>0) {
					   
				   System.out.println("\nDo you want to buy this product?");
				   System.out.println("1- Yes");
				   System.out.println("2- No");
				   System.out.println("Enter your choice:");
				   
				   try {
				   shop_choice=s5.nextInt();
				   
				   if(shop_choice==1 || shop_choice==2)
					   proper_entry12++;
				   else
					   throw Exception();
				   }
				   
				   catch(Exception e) {
					   System.out.println("Wrong choice");
				   }
			   }
						   
				   if(shop_choice==1) {
					   while(proper_entry13==0) {
/*
					   	if(employee_index==0){
					   	   System.out.println("There are not any branch employees in the system.You can not buy this product");
					   	   break;
					   	}
						   */
					   System.out.println("\nFrom which branch would you like to buy this product?");
					   
					   for(i=0;i<branch_num;i++) {
						   if(product[i][product_ch-1].get_ProductNum(model_ch-1,color_ch-1)!=0)
						   System.out.printf("%d- Branch %d\n",i+1,i+1);
					   }
					   
					   System.out.printf("Enter your choice:");
					   
					   try {
					   branch_choice=s5.nextInt();
					   
					   if(branch_choice>=1 && branch_choice<=branch_num)
						   proper_entry13++;
					   else
						   throw Exception();
					   }
					   
					   catch(Exception e) {
						   System.out.println("Wrong choice");
					   }
				   }
							   
					   System.out.printf("\nHow many would you like to buy this product:");
					   product_amount=s5.nextInt();
					   
					   if(product[branch_choice-1][product_ch-1].get_ProductNum(model_ch-1,color_ch-1)-product_amount<0) {
						   System.out.println("This store does not have the quantity you want of this product.");
					   }
					   
					   else {
						   if(employee_index==0)
	                          System.out.println("Shopping failed because there are not any branch employees.");
						   else {
						   ((Customer)customers[log_customer_index]).shopInfos();
						   System.out.println("Shopping completed successfully.");
						   product[branch_choice-1][product_ch-1].remove_product(model_ch-1,color_ch-1,product_amount);
						   ((Employee)employees[0]).set_CustomerOrder(((Customer)customers[log_customer_index]).getCustomer_num(),product[branch_choice-1][product_ch-1].getName(),
								   model_ch,color_ch,product_amount);
						   
					   }
					 }
				   }
			   }
			   
			   else if(ch==3) {
				   ((Customer)customers[log_customer_index]).print_orders(((Customer)customers[log_customer_index]).getCustomer_num());
			   }
			   
			   else if(ch==4) {
				  exit_loop3=true;
			   }
			   else
				   throw Exception();
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
	}

	private static Exception Exception() {
		return null;
	}
	
}
	
