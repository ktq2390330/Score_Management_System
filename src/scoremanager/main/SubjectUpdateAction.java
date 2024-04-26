package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学校コードがnullの場合はエラーページにリダイレクト
        if (teacher == null || teacher.getSchool() == null || teacher.getSchool().getCd() == null) {
            response.sendRedirect("error.jsp"); // エラーページのパスを適切なものに置き換えてください
            return;
        }

        School school = teacher.getSchool();

        // リクエストパラメータから更新する科目のコードを取得
        String cd = request.getParameter("cd");

        // 科目情報を取得
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(cd, school);

        // 科目情報が取得できない場合はエラーページにリダイレクト
        if (subject == null) {
            response.sendRedirect("error.jsp"); // エラーページのパスを適切なものに置き換えてください
            return;
        }

        // 科目情報をリクエストスコープに設定
        request.setAttribute("subject", subject);

        // 更新ページにフォワード
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}
