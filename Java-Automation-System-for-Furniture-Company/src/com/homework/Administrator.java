package com.homework;

	public class Administrator extends Person{
	       
		
		   /**
		    * It initializes name,surname,e_mail and password variable.
		    * @param name
		    * @param surname
		    * @param e_mail
		    * @param password
		    */
		   public Administrator(String name,String surname,String e_mail,String password) {
			   
			  super(name,surname,e_mail,password);

		   }

		   /**
		    * It prints administrator infos.
		    */
			@Override
			public void showInfos() {
			
				 System.out.println("Your name:" + getName());
				 System.out.println("Your surname:" + getSurname());
				 System.out.println("Your e-mail:" + getE_mail());
				 System.out.println("Your password:" + getPassword());
		
			}
		   
		   
	}

