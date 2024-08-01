package com.homework;

	public abstract class Person {

		private String name;
		private String surname;
		private String e_mail;
		private String password;
		
		
		/**
		 * It initializes name,surname,e_mail and password variable.
		 * @param name
		 * @param surname
		 * @param e_mail
		 * @param password
		 */
		Person(String name,String surname,String e_mail,String password){
			this.name=name;
			this.surname=surname;
			this.e_mail=e_mail;
			this.password=password;
		}
		
		/**
		 * It returns name.
		 * @return name
		 */
		public String getName() {
			return name;
		}
		
		/**
		 * It sets name variable.
		 * @param name
		 */
		public void setName(String name) {
			this.name = name;
		}
		
		/**
		 * It returns surname.
		 * @return surname
		 */
		public String getSurname() {
			return surname;
		}
		
		/**
		 * It sets surname variable.
		 * @param surname
		 */
		public void setSurname(String surname) {
			this.surname = surname;
		}
		
		/**
		 * It returns e_mail.
		 * @return e_mail
		 */
		public String getE_mail() {
			return e_mail;
		}
		
		/**
		 * It sets e_mail variable.
		 * @param e_mail
		 */
		public void setE_mail(String e_mail) {
			this.e_mail = e_mail;
		}
		
		/**
		 * It returns password.
		 * @return password
		 */
		public String getPassword() {
			return password;
		}
		
		/**
		 * It sets password variable.
		 * @param password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		
		abstract public void showInfos();
		

	}


