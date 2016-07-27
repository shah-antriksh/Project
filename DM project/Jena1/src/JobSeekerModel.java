public class JobSeekerModel {
	String work_exp;
	String location;
	String competence;
	int year;
	public JobSeekerModel(String work_exp, String location, String competence, String year) {
		super();
		this.work_exp = work_exp;
		this.location = location;
		this.competence = competence;
		this.year=Integer.parseInt(year);
	}
	

}
