package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // リクエストパラメータから削除する科目のコードを取得
        String cd = request.getParameter("cd");

        // 科目を削除
        SubjectDao subjectDao = new SubjectDao();
        Subject subject = new Subject();
        subject.setCd(cd);
        subjectDao.delete(subject);

        // 削除後は科目一覧ページにリダイレクト
        response.sendRedirect("subject_delete_done.jsp");
    }
}
