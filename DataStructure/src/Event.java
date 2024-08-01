import java.util.ArrayList;
import java.util.List;

/**
 * Event class for the Automation System.
 * @author mehmet_acar
 */

public class Event {
	
	private String name;
	private String date;
	private final List<StandartReader>participants;
	private ArrayList<Integer> votes = new ArrayList<>();
	
	
	/**
     * Constructor for Event.
     * @param name Name of the event.
     */
	public Event(String name) {
		this.name=name;
		this.date="not-added or unknown";
		participants= new ArrayList<>();
	}
	
	/**
     * Constructor with additional info date for Event.
     * @param name Name of the event.
     * @param date Date of the event.
     */
	public Event(String name,String date) {
		this.name=name;
		this.date=date;
		participants= new ArrayList<>();
	}
	
	/**
     * Constructor with additional info date and participants for Event.
     * @param name Name of the event.
     * @param date Date of the event.
     * @param participants Participants of the Event.
     */
	public Event(String name, String date, ArrayList<StandartReader> participants){
        this.name=name;
        this.date=date;
        this.participants = participants;
    }
	
	/**
     * Setter for name.
     * @param name Name of the event.
     */
	public void setName(String name) {
		this.name=name;
	}
	
	/**
     * Setter for date.
     * @param date Date of the event.
     */
	public void setDate(String date) {
		this.date=date;
	}
	
	/**
     * Getter for name.
     * @return Name of the event.
     */
	public String getName() {
		return this.name;
	}
	
	/**
     * Getter for date.
     * @return Date of the event.
     */
	public String getDate() {
		return this.date;
	}

	public ArrayList<Integer> getVotes() {
		return this.votes;
	}
	
	/**
     * Add a new participant to event.
     * @param participant New participant.
     */
	public void addParticipant(StandartReader participant) {
		participants.add(participant);
	}
	
	/**
     * Remove a participant from event according to StandartReader parameter.
     * @param participant Removed participant.
     * @return removed participant or throws exception.
     */
	public StandartReader removeParticipant(StandartReader participant) throws Exception{
	    
		StandartReader temp,removed_user;
		
		if(participants.size()==0) {
			throw new Exception("Event has not participants.You can not remove participant.");
		}
		else {
			for(int i=0;i<participants.size();i++) {
				temp=participants.get(i);
				if(temp.getName().equals(participant.getName()) && temp.getSurname().equals(participant.getSurname()) &&
				   temp.getID().equals(participant.getID()) && temp.getPW().equals(participant.getPW())) {
					removed_user=participants.get(i);
					participants.remove(i);
					return removed_user;
				}
			}
			
			throw new Exception("Event has not this participant.");
			
		}

	}
	
	 /**
     * Getter for participants.
     * @return Participants of the event.
     */
    public ArrayList<StandartReader> getParticipants() {
        return (ArrayList<StandartReader>) participants;
    }
	
    /**
     * Getter for participant.
     * @return Participant of the event according to index parameter.
     */
	public StandartReader getParticipant(int index) {
		return participants.get(index);
	}

	public boolean addVote(int vote)
	{
		votes.add(vote);
		return true;
	}
	
	 /**
     * Returns string representation of the object.
     * @return Information of Event.
     */
	@Override
    public String toString() {
        return "Name: " + name + "\nDate: " + date + "\nParticipants:" + participants;
    }
	
	/**
     * Prints event infos.
     */
	public void printEventInfos() {
		
		System.out.printf("Name: %s \nDate: %s\n", name,date);
		System.out.println(participants);
		
	}

	   //-----------------------------------------ENTITY METHODS--------------------------

    
/*
    /**
     * Event constructor for Database operations.
     * @param entity EventEntity class object.
     
    public Event(EventEntity entity){

        this.name = entity.getName();
        this.date = entity.getDate();
        this.participants = entity.getParticipants();
        this.votes = entity.getVotes();

    }

    /**
     * Method to save Event data field to EventEntity object.
     * @return EventEntity object.
     
    public EventEntity getEntity(){

        EventEntity entity;

        entity.setName(getName());
        entity.setDate(getDate());

        for (StandartReader rdr : participants)           
           entity.participants.add(rdr);

        for (Integer vote : votes)           
           entity.votes.add(vote);          
        
        return entity;

    }

    */
	
}
