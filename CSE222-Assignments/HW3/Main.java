import java.util.Scanner;
import com.homework.*; 

public class Main {

	public static void main(String[] args) {
		
		HybridList furniture=new HybridList();
		KWSingleLinkedListBranch branch=new KWSingleLinkedListBranch();
		KWArrayListUser<String> admin=new Administrator<String>();
		KWArrayListUser<String> employee=new Employee<String>();
		KWArrayListUser<String> customer=new Customer<String>();
		
		furniture.add(0,"Office Chairs",7,5);
		furniture.add(1,"Office Desks",5,4);
		furniture.add(2,"Meeting Tables",10,4);
		furniture.add(3,"Bookcases",12,1);
		furniture.add(4,"Office Cabinets",12,1);
		
		int i,j,k,l;

		for(i=0;i<4;i++) {
			branch.add(i, furniture);
		}
		
		int choice;
		Scanner scan=new Scanner(System.in);
		while(true) {
		System.out.println("\n1- Run this program with driver");
		System.out.println("2- Run this program with menu");
		System.out.printf("Enter your choice: ");
		try {
		  if(!scan.hasNextInt()) {
			 System.out.println("Wrong input");
			 scan.nextLine();
			 continue;
		  }
			 choice=scan.nextInt();
			 
			 if(choice==1 || choice==2) {
					break;
			}
			 else {
				 throw Exception();
			 }
		}
		catch(Exception e) {
			System.out.println("Wrong choice");
		}
		
	    }
		
		
		if(choice==1) {
		
		admin=new Administrator<String>(0,"Mehmet","Acar","mehmetacar@hotmail.com","2598");
		System.out.println("\n" + admin.getName(admin.getSize()-1)+ " " + admin.getSurname(admin.getSize()-1) + " is login the system");
		employee=new Employee<String>(0,"Ali","Demir","alidemir@hotmail.com","1802");
		System.out.println(employee.getName(employee.getSize()-1)+ " " + employee.getSurname(employee.getSize()-1) + " is login the system");
		customer=new Customer<String>(0,"Mustafa","Kaya","mustafakaya@hotmail.com","1234");
		System.out.println(customer.getName(customer.getSize()-1)+ " " + customer.getSurname(customer.getSize()-1) + " is login the system");
		
		try {
		System.out.println("\n\nThe administrator's task to add and remove branch is below\n");
		((Administrator<String>)admin).addBranch(branch,furniture);
		System.out.println("New branch is added. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		String removed_branch=((Administrator<String>)admin).removeBranch(branch);
		System.out.println(removed_branch + " is removed. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		removed_branch=((Administrator<String>)admin).removeBranch(branch);
		System.out.println(removed_branch + " is removed. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		removed_branch=((Administrator<String>)admin).removeBranch(branch);
		System.out.println(removed_branch + " is removed. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		removed_branch=((Administrator<String>)admin).removeBranch(branch);
		System.out.println(removed_branch + " is removed. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		removed_branch=((Administrator<String>)admin).removeBranch(branch);
		System.out.println(removed_branch + " is removed. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		removed_branch=((Administrator<String>)admin).removeBranch(branch);
		System.out.println(removed_branch + " is removed. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		}catch(NullPointerException ex) {
			System.out.println("Branch num is 0. You can not remove branch");
		}
		((Administrator<String>)admin).addBranch(branch, furniture);
		System.out.println("\nNew branch is added. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		((Administrator<String>)admin).addBranch(branch, furniture);
		System.out.println("New branch is added. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		((Administrator<String>)admin).addBranch(branch, furniture);
		System.out.println("New branch is added. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		((Administrator<String>)admin).addBranch(branch, furniture);
		System.out.println("New branch is added. New branch num is " + ((Administrator<String>)admin).getBranchSize(branch));
		
		
		try {
		System.out.println("\n\nThe administrator's task to add and remove branch employee is below\n");
		((Administrator<String>)admin).add_Employee(((Employee<String>)employee), "Ahmet", "Polat", "ahmetpolat@hotmail.com", "1057");
		System.out.println(((Administrator<String>)admin).get_Name(((Employee<String>)employee))+ " " + 
		((Administrator<String>)admin).get_Surname((Employee<String>)employee) + " is added the system");
		String removed_name=((Administrator<String>)admin).get_Name(((Employee<String>)employee));
		String removed_surname=((Administrator<String>)admin).get_Surname(((Employee<String>)employee));
		((Administrator<String>)admin).remove_Employee(((Employee<String>)employee));
		System.out.println(removed_name + " " + removed_surname + " is removed from system");
		removed_name=((Administrator<String>)admin).get_Name(((Employee<String>)employee));
		removed_surname=((Administrator<String>)admin).get_Surname(((Employee<String>)employee));
		((Administrator<String>)admin).remove_Employee(((Employee<String>)employee));
		System.out.println(removed_name + " " + removed_surname + " is removed from system");
		removed_name=((Administrator<String>)admin).get_Name(((Employee<String>)employee));
		removed_surname=((Administrator<String>)admin).get_Surname(((Employee<String>)employee));
		((Administrator<String>)admin).remove_Employee(((Employee<String>)employee));
		System.out.println(removed_name + " " + removed_surname + " is removed from system");
		}
		catch(IndexOutOfBoundsException ex) {
			System.out.println("Branch employee num is 0. You can not remove branch employee.");
	    }
		
		System.out.println("\n\nThe branch employee's task to inquire products in stock is below\n");
		
		int furniture_ch=3; // Meeting Tables
		int model_ch=8;     // Model 8
		int color_ch=2;     // Color 2
		
        
	  try {
		for(i=0;i<((Employee<String>)employee).getBranchSize(branch);i++) {     
		     System.out.println(i+1 + ". branch has " +  ((Employee<String>)employee).get_ProductNum(branch,i, furniture_ch-1, model_ch-1, color_ch-1) + " " +
		     ((Employee<String>)employee).getFurnitureName(branch, i, furniture_ch-1) + " which is Model " + model_ch  + " and Color " + color_ch);
	    }
	  }
	  catch(IndexOutOfBoundsException ex) {
			System.out.println("\nYou can not inquire this product in stock. Because furniture or model or color index is wrong.\n");
	  }
		
	   furniture_ch=6;   // Wrong furniture choice
	   model_ch=3;       
	   color_ch=1;
	   try {
		 for(i=0;i<((Employee<String>)employee).getBranchSize(branch);i++) {     
			 System.out.println(i+1 + ". branch has " +  ((Employee<String>)employee).get_ProductNum(branch,i, furniture_ch-1, model_ch-1, color_ch-1) + " " +
			 ((Employee<String>)employee).getFurnitureName(branch, i, furniture_ch-1) + " which is Model " + model_ch  + " and Color " + color_ch);
		 }
	   }
	   catch(IndexOutOfBoundsException ex) {
			System.out.println("\nYou can not inquire this product in stock. Because furniture or model or color index is wrong.\n");
	   }
	  
	  System.out.println("\nThe branch employee's task to add and remove products is below\n");
		
	   int branch_choice=4;
	   furniture_ch=1;   // Office Chairs
	   model_ch=6;       // Model 6
	   color_ch=3;       // Color 3
	   int added_product_amount=10;
	   
	   try {
	   if(branch_choice<1 || branch_choice>((Employee<String>)employee).getBranchSize(branch)) {
		   throw new ArrayIndexOutOfBoundsException(branch_choice-1);
	   }
	   if(furniture_ch<1 || furniture_ch>((Employee<String>)employee).getFurnitureSize(furniture)) {
		   throw new ArrayIndexOutOfBoundsException(furniture_ch-1);
	   }
	   if(model_ch<1 || model_ch>((Employee<String>)employee).getModelNum(furniture,furniture_ch-1)) {
		   throw new ArrayIndexOutOfBoundsException(model_ch-1);
	   }
	   if(color_ch<1 || color_ch>((Employee<String>)employee).getColorNum(furniture,furniture_ch-1)) {
		   throw new ArrayIndexOutOfBoundsException(color_ch-1);
	   }
	   
	   ((Employee<String>)employee).add(branch, branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1, added_product_amount);
	   
	   System.out.println(added_product_amount + " " + ((Employee<String>)employee).getFurnitureName(branch,branch_choice-1, furniture_ch-1) + " Model " 
	   + model_ch + " Color " + color_ch + " product is added to " + branch_choice + ". branch." + " New " + ((Employee<String>)employee).getFurnitureName
	   (branch,branch_choice-1, furniture_ch-1) + " Model " + model_ch + " Color " + color_ch + " num in " + branch_choice + ". branch is " + 
	   ((Employee<String>)employee).get_ProductNum(branch,branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1));
	   }
	   catch(ArrayIndexOutOfBoundsException ex) {
		   System.out.println("\nYou can not add this product to given branch. Because branch or furniture or model or color index is wrong.");
	   }
	   
	   branch_choice=2;
	   furniture_ch=3;   // Meeting Tables
	   model_ch=5;       // Model 5
	   color_ch=1;       // Color 1
	   int removed_product_amount=100;
	   
	   try {
		   if(branch_choice<1 || branch_choice>((Employee<String>)employee).getBranchSize(branch)) {
			   throw new ArrayIndexOutOfBoundsException(branch_choice-1);
		   }
		   if(furniture_ch<1 || furniture_ch>((Employee<String>)employee).getFurnitureSize(furniture)) {
			   throw new ArrayIndexOutOfBoundsException(furniture_ch-1);
		   }
		   if(model_ch<1 || model_ch>((Employee<String>)employee).getModelNum(furniture,furniture_ch-1)) {
			   throw new ArrayIndexOutOfBoundsException(model_ch-1);
		   }
		   if(color_ch<1 || color_ch>((Employee<String>)employee).getColorNum(furniture,furniture_ch-1)) {
			   throw new ArrayIndexOutOfBoundsException(color_ch-1);
		   }
		   
		   if(((Employee<String>)employee).get_ProductNum(branch,branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1)-removed_product_amount<0) {
			   throw new Exception();
		   }
		   
		   ((Employee<String>)employee).remove(branch, branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1, removed_product_amount);
		   
		   System.out.println(removed_product_amount + " " + ((Employee<String>)employee).getFurnitureName(branch,branch_choice-1, furniture_ch-1) + " Model " 
		   + model_ch + " Color " + color_ch + " product is removed from " + branch_choice + ". branch." + " New " + ((Employee<String>)employee).getFurnitureName
		   (branch,branch_choice-1, furniture_ch-1) + " Model " + model_ch + " Color " + color_ch + " num in " + branch_choice + ". branch is " + 
		   ((Employee<String>)employee).get_ProductNum(branch,branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1));
		   }
		   catch(ArrayIndexOutOfBoundsException ex) {
			   System.out.println("\nYou can not remove this product from given branch. Because branch or furniture or model or color index is wrong.");
		   }
	       catch(Exception e) {
	    	   System.out.println("Remove product from branch operation is unsuccessful. Because " +  branch_choice + ". branch has not " + 
	           removed_product_amount + " Model " + model_ch + " Color " + color_ch + " product.");
	       }
	   
	   System.out.println("\n\nThe administrator's task to find products that need to be supplied is below");
	   
	   for(i=0;i<((Administrator<String>)admin).getBranchSize(branch);i++) {
		   for(j=0;j<((Administrator<String>)admin).getFurnitureNum(furniture);j++) {
			   for(k=0;k<((Administrator<String>)admin).getModelNum(furniture,j);k++) {
				   for(l=0;l<((Administrator<String>)admin).getColorNum(furniture,j);l++) {
					   if(((Administrator<String>)admin).get_ProductNum(branch, i, j, k, l)==0)
						   System.out.printf("In %d. branch %s Model %d Color %d product is need to be supplied\n", 
						            i+1, ((Administrator<String>)admin).getFurnitureName(branch, i, j) , k+1 ,l+1);
				   }
			   }
		   }
	   }
	   
	   System.out.println("\n\nThe customer's task to see list of products is below");
	   
	   for(i=0;i<((Customer<String>)customer).getFurnitureNum(furniture);i++) {
		   System.out.println(i+1 + "- " + ((Customer<String>)customer).getFurnitureName(furniture, i));
	   }
	   
	   System.out.println("\n\nThe customer's task to see which store a product is in and shopping is below\n");
	   
	   furniture_ch=5; //Office Cabinets
	   model_ch=10;    // Model 10
	   color_ch=1;     // Color 1
	   
	   for(i=0;i<((Customer<String>)customer).getBranchSize(branch);i++) {
	        if(((Customer<String>)customer).get_ProductNum(branch, i, furniture_ch-1, model_ch-1, color_ch-1)>0) {
	        	System.out.println(i+1 + ". branch has " + ((Customer<String>)customer).get_ProductNum(branch, i, 
	        	furniture_ch-1, model_ch-1, color_ch-1) + " proper " + ((Customer<String>)customer).getFurnitureName
	        	(branch, i, furniture_ch-1) + " which is Model " + model_ch + " and Color " + color_ch);
	        }
	   }
	   
	   branch_choice=3;
	   int shop_product_amount=50;
	   int shop_condition=1;
	   
	   try {
	   if((((Customer<String>)customer).get_ProductNum(branch,branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1))-shop_product_amount<0){
		   shop_condition=0;
		   System.out.println(branch_choice + ". branch has not " + shop_product_amount + " " + 
	       ((Customer<String>)customer).getFurnitureName(furniture, furniture_ch-1)+ " Model " + 
		    model_ch + " Color " + color_ch + " product");
		   ((Employee<String>)employee).informAdmin(((Administrator<String>)admin));
	   }
	   
	   }
	   catch(IndexOutOfBoundsException ex) {
			System.out.println("\nShopping is unsuccessful. Because branch or furniture or model or color index is wrong.\n");
			shop_condition=0;
	   }
	   
	   if(shop_condition==1 && ((Customer<String>)customer).getEmployeeNum(((Employee<String>)employee))==0) {
		   System.out.println("\nShopping failed because there are not any branch employees.\n");
	   }
	   
	   else if(shop_condition==1 && ((Customer<String>)customer).getEmployeeNum(((Employee<String>)employee))!=0) {
		   System.out.println("Shopping completed successfully.");
		   ((Customer<String>)customer).shopInfos(customer.getSize()-1,"Gebze","5326781256");
		   System.out.println("Address and phone number information is entered.");
		   ((Employee<String>)employee).remove(branch, branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1, shop_product_amount);
		   ((Employee<String>)employee).set_CustomerOrder(customer.getSize(),((Employee<String>)employee).getFurnitureName(branch, 
			branch_choice-1, furniture_ch-1), model_ch, color_ch, shop_product_amount);
	   }
	   
	   ((Employee<String>)employee).addEmployee("Hasan", "Korkmaz", "hasankorkmaz@hotmail.com", "3356");
	   System.out.println(employee.getName(employee.getSize()-1)+ " " + 
	   employee.getSurname(employee.getSize()-1) + " is added the system\n");
	   
	   furniture_ch=2; //Office Desks
	   model_ch=4;    // Model 4
	   color_ch=2;     // Color 2
	   
	   for(i=0;i<((Customer<String>)customer).getBranchSize(branch);i++) {
	        if(((Customer<String>)customer).get_ProductNum(branch, i, furniture_ch-1, model_ch-1, color_ch-1)>0) {
	        	System.out.println(i+1 + ". branch has " + ((Customer<String>)customer).get_ProductNum(branch, i, 
	        	furniture_ch-1, model_ch-1, color_ch-1) + " proper " + ((Customer<String>)customer).getFurnitureName
	        	(branch, i, furniture_ch-1) + " which is Model " + model_ch + " and Color " + color_ch);
	        }
	   }
	   
	   branch_choice=4;
	   shop_product_amount=30;
	   shop_condition=1;
	   
	   try {
	   if((((Customer<String>)customer).get_ProductNum(branch,branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1))-shop_product_amount<0){
		   shop_condition=0;
		   System.out.println(branch_choice + ". branch has not " + shop_product_amount + " " + 
	       ((Customer<String>)customer).getFurnitureName(furniture, furniture_ch-1)+ " Model " + 
		    model_ch + " Color " + color_ch + " product");
		   ((Employee<String>)employee).informAdmin(((Administrator<String>)admin));;
	   }
	   
	   }
	   catch(IndexOutOfBoundsException ex) {
			System.out.println("\nShopping is unsuccessful. Because branch or furniture or model or color index is wrong.\n");
			shop_condition=0;
	   }
	   
	   if(shop_condition==1 && ((Customer<String>)customer).getEmployeeNum(((Employee<String>)employee))==0) {
		   System.out.println("\nShopping failed because there are not any branch employees.");
	   }
	   
	   else if(shop_condition==1 && ((Customer<String>)customer).getEmployeeNum(((Employee<String>)employee))!=0) {
		   System.out.println("\nShopping completed successfully.");
		   ((Customer<String>)customer).shopInfos(customer.getSize()-1,"Gebze","5326781256");
		   System.out.println("Address and phone number information is entered.");
		   ((Employee<String>)employee).remove(branch, branch_choice-1, furniture_ch-1, model_ch-1, color_ch-1, shop_product_amount);
		   ((Employee<String>)employee).set_CustomerOrder(customer.getSize(),((Employee<String>)employee).getFurnitureName(branch, 
			branch_choice-1, furniture_ch-1), model_ch, color_ch, shop_product_amount);
	   }
	   
	   System.out.println("\n\nThe customer's task to view their previous orders is below");
	   ((Customer<String>)customer).print_orders(((Customer<String>)customer).getCustomer_num(customer.getSize()-1));
	   
	}
	   
		else if(choice==2) {
		
		
		boolean exit_program=false;
		int admin_index=0;
		int employee_index=0;
		int customer_index=0;
		int branch_num=4;
	    
		while(exit_program==false) {
			
		System.out.println("\n1-Registration");
		System.out.println("2-Login");
		System.out.println("3-Exit");
		scan=new Scanner(System.in);
		System.out.printf("Enter your choice:");
	
		if(!scan.hasNextInt()) {
			System.out.println("Wrong input");
			scan.nextLine();
			continue;
		}
		choice=scan.nextInt();
		
		if(choice==1) {
			
		  int proper_entry1=0;
		  String name = null,surname = null,e_mail = null,password = null;
		  
		  while(proper_entry1==0) {
			  
		  scan=new Scanner(System.in);
		  System.out.println("\n1-Administrator");
		  System.out.println("2-Branch Employee");
		  System.out.println("3-Customer");
		  System.out.println("4-Back to main menu");
		  System.out.printf("Enter your choice:");
		  int reg_choice;
		  
		  try {
		  
		  if(!scan.hasNextInt()) {
			  System.out.println("Wrong input");
			  scan.nextLine();
			  continue;
		  }
		  reg_choice=scan.nextInt();

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
			  if(admin_index==0) {
			     admin=new Administrator<String>(admin_index,name,surname,e_mail,password);
			  }
			  else{
				  ((Administrator<String>)admin).addAdministrator(admin_index, name, surname, e_mail, password);
			  }
			  admin_index++;
			  proper_entry1++;
		  }
		  
		  else if(reg_choice==2) {
			  if(employee_index==0) {
				 employee=new Employee<String>(employee_index,name,surname,e_mail,password);
			  }
			  else {
				  ((Employee<String>)employee).addEmployee(employee_index, name, surname, e_mail, password);
			  }
			  employee_index++;
			  proper_entry1++;
		  }
		  
		  else if(reg_choice==3) {
			  if(customer_index==0) {
				 customer=new Customer<String>(customer_index,name,surname,e_mail,password);
			  }
			  else {
				  ((Customer<String>)customer).addCustomer(customer_index, name, surname, e_mail, password);
			  }
			  ((Customer<String>)customer).showInfos(customer_index);
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
			try {
			Scanner scanner=new Scanner(System.in);
			System.out.printf("\nEnter your e-mail:");
			String e_mail=scanner.nextLine();
			System.out.printf("Enter your password:");
			String password=scanner.nextLine();
			int counter1=0,counter2=0,counter3=0;
			int log_customer_index=0;
			
			for(i=0;i<admin_index;i++) {
				if(admin.getE_mail(i).equals(e_mail) && admin.getPassword(i).equals(password)) {
					counter1++;
					break;
				}
			}
			for(i=0;i<employee_index;i++) {
				if(employee.getE_mail(i).equals(e_mail) && employee.getPassword(i).equals(password)) {
					counter2++;
					break;
				}
			}
			for(i=0;i<customer_index;i++) {
				if(customer.getE_mail(i).equals(e_mail) && customer.getPassword(i).equals(password)) {
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
					   
				   if(!s1.hasNextInt()) {
					  System.out.println("Wrong input");
					  s1.nextLine();
					  continue;
				   }
				   admin_ch=s1.nextInt();
				   
				   if(admin_ch==1) {
					   branch_num++;
					   ((Administrator<String>)admin).addBranch(branch,furniture);
					   System.out.println("\nNew branch is added.");
				   }
				   
				   else if(admin_ch==2) {
					   if(branch_num>0) {
					   ((Administrator<String>)admin).removeBranch(branch);
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
					   ((Administrator<String>)admin).add_Employee(((Employee<String>) employee), name2, surname2, e_mail2, password2);
					   System.out.println("New employee is added");
					   employee_index++;
				   }
				   
				   else if(admin_ch==4) {
					   if(employee_index>0) {
					   ((Administrator<String>)admin).remove_Employee(((Employee<String>) employee));
					   employee_index--;
					   System.out.println("\nThe employee which was created last is removed.");
					   }
					   else
					   System.out.println("\nThere are not any employees. You can not remove employee.");
				   }
				   
				   else if(admin_ch==5) {
					   for(i=0;i<((Administrator<String>)admin).getBranchSize(branch);i++) {
						   for(j=0;j<((Administrator<String>)admin).getFurnitureNum(furniture);j++) {
							   for(k=0;k<((Administrator<String>)admin).getModelNum(furniture,j);k++) {
								   for(l=0;l<((Administrator<String>)admin).getColorNum(furniture,j);l++) {
									   if(((Administrator<String>)admin).get_ProductNum(branch, i, j, k, l)==0)
										   System.out.printf("In %d. branch %s Model %d Color %d product is need to be supplied\n", 
										            i+1, ((Administrator<String>)admin).getFurnitureName(branch, i, j) , k+1 ,l+1);
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
					   
					   if(!s3.hasNextInt()) {
							System.out.println("Wrong input");
							s3.nextLine();
							continue;
						}
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
							   
						   s3=new Scanner(System.in);
						   System.out.println("\nWhich furniture do you want to inquire");
						   for(i=0;i<((Employee<String>)employee).getFurnitureSize(furniture);i++) {
							   System.out.println(i+1 + "- " + ((Employee<String>)employee).getFurnitureName(furniture, i));
						   }
						   System.out.printf("Enter your choice:");
						   
						   try {
							   
							if(!s3.hasNextInt()) {
							   System.out.println("Wrong input");
							   s3.nextLine();
							   continue;
							}
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
							   
						   s3=new Scanner(System.in);
						   System.out.println("\nWhich model do you want to inquire?");
						   
						   for(i=0;i<((Employee<String>)employee).getModelNum(furniture, product_ch-1);i++) {  
						   System.out.printf("%d- Model %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
							   
						   if(!s3.hasNextInt()) {
							  System.out.println("Wrong input");
							  s3.nextLine();
							  continue;
						   }
						   model_ch=s3.nextInt();
						   
						   if(model_ch>=1 && model_ch<=((Employee<String>)employee).getModelNum(furniture, product_ch-1))
							   proper_entry3++;
						   else
							   throw Exception();
					      }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
						  }
						   
						   while(proper_entry4==0) {
							   
						   s3=new Scanner(System.in);
						   System.out.println("\nWhich color do you want to inquire?");
						   
						   for(i=0;i<((Employee<String>)employee).getColorNum(furniture, product_ch-1);i++) {
							   System.out.printf("%d- Color %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
						   
						   if(!s3.hasNextInt()) {
							  System.out.println("Wrong input");
							  s3.nextLine();
							  continue;
						   }
						   color_ch=s3.nextInt();
						   
						   if(color_ch>=1 && color_ch<=((Employee<String>)employee).getColorNum(furniture, product_ch-1))
							   proper_entry4++;
						   else
							   throw Exception();
						   }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					     }
						   System.out.printf("\n");
						   
						   for(i=0;i<branch_num;i++) {     
							     System.out.println(i+1 + ". branch has " +  ((Employee<String>)employee).get_ProductNum(branch, i, 
							     product_ch-1, model_ch-1, color_ch-1) + " " + ((Employee<String>)employee).getFurnitureName(branch, 
							     i, product_ch-1) + " which is Model " + model_ch + " and Color " + color_ch);
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
							   
						   s3=new Scanner(System.in);
						   System.out.println("\nWhich furniture do you want to select?");
						   for(i=0;i<((Employee<String>)employee).getFurnitureSize(furniture);i++) {
							   System.out.println(i+1 + "- " + ((Employee<String>)employee).getFurnitureName(furniture, i));
						   }
						   System.out.printf("Enter your choice:");
						   
						   try {
							   
						   if(!s3.hasNextInt()) {
							  System.out.println("Wrong input");
							  s3.nextLine();
							  continue;
						   }
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
							   
						   s3=new Scanner(System.in);
						   System.out.println("\nWhich model do you want to select?");
						   
						   for(i=0;i<((Employee<String>)employee).getModelNum(furniture, product_ch-1);i++) {
						   System.out.printf("%d- Model %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
							   
						   if(!s3.hasNextInt()) {
							  System.out.println("Wrong input");
							  s3.nextLine();
							  continue;
						   }
						   model_ch=s3.nextInt();
						   
						   if(model_ch>=1 && model_ch<=((Employee<String>)employee).getModelNum(furniture, product_ch-1))
							   proper_entry6++;
						   else
							   throw Exception();
					      }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					   }
						   
						   while(proper_entry7==0) {
							   
						   s3=new Scanner(System.in);
						   System.out.println("\nWhich color do you want to select?");
						   
						   for(i=0;i<((Employee<String>)employee).getColorNum(furniture, product_ch-1);i++) {
							   System.out.printf("%d- Color %d\n",i+1,i+1);
						   }
						   
						   System.out.printf("Enter your choice:");
						   
						   try {
							   
						   if(!s3.hasNextInt()) {
							  System.out.println("Wrong input");
							  s3.nextLine();
							  continue;
						   }
						   color_ch=s3.nextInt();
						   
						   if(color_ch>=1 && color_ch<=((Employee<String>)employee).getColorNum(furniture, product_ch-1))
							   proper_entry7++;
						   else
							   throw Exception();
					       }
						   
						   catch(Exception e) {
							   System.out.println("Wrong choice");
						   }
					     }
						   
						   while(proper_entry8==0) {
							   
						   s3=new Scanner(System.in);
						   
						   if(ch==2)
						       System.out.println("\nWhich branch would you like to add products to?");
						   else if(ch==3)
							   System.out.println("\nFrom which branch do you want to remove products?");
						   
						   for(i=0;i<branch_num;i++) {
							   System.out.printf("%d- Branch %d\n",i+1,i+1);
						   }
					
						   System.out.printf("Enter your choice:");
						   
						   try {
							   
						   if(!s3.hasNextInt()) {
							  System.out.println("Wrong input");
							  s3.nextLine();
							  continue;
						   }
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
						   
						   while(true) {
							   
						   s3=new Scanner(System.in);
						   
						   if(ch==2) {
							 System.out.printf("\nHow many product do you want to add:");
							 
							 if(!s3.hasNextInt()) {
							    System.out.println("Wrong input");
							    s3.nextLine();
								continue;
							 }
						     product_amount=s3.nextInt();
						     ((Employee<String>)employee).add(branch, branch_choice-1, product_ch-1, model_ch-1, color_ch-1, product_amount);
						     System.out.println("Adding product to selected branch completed successfully.");
						     break;
						   }
						   
						   else if(ch==3) {
							  System.out.printf("\nHow many product do you want to remove:");
							  
							  if(!s3.hasNextInt()) {
								 System.out.println("Wrong input");
								 s3.nextLine();
								 continue;
							  }
							  product_amount=s3.nextInt();
							  
							  if(((Employee<String>)employee).get_ProductNum(branch, branch_choice-1, product_ch-1, model_ch-1, color_ch-1)-product_amount<0){
								   System.out.println("The amount of product you want to remove from this branch is incorrect.");
								   break;
							   }
							  
							  else {
								  ((Employee<String>)employee).remove(branch, branch_choice-1, product_ch-1, model_ch-1, color_ch-1, product_amount);
								  System.out.println("Removing product from selected branch compeleted successfully.");
								  break;
							  }
							  
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
				   
			   if(!s4.hasNextInt()) {
				  System.out.println("Wrong input");
				  s4.nextLine();
				  continue;
			   }
			   ch=s4.nextInt();
			   
			   if(ch==1) {
				   System.out.printf("\n");
				   for(i=0;i<((Customer<String>)customer).getFurnitureNum(furniture);i++) {
					   System.out.println(i+1 + "- " + ((Customer<String>)customer).getFurnitureName(furniture, i));
				   }
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
					   
				   s5=new Scanner(System.in);
				   System.out.println("\nWhich product do you want to see?");
				   for(i=0;i<((Customer<String>)customer).getFurnitureNum(furniture);i++) {
					   System.out.println(i+1 + "- " + ((Customer<String>)customer).getFurnitureName(furniture, i));
				   }
				   System.out.printf("Enter your choice:");
				   
				   try {
					   
				   if(!s5.hasNextInt()) {
					  System.out.println("Wrong input");
					  s5.nextLine();
					  continue;
				   }
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
					   
				   s5=new Scanner(System.in);
				   System.out.println("\nWhich model do you want to see?");
				   
				   for(i=0;i<((Customer<String>)customer).getModelNum(furniture, product_ch-1);i++) {
				   System.out.printf("%d- Model %d\n",i+1,i+1);
				   }
				   
				   System.out.printf("Enter your choice:");
				   
				   try {
					   
				   if(!s5.hasNextInt()) {
					  System.out.println("Wrong input");
					  s5.nextLine();
					  continue;
				   }
				   model_ch=s5.nextInt();
				   
				   if(model_ch>=1 && model_ch<=((Customer<String>)customer).getModelNum(furniture, product_ch-1))
					   proper_entry10++;
				   else
					   throw Exception();
				   }
				   
				   catch(Exception e) {
					   System.out.println("Wrong choice");
				   }
			   }
				   
				   while(proper_entry11==0) {
					   
				   s5=new Scanner(System.in);  
				   System.out.println("\nWhich color do you want to see?");
				   
				   for(i=0;i<((Customer<String>)customer).getColorNum(furniture, product_ch-1);i++) {
					   System.out.printf("%d- Color %d\n",i+1,i+1);
				   }
				   
				   System.out.printf("Enter your choice:");
				   
				   try {
					   
				   if(!s5.hasNextInt()) {
					  System.out.println("Wrong input");
					  s5.nextLine();
					  continue;
				   }
				   color_ch=s5.nextInt();
				   
				   if(color_ch>=1 && color_ch<=((Customer<String>)customer).getColorNum(furniture, product_ch-1))
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
					        if(((Customer<String>)customer).get_ProductNum(branch, i, product_ch-1, model_ch-1, color_ch-1)>0) {
					        	System.out.println(i+1 + ". branch has " + ((Customer<String>)customer).get_ProductNum(branch, i, 
					        	product_ch-1, model_ch-1, color_ch-1) + " proper " + ((Customer<String>)customer).getFurnitureName
					        	(branch, i, product_ch-1) + " which is Model " + model_ch + " and Color " + color_ch);
					        }
					   }
				   
				   while(proper_entry12==0) {
					   
				   s5=new Scanner(System.in);
				   System.out.println("\nDo you want to buy this product?");
				   System.out.println("1- Yes");
				   System.out.println("2- No");
				   System.out.println("Enter your choice:");
				   
				   try {
					   
				   if(!s5.hasNextInt()) {
					  System.out.println("Wrong input");
					  s5.nextLine();
					  continue;
				   }
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
					   
					   s5=new Scanner(System.in);
					   System.out.println("\nFrom which branch would you like to buy this product?");
					   
					   for(i=0;i<branch_num;i++) {
						   if(((Customer<String>)customer).get_ProductNum(branch, i, product_ch-1, model_ch-1, color_ch-1)!=0)
						   System.out.printf("%d- Branch %d\n",i+1,i+1);
					   }
					   
					   System.out.printf("Enter your choice:");
					   
					   try {
						   
					   if(!s5.hasNextInt()) {
						  System.out.println("Wrong input");
						  s5.nextLine();
						  continue;
					   }
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
					   while(true) {
						   
					   s5=new Scanner(System.in);
					   System.out.printf("\nHow many would you like to buy this product:");
					   
					   if(!s5.hasNextInt()) {
						  System.out.println("Wrong input");
						  s5.nextLine();
						  continue;
					   }
					   product_amount=s5.nextInt();
					   break;
					   
					   }
					   
					   if(((Customer<String>)customer).get_ProductNum(branch, branch_choice-1, product_ch-1, model_ch-1, color_ch-1)-product_amount<0) {
						   System.out.println("This store does not have the quantity you want of this product.");
						   ((Employee<String>)employee).informAdmin(((Administrator<String>)admin));
					   }
					   
					   else {
						   if(employee_index==0)
	                          System.out.println("Shopping failed because there are not any branch employees.");
						   else {
						   ((Customer<String>)customer).shopInfos(log_customer_index);
						   System.out.println("Shopping completed successfully.");
						   ((Employee<String>)employee).remove(branch, branch_choice-1, product_ch-1, model_ch-1, color_ch-1, product_amount);
						   ((Employee<String>)employee).set_CustomerOrder(((Customer<String>)customer).getCustomer_num(log_customer_index), 
						   ((Employee<String>)employee).getFurnitureName(branch, branch_choice-1, product_ch-1), model_ch, color_ch, product_amount);
						   
					   }
					 }
				   }
			   }
			   
			   else if(ch==3) {
				   ((Customer<String>)customer).print_orders(((Customer<String>)customer).getCustomer_num(customer.getSize()-1));
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
			catch(ArrayIndexOutOfBoundsException ex) {
				System.out.println("ArrayIndexOutOfBoundsException is catched");
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
	}
	
	private static Exception Exception() {
		return null;
	}
  
}
	

	
