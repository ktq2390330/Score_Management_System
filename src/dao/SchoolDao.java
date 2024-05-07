package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.School;

public class SchoolDao extends Dao {

    public School get(String no)throws Exception{
        School school=new School();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rSet=null;

        try{
            connection=getConnection();
            statement=connection.prepareStatement("select * from school where cd = ?");
            statement.setString(1,no);
            rSet=statement.executeQuery();
            if(rSet.next()){
                school.setCd(rSet.getString("cd"));
                school.setName(rSet.getString("name"));
            }else{school=null;}
        }catch(Exception e){throw e;
        }finally {closeResources(connection, statement, rSet);}

        return school;
    }

    private void closeResources(Connection connection, PreparedStatement statement, ResultSet rSet) {
        try{
            if(rSet!=null){rSet.close();}
            if(statement != null){statement.close();}
            if(connection != null){connection.close();}
        }catch(SQLException sqle){sqle.printStackTrace();}
    }
}