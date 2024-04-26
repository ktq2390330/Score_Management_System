package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// セッションからユーザー情報を取得
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// リクエストパラメータから科目情報を取得
		String cd = request.getParameter("cd");
		String name = request.getParameter("name");

		// 文字数が3でない場合はエラーメッセージを設定して科目登録ページにフォワード
		if (cd.length() != 3) {
			request.setAttribute("errorMessage", "科目コードは3文字で入力してください");
			request.getRequestDispatcher("subject_create.jsp").forward(request, response);
			return;
		}

		// 科目コードの重複をチェック
        SubjectDao subjectDao = new SubjectDao();
        boolean cdExists = subjectDao.exists(cd, teacher.getSchool());

        if (cdExists) {
            // 重複がある場合はエラーメッセージを設定して科目登録ページにフォワード
            request.setAttribute("errorMessage", "科目コードが重複しています");
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

		// 科目情報を作成してデータベースに保存
		Subject subject = new Subject();
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());

		subjectDao.save(subject, teacher.getSchool());

		// 科目登録完了ページにリダイレクト
		response.sendRedirect("subject_create_done.jsp");
	}
}
