package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao{
    public List<String> filter(School school)throws Exception{
        List<String> list=new ArrayList<>();
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet rSet=null;

        try{
            connection=getConnection();
            statement=connection.prepareStatement("select class_num from class_num where school_cd=? order by class_num");
            statement.setString(1,school.getCd());
            rSet=statement.executeQuery();

            while(rSet.next()){list.add(rSet.getString("class_num"));}
        }catch(Exception e){throw e;}
         finally{closeResources(connection, statement, rSet);}

        return list;
    }

    private void closeResources(Connection connection,PreparedStatement statement,ResultSet rSet){
        try{if(rSet!=null){rSet.close();}}
        catch(SQLException sqle){sqle.printStackTrace();}

        try{if(statement!=null){statement.close();}}
        catch (SQLException sqle){sqle.printStackTrace();}

        try{if(connection!=null){connection.close();}}
        catch (SQLException sqle){sqle.printStackTrace();}
    }
}