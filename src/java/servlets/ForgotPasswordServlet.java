/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.NotesDBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.AccountService;

/**
 *
 * @author 747787
 */
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountService ac = new AccountService();
        try {
            if ((ac.forgotPassword(email, getServletContext().getRealPath("/WEB-INF")) == true)) {
                request.setAttribute("sent", "Please check your email for the password.");
                getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);

            } else {
                request.setAttribute("sent", "Invalid Email Try Again.");
                getServletContext().getRequestDispatcher("/WEB-INF/forgot.jsp").forward(request, response);

            }
        } catch (NotesDBException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
