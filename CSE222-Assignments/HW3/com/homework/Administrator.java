package com.homework;
import com.homework.*;

	public class Administrator<E> extends KWArrayListUser<E>{
	       
		
		   public Administrator() {
			   
		   }
		   
		   /**
		    * Initializes name,surname,e_mail and password variable of admin.
		    * @param name
		    * @param surname
		    * @param e_mail
		    * @param password
		    */
		   public Administrator(int index,E name,E surname,E e_mail,E password) {
			  this.add(index,name, surname, e_mail, password);
		   }
		   
		   /**
		    * Adds new admin and initializes name,surname,e_mail and password variable of new admin.
		    * @param name
		    * @param surname
		    * @param e_mail
		    * @param password
		    */
		   public void addAdministrator(int index,E name,E surname,E e_mail,E password) {
			   this.add(index,name, surname, e_mail, password);
		   }

		   /**
		    * Increments branch number 1.
		    * @param branch
		    * @param furniture
		    */
			public void addBranch(KWSingleLinkedListBranch branch,HybridList furniture) {
				branch.add(branch.getSize(), furniture);
			}
			
			/**
			 * Decrements branch number 1.
			 * @param branch
			 * @return removed branch
			*/
			public String removeBranch(KWSingleLinkedListBranch branch) {
				return branch.remove(branch.getSize()-1);
			}
			
			/** Adds new employee to the system.
			  * @param employee
			  * @param name
			  * @param surname
			  * @param e_mail
			  * @param password
			  * @return true
			*/ 
			public boolean add_Employee(Employee<E> employee,E name,E surname,E e_mail,E password) {
				return employee.add(name, surname, e_mail, password);
			}
			
			/**
			  * Returns employee name.
			  * @param employee
			  * @return employee name
			*/
			public E get_Name(Employee<E> employee) {
				return employee.getName(employee.getSize()-1);
			}
			
			/**
			  * Returns employee surname.
			  * @param employee
			  * @return employee surname
			*/
			public E get_Surname(Employee<E> employee) {
				return employee.getSurname(employee.getSize()-1);
			}
			
			/**
			  * Removes employee from system and returns removed employee.
			  * @param employee
			  * @return removed employee
			*/
			public String remove_Employee(Employee<E> employee) {
				return employee.remove(employee.getSize()-1);
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
			  * @param index
			  * @return model num
			*/
			public int getModelNum(HybridList furniture,int index) {
				return furniture.get2(index);
			}
			
			/**
			  * Returns furniture color num according to index.
			  * @param furniture
			  * @param index
			  * @return color num
			*/
			public int getColorNum(HybridList furniture,int index) {
				return furniture.get3(index);
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
			  * Returns furniture num.
			  * @param furniture
			  * @return furniture num
			*/
		    public int getFurnitureNum(HybridList furniture) {
		    	return furniture.getSize();
		    }
		    
		    /**
			  * Reporting of lack of product to admin.
			*/
		    public void MessageByEmployee() {
		    	System.out.println("The lack of product situation was reported to the admin");
		    }
		   
	}

