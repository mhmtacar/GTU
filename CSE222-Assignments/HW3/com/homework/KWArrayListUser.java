package com.homework;
import java.util.Arrays;

public class KWArrayListUser<E> {
	
	// Data Fields
	/** The default initial capacity */
	private static final int INITIAL_CAPACITY = 10;
	
	/** The underlying data array */
	private String[] user_num;
	private E[] name;
	private E[] surname;
	private E[] e_mail;
	private E[] password;
	
	/** The current size */
	private int size = 0;
	
	/** The current capacity */
	private int capacity = 0;
	
	public KWArrayListUser() {
		capacity = INITIAL_CAPACITY;
		user_num = new String[capacity];
		name = (E[]) new Object[capacity];
		surname = (E[]) new Object[capacity];
		e_mail = (E[]) new Object[capacity];
		password = (E[]) new Object[capacity];
	}
	
	/** Add user informations to the end of array list 
	 *@param name the name to be added
	 *@param surname the surname to be added
	 *@param e_mail the e_mail to be added
	 *@param password the password to be added
	 *@return true
	 */
	public boolean add(E name,E surname,E e_mail,E password) {
		if (size == capacity) {
		reallocate();
		}
		int user_num=size+1;
		this.user_num[size]="User "+user_num;
		this.name[size] = name;
		this.surname[size] = surname;
		this.e_mail[size] = e_mail;
		this.password[size] = password;
		size++;
		return true;
		}
	
	/** Add user informations to the given index of array list 
	 *@param name the name to be added
	 *@param surname the surname to be added
	 *@param e_mail the e_mail to be added
	 *@param password the password to be added
	 @throws IndexOutOfBoundsException if index is out of range
	 */
	public void add(int index,E name,E surname,E e_mail,E password) {
		if (index < 0 || index > size) {
		throw new ArrayIndexOutOfBoundsException(index);
		}
		if (size == capacity) {
		reallocate();
		}
		// Shift data in elements from index to size - 1
		for (int i = size; i > index; i--) {
		this.name[i] = this.name[i - 1];
		this.surname[i] = this.surname[i - 1];
		this.e_mail[i] = this.e_mail[i - 1];
		this.password[i] = this.password[i - 1];
		}
		// Insert the new item.
		int user_num=size+1;
		this.user_num[size]="User "+user_num;
		this.name[index] = name;
		this.surname[index] = surname;
		this.e_mail[index] = e_mail;
		this.password[index] = password;
		
		size++;
	}
	
	/** Get the name at the given index
	@param index The position of name in arraylist
	@return name
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E getName(int index) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
		}
		return this.name[index];
		}
	
	/** Get the surname at the given index
	@param index The position of surname in arraylist
	@return surname
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E getSurname(int index) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
		}
		return this.surname[index];
	}
	
	/** Get the e_mail at the given index
	@param index The position of e_mail in arraylist
	@return e_mail
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E getE_mail(int index) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
		}
		return this.e_mail[index];
	}
	
	/** Get the password at the given index
	@param index The position of password in arraylist
	@return password
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E getPassword(int index) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
		}
		return this.password[index];
	}
	
	/** Get the user num
	@return user num
	*/
	public int getSize() {
		return size;
	}
	
	/** Set the name at the given index with newName
	@param index The position of name in arraylist
	@param newName The new name of user at the given index
	@return old name
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E setName(int index, E newName) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
	}
		
		E oldName = this.name[index];
		this.name[index] = newName;
		return oldName;
	}
	
	/** Set the surname at the given index with newSurname
	@param index The position of surname in arraylist
	@param newSurname The new surname of user at the given index
	@return old surname
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E setSurname(int index, E newSurname) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
	}
		
		E oldSurname = this.surname[index];
		this.surname[index] = newSurname;
		return oldSurname;
	}
	
	/** Set the e_mail at the given index with newE_mail
	@param index The position of e_mail in arraylist
	@param newE_mail The new e_mail of user at the given index
	@return old e-mail
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E setE_mail(int index, E newE_mail) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
	}
		
		E oldE_mail = this.e_mail[index];
		this.e_mail[index] = newE_mail;
		return oldE_mail;
	}
	
	/** Set the password at the given index with newPassword
	@param index The position of password in arraylist
	@param newPassword The new password of user at the given index
	@return old password
	@throws IndexOutOfBoundsException if index is out of range
	*/
	public E setPassword(int index, E newPassword) {
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
	}
		
		E oldPassword = this.password[index];
		this.password[index] = newPassword;
		return oldPassword;
	}
	
	/** Removes user informations from the given index of arraylist
	@param index The position where user informations is removed
	@return removed user
	*/
	public String remove(int index) {
		int i;
		if (index < 0 || index >= size) {
		throw new ArrayIndexOutOfBoundsException(index);
		}
		String returnUser = this.user_num[index];
		for (i = index + 1; i < size; i++) {
		    this.name[i - 1] = this.name[i];
		}
		for (i = index + 1; i < size; i++) {
			this.surname[i - 1] = this.surname[i];
		}
		for (i = index + 1; i < size; i++) {
			this.e_mail[i - 1] = this.e_mail[i];
		}
		for (i = index + 1; i < size; i++) {
			this.password[i - 1] = this.password[i];
		}
		size--;
		return returnUser;
	}
	
	/** Frees up memory when the size of the arraylist is equal to its capacity*/
	private void reallocate() {
		capacity = 2 * capacity;
		user_num = Arrays.copyOf(user_num,capacity);
		name = Arrays.copyOf(name, capacity);
		surname = Arrays.copyOf(name, capacity);
		e_mail = Arrays.copyOf(name, capacity);
		password = Arrays.copyOf(name, capacity);
		
		}

	
}
