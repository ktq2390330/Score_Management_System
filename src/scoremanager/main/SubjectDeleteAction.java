package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
    	// セッションからユーザー情報を取得
        HttpSession session=request.getSession();
        Teacher teacher=(Teacher)session.getAttribute("user");

    	// 科目コードを取得
        String cd=request.getParameter("cd");

        // 科目情報を取得してリクエストスコープに設定
        SubjectDao subjectDao=new SubjectDao();
        Subject subject=subjectDao.get(cd, teacher.getSchool());
        request.setAttribute("subject",subject);

        // 科目削除ページにフォワード
        request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
    }
}
