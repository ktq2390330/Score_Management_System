package scoremanager.main;

import java.time.LocalDate;
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
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;
public class TestListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		String schoolCd = teacher.getSchoolCdAsString();
		School school = new School();
		school.setCd(schoolCd);

		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subjectStr = request.getParameter("f3");
		String studentNum = request.getParameter("f4");
		String f = request.getParameter("f");
		int entYear = 0;

		String subjectName = null;
		String take = null;
		Subject subject = new Subject();
		StudentDao studentDao = new StudentDao();
		List<Student> students = new ArrayList<>();
		if (f == null || "sj".equals(f)) {
			if (entYearStr != null && !entYearStr.isEmpty()) {
				entYear = Integer.parseInt(entYearStr);
				take = "taking";
			}

			if (subjectStr != null) {
				subject.setCd(subjectStr);
				subjectName = new SubjectDao().get(subject.getCd(), school).getName();
				boolean isAttend = false;
				students = studentDao.filter(teacher.getSchool(),entYear,classNum, isAttend);
			}

			SubjectDao subjectDao = new SubjectDao();
			TestDao testDao = new TestDao();
			ClassNumDao cNumDao = new ClassNumDao();
			List<Test> tests = new ArrayList<>();
			List<String> classNumSet = cNumDao.filter(teacher.getSchool());
			List<Subject> subjectSet = subjectDao.filter(teacher.getSchool());
			List<String> subject_cdSet = getSubjectCdList(subjectSet);
			List<String> subject_nameSet = getSubjectNameList(subjectSet);
			if (entYear != 0 && !classNum.equals("0")) {
				for (int num = 1; num <= 2; num++) {
				    List<Test> testsForNum = testDao.filter(entYear, classNum, subject, num, teacher.getSchool());
				    if (testsForNum != null && !testsForNum.isEmpty()) {
				        for (Test test : testsForNum) {
				            if (test != null) {
				                tests.add(test);
				            }
				        }
				    }
				}
			}

			List<Integer> entYearSet = new ArrayList<>();
			int year = LocalDate.now().getYear();
			for (int i = year - 10; i <= year; i++) {
				entYearSet.add(i);
			}

			request.setAttribute("f1", entYear);
			request.setAttribute("f2", classNum);
			request.setAttribute("f3", subjectStr);
			request.setAttribute("tests", tests);
			request.setAttribute("students",students);
			request.setAttribute("class_num_set", classNumSet);
			request.setAttribute("ent_year_set", entYearSet);
			request.setAttribute("subject_cdset", subject_cdSet);
			request.setAttribute("subject_nameset", subject_nameSet);
			request.setAttribute("subject_name2", subjectName);
			request.setAttribute("take", take);

			request.getRequestDispatcher("test_list.jsp").forward(request, response);

		}else if ("st".equals(f)) {
			take = "taking";
			request.setAttribute("take", take);
			request.setAttribute("studentNum", studentNum);
			request.getRequestDispatcher("TestListStudentExecute.action").forward(request, response);
		}
	}

	public List<String> getSubjectCdList(List<Subject> subjectList) {
		List<String> subjectCdList = new ArrayList<>();
		for (Subject subject : subjectList) {
			subjectCdList.add(subject.getCd());
		}
		return subjectCdList;
	}

	public List<String> getSubjectNameList(List<Subject> subjectList) {
		List<String> subjectNameList = new ArrayList<>();
		for (Subject subject : subjectList) {
			subjectNameList.add(subject.getName());
		}
		return subjectNameList;
	}
}
