/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.NotesDBException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import services.AccountService;
import services.UserService;

/**
 *
 * @author 747787
 */
public class ResetPasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uuid = request.getParameter("uuid");
        request.setAttribute("uuid", uuid);

        if (uuid == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);

        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/resetpassword.jsp").forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        AccountService ac = new AccountService();
        String url = request.getRequestURL().toString();
        String uuid = request.getParameter("uuid");
        UserService uc = new UserService();
        String password = request.getParameter("password");
        if (uuid == null) {
            uuid = UUID.randomUUID().toString();
            String link = url + "?uuid=" + uuid;
            try {
                if (ac.resetPassword(email, getServletContext().getRealPath("/WEB-INF"), link) == true) {
                    User user = uc.getByEmail(email);
                    user.setResetPasswordUUID(uuid);
                    uc.update(user.getUsername(), user.getPassword(), user.getFirstname(), user.getLastname(), user.getEmail(),user.getResetPasswordUUID());
                    request.setAttribute("sent", "Please check your email for a reset link.");
                    getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);

                } else {
                    request.setAttribute("sent", "Invalid Email Try Again.");
                    getServletContext().getRequestDispatcher("/WEB-INF/reset.jsp").forward(request, response);

                }
            } catch (NotesDBException ex) {
                Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ResetPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if (ac.changePassword(uuid, password) == true) {
                request.setAttribute("sent", "Password Changed.");
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

            }
        }

    }
}
