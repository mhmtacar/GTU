import java.util.HashMap;

/**
 * @author mehmet_acar
 * @author yesimyalc
 * @author musosman
 */

public class Task
{
	 private HashMap<Integer, String> allTasks;
	 private String TaskId;
	 private String TaskName;
	 private boolean doneTask;
	 
	 /**
	  * Constructor of janitor
	  * @param ID
	  */
	 public Task(String ID)
	 {
		 	setAllTasks();
		 	if(allTasks.containsKey(Integer.parseInt(ID))) {
				this.TaskId=ID;
				doneTask=false;
				setTaskName(ID);
		 	}
	 }
	 
	 /**
	  * Sets all the possible tasks that can be registered.
	  */
	 private void setAllTasks()
	 {
		 allTasks=new HashMap<Integer, String>();
		 allTasks.put(001, "Insert a task here");
		 allTasks.put(010, "Insert a task here");
		 allTasks.put(011, "Insert a task here");
		 allTasks.put(100, "Insert a task here");
		 allTasks.put(101, "Insert a task here");
		 allTasks.put(110, "Insert a task here");
		 allTasks.put(111, "Insert a task here");
	 }
	 
	 /**
	  * If there is a registered task for the given ID, sets the task.
	  * If there is not a registered task for the given ID, throws an exception. 
	  * @param ID is the given ID.
	  */
	 private void setTaskName(String ID)
	 {
		 int id=Integer.parseInt(ID);
		 String taskName=allTasks.get(id);
		 if(taskName==null)
			 throw new IllegalArgumentException();
		 else {
			 TaskName=taskName;
		 }
	 }
	 
	 
	 /**
	 * Getter for TaskId
	 * @return
	 */
	public String getTaskId() 
	{
		return TaskId;
	}
	 
	 /**
     * Getter for TaskName
     * @return TaskName
     */
	public String getTaskName() 
	{
		return TaskName;
	}

    /**
     * Setter for TaskId
     * @param taskId Task Id of the task
     */
	public void setTaskId(String taskId) 
	{
		TaskId = taskId;
		setTaskName(taskId);
	}
	
    /**
     * Setter for doneTask
     * @param doneTask Variable that keeps the task done or not
     */
	public void setDoneTask(boolean doneTask) 
	{
		this.doneTask = doneTask;
	}
	
	 /**
     * Getter for doneTask
     * @return
     */
	public boolean isDoneTask() 
	{
		return doneTask;
	}

	@Override
	public String toString(){
		String status;
		if(doneTask) status = "Done";
		else status = "Not-Done";
		return "Task Name: " + TaskName + "\nTask ID: " + TaskId + "\nStatus:" + status;
	}
	

}
