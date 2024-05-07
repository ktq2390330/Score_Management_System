package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecuteAction extends Action{

    @Override
    public void execute(HttpServletRequest req,HttpServletResponse res)throws Exception{
        // セッションからユーザー情報を取得
        HttpSession session=req.getSession();
        Teacher teacher=(Teacher) session.getAttribute("user");
        String schoolCd=teacher.getSchoolCdAsString();

        // リクエストパラメータから学生情報を取得
        Student student=new Student();
        student.setEntYear(Integer.parseInt(req.getParameter("entyear")));
        student.setNo(req.getParameter("no"));
        student.setName(req.getParameter("name"));
        student.setClassNum(req.getParameter("classnum"));

        // 在学中の情報を取得
        // 新規登録時は在学中に関する情報がないので、デフォルト値を設定
        boolean isAttend=true;
        student.setAttend(isAttend);

        // 学生情報をデータベースに保存
        StudentDao studentDao=new StudentDao();

        // 同じ学生番号のデータが存在するか確認
        boolean studentExists=studentDao.exists(student.getNo(),schoolCd);

        if(studentExists){
            // 学校コードを使用して、対応するクラス番号の一覧を取得
            ClassNumDao cNumDao=new ClassNumDao();
            List<String> classNumList=cNumDao.filter(teacher.getSchool());

            // 取得したクラス番号の一覧をリクエスト属性にセット
            req.setAttribute("classNumList",classNumList);

            // 今日の年を取得
            int currentYear=LocalDate.now().getYear();

            // 10年前から10年後までの年のリストを生成
            List<Integer> yearList=new ArrayList<>();
            for(int i=currentYear-10;i<=currentYear+10;i++){yearList.add(i);}

            // 年のリストをリクエスト属性にセット
            req.setAttribute("yearList",yearList);

            // 同じ学生番号のデータが存在する場合、エラーメッセージを設定してstudent_create.jspに転送
        	req.setAttribute("errorMessage","同じ学生番号のデータが既に存在します");
            req.getRequestDispatcher("student_create.jsp").forward(req, res);}
        else{
            // 同じ学生番号のデータが存在しない場合、学生情報を保存して処理完了を通知するページにフォワード
            studentDao.save(student,schoolCd);
            req.getRequestDispatcher("student_create_done.jsp").forward(req, res);}
    }
}