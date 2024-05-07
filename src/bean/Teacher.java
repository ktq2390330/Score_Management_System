package bean;

import java.io.Serializable;

public class Teacher implements Serializable {
	private String id;
	private String password;
	private String name;
	private School school;
	private boolean isAuthenticated;

	public String getId(){return id;}
	public void setId(String id){this.id=id;}

	public String getPassword(){return password;}
	public void setPassword(String password){this.password=password;}

	public String getName(){return name;}
	public void setName(String name){this.name=name;}

	public School getSchool(){return school;}
	public void setSchool(School school){this.school=school;}

	public boolean isAuthenticated(){return isAuthenticated;}
	public void setAuthenticated(boolean isAuthenticated){this.isAuthenticated=isAuthenticated;}

	public String getSchoolCdAsString(){
	    School school = getSchool();
	    if (school != null){return school.getCd();
	    }else{return null;}
	}
}