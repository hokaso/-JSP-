package action;

import java.sql.Timestamp;

import com.opensymphony.xwork2.ActionSupport;

import bean.Student;
import work.RegisterWork;

public class Register extends ActionSupport{
	private Student student;
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	//ѧԱע��
	public String UserRegister(){
		student.setScoin(0);
		//��ȡ��ǰʱ��
		Timestamp d = new Timestamp(System.currentTimeMillis());
		student.setSregisterTime(d);
	
		RegisterWork rt=new RegisterWork();
		if(rt.Register(student)){
			return SUCCESS;
		}
		
		return "error";
	}
}
