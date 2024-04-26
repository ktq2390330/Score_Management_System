package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションからユーザー情報を取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 学校コードを使用して、対応するクラス番号の一覧を取得
        ClassNumDao cNumDao = new ClassNumDao();
        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        // 取得したクラス番号の一覧をリクエスト属性にセット
        request.setAttribute("classNumList", classNumList);

        // 今日の年を取得
        int currentYear = LocalDate.now().getYear();

        // 10年前から10年後までの年のリストを生成
        List<Integer> yearList = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear + 10; i++) {
            yearList.add(i);
        }

        // 年のリストをリクエスト属性にセット
        request.setAttribute("yearList", yearList);

        // StudentCreate.jsp にフォワード
        request.getRequestDispatcher("student_create.jsp").forward(request, response);
    }
}