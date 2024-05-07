package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)throws Exception{
		// セッションからユーザー情報を取得
        HttpSession session=req.getSession();
        Teacher teacher=(Teacher) session.getAttribute("user");
        String schoolCd=teacher.getSchoolCdAsString();

		// リクエストパラメータから学生情報を取得
		Student student=new Student();
		student.setEntYear(Integer.parseInt(req.getParameter("entyear")));
		student.setNo(req.getParameter("no"));
		student.setName(req.getParameter("name"));
		student.setClassNum(req.getParameter("classnum"));

		// 在学中の情報を取得
		String isAttendParam=req.getParameter("isattend");
		boolean isAttend="true".equals(isAttendParam);
		student.setAttend(isAttend);

		// 学生情報をデータベースに保存
		new StudentDao().save(student,schoolCd);

		// 処理完了を通知するページにフォワード
		req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}
}