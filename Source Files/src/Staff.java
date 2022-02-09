/**
Represents a staff
@author Yeong Ka Shing Linus
@version 1.0\
@since 13-11-2021
*/
enum Gender {MALE, FEMALE}
enum JobTitle {MANAGER, WAITER}

public class Staff {
  /**
  * The name of the staff.
  */
	private String name;
  /**
  * The gender of the staff.
  */
	private Gender gender;
  /**
  * The id of the staff.
  */
	private int employeeId;
  /**
  * The job title of the staff.
  */
	private JobTitle jobTitle;
	/**
 * Creates a new staff.
 * @param name The name of the staff.
 * @param gender The gender of the staff.
 * @param employeeId The id of the staff.
 * @param jobTitle The job title of the staff.
 */
	public Staff(String name, Gender gender, int employeeId, JobTitle jobTitle) {
		this.name = name;
		this.gender = gender;
		this.employeeId = employeeId;
		this.jobTitle = jobTitle;
	}
	/**
 * Creates a new staff.
 * @param name The name of the staff.
 * @param gender The gender of the staff.
 * @param employeeId The id of the staff.
 */
	public Staff(String name, Gender gender, int employeeId) {
		this.name = name;
		this.gender = gender;
		this.employeeId = employeeId;
	}
	/**
 * Set the jobtitle of a staff.
 * @return The jobTitle of the staff.
 */
	public void assignJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}
	/**
 * Get the name of a staff.
 * @return The name of the staff.
 */
	public String getName() {
		return this.name;
	}
	/**
 * Get the gender of a staff.
 * @return The gender of the staff.
 */	
	public Gender getGender() {
		return this.gender;
	}
	/**
 * Get the id of a staff.
 * @return employeeId The id of the staff.
 */	
	public int getEmployeeId() {
		return this.employeeId;
	}
	/**
 * Get the job title of a staff.
 * @return jobTitle The job title of the staff.
 */	
	public JobTitle getJobTitle() {
		return this.jobTitle;
	}
}
