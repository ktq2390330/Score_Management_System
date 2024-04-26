package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao{
	String baseSql="select * from test where ";

	public Test get(Student student,Subject subject,School school,int no) throws Exception {
	    Test test = new Test();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;


	    try {
	        statement = connection.prepareStatement(baseSql+" student_no=? and subject_cd=? and school_cd=? and no=? ");
	        statement.setString(1, student.getNo());
	        statement.setString(2, subject.getCd());
	        statement.setString(3, school.getCd());
	        statement.setString(4, ""+no);
	        rSet=statement.executeQuery();
	        if (rSet.next()) {
	            test.setClassNum(rSet.getString("class_num"));
	            test.setNo(rSet.getInt("no"));
	            test.setPoint(rSet.getInt("point"));
	            test.setSchool(school);
	            test.setStudent(student);
	            test.setSubject(subject);
	        } else {
	            test = null;
	        }
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (rSet != null) {
	            try {
	                rSet.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }
	    return test;
	}

	private Test postFilter(ResultSet rSet,int entYear, School school) throws Exception {
	    Test test = null;
	    StudentDao studentDao = new StudentDao();
	    SubjectDao subjectDao = new SubjectDao();
	    try {
	        if (rSet.next()) { // ResultSetが空でないことを確認する
	            test = new Test();
	            test.setNo(rSet.getInt("no"));
	            test.setClassNum(rSet.getString("class_num"));
	            test.setPoint(rSet.getInt("point"));
	            test.setSchool(school);
	            test.setStudent(studentDao.get(rSet.getString("student_no")));
	            test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // エラーログの出力を行う場合、適切に処理を行います
	    } finally {
	        if (rSet != null) {
	            try {
	                rSet.close();
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	                // エラーログの出力を行う場合、適切に処理を行います
	            }
	        }
	    }
	    return test;
	}


	public List<Test> filter(int entYear, String classNum,Subject subject,int num,School school) throws Exception {
	    List<Test> list = new ArrayList<>();
	    List<Student> slist=new ArrayList<>();
	    StudentDao stDao=new StudentDao();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;

	    try {
	    	slist=stDao.filter(school, entYear, classNum, true);
	    	for(Student st:slist){
	    		statement=connection.prepareStatement(baseSql+" student_no=? and subject_cd=? and school_cd=? and no=? and class_num=? ");
	    		statement.setString(1, st.getNo());
		        statement.setString(2, subject.getCd());
		        statement.setString(3, school.getCd());
		        statement.setString(4, ""+num);
		        statement.setString(5, classNum);
		        rSet=statement.executeQuery();
		        Test result=postFilter(rSet, entYear, school);
		        list.add(result);
	    	}
	    } catch (Exception e) {
	        throw e;
	    } finally {
	        if (rSet != null) {
	            try {
	                rSet.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }
	    return list;
	}

	public boolean save(List<Test> list) throws Exception {
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	int count = 0;

    	try {
    		for (Test t:list){
	    		Test old = get(t.getStudent(),t.getSubject(),t.getSchool(),t.getNo());
	    		if (old == null) {
	    			statement = connection.prepareStatement(
	    					"insert into test(student_no, subject_cd, school_cd, no, point, class_num) values (?, ?, ?, ?, ?, ?)");
	    			statement.setString(1,  t.getStudent().getNo());
	    			statement.setString(2, t.getSubject().getCd());
	    			statement.setString(3,  t.getSchool().getCd());
	    			statement.setInt(4,  t.getNo());
	    			statement.setInt(5,  t.getPoint());
	    			statement.setString(6, t.getClassNum());
	    		} else {
	    			statement = connection.prepareStatement(
	    					"update test set point=? where class_num=? and student_no=? and subject_cd=?");
	    			statement.setInt(1,  t.getPoint());
	    			statement.setString(2,  t.getClassNum());
	    			statement.setString(3,  t.getStudent().getNo());
	    			statement.setString(4, t.getSubject().getCd());
	    		}

	    		count = statement.executeUpdate();
    		}
    	} catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            // コネクションのクローズも行う
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        if (count == list.size()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(List<Test> list) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
        	for(Test t:list){
	            statement = connection.prepareStatement("delete from test where student_no=? and subject_no=?");
	            statement.setString(1, t.getStudent().getNo());
	            statement.setString(2, t.getSubject().getCd());

	            count = statement.executeUpdate();
        	}
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count == list.size();
    }
}