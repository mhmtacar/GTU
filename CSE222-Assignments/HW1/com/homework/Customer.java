package com.homework;
import java.util.Scanner;

	public class Customer extends Person implements CustomerInfos{
		
		private int customer_num;
		private String address;
		private String phone_num;
		public static int customer_object=1;
		public static String [][] customer_order_name=new String[100][10];
		public static int [][] customer_order_model=new int[100][10];
		public static int [][] customer_order_color=new int [100][10];
		public static int [][] customer_product_num=new int[100][10];
		public static int [] customer_order_num=new int[100];
		
		
		/**
		 * It initializes name,surname,e_mail and password variable.
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		public Customer(String name,String surname,String e_mail,String password) {
			   
			   super(name,surname,e_mail,password);
			   this.customer_num=customer_object;
			   customer_object++;
			   
		   }
		
		/**
		 * It prints customer num.
		 */
		@Override
		public void showInfos() {
			
			System.out.println("Your customer num:" + this.customer_num);
			
		}

		/**
		 * It returns customer_num.
		 * @return customer_num
		 */
		public int getCustomer_num() {
			return customer_num;
		}

		/**
		 * It sets customer_num variable.
		 * @param customer_num
		 */
		public void setCustomer_num(int customer_num) {
			this.customer_num = customer_num;
		}
		
		/**
		 * It returns address.
		 * @return address
		 */
		@Override
		public String getAddress() {
			return address;
		}

		/**
		 * It sets address variable.
		 * @param address
		 */
		@Override
		public void setAddress(String address) {
			this.address = address;
		}

		/**
		 * It returns phone_num.
		 * @return phone_num
		 */
		@Override
		public String getPhone_num() {
			return phone_num;
		}

		/**
		 * It sets phone_num variable.
		 * @param phone_num
		 */
		@Override
		public void setPhone_num(String phone_num) {
			this.phone_num = phone_num;
		}

		/**
		 * Takes address and phone_num information from customer.
		 */
		@Override
		public void shopInfos() {
			String address,phone_num;
			Scanner scanner=new Scanner(System.in);
			System.out.printf("Enter your address:");
			address=scanner.nextLine();
			System.out.printf("Enter your phone number:");
			phone_num=scanner.nextLine();
			setAddress(address);
			setPhone_num(phone_num);
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

	
	
