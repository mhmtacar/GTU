import java.util.*;

/**
 * Encyclopedia class for the Automation System.
 * @author gokbey_gazi_keskin
 */
public class Encyclopedia extends AbstractPublication{
    private HashMap<Integer,String> sections;

    /**
     * Constructor for Encyclopedia.
     * @param nm Name of the publication.
     * @param athr Information of the author.
     * @param lng Language of the Publication.
     * @param pg Page amounr of the Publication.
     */
    public Encyclopedia(String nm, Author athr, Language lng, Integer pg) {
        super(nm, athr, lng, pg);
        sections = new HashMap<>();
    }

    /**
     * Constructor with page specifications for Encyclopedia.
     * @param nm Name of the publication.
     * @param athr Information of the author.
     * @param lng Language of the Publication.
     * @param pg Page amounr of the Publication.
     * @param sec Page-Section heading info's of the Encyclopedia.
     */
    public Encyclopedia(String nm, Author athr, Language lng, Integer pg,HashMap<Integer,String> sec) {
        super(nm, athr, lng, pg);
        sections = sec;
    }

    /**
     * Add a new Section to the Encyclopedia.
     * @param pg Page number.
     * @param heading Section heading.
     */
    public void addSection(Integer pg, String heading){
        sections.put(pg,heading);
    }

     /**
     * Getter for the Sections of the Encyclopedia.
     * @return Sections of the Encyclopedia.
     */
    public HashMap<Integer,String> getSections() {
        return sections;
    }

    /**
     * Returns string representation of the object.
     * @return Information of Encyclopedia.
     */
    @Override
    public String toString(){
        return super.toString() + "\nSections: " + sections;
    }

    //-----------------------------------------ENTITY METHODS--------------------------

    /*

    /**
     * Encyclopedia constructor for Database operations.
     * @param entity EncyclopediaEntity class object.
     
    public Encyclopedia(EncyclopediaEntity entity){

        super(entity); 
        this.sections = entity.getSections();
    }

    /**
     * Method to save Encyclopedia data field to EncyclopediaEntity object.
     * @return EncyclopediaEntity object.
     
    public EncyclopediaEntity getEntity(){

        EncyclopediaEntity entity;

        entity.setName(getName());
        entity.setAuthor(getAuthor());
        entity.setID(getID());
        entity.setLang(getLang());
        entity.setPage(getPage());
        
        for(Map.Entry<Integer, String> entry : sections.entrySet())
            entity.sections.put(entry.getKey(),entry.getValue());

        for (String comment : getComments())           
           entity.comments.add(comment);         
        
        return entity;

    }

    */
}
