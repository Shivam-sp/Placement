package info.devloper.Placement.Mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Shivam Patel on 25-03-2017.
 */

public class SMTPAuthenticator extends Authenticator
{
    public SMTPAuthenticator() {

        super();
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
        String username = "shivam2296.ps@gmail.com";
        String password = "8128shivam";
        if ((username != null) && (username.length() > 0) && (password != null)
                && (password.length() > 0)) {

            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}
