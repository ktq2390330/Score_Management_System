package bean;

import java.io.Serializable;

public class Subject implements Serializable{
	String cd;
	String name;
	School school;

	public String getCd(){return cd;}
	public void setCd(String cd){this.cd=cd;}

	public String getName(){return name;}
	public void setName(String name){this.name=name;}

	public School getSchool(){return school;}
	public void setSchool(School school){this.school=school;}
}