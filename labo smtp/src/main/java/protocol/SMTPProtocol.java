package protocol;

public class SMTPProtocol {
    public final static String CMD_EHLO = "EHLO ";
    public final static String CMD_MAIL_FROM = "MAIL FROM: ";
    public final static String CMD_RCPT_TO = "RCPT TO: ";
    public final static String CMD_DATA = "DATA\r\n";
    public final static String CMD_DATA_END = "\r\n.\r\n";
    public final static String CMD_BYE = "quit\r\n";
}
