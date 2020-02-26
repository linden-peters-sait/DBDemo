/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lpeters
 */
public class NoteServlet extends HttpServlet {



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dbURL = "jdbc:mysql://localhost:3306/dbdemo";
            String username = "username";
            String password = "password";
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                dbURL, username, password);
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `note`");
            ResultSet result = stmt.executeQuery();
            ArrayList<String> notes = new ArrayList<String>();
            while (result.next()) {
                int noteId = result.getInt(1);
                String noteContent = result.getString(2);
                System.out.println("Note #"+noteId+": \""+noteContent+"\"");
                notes.add(noteContent);
            }
            request.setAttribute("notes", notes);
        } catch(SQLException e) {
            for (Throwable t : e)
                t.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/WEB-INF/note.jsp").forward(request,response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fldNote = request.getParameter("fldNote");
        if (fldNote != null && !fldNote.equals("")) {
            try {
                String dbURL = "jdbc:mysql://localhost:3306/dbdemo";
                String username = "username";
                String password = "password";
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(
                    dbURL, username, password);
                PreparedStatement stmt = connection.prepareStatement("INSERT INTO `note` (`content`) VALUES (?)");
                stmt.setString(1, fldNote);
                int result = stmt.executeUpdate();
                System.out.println("Rows Affected: "+result);
            } catch(SQLException e) {
                for (Throwable t : e)
                    t.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("note");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
