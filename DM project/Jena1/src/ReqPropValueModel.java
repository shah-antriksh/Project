
public class ReqPropValueModel {

	
	String job_name;
	String work_Location;
	String requires_competence;
	int requires_work_experience;
	String orgEmail;
	String organizationName; 
	int max_age;
	public ReqPropValueModel(String job_name, String work_Location, String requires_competence,
			int requires_work_experience, String orgEmail, String organizationName, int max_age) {
		super();
		this.job_name = job_name;
		this.work_Location = work_Location;
		this.requires_competence = requires_competence;
		this.requires_work_experience = requires_work_experience;
		this.orgEmail = orgEmail;
		this.organizationName = organizationName;
		this.max_age = max_age;
	}
	
		
}
