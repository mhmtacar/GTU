import java.util.*;

/**
 * Janitor of the library
 * @author mehmet_acar
 * @author Mustafa Gurler
 */
public class Janitor extends User{

	private Queue<Task> Tasks;
	private Library lib;
    /**
     * Constructor of Task
     */
    public Janitor(String name,String surname,String pw,Library lib)
    {
        super(name,surname,pw);
        Tasks = new ArrayDeque<Task>();
        this.lib = lib;
    }
    
	public Queue<Task> getTasks() {
		return Tasks;
	}
	public Library getLib() {
		return lib;
	}
    public void setLib(Library lib){

        this.lib = lib;
    }
	
    /**
     * It views all of the task that belongs to janitor
     * @param
     */
    public void viewTask(String ID){
    	for (Task element : Tasks)
        {
            System.out.println(element.toString());
        }
    }
 
    /**
     * It updates specific task that belongs to janitor
     * @param TaskId TaskId for specific task
     * @return
     */
    public boolean updateTask(String TaskId){
    	for (Task element : Tasks)
        {
          if(element.getTaskId().equals(TaskId)) {
        	 element.setDoneTask(true);
        	 return true;
          }
        }
    	return false;
    }
    /**
     * Adds the task to the janitors' task
     * @param newTask New Task
     * @return
     */
    public boolean addTask(String theTaskID)
    { 
        Task newTask = new Task(theTaskID);
        if(newTask.getTaskId()!=null) {
           return Tasks.offer(newTask);
        }
        else {
           return false;
        }
    }

    //-----------------------------------------ENTITY METHODS--------------------------

   /*
    /**
     * Janitor constructor for Database operations.
     * @param entity JanitorEntity class object.
     *  
    public Janitor(JanitorEntity entity){

        super(entity);
        this.lib = entity.getLib();
        this.Tasks = entity.getTasks(); 
    }

    /**
     * Method to save Janitor data field to JanitorEntity object.
     * @return JanitorEntity object.
     *
     
    public JanitorEntity getEntity(){

        JanitorEntity entity;

        entity.setName(getName());
        entity.setSurname(getSurname());
        entity.setID(getID());
        entity.setPW(getPW());
        entity.setLib(getLib());

        for (Task task : Tasks)           
           entity.Tasks.add(task);        
        
        return entity;

    }
*/
    
}
