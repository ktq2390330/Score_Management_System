package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import tool.Action;

public class StudentDeleteExecuteAction extends Action{
    @Override
    public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{
        // 学生番号を取得
        String studentNo=req.getParameter("no");

        // 学生データを削除
        StudentDao sDao=new StudentDao();
        sDao.delete(studentNo);

        // 削除の結果を通知するページにフォワード
        req.getRequestDispatcher("student_delete_done.jsp").forward(req, res);
    }
}