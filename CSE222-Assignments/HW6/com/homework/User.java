package com.homework;

/**
 * User class
 * 
 * @author mehmet_acar
 */

public class User implements Human {
    
	private String id;
	private String password;
	
	
	/**
     * Constructor of User
     * @param id ID of the User
     * @param password Password of the User
     */
	public User(String id,String password) {
		this.id=id;
		this.password=password;
	}
	
	
	@Override
	/**
	 * Setter for ID
	 * @param id ID of the User
	 */
	public void setID(String id){
	    this.id = id;
	}
	
	
	@Override
	/**
	 * Getter for ID
	 * @return ID of the User
	 */
	public String getID(){
	    return id;
	}
	
	
	@Override
	/**
	 * Setter for Password
	 * @param pw Password of the User
	 */
	public void setPW(String pw){
	    this.password = pw;
	}
	
	
	@Override
	/**
	 * Getter for Password
	 * @return Password of the User
	 */
	public String getPW(){
	    return password;
	}

	
}

