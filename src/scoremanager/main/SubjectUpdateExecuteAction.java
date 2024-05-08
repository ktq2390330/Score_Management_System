package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action{
    @Override
    public void execute(HttpServletRequest request,HttpServletResponse response)throws Exception{
        // セッションからユーザー情報を取得
        HttpSession session=request.getSession();
        Teacher teacher=(Teacher) session.getAttribute("user");

        // リクエストパラメータから科目情報を取得
        String cd=request.getParameter("cd");
        String name=request.getParameter("name");

        // 科目情報を作成してデータベースに保存
        Subject subject=new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        SubjectDao subjectDao=new SubjectDao();

        // 科目を取得して存在を確認
        Subject existingSubject=subjectDao.get(cd,teacher.getSchool());

        if(existingSubject==null){
            // 存在しない場合はエラーメッセージを設定して更新ページにフォワード
            request.setAttribute("errorMessage","科目が存在しません");
            // 科目情報をリクエストスコープにセットしてフォワード
            request.setAttribute("subject",subject);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);}

        // 存在する場合は科目情報を更新
        subjectDao.save(subject,teacher.getSchool());

        // 更新完了ページにリダイレクト
        response.sendRedirect("subject_update_done.jsp");
    }
}