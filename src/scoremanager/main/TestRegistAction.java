package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;
public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String subjectStr = request.getParameter("f3");
        String numStr = request.getParameter("f4");
        int entYear = 0;
        int num = 0;
        Subject subject = new Subject();
        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }
        if (numStr != null) {
            num = Integer.parseInt(numStr);
        }
        if (subjectStr != null) {
        	subject.setCd(subjectStr);
        }
        SubjectDao subjectDao = new SubjectDao();
        TestDao testDao = new TestDao();
        ClassNumDao cNumDao = new ClassNumDao();
        List<Test> tests = null;
        List<String> classNumSet = cNumDao.filter(teacher.getSchool());
        List<Subject> subjectSet = subjectDao.filter(teacher.getSchool());
        List<String> subject_cdSet = getSubjectCdList(subjectSet);
        List<String> subject_nameSet = getSubjectNameList(subjectSet);
        if (entYear != 0 && !classNum.equals("0")) {
            tests = testDao.filter(entYear, classNum, subject, num, teacher.getSchool());
        }

        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }


        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", subjectStr);
        request.setAttribute("f4", num);
        request.setAttribute("tests", tests);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);
        request.setAttribute("subject_cdset", subject_cdSet);
        request.setAttribute("subject_nameset", subject_nameSet);

        request.getRequestDispatcher("test_regist.jsp").forward(request, response);
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
