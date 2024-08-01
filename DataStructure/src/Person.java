
/**
 * User interface for all users of the Library Automation System.
 * @author gokbey_gazi_keskin
 * */
public interface Person{

    /**
     * Setter for Name
     * @param name Name of the User
     */
    void setName(String name);

    /**
     * Getter for Name
     * @return Name of the User
     */
    String getName();

    /**
     * Setter for Surname
     * @param surname Surname of the User
     */
    void setSurname(String surname);

    /**
     * Getter for Surname
     * @return Surname of the User
     */
    String getSurname();

    /**
     * Setter for ID
     * @param id ID of the User
     */
    void setID(String id);

    /**
     * Getter for ID
     * @return ID of the User
     */
    String getID();

    /**
     * Setter for Password
     * @param pw Password of the User
     */
    void setPW(String pw);

    /**
     * Getter for Password
     * @return Password of the User
     */
    String getPW();


    /**
     * Login method
     * @param id ID of the User
     * @param pw Password of the User
     * @return true if login is successful,false otherwise;
     */
    boolean login(String id,String pw);


    /**
     * Equals implementation for User Class.
     * @param other Another User Object
     * @return true if they are the same User, false otherwise.
     */
    boolean equals(User other);

    /**
     * toString implementation for User Class
     * @return String representation of User.
     */
    public String toString();
}