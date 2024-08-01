
import java.util.List;


/**
 * Publication interface for all publications of the Library Automation System. (publication,Encyclopedia,Magazine)
 * @author gokbey_gazi_keskin
 * */
public interface Publication extends Comparable<Publication> {
    /**
     * Setter for Name
     * @param name Name of the publication.
     */
    void setName(String name);

    /**
     * Getter for Name
     * @return Name of the publication.
     */
    String getName();

    /**
     *  Setter for Author
     * @param author Author of the publication.
     */
    void setAuthor(Author author);

    /**
     * Getter for Author.
     * @return Author of the publication.
     */
    Author getAuthor();


    /**
     * Setter for ID.
     * @param id ID of the publication.
     */
    void setID(String id);

    /**
     * Getter for ID.
     * @return ID of the publication.
     */
    String getID();

    /**
     * Setter for Language.
     * @param language Language of the publication.
     */
    void setLang(Language language);

    /**
     * Getter for Language.
     * @return Language o the publication.
     */
    Language getLang();

    /**
     * Setter for page count.
     * @param pg page count.
     */
    void setPage(int pg);

    /**
     * Getter for page count.
     * @return page count.
     */
     int getPage();

    /**
     * Getter for comments of the publication.
     * @return An array list of comments.
     */
    List<String> getComments();

    /**
     * Add a new comment to publication.
     * @param comment New comment.
     */
     void addComment(String comment);


}
