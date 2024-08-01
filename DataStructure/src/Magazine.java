/**
 * Magazine class for the Automation System.
 * @author gokbey_gazi_keskin
 */
public class Magazine extends AbstractPublication{
    private Integer issue;

    /**
     * Constructor for Magazine.
     * @param nm Name of the publication.
     * @param athr Information of the author.
     * @param lng Language of the Publication.
     * @param pg Page amounr of the Publication.
     * @param is Issue number of the magazine.
     */
    public Magazine(String nm, Author athr, Language lng, Integer pg,Integer is) {
        super(nm, athr, lng, pg);
        issue = is;
    }


    /**
     * Setter for issue of the magazine(ith issue).
     * @param issue issue number of the magazine.
     */
    public void setIssue(Integer issue) {
        this.issue = issue;
    }


    /**
     * Getter for issue of the magazine. (ith issue).
     * @return issue number of the publication.
     */
    public Integer getIssue() {
        return issue;
    }

    /**
     * Returns string representation of the object.
     * @return Information of Magazine.
     */
    @Override
    public String toString(){
        return super.toString() + "\nIssue: " + issue;
    }

    //-----------------------------------------ENTITY METHODS--------------------------

    /*

    /**
     * Magazine constructor for Database operations.
     * @param entity MagazineEntity class object.
     
    public Magazine(MagazineEntity entity){

        super(entity); 
        this.issue = entity.getIssue();
    }

    /**
     * Method to save Magazine data field to MagazineEntity object.
     * @return MagazineEntity object.
     
    public MagazineEntity getEntity(){

        MagazineEntity entity;

        entity.setName(getName());
        entity.setAuthor(getAuthor());
        entity.setID(getID());
        entity.setLang(getLang());
        entity.setPage(getPage());
        entity.setIssue(issue);

        for (String comment : getComments())           
           entity.comments.add(comment);         
        
        return entity;

    }

    */  

}
