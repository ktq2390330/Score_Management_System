package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");

        TeacherDao teacherDao = new TeacherDao();
        School school = teacher.getSchool();
        List<Teacher> teachers = teacherDao.filter(school);//filterを設定する

        request.setAttribute("teachers", teachers);

        request.getRequestDispatcher("teacher_list.jsp").forward(request, response);
    }
}