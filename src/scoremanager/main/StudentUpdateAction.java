package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 学生の学籍番号を取得
        String no = request.getParameter("no");

        // 学生の情報を取得
        StudentDao studentDao = new StudentDao();
        Student student = studentDao.get(no);

        // 学生情報をリクエストスコープに設定
        request.setAttribute("student", student);

        // ClassNumDaoをインスタンス化
        ClassNumDao classNumDao = new ClassNumDao();

        // ClassNumDaoのfilterメソッドを実行してクラス番号のリストを取得
        List<String> classNumbers = classNumDao.filter(student.getSchool());

        // クラス番号のリストをリクエストスコープに設定
        request.setAttribute("classNumbers", classNumbers);

        // 更新ページにフォワード
        request.getRequestDispatcher("student_update.jsp").forward(request, response);
    }
}
