
public class ReqPropModel {
	String file_name;
	String job_name;
	String work_Location;
	String requires_competence;
	String requires_work_experience;
	String hasOrgEmail;
	String hasOrganizationName; 
	String Max_age;
	public ReqPropModel(String file_name, String job_name, String work_Location, String requires_competence,
			String requires_work_experience, String hasOrgEmail, String hasOrganizationName, String max_age) {
		super();
		this.file_name = file_name;
		this.job_name = job_name;
		this.work_Location = work_Location;
		this.requires_competence = requires_competence;
		this.requires_work_experience = requires_work_experience;
		this.hasOrgEmail = hasOrgEmail;
		this.hasOrganizationName = hasOrganizationName;
		this.Max_age = max_age;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getJob_name() {
		return job_name;
	}
	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}
	public String getWork_Location() {
		return work_Location;
	}
	public void setWork_Location(String work_Location) {
		this.work_Location = work_Location;
	}
	public String getRequires_competence() {
		return requires_competence;
	}
	public void setRequires_competence(String requires_competence) {
		this.requires_competence = requires_competence;
	}
	public String getRequires_work_experience() {
		return requires_work_experience;
	}
	public void setRequires_work_experience(String requires_work_experience) {
		this.requires_work_experience = requires_work_experience;
	}
	public String getHasOrgEmail() {
		return hasOrgEmail;
	}
	public void setHasOrgEmail(String hasOrgEmail) {
		this.hasOrgEmail = hasOrgEmail;
	}
	public String getHasOrganizationName() {
		return hasOrganizationName;
	}
	public void setHasOrganizationName(String hasOrganizationName) {
		this.hasOrganizationName = hasOrganizationName;
	}
	public String getMax_age() {
		return Max_age;
	}
	public void setMax_age(String max_age) {
		Max_age = max_age;
	}
	
	
	
	

}
