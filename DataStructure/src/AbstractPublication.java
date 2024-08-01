import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Publication class for all publications of the system.
 * @author gokbey_gazi_keskin
 */
public class AbstractPublication implements Publication {
    private String name;
    private Author author;
    private String id;
    private static Integer nextID = 10000;
    private Language lang;
    private Integer page;
    private List<String> comments;
    
    /**
     * Constructor for AbstractPublication.
     * @param nm Name of the publication.
     * @param athr Information of the author.
     * @param lng Language of the Publication.
     * @param pg Page amounr of the Publication.
     */
    public AbstractPublication(String nm,Author athr,Language lng, Integer pg){
        name = nm;
        author = athr;
        id = String.valueOf(nextID++); //id is automatically generated. First ID is 10000
        lang = lng;
        page = pg;
        comments = new ArrayList<>();
    }

    /**
     * Setter for name.
     * @param name Name of the publication.
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for name.
     * @return Name of the publication.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Setter for Author.
     * @param author Author of the publication.
     */
    @Override
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * Getter for Author.
     * @return Author of the publication.
     */
    @Override
    public Author getAuthor() {
        return author;
    }

    /**
     * Setter for ID.
     * @param id ID of the publication.
     */
    @Override
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Getter for ID.
     * @return ID of the publication.
     */
    @Override
    public String getID() {
        return id;
    }

    /**
     * Setter for Language.
     * @param language Language of the publication.
     */
    @Override
    public void setLang(Language language) {
        lang = language;
    }

    /**
     * Getter for Language
     * @return Language of the publication.
     */
    @Override
    public Language getLang() {
        return lang;
    }

    /**
     * Setter for Page.
      * @param pg Page count.
     */
    @Override
    public void setPage(int pg) {
        page = pg;
    }

    /**
     * Getter for Page.
     * @return Page count.
     */
    @Override
    public int getPage() {
        return page;
    }

    /**
     * Getter for the Comments of the publication.
     * @return Comments of the Publication.
     */
    @Override
    public List<String> getComments() {
        return comments;
    }

    /**
     * Add a new comment to Book.
     * @param comment New comment.
     */
    @Override
    public void addComment(String comment){
        comments.add(comment);
    }
    /**
     * Returns string representation of the object.
     * @return Information of Publication.
     */
    @Override
    public String toString(){
        return "Name: " + name + "\nPage Amount: " + page + "\nLanguage: " + lang + "\nID: " + id +
                "\nAuthor's info:" + author + "\nComments: " + comments;
    }

    /**
     * CompareTo method for compare publication ID's.
     */
    @Override
    public int compareTo(Publication o) {
        return id.compareTo(o.getID());
    }

    //-----------------------------------------ENTITY METHODS--------------------------
    
    /*
    public AbstractPublication(BookEntity entity){

        name = entity.getName();
        author = entity.getAuthor();
        id = entity.getID();
        lang = entity.getLang();
        page = entity.getPage();
        comments = entity.getComments(); 
    }

    public AbstractPublication(EncyclopediaEntity entity){

        name = entity.getName();
        author = entity.getAuthor();
        id = entity.getID();
        lang = entity.getLang();
        page = entity.getPage();
        comments = entity.getComments(); 
    }
    
    public AbstractPublication(MagazineEntity entity){

        name = entity.getName();
        author = entity.getAuthor();
        id = entity.getID();
        lang = entity.getLang();
        page = entity.getPage();
        comments = entity.getComments(); 
    }

    */

}
