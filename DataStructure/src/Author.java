import java.util.ArrayList;
import java.util.List;

/**
 * Author class for Automation System. Holds info of an Author.
 * @author gokbey_gazi_keskin
 */
public class Author {
    String name;
    String surname;
    String dateOfBirth;
    List<Publication> publications;

    /**
     * Constructor for Author.
     * @param nm Name of the author.
     * @param snm Surname of the author.
     */
    public Author(String nm, String snm){
        this(nm,snm,"not-added or unknown",new ArrayList<>());
    }

    /**
     *  Constructor with additional info date of birth for Author.
     * @param nm Name of the author.
     * @param snm Surname of the author.
     * @param dateOfBirth Date of Birth of the author.
     */
    public Author(String nm, String snm, String dateOfBirth){
        this(nm,snm,dateOfBirth,new ArrayList<>());

    }

    /**
     *  Constructor with additional infos date of birth and publications for Author .
     * @param nm Name of the author.
     * @param snm Surname of the author.
     * @param dateOfBirth Date of Birth of the author.
     * @param pb Publications of the Author.
     */
    public Author(String nm, String snm, String dateOfBirth,ArrayList<Publication> pb){
        name = nm;
        surname = snm;
        this.dateOfBirth = dateOfBirth;
        publications = pb;
    }

    /**
     * Setter for name.
     * @param name Name of the author.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for surname
     * @param surname Surname of the author.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Setter for the date of birth.
     * @param dateOfBirth Date of birth of the author.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Add a new publication to author.
     * @param publication New publication.
     */
    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    /**
     * Getter for name.
     * @return Name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for surname.
     * @return Surname of the author
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Getter for date of birth.
     * @return Date of birth of the author.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Getter for publications.
     * @return Publications of the author.
     */
    public List<Publication> getPublications() {
        return publications;
    }

    /**
     * Returns string representation of the object.
     * @return Information of Author.
     */
    @Override
    public String toString(){
        return "Name: " + name + "\nSurname:  " + surname +
                "\nDateOfBirth: " + dateOfBirth + "Publications:" + publications;

    }
    //-----------------------------------------ENTITY METHODS--------------------------

    /*

    /**
     * Author constructor for Database operations.
     * @param entity AuthorEntity class object.
     
    public Author(AuthorEntity entity){

        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.dateOfBirth = entity.getDateOfBirth();
        this.publications = entity.getPublications();

    }

    /**
     * Method to save Author data field to AuthorEntity object.
     * @return AuthorEntity object.
     
    public AuthorEntity getEntity(){

        AuthorEntity entity;

        entity.setName(this.name);
        entity.setSurname(this.surname);
        entity.setDateOfBirth(this.dateOfBirth);

        for (Publication pub : publications)           
           entity.publications.add(pub);        
        
        return entity;

    }

    */

}
