import java.util.*;


/**
 * @author ogulcan_kalafatoglu
 */


 


public class Library{


	private String name;
    private String address;
    private String id;
    private Manager manager;

    private AVLTree<Publication> publications;
    private PriorityQueue<Event> upcomingEvents;

    private List<Publication> demandedBooks;
    
    private List<Event> pastEvents;
    private List<Event> offeredEvents;

    private NavigableSet<Librarian> librarians;
    private NavigableSet<Janitor> janitors;
    private static final SkipList<String> existingIDs = new SkipList<>();

    public Library(){
        this(null,null,null);
    }

    public Library(String name,String address,String id){
        this.name = name;
        this.address = address;
        if(!existingIDs.contains(id)) {
            this.id = id;
            existingIDs.insert(id);
        }
        else{
            throw new IllegalArgumentException("ID already exists.");
        }
        this.manager = null;
        
        publications = new AVLTree<>(new Comparator<Publication>(){
            @Override
            public int compare(Publication p1, Publication p2){
                if(p1.getName().compareTo(p2.getName()) == 0){
                    return p1.getLang().compareTo(p2.getLang());
                }
                
                return p1.getName().compareTo(p2.getName());
                
            }
        });
        demandedBooks = new ArrayList<>();
        pastEvents = new ArrayList<>();
        upcomingEvents = new PriorityQueue<>();
        librarians = new TreeSet<>();
        janitors = new TreeSet<>();
    }
    
    public AVLTree<Publication> getPublications(){return publications;}

    public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

    public String getName() {
		return name;
	}

    public List<Event> getOfferedEvents()
    {
        return offeredEvents;
    }

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public List<Publication> getDemandedBooks()
    {
        return demandedBooks;
    }

    public List<Event> getPastEvents()
    {
        return pastEvents;
    }


    public PriorityQueue<Event> getUpcomingEvents()
    {
        return upcomingEvents;
    }

    public NavigableSet<Librarian> getLibrarians()
    {
        return librarians;
    }

    public NavigableSet<Janitor> getJanitors()
    {
        return janitors;
    }

    /**
     * Get a specific publication with name and language
     * @param bookName Name of the publication
     * @return searched publication or null if publication is not in library
     */
    public Publication getBook(String bookName, Language bookLanguage){
        return publications.find(new Book(bookName,null,bookLanguage,null,null));
    }

    /**
     * Check if librarian with given id exists in library.
     * @param id id of the librarian
     * @return -1 if librarian does not exist else id of the librarian.
     */
    public int isLibrarian(String id){
        for(Librarian i : librarians){
            if(i.getID().equals(id))
                return 1;
        }

        return -1;
    }

    /**
     * Check if janitor with given id exists in library.
     * @param id id of the janitor
     * @return -1 if janitor does not exist else id of the librarian.
     */
    public int isJanitor(String id){
        for(Janitor i : janitors){
            if(i.getID().equals(id))
                return 1;
        }

        return -1;
    }


    /**
     * Checks if given publication exists in stock
     * @param searchedBook
     * @param bookLanguage
     * @return true if publication's stock is bigger than 0 else false
     */
    public boolean isInStock(String searchedBook, Language bookLanguage){
        //TODO 
        //compare method should be implemented for publications in AbstractPublications class.
        //Or AVLTree should have a constructor that takes Comparator.
        return (publications.getAmount(new AbstractPublication(searchedBook, null, bookLanguage, null)) != 0);
    }


    /**
     * Changes stock of given publication by given amount
     * if given publication is not in the library, adds to the library.
     * @param givenPublication
     * @return true
     */
    public boolean changeStock(Publication givenPublication, int amount){

        if(amount < 0) return publications.delete(givenPublication) != null;
        
        return publications.add(givenPublication);
    }


    /**
     * Returns publication amount of a specific publication by name and language 
     * @return Stock amount of desired publication, -1 if publication is not found
     */
    public int bookAmount(String bookName, Language bookLanguage){
        return publications.getAmount(new AbstractPublication(bookName, null, bookLanguage, null));
    }


    /**
     * Adds the publication to demanded publications list.
     * @param demandedBook
     * @return true
     */
    public boolean demandBook(Publication demandedBook){
        return demandedBooks.add(demandedBook);
    }
    
    public boolean removeDemandedBook(Publication demandedBook)
    {
    	return demandedBooks.remove(demandedBook);
    }

    public Publication removeDemandedBook()
    {
        if(demandedBooks.size() == 0) return null;

        return demandedBooks.remove(demandedBooks.size() - 1);
    }

    /**
     * Add a new Janitor to the janitors list
     * @param janitor
     * @return true
     */
    public boolean addJanitor(Janitor janitor){
        return janitors.add(janitor);
    }

    /**
     * Add a new Librarian to the librarians list
     * @param librarian
     * @return true
     */
    public boolean addLibrarian(Librarian librarian){
        return librarians.add(librarian);
    }

    /**
     * Remove given Librarian from the librarians list
     * @param librarian
     * @return Removed Librarian
     */
    public boolean removeLibrarian(Librarian librarian){
        return librarians.remove(librarian);
    }

    /**
     * Get Librarian by their id
     * @param id
     * @return Searched Librarian
     */
    public Librarian getLibrarian(String id){
        for(Librarian i : librarians)
            if(i.getID().equals(id))
                return i;
        return null;
    }

    /**
     * Remove given Janitor.
     * @param janitor
     * @return removed Janitor, if janitor is not in the list return null
     */
    public boolean removeJanitor(Janitor janitor){
        return janitors.remove(janitor);
    }

    /**
     * Get janitor by their id.
     * @param id
     * @return searched janitor, if janitor is not found returns null
     */
    public Janitor getJanitor(String id){
        for(Janitor i : janitors)
            if(i.getID().equals(id))
                return i;
        return null;
    }


    /**
     * Add event to the upcoming events list 
     * @param event
     * @return true
     */
    public boolean addUpcomingEvent(Event event)
    {
    	if(offeredEvents.contains(event))
    		offeredEvents.remove(event);
    	
        return upcomingEvents.add(event);
    }

    /**
     * Delete first event from upcoming event list and add it to past events
     * @return true
     */
    public Event endEvent(){
    	
    	Event endingEvent=upcomingEvents.poll();
    	
    	if(endingEvent!=null)
    		pastEvents.add(endingEvent);

        return endingEvent;
    }

    /**
     * Remove event from offered events list.
     * @param event
     * @return false if event is not in offeredEvents else true. 
     */

    public boolean removeEvent(Event event){
        int index = offeredEvents.indexOf(event);
        if(index == -1) return false;

        offeredEvents.remove(index);
        return true;
    }

    /**
     * Returns nearest event
     * @return event
     */
    public Event getNearestEvent(){
        return upcomingEvents.peek();
    }
    
    public void printPastEvents()
    {
    	Iterator<Event> iter=pastEvents.iterator();
    	
    	int i=1;
    	while(iter.hasNext())
    	{
    		System.out.println(i+"-) "+iter.next());
    		i++;
    	}
    }
    
    public void printUpcomingEvents()
    {
    	PriorityQueue<Event> temp=upcomingEvents;
    	
    	Event nextEvent=temp.poll();
    	int i=1;
    	while(nextEvent!=null)
    	{
    		System.out.println(i+"-) "+nextEvent);
    		i++;
    		nextEvent=temp.poll();
    	}
    }
    
    /**
	 * Prints out all the books with the given category.
	 * Does not include the books with the same name more than once.
     * Print output is alphabetically ordered.
     * @param theGenre desired genre
	 */
    public void printGenre(BookGenre theGenre)
    {
        Iterator<Publication> iter = publications.preOrderIterator();
        List<Publication> bookList = new ArrayList<>();
        
        while(iter.hasNext())
        {
            Publication tempPub = iter.next();
            if(tempPub instanceof Book)
            {
                Book tempBook = (Book) tempPub;
                if(tempBook.getGenre().equals(theGenre)){
                    bookList.add(tempPub);
                }
            }
        }
        if(bookList.size() == 0)
        {
            System.out.printf("Book with %s genre does not exist in this library\n", theGenre.toString());
            return;
        }
        //Sort bookList by name using merge sort.
        ALSort.mergeSort(bookList, new Comparator<Publication>(){
            @Override
            public int compare(Publication o1, Publication o2){
                return o1.getName().compareTo(o2.getName());
            }
        });
        
        for(Publication pub : bookList)
        {
            System.out.println(pub + "\n");
        }

    }

    @Override
    public String toString(){
       return "Name:" + name + "Address: " + address + "Current Manager: " + manager;
    }

           //-----------------------------------------ENTITY METHODS--------------------------

    
    /*
    /**
     * Event constructor for Database operations.
     *  @param entity EventEntity class object.
     * 
     
    public Event(EventEntity entity){

    this.name = entity.getName();
    this.address = entity.getAddress();
    this.id = entity.getID();
    this.manager = entity.getManager();
    this.demandedBooks = entity.getDemandedBooks();
    this.pastEvents = entity.getPastEvents();
    this.offeredEvents = entity.getOfferedEvents();
    this.upcomingEvents = entity.getUpcomingEvents();
    this.librarians = entity.getLibrarians();
    this.janitors = entity.getJanitors();


    for (Publication pub : entity.publications)           
        this.publications.add(pub);

    for (String ids : entity.existingIDs)           
        this.existingIDs.insert(ids);   

    }

    /**
     * Method to save Event data field to EventEntity object.
     * @return EventEntity object.
     * 

     
    public EventEntity getEntity(){

        EventEntity entity;

        entity.setName(getName());
        entity.setAddress(getAddress());
        entity.id = this.id;
        entity.setManager(getManager());
        entity.demandedBooks = this.demandedBooks;
        entity.pastEvents = this.pastEvents;
        entity.offeredEvents = this.offeredEvents;
        entity.upcomingEvents = this.upcomingEvents;
        entity.librarians = this.librarians;
        entity.janitors = this.janitors;

        for (Publication pub : publications)           
           entity.publications.add(pub);

        for (String ids : existingIDs)           
           entity.existingIDs.add(ids);          
        
        return entity;
    }
*/
    
}