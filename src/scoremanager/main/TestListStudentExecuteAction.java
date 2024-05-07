package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;
public class TestListStudentExecuteAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			String error = null;

			HttpSession session = request.getSession();
			Teacher teacher = (Teacher) session.getAttribute("user");
			String schoolCd = teacher.getSchoolCdAsString();
			School school = new School();
			school.setCd(schoolCd);

			SubjectDao subjectDao = new SubjectDao();
			TestDao testDao = new TestDao();
			ClassNumDao cNumDao = new ClassNumDao();
			List<String> classNumSet = cNumDao.filter(teacher.getSchool());
			List<Subject> subjectSet = subjectDao.filter(teacher.getSchool());
			List<String> subject_cdSet = getSubjectCdList(subjectSet);
			List<String> subject_nameSet = getSubjectNameList(subjectSet);
			List<Integer> entYearSet = new ArrayList<>();
			int year = LocalDate.now().getYear();
			for (int i = year - 10; i <= year; i++) {
				entYearSet.add(i);
			}

			String take = (String) request.getAttribute("take");
			String studentNum = (String) request.getAttribute("studentNum");
			StudentDao studentDao = new StudentDao();
			String studentName = studentDao.get(studentNum).getName();
			String classNum = studentDao.get(studentNum).getClassNum();
			int entYear = studentDao.get(studentNum).getEntYear();

			List<Test> tests = null;
			List<Test> testsAll = new ArrayList<>();
			Subject subject = new Subject();
			for (String subject_cd : subject_cdSet) {
				for (int num = 1; num <= 2; num++) {

					subject.setCd(subject_cd);
					tests = testDao.filter(entYear, classNum, subject, num, teacher.getSchool());
					if (tests != null && !tests.isEmpty()) {
						for (Test test : tests) {
							if (test != null && !testsAll.contains(test) && test.getStudent().getNo().equals(studentNum)) {
								testsAll.add(test);
							}
						}
					}

				}
			}

			request.setAttribute("take", take);
			request.setAttribute("name", studentName);
			request.setAttribute("num", studentNum);
			request.setAttribute("tests", testsAll);
			request.setAttribute("class_num_set", classNumSet);
			request.setAttribute("ent_year_set", entYearSet);
			request.setAttribute("subject_cdset", subject_cdSet);
			request.setAttribute("subject_nameset", subject_nameSet);
			request.setAttribute("error", error);

			request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
		} catch (NullPointerException e) {
			e.printStackTrace(); // エラーをコンソールに出力する
			String error = "error";

			HttpSession session = request.getSession();
			Teacher teacher = (Teacher) session.getAttribute("user");
			String schoolCd = teacher.getSchoolCdAsString();
			School school = new School();
			school.setCd(schoolCd);

			SubjectDao subjectDao = new SubjectDao();
			ClassNumDao cNumDao = new ClassNumDao();
			List<String> classNumSet = cNumDao.filter(teacher.getSchool());
			List<Subject> subjectSet = subjectDao.filter(teacher.getSchool());
			List<String> subject_cdSet = getSubjectCdList(subjectSet);
			List<String> subject_nameSet = getSubjectNameList(subjectSet);
			List<Integer> entYearSet = new ArrayList<>();
			int year = LocalDate.now().getYear();
			for (int i = year - 10; i <= year; i++) {
				entYearSet.add(i);
			}
			String studentNum = (String) request.getAttribute("studentNum");

			request.setAttribute("num", studentNum);
			request.setAttribute("class_num_set", classNumSet);
			request.setAttribute("ent_year_set", entYearSet);
			request.setAttribute("subject_cdset", subject_cdSet);
			request.setAttribute("subject_nameset", subject_nameSet);
			request.setAttribute("error", error);

			request.getRequestDispatcher("test_list_student.jsp").forward(request, response);
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
