package info.devloper.Placement.Mail;

import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Shivam Patel on 25-03-2017.
 */

public class Mail extends javax.mail.Authenticator {

    private String _user;
    private String _pass;

    private String[] _to;
    private String _from;

    private String _port;
    private String _sport;

    private String _host;

    private String _subject;
    private String _body;

    private boolean _auth;

    private boolean _debuggable;

    private Multipart _multipart;

    public Mail() {
        _host = "smtp.gmail.com"; // default smtp server
        _port = "465"; // default smtp port
        _sport = "465"; // default socketfactory port

        _user = "shivam2296.ps@gmail.com"; // username
        _pass = "8128shivam"; // password
        _from = "shivam2296.ps@gmail.com"; // email sent from
        _subject = ""; // email subject
        _body = ""; // email body

        _debuggable = false; // debug mode on or off - default off
        _auth = true; // smtp authentication - default on

        _multipart = new MimeMultipart();

// There is something wrong with MailCap, javamail can not find a handler for the multipart
// /mixed part, so this bit needs to be added.
        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.Mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.Mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.Mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.Mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.Mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
    }

    public Mail(String user, String pass) {
        this();

        _user = user;
        _pass = pass;
    }

    public boolean send() throws Exception {
        Properties props = _setProperties();

        if(!_user.equals("") && !_pass.equals("") && _to.length > 0 && !_from.equals("") &&
                !_subject.equals("") && !_body.equals("")) {

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("shivam2296.ps@gmail.com","8128shivam");
                }
            });
            SMTPAuthenticator authentication = new SMTPAuthenticator();
            javax.mail.Message msg = new MimeMessage(Session
                    .getDefaultInstance(props, authentication));
            msg.setFrom(new InternetAddress(_from));

            InternetAddress[] addressTo = new InternetAddress[_to.length];
            for (int i = 0; i < _to.length; i++) {
                addressTo[i] = new InternetAddress(_to[i]);
            }
            msg.setRecipients(MimeMessage.RecipientType.TO, addressTo);

            msg.setSubject(_subject);
            msg.setSentDate(new Date());

// setup message body
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(_body);
            _multipart.addBodyPart(messageBodyPart);

// Put parts in message
            msg.setContent(_multipart);

// send email
            String protocol = "smtp";
            props.put("Mail." + protocol + ".auth", "true");
            Transport t = session.getTransport(protocol);
            try {
                t.connect("smtp.gmail.com","shivam2296.ps@gmail.com","8128shivam");
                t.sendMessage(msg, msg.getAllRecipients());
            } finally {
                t.close();
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(_user, _pass);
    }

    private Properties _setProperties() {
        Properties props = new Properties();

        props.put("Mail.smtp.host", _host);

        if(_debuggable) {
            props.put("Mail.debug", "true");
        }

        if(_auth) {
            props.put("Mail.smtp.auth", "true");
        }

        props.put("Mail.smtp.port", _port);
        props.put("Mail.smtp.starttls.enable", "true");
        props.put("Mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("Mail.smtp.ssl.enable",true);

        return props;
    }

    // the getters and setters
    public String getBody() {
        return _body;
    }

    public void setBody(String _body) {
        this._body = _body;
    }
    public void setTo(String[] to) {
        this._to = to;
    }
    public void setFrom(String from) {
        this._from = from;
    }
    public void setSubject(String subject) {
        this._subject = subject;
    }
}
