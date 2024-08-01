import java.util.LinkedList;

/**
 * Librarian of the library
 * @author Mustafa Gurler
 */
public class PremiumReader extends StandartReader
{
    public PremiumReader(String name,String surname,String pw, Library workingLib)
	{
        super(name, surname, pw, workingLib);
        borrowedBooks = new LinkedList<>();
    }
    
    /**
	 * demand book
	 * @return true if the commenting is successful, false otherwise
	 */
	public boolean demandBook(Book book)
	{
		lib.getDemandedBooks().add(book);
		return true;
	}

    
    /**
	 * creating event request
	 * @return true if the requesting is successful, false if it is not.
	 */
	public boolean request_event(Event event)
	{
		lib.getOfferedEvents().add(event);
		return true;
	}

	//-----------------------------------------ENTITY METHODS--------------------------
	/*
    /**
     * PremiumReader constructor for Database operations.
     * @param entity PremiumReaderEntity class object.
     *
    public PremiumReader(PremiumReaderEntity entity){

        super(entity);
    }

    /**
     * Method to save PremiumReader data field to PremiumReaderEntity object.
     * @return PremiumReaderEntity object.
     *
     
    public PremiumReaderEntity getEntity(){

        PremiumReaderEntity entity;

        entity.setName(getName());
        entity.setSurname(getSurname());
        entity.setID(getID());
        entity.setPW(getPW());
        entity.setLib(getLib());

        for (Publication book : getBorrowed())           
          entity.getBorrowed().add(book);    
        
        return entity;

    }

*/

}
