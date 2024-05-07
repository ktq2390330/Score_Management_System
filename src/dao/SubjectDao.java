package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao{
	public Subject get(String cd,School school) throws Exception{
		Subject subject=new Subject();
	    Connection connection=getConnection();
	    PreparedStatement statement=null;
	    ResultSet rSet=null;


	    try {
	        statement=connection.prepareStatement("select * from subject where cd=? and school_cd=?");
	        statement.setString(1,cd);
	        statement.setString(2,school.getCd());
	        rSet=statement.executeQuery();
	        if(rSet.next()){
	            subject.setCd(rSet.getNString("cd"));
	            subject.setName(rSet.getNString("name"));
	            subject.setSchool(school);}
	        else{subject=null;}}
	    catch(Exception e){throw e;}
	    finally{
	        if(rSet!=null){
	            try{rSet.close();}
	            catch(SQLException sqle){throw sqle;}}
	        if(statement!=null){
	            try{statement.close();}
	            catch(SQLException sqle){throw sqle;}}
	        if(connection!=null){
	            try{connection.close();}
	            catch(SQLException sqle){throw sqle;}}
	    }
		return subject;
	}

	private List<Subject> postFilter(ResultSet rSet,School school)throws Exception{
	    List<Subject> list=new ArrayList<>();
	    try{
	        while(rSet.next()){
	            Subject subject=new Subject();
	            subject.setCd(rSet.getString("cd"));
	            subject.setName(rSet.getString("name"));
	            subject.setSchool(school);
	            list.add(subject);}}
	    catch(SQLException|NullPointerException e){e.printStackTrace();}
	    finally{
	        if(rSet!=null){
	            try{rSet.close();}
	            catch(SQLException sqle){throw sqle;}}
	    }
	    return list;
	}

	public List<Subject> filter(School school)throws Exception{
		List<Subject> list=new ArrayList<>();
		Connection connection=getConnection();
	    PreparedStatement statement=null;
	    ResultSet rSet=null;
	    try{
	        statement=connection.prepareStatement("select * from subject where school_cd=?");
	        statement.setString(1,school.getCd());
	        rSet=statement.executeQuery();
	        list=postFilter(rSet, school);}
	    catch(Exception e){throw e;}
	    finally{
	        if(rSet!=null){
	            try{rSet.close();}
	            catch(SQLException sqle){throw sqle;}}
	        if(statement!=null){
	            try{statement.close();}
	            catch(SQLException sqle){throw sqle;}}
	        if(connection!=null){
	            try{connection.close();}
	            catch(SQLException sqle){throw sqle;}}
	    }
	    return list;
	}

	public boolean save(Subject subject,School school)throws Exception{
		boolean flg=false;
		Connection connection=getConnection();
    	PreparedStatement statement=null;
    	int count=0;

    	try{
    		Subject old=get(subject.getCd(),school);
    		if(old==null){
    			statement=connection.prepareStatement("insert into subject(school_cd, cd, name) values (?, ?, ?)");
    			statement.setString(1, subject.getSchool().getCd());
    			statement.setString(2, subject.getCd());
    			statement.setString(3, subject.getName());}
    		else{
    			statement = connection.prepareStatement("update subject SET name=? where cd=?");
    			statement.setString(1,subject.getName());
    			statement.setString(2,subject.getCd());}
    		count = statement.executeUpdate();}
    	catch(Exception e){throw e;}
    	finally{
            if(statement!=null){
                try{statement.close();}
                catch(SQLException sqle){throw sqle;}}
            if(connection!=null){
                try{connection.close();}
                catch(SQLException sqle){throw sqle;}}}
        if(count>0){flg=true;}
		return flg;
	}

	public boolean delete(Subject subject)throws Exception{
		Connection connection=getConnection();
        PreparedStatement statement=null;
        int count=0;

        try {
            statement=connection.prepareStatement("delete from subject where cd = ?");
            statement.setString(1,subject.getCd());

            count=statement.executeUpdate();}
        catch(Exception e){throw e;}
        finally{
            if(statement!=null){
                try{statement.close();}
                catch(SQLException sqle){throw sqle;}}
            if(connection!=null){
                try{connection.close();}
                catch(SQLException sqle){throw sqle;}}}
        return count>0;
	}

	public boolean exists(String cd, School school)throws Exception{
	    Connection connection=getConnection();
	    PreparedStatement statement=null;
	    ResultSet resultSet=null;
	    boolean exists=false;

	    try {
	        statement=connection.prepareStatement("SELECT COUNT(*) FROM subject WHERE cd = ? AND school_cd = ?");
	        statement.setString(1,cd);
	        statement.setString(2,school.getCd());
	        resultSet=statement.executeQuery();
	        if(resultSet.next()){
	            int count=resultSet.getInt(1);
	            exists=(count>0);}}
	    catch(SQLException e){throw e;}
	    finally{
	        if(resultSet!=null){resultSet.close();}
	        if(statement!=null){statement.close();}
	        if(connection!=null){connection.close();}}

	    return exists;
	}
}