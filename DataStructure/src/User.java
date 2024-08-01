import javax.swing.*;

/**
 * User general class for all users of the Library Automation System.
 * @author gokbey_gazi_keskin
 * @author Mustafa Gurler
 * */
public abstract class User implements Person,Comparable<User>{

    protected String name;
    protected String surname;
    protected String id;
    protected String password;
    private static String lastID = "10000";
    /**
     * Constructor of User
     * @param name Name of the User
     * @param surname Surname of the User
     * @param password Password of the User
     */
    public User(String name,String surname,String password){
        this.name = name;
        this.surname = surname;
        this.id = generateID();
        this.password = password;
        System.out.println("Your ID is:" + id +". You will login with this ID.");
    }

    private String generateID(){
        String returnID = lastID;
        lastID = String.valueOf(Integer.parseInt(lastID)+1);
        return returnID;
    }

    /**
     * Setter for Name
     * @param name Name of the User
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Getter for Name
     * @return Name of the User
     */
    public String getName(){
        return name;
    }

    /**
     * Setter for Surname
     * @param surname Surname of the User
     */
    public void setSurname(String surname){
        this.surname = surname;
    }

    /**
     * Getter for Surname
     * @return Surname of the User
     */
    public String getSurname(){
        return surname;
    }

    /**
     * Setter for id
     * @param id id of the User
     */
    public void setID(String id){
        this.id = id;
    }

    /**
     * Getter for id
     * @return id of the User
     */
    public String getID(){
        return id;
    }

    /**
     * Setter for Password
     * @param pw Password of the User
     */
    public void setPW(String pw){
        this.password = pw;
    }

    /**
     * Getter for Password
     * @return Password of the User
     */
    public String getPW(){
        return password;
    }


    /**
     * Login method
     * @param id ID of the User
     * @param pw Password of the User
     * @return true if login is successful,false otherwise;
     */
    public boolean login(String id,String pw){
        return id.equals(this.id) && pw.equals(this.password);
    }



    /**
     * Equals implementation for User Class.
     * @param other Another User Object
     * @return true if they are the same User, false otherwise.
     */
    public boolean equals(User other){
        return other.getID().equals(id) && other.getPW().equals(password);
    }



    @Override
    public int compareTo(User o) {
        return getID().compareTo(o.getID());
    }

    /**
     * toString implementation for User Class
     * @return String representation of User.
     */
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("User Information\n");
        str.append("Name:").append(getName());
        str.append("\nSurname:").append((getSurname()));
        str.append("\nID:").append(getID());
        return str.toString();
    }

        //-----------------------------------------ENTITY METHODS--------------------------
    
    /*
    public User(AdminEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.ID = entity.getID();
        this.password = entity.getPW();
    }
   
    public User(JanitorEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.ID = entity.getID();
        this.password = entity.getPW();
    }

    public User(LibrarianEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.ID = entity.getID();
        this.password = entity.getPW();
    }
 
    public User(ManagerEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.ID = entity.getID();
        this.password = entity.getPW();
    }

    public User(PremiumReaderEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.ID = entity.getID();
        this.password = entity.getPW();
    }

    public User(StandartReaderEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.ID = entity.getID();
        this.password = entity.getPW();
    }
    
    */



    
}
