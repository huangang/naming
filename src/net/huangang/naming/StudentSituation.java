package net.huangang.naming;

public class StudentSituation {
	private String student_name;
	private String student_number;
	private String student_situation;
	private String created_time;
    
    public StudentSituation(String student_name, String student_number, String student_situation, String created_time) {
    	setStudent_name(student_name);
    	setStudent_number(student_number);
    	setStudent_situation(student_situation);
    	setCreated_time(created_time);
    }

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_number() {
		return student_number;
	}

	public void setStudent_number(String student_number) {
		this.student_number = student_number;
	}

	public String getStudent_situation() {
		return student_situation;
	}

	public void setStudent_situation(String student_situation) {
		this.student_situation = student_situation;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
    
    
    
    
}
