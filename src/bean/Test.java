package bean;

import java.io.Serializable;

public class Test implements Serializable{
	Student student;
	String classNum;
	Subject subject;
	School school;
	int no;
	int point;

	public Student getStudent(){return student;}
	public void setStudent(Student student){this.student=student;}

	public String getClassNum(){return classNum;}
	public void setClassNum(String classNum){this.classNum=classNum;}

	public Subject getSubject(){return subject;}
	public void setSubject(Subject subject){this.subject=subject;}

	public School getSchool(){return school;}
	public void setSchool(School school){this.school=school;}

	public int getNo(){return no;}
	public void setNo(int no){this.no=no;}

	public int getPoint(){return point;}
	public void setPoint(int point){this.point=point;}
}