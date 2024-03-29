package servlets;

import db.DBManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Tasks;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Tasks> tasksList = DBManager.getTasks();
            req.setAttribute("tasksList", tasksList);
            req.getRequestDispatcher("home.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String deadlineDate = req.getParameter("deadlineDate");
            Tasks task = new Tasks(name, description, deadlineDate);
            DBManager.addTask(task);
            resp.sendRedirect("/home");

    }
}
