package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;


public class StudentListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");

        int entYear = 0;
        boolean isAttend = false;

        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (isAttendStr != null && !isAttendStr.isEmpty()) {
            isAttend = true;
        }

        StudentDao studentDao = new StudentDao();
        ClassNumDao cNumDao = new ClassNumDao();
        List<Student> students;
        List<String> classNumSet = cNumDao.filter(teacher.getSchool());


        if (entYear != 0 && !classNum.equals("0")) {
            students = studentDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = studentDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            students = studentDao.filter(teacher.getSchool(), isAttend);
        } else {
            Map<String, String> errors = new HashMap<>();
            errors.put("f1", "クラスを指定する人は入学年度も確認してください");
            request.setAttribute("errors", errors);
            students = studentDao.filter(teacher.getSchool(), isAttend);
        }

        List<Integer> entYearSet = new ArrayList<>();
        int year = LocalDate.now().getYear();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }


        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttendStr);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classNumSet);
        request.setAttribute("ent_year_set", entYearSet);

        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }

}