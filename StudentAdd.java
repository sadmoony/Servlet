package ua.edu.sumdu;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.LinkedList;

@WebServlet(urlPatterns = {"/StudentAdd"})
public class StudentAdd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<Student> students = (List<Student>)session.getAttribute("students");
        
        if (students == null){
            students = new LinkedList<Student>();
            session.setAttribute("students", students);
        }
        
        if (request.getParameter("name") != "" || request.getParameter("surname") != ""){
            Student student = new Student(request.getParameter("name"),
                                          request.getParameter("surname"),
                                          request.getParameter("age"),
                                          request.getParameter("email"),
                                          request.getParameter("group"),
                                          request.getParameter("faculty"));
            students.add(student);
        }
        
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
