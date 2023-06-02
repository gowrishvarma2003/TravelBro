package com.groupnine.travelbuddy;

import com.groupnine.travelbuddy.TBBase.TBBaseConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
@WebServlet(name="sos", value="/sos_button")
public class SOS extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String senderEmail = "buddy.travel@outlook.com";
        String senderPassword = "buddy_travel_srmap";
        String loginusermail = (String) req.getSession().getAttribute("user_email");
        String recipientEmail=null;
        String loginusername=null;
        String loginusermobile=null;
        try {
            Connection con = new TBBaseConnection().getConnection();
            PreparedStatement pstmt = con.prepareStatement("SELECT fullname,alt_email,mobile FROM bt_base.users where email=?");
            pstmt.setString(1,loginusermail);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            loginusername = rs.getString("fullname");
            recipientEmail = rs.getString("alt_email");
            loginusermobile = rs.getString("mobile");
            pstmt.close();
            con.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        // SMTP server details for Outlook/Office 365
        String host = "smtp.office365.com";
        int port = 587;

        // Create properties object and configure SMTP server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create session with authenticator
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };
        Session session = Session.getInstance(props, auth);
        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Alert from Travel Buddy!");
            message.setText(loginusername+" is in Emergency!\nContact: "+loginusermobile+"\n\n\nThis was an auto generated mail by Travel Buddy application in view of users safety\n\nRegards,\nTravel Buddy\n");
            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/index.jsp");
    }
}
