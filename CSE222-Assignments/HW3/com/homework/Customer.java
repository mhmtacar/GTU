package com.homework;
import com.homework.*;
import java.util.Scanner;

	public class Customer<E> extends KWArrayListUser<E> implements CustomerInfos{
		
		private int [] customer_num=new int[100];
		private String [] address=new String[100];
		private String [] phone_num=new String[100];
		public static int customer_object=1;
		public static String [][] customer_order_name=new String[100][10];
		public static int [][] customer_order_model=new int[100][10];
		public static int [][] customer_order_color=new int [100][10];
		public static int [][] customer_product_num=new int[100][10];
		public static int [] customer_order_num=new int[100];
		
		
		public Customer() {
			
		}
		
		/**
		 * Initializes name,surname,e_mail and password variable and sets customer num.
		 * @param index
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		public Customer(int index,E name,E surname,E e_mail,E password) {
			   
			   this.add(index, name, surname, e_mail, password);
			   this.customer_num[index]=customer_object;
			   customer_object++;
			   
		   }
		
		/**
		 * Adds new customer and initializes name,surname,e_mail and password variable of customer.
		 * @param index
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		public void addCustomer(int index,E name,E surname,E e_mail,E password) {
			   this.add(index,name, surname, e_mail, password);
			   this.customer_num[index]=customer_object;
			   customer_object++;
		 }
		
		/**
		 * It prints customer num.
		 * @param index
		 */
		public void showInfos(int index) {
			
			System.out.println("Your customer num:" + this.customer_num[index]);
			
		}

		/**
		 * It returns customer_num.
		 * @param index
		 * @return customer_num
		 */
		public int getCustomer_num(int index) {
			return customer_num[index];
		}

		/**
		 * It sets customer_num variable.
		 * @param index
		 * @param customer_num
		 */
		public void setCustomer_num(int index,int customer_num) {
			this.customer_num[index] = customer_num;
		}
		
		/**
		  * Returns furniture num.
		  * @param furniture
		  * @return furniture num
		*/
		public int getFurnitureNum(HybridList furniture) {
	    	return furniture.getSize();
	    }
		
		/**
		  * Returns furniture name.
		  * @param furniture
		  * @param index
		  * @return furniture name
		*/
		public String getFurnitureName(HybridList furniture,int index) {
	    	return furniture.get(index);
	    }
		
		/**
		  * Returns proper product num according to parameters.
		  * @param branch
		  * @param branch_index
		  * @param furniture_index
		  * @param model_index
		  * @param color_index
		  * @return product num
		*/
		public int get_ProductNum(KWSingleLinkedListBranch branch,int branch_index,int furniture_index,int model_index,int color_index) {
			return branch.getProductNum(branch_index, furniture_index, model_index, color_index);
		}
		
		/**
		  * Returns proper furniture name according to parameters.
		  * @param branch
		  * @param branch_index
		  * @param furniture_index
		  * @return furniture name
		*/
		public String getFurnitureName(KWSingleLinkedListBranch branch,int branch_index,int furniture_index) {
			return branch.get(branch_index, furniture_index);
		}
		
		/**
		  * Returns employee num.
		  * @param employee
		  * @return employee num
		*/
		public int getEmployeeNum(Employee<E> employee) {
			return employee.get_EmployeeNum();
		}
		
		/**
		  * Returns branch size.
		  * @param branch
		  * @return branch size
		*/
		public int getBranchSize(KWSingleLinkedListBranch branch) {
			return branch.getSize();
		}
		
		/**
		  * Returns furniture model num according to index.
		  * @param furniture
		  * @param furniture_index
		  * @return model num
		*/
		public int getModelNum(HybridList furniture,int furniture_index) {
			return furniture.get2(furniture_index);
		}
		
		/**
		  * Returns furniture color num according to index.
		  * @param furniture
		  * @param furniture_index
		  * @return color num
		*/
		public int getColorNum(HybridList furniture,int furniture_index) {
			return furniture.get3(furniture_index);
		}
		
		/**
		 * It returns address.
		 * @param index
		 * @return address
		 */
		@Override
		public String getAddress(int index) {
			return address[index];
		}

		/**
		 * It sets address variable.
		 * @param index
		 * @param address
		 */
		@Override
		public void setAddress(int index,String address) {
			this.address[index] = address;
		}

		/**
		 * It returns phone_num.
		 * @param index
		 * @return phone_num
		 */
		@Override
		public String getPhone_num(int index) {
			return phone_num[index];
		}

		/**
		 * It sets phone_num variable.
		 * @param index
		 * @param phone_num
		 */
		@Override
		public void setPhone_num(int index,String phone_num) {
			this.phone_num[index] = phone_num;
		}

		/**
		 * Takes address and phone_num information from customer.
		 * @param index
		 */
		@Override
		public void shopInfos(int index) {
			String address,phone_num;
			Scanner scanner=new Scanner(System.in);
			System.out.printf("Enter your address:");
			address=scanner.nextLine();
			System.out.printf("Enter your phone number:");
			phone_num=scanner.nextLine();
			setAddress(index,address);
			setPhone_num(index,phone_num);
		}
		
		/**
		 * Filling address and phone_num information of customer.
		 * @param index
		 * @param address
		 * @param phone_num
		 */
		@Override
		public void shopInfos(int index,String address,String phone_num) {
			setAddress(index,address);
			setPhone_num(index,phone_num);
		}
		
		/**
		 * Removes product from branch.
		 * @param branch
		 * @param branch_index
		 * @param furniture_index
		 * @param model_index
		 * @param color_index
		 * @param product_val
		 */
		public void remove(KWSingleLinkedListBranch branch,int branch_index,int furniture_index,int model_index,int color_index,int product_val) {
			branch.remove_product(branch_index, furniture_index, model_index, color_index, product_val);
		}
		
		/**
		 * It returns product name according to customer_num parameter.
		 * @param customer_num
		 * @return customer_order_name[customer_num-1][customer_order_num[customer_num-1]-1]
		 */
		public String getProduct_name(int customer_num) {
			return customer_order_name[customer_num-1][customer_order_num[customer_num-1]-1];
		}
		
		/**
		 * It returns product model according to customer_num parameter.
		 * @param customer_num
		 * @return customer_order_model[customer_num-1][customer_order_num[customer_num-1]-1]
		 */
		public int getProduct_model(int customer_num) {
			return customer_order_model[customer_num-1][customer_order_num[customer_num-1]-1];
		}
		
		/**
		 * It returns product color according to customer_num parameter.
		 * @param customer_num
		 * @return customer_order_color[customer_num-1][customer_order_num[customer_num-1]-1]
		 */
		public int getProduct_color(int customer_num) {
			return customer_order_color[customer_num-1][customer_order_num[customer_num-1]-1];
		}
		
		/**
		 * It returns product num according to customer_num parameter.
		 * @param customer_num
		 * @return customer_product_num[customer_num-1][customer_order_num[customer_num-1]-1]
		 */
		public int getProduct_num(int customer_num) {
			return customer_product_num[customer_num-1][customer_order_num[customer_num-1]-1];
		}
		
		/**
		 * It prints customer's previous orders.
		 * @param customer_num
		 */
		public void print_orders(int customer_num) {
			
			int i;
			System.out.printf("\n");
			
			for(i=0;i<customer_order_num[customer_num-1];i++) {
				System.out.printf("%d- %d ",i+1,customer_product_num[customer_num-1][i]);
				System.out.printf("%s ",customer_order_name[customer_num-1][i]);
				System.out.printf("which is Model %d ",customer_order_model[customer_num-1][i]);
				System.out.printf("and Color %d\n",customer_order_color[customer_num-1][i]);
			}
		}
		
		
	}

	
	
