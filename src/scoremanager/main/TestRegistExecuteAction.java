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
		//送信ボタンの値を出力
		String submitButtonValue = req.getParameter("submitButton");
		// セッションからユーザー情報を取得
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String schoolCd = teacher.getSchoolCdAsString();

		// フォームから送信されたデータを取得
		String[] Nos = req.getParameterValues("no");
		String[] classNums = req.getParameterValues("classNum");
		String[] studentNos = req.getParameterValues("student.no");
		String[] subjectCds = req.getParameterValues("subject.cd");
		String[] points = req.getParameterValues("point");
		String[] deleteTests = req.getParameterValues("deleteTest");

		String delete_test_no = req.getParameter("no2");
		String delete_subject_cd = req.getParameter("subject.cd2");
		List<Test> deletetests = new ArrayList<>();
		// 取得した削除する生徒のデータをテストオブジェクトに変換してリストに追加
		if (deleteTests != null) {
			for (int i = 0; i < deleteTests.length; i++) {
				Test deleteTest = new Test();
		        Student deleteStudent = new Student();
		        Subject deleteSubject = new Subject();

				deleteStudent.setNo(deleteTests[i]);
				deleteTest.setStudent(deleteStudent);
				deleteSubject.setCd(delete_subject_cd);
				deleteTest.setSubject(deleteSubject);
				deleteTest.setNo(Integer.parseInt(delete_test_no));

				deletetests.add(deleteTest);
			}
		}

		// 取得した登録する生徒のデータをテストオブジェクトに変換してリストに追加
		List<Test> tests = new ArrayList<>();
		if (classNums != null) {
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
				test.setNo(Integer.parseInt(Nos[i]));
				test.setPoint(Integer.parseInt(points[i]));

				// deletetests 内に同じ学生番号のデータが存在しない場合にのみ tests に追加
				boolean found = false;
				for (Test deleteTest : deletetests) {
					if (deleteTest.getStudent().getNo().equals(studentNos[i])) {
						found = true;
						break;
					}
				}
				if (!found) {
					tests.add(test);
				}
			}
		}

		TestDao testDao = new TestDao();

		if (deletetests != null && !deletetests.isEmpty()) {
		    testDao.delete(deletetests);
		}

		if (tests != null && !tests.isEmpty()) {
			testDao.save(tests);
		}


		if (submitButtonValue != null) {
		    if (submitButtonValue.equals("登録して終了")) {
		        // 登録して終了ボタンがクリックされた場合の処理
		    	req.getRequestDispatcher("test_regist_done.jsp").forward(req, res);
		    } else if (submitButtonValue.equals("登録して再度入力")) {
		        // 登録して再度入力ボタンがクリックされた場合の処理
		    	res.sendRedirect("TestRegist.action");
		    }
		}
	}
}