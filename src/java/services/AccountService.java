/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import database.NotesDBException;
import database.UserDB;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.User;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author awarsyle
 */
public class AccountService {

    public User login(String username, String password, String path) {
        try {
            UserDB userDB = new UserDB();
            User user = userDB.getUser(username);

            if (user.getPassword().equals(password)) {
                // successful login
                Logger.getLogger(AccountService.class.getName())
                        .log(Level.INFO, "User {0} logged in.", user.getUsername());

                // send email upon successful login
                //GmailService.sendMail(user.getEmail(), "Notes App Login",
                //        "Hi " + user.getFirstname() + "\nYou just logged in.", false);
                String email = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("date", ((new java.util.Date())).toString());

                GmailService.sendMail(email, subject, template, tags);

                return user;
            }
        } catch (Exception e) {

        }

        return null;
    }

    public boolean forgotPassword(String email, String path) throws NotesDBException {
        UserDB userDB = new UserDB();
        User user = userDB.getUserByEmail(email);
        if(user.getEmail().equals(email)){
                
                String subject = "Notes App Forgot Password";
                String template = path + "/emailtemplates/forgotpass.html";

                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstname());
                tags.put("lastname", user.getLastname());
                tags.put("username",user.getUsername());
                tags.put("password", user.getPassword());

                GmailService.sendMail(email, subject, template, tags);

            
            return true;
        }
        return false;
    }
}
