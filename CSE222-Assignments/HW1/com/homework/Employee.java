package com.homework;

	public class Employee extends Person{
		
		
		/**
		 * It initializes name,surname,e_mail and password variable.
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		public Employee(String name,String surname,String e_mail,String password) {
			   
			super(name,surname,e_mail,password);
			
			for(int i=0;i<100;i++) {
				Customer.customer_order_num[i]=0;
			}
			  
		   }

		/**
		 * It prints employee infos.
		 */
		@Override
		public void showInfos() {
			
			System.out.println("Your name:" + getName());
			System.out.println("Your surname:" + getSurname());
			System.out.println("Your e-mail:" + getE_mail());
		    System.out.println("Your password:" + getPassword());
		    
		}
		
		/**
		 * It updates customers' orders.
		 * @param customer_num
		 * @param product_name
		 * @param product_model
		 * @param product_color
		 * @param product_num
		 */
		public void set_CustomerOrder(int customer_num,String product_name,int product_model,int product_color,int product_num) {
			Customer.customer_order_name[customer_num-1][Customer.customer_order_num[customer_num-1]]=product_name;
			Customer.customer_order_model[customer_num-1][Customer.customer_order_num[customer_num-1]]=product_model;
			Customer.customer_order_color[customer_num-1][Customer.customer_order_num[customer_num-1]]=product_color;
			Customer.customer_product_num[customer_num-1][Customer.customer_order_num[customer_num-1]]=product_num;
			Customer.customer_order_num[customer_num-1]++;
		}
		
		
	}

	
	
