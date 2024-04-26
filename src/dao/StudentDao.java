package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
public class StudentDao extends Dao {
	private String baseSql = "select * from student where school_cd=? and deleted=false ";
//	select * from student where school_cd=? and deleted=false
	public Student get(String no) throws Exception {
	    Student student = new Student();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;


	    try {
	        statement = connection.prepareStatement("select * from student where no = ?");
//	        select * from student where no = ? and deleted=false
	        statement.setString(1, no);
	        rSet = statement.executeQuery();
	        SchoolDao schoolDao = new SchoolDao();
	        if (rSet.next()) {
	            student.setNo(rSet.getString("no"));
	            student.setName(rSet.getString("name"));
	            student.setEntYear(rSet.getInt("ent_year"));
	            student.setClassNum(rSet.getString("class_num"));
	            student.setAttend(rSet.getBoolean("is_attend"));
	            student.setSchool(schoolDao.get(rSet.getString("school_cd")));
	        } else {
	            student = null;
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
	    return student;
	}


	private List<Student> postFilter(ResultSet rSet, School school) throws Exception {
	    List<Student> list = new ArrayList<>();
	    try {
	        while (rSet.next()) {
	            Student student = new Student();
	            student.setNo(rSet.getString("no"));
	            student.setName(rSet.getString("name"));
	            student.setEntYear(rSet.getInt("ent_year"));
	            student.setClassNum(rSet.getString("class_num"));
	            student.setAttend(rSet.getBoolean("is_attend"));
	            student.setSchool(school);
	            list.add(student);
	        }
	    } catch (SQLException | NullPointerException e) {
	        e.printStackTrace();
	        // エラーログの出力を行う場合、適切に処理を行います
	    } finally {
	        if (rSet != null) {
	            try {
	                rSet.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }
	    return list;
	}


	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {
	    List<Student> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;
	    String condition = "and ent_year = ? and class_num = ?";
	    String order = "order by no asc";

	    String conditionIsAttend = "";
	    if (isAttend) {
	        conditionIsAttend = "and is_attend = true";
	    }

	    try {
	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
	        statement.setString(1, school.getCd());
	        statement.setInt(2, entYear);
	        statement.setString(3, classNum);
	        rSet = statement.executeQuery();
	        list = postFilter(rSet, school);
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

	public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {
	    List<Student> list = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet rSet = null;
	    String condition = "and ent_year=?";
	    String order = "order by no asc";
	    String conditionIsAttend = "";
	    if (isAttend) {
	        conditionIsAttend = "and is_attend = true";
	    }

	    try {
	        connection = getConnection();
	        statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
	        statement.setString(1, school.getCd());
	        statement.setInt(2, entYear);
	        rSet = statement.executeQuery();
	        list = postFilter(rSet, school);
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

	public List<Student> filter(School school, boolean isAttend) throws Exception {
	    List<Student> list = new ArrayList<>();
	    Connection connection = getConnection();
	    PreparedStatement statement = null;
	    ResultSet rSet = null;
	    String order = " order by no asc";
	    String conditionIsAttend = "";
	    if (isAttend) {
	        conditionIsAttend = "and is_attend = true";
	    }
	    try {
	        statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
	        statement.setString(1, school.getCd());
	        rSet = statement.executeQuery();
	        list = postFilter(rSet, school);
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


    public boolean save(Student student, String schoolCd) throws Exception {
    	Connection connection = getConnection();
    	PreparedStatement statement = null;
    	int count = 0;

    	try {
    		Student old = get(student.getNo());
    		if (old == null) {
    			statement = connection.prepareStatement(
    					"insert into student(no, name, ent_year, class_num, is_attend, school_cd, deleted) VALUES (?, ?, ?, ?, ?, ?,false)");
    			statement.setString(1,  student.getNo());
    			statement.setString(2, student.getName());
    			statement.setInt(3,  student.getEntYear());
    			statement.setString(4,  student.getClassNum());
    			statement.setBoolean(5,  student.isAttend());
    			statement.setString(6,  schoolCd);
    		} else {
    			statement = connection.prepareStatement(
    					"update student set name=?, ent_year=?, class_num=?, is_attend=? where no =?");
    			statement.setString(1,  student.getName());
    			statement.setInt(2,  student.getEntYear());
    			statement.setString(3,  student.getClassNum());
    			statement.setBoolean(4, student.isAttend());
    			statement.setString(5,  student.getNo());
    		}

    		count = statement.executeUpdate();
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

        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String studentNo) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count=0;

        try {
            statement=connection.prepareStatement("update student set deleted=true where no=?");
            statement.setString(1, studentNo);

            count=statement.executeUpdate();
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

        return count>0;
    }

    public boolean exists(String studentNo, String schoolCd) {
        String sql = "SELECT COUNT(*) FROM student WHERE no = ? AND school_cd = ? and deleted=false;";
        try (
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, studentNo);
            pstmt.setString(2, schoolCd);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}