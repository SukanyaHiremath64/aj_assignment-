package com.sukanya;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LanguageServlet extends HttpServlet {
    private Map<String, String> greetings = new HashMap<>();
    public void init() {
        greetings.put("English", "Welcome");
        greetings.put("Hindi", "Namaste");
        greetings.put("Kannada", "Namaskara");
        greetings.put("Telugu", "Namaskaram");
        greetings.put("Tamil", "Vanakkam");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        String lang = req.getParameter("lang");
        Cookie c = new Cookie("userLang", lang);
        c.setMaxAge(2592000); 
        res.addCookie(c);
        res.sendRedirect("LanguageServlet");
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String lang = "English";
        Cookie[] cookies = req.getCookies();
        if(cookies != null) {
            for(Cookie c : cookies) if(c.getName().equals("userLang")) lang = c.getValue();
        }
        res.getWriter().println("<h1>" + greetings.get(lang) + "!</h1><a href='index.html'>Back</a>");
    }
}