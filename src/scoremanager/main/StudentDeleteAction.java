package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentDeleteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	// 学生の学籍番号を取得
        String no = request.getParameter("no");

        // 学生の情報を取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);

        // 学生情報をリクエストスコープに設定
        request.setAttribute("student", student);

        // 更新ページにフォワード
        request.getRequestDispatcher("student_delete.jsp").forward(request, response);
    }
}