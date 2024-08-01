package com.homework;
import com.homework.*;

public class Employee<E> extends KWArrayListUser<E>{
		
	
		public Employee() {
			
		}
		
		/**
		 * Initializes name,surname,e_mail and password variable of employee.
		 * @param index
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		public Employee(int index,E name,E surname,E e_mail,E password) {
			   
			this.add(index,name, surname, e_mail, password);
			
			for(int i=0;i<100;i++) {
				Customer.customer_order_num[i]=0;
			}
			
		   }
		
		/**
		 * Adds new employee and initializes name,surname,e_mail and password variable of employee.
		 * @param index
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		 public void addEmployee(int index,E name,E surname,E e_mail,E password) {
			   this.add(index, name, surname, e_mail, password);
		 }
		 
		 /**
		  * Adds new employee and initializes name,surname,e_mail and password variable of employee.
		  * @param name
		  * @param surname
		  * @param e_mail
		  * @param password
		  * @return true
		 */
		 public boolean addEmployee(E name,E surname,E e_mail,E password) {
			  return this.add(name, surname, e_mail, password);
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
		
		/**
		  * Returns branch size.
		  * @param branch
		  * @return branch size
		*/
		public int getBranchSize(KWSingleLinkedListBranch branch) {
			return branch.getSize();
		}
		
		/**
		  * Returns furniture size.
		  * @param furniture
		  * @return furniture size
		*/
		public int getFurnitureSize(HybridList furniture) {
			return furniture.getSize();
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
		  * Returns furniture name.
		  * @param furniture
		  * @param index
		  * @return furniture name
		*/
		public String getFurnitureName(HybridList furniture,int index) {
	    	return furniture.get(index);
	    }
		
		/**
		  * Returns proper furniture name according to parameters.
		  * @param branch
		  * @param branch_index
		  * @param furniture_index
		  * @return furniture name
		*/
		public String getFurnitureName(KWSingleLinkedListBranch branch,int branch_index,int furniture_index) {
			String furniture_name=branch.get(branch_index, furniture_index);
			return furniture_name;
		}
		
		/* Add products to proper furniture product
		  * @param branch
		  * @param branch_index
		  * @param furniture_index
		  * @param model_index
		  * @param color_index
		  * @param product_val
		 */
		public void add(KWSingleLinkedListBranch branch,int branch_index,int furniture_index,int model_index,int color_index,int product_val) {
			branch.add_product(branch_index, furniture_index, model_index, color_index, product_val);
		}
		
		/* Remove products from proper furniture product
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
		  * Returns employee num.
		  * @return employee num
		*/
		public int get_EmployeeNum() {
			return this.getSize();
		}
		
		/**
		  * Sends message to admin about lack of product.
		*/
		public void informAdmin(Administrator<E> admin) {
			admin.MessageByEmployee();
		}
		
		
	}

	
	
