
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import tool.Action;


public class TestRegistExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// セッションからユーザー情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        String schoolCd = teacher.getSchoolCdAsString();

		// フォームから送信されたデータを取得
        String[] classNums = req.getParameterValues("classNum");
        String[] studentNos = req.getParameterValues("student.no");
        String[] subjectCds = req.getParameterValues("subject.cd");
        String[] points = req.getParameterValues("point");

        // 取得したデータをテストオブジェクトに変換してリストに追加
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < classNums.length; i++) {
            Test test = new Test();
            Student student = new Student();
            Subject subject = new Subject();
            School school = new School();
            student.setNo(studentNos[i]);
            test.setStudent(student);
            subject.setCd(subjectCds[i]);
            test.setSubject(subject);
            test.setClassNum(classNums[i]);
            school.setCd(schoolCd);
            test.setSchool(school);
            test.setPoint(Integer.parseInt(points[i]));
            tests.add(test);
        }

        new TestDao().save(tests);

        // 処理完了を通知するページにフォワード
        req.getRequestDispatcher("student_update_done.jsp").forward(req, res);
	}
}

