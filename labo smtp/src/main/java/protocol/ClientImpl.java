package protocol;

import model.mail.Person;
import model.prank.Prank;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * this class implement a client of the prank application
 *
 * @author olivier kopp, florent piller
 */
public class ClientImpl implements IClient {

    private static final Logger LOG = Logger.getLogger(ClientImpl.class.getName());

    private Socket socket;
    private BufferedReader br;
    private PrintWriter pw;
    private String serverAddress;
    private int serverPort;

    public ClientImpl(String serverAddress, int serverPort) throws IOException {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    /**
     * this method send a prank according to configuration using the smtp protocol
     * @param p
     * @throws IOException
     */
    public void sendPrank(Prank p) throws IOException {
        if(p.getSender() == null || p.getReceivers().size() == 0){
            LOG.info("a prank need a sender and at least a receiver\n");
            return;
        }
        connect();

        sendMessage(SMTPProtocol.CMD_MAIL_FROM + p.getSender().getAddress() + "\r\n");
        LOG.info(br.readLine());

        for (Person person : p.getReceivers()){
            sendMessage(SMTPProtocol.CMD_RCPT_TO + person.getAddress() + "\r\n");
            LOG.info(br.readLine());
        }

        for (Person person : p.getReceiversCC()){
            sendMessage(SMTPProtocol.CMD_RCPT_TO + person.getAddress() + "\r\n");
            LOG.info(br.readLine());
        }

        for (Person person : p.getReceiversBCC()){
            sendMessage(SMTPProtocol.CMD_RCPT_TO + person.getAddress() + "\r\n");
            LOG.info(br.readLine());
        }

        sendMessage(SMTPProtocol.CMD_DATA);
        LOG.info(br.readLine());

        sendMessage("From: " + p.getSender().getAddress() + "\r\n");
        sendMessage("To: " + p.getReceivers().get(0).getAddress());
        for (int i = 1; i < p.getReceivers().size(); i++){
            sendMessage(", " + p.getReceivers().get(i).getAddress());
        }
        sendMessage("\r\n");

        if(!p.getReceiversCC().isEmpty()) {
            sendMessage("Cc: " + p.getReceiversCC().get(0).getAddress());
            for (int i = 1; i < p.getReceiversCC().size(); i++) {
                sendMessage(", " + p.getReceiversCC().get(i).getAddress());
            }
            sendMessage("\r\n");
        }

        if(!p.getReceiversBCC().isEmpty()) {
            sendMessage("Bcc: " + p.getReceiversBCC().get(0).getAddress());
            for (int i = 1; i < p.getReceiversBCC().size(); i++) {
                sendMessage(", " + p.getReceiversBCC().get(i).getAddress());
            }
            sendMessage("\r\n");
        }

        sendMessage(p.getMessage() + SMTPProtocol.CMD_DATA_END);

        LOG.info(br.readLine());

        disconnect();

    }

    /**
     * this method send a messaage to the server
     * @param message
     */
    public void sendMessage(String message){
        pw.print(message);
        pw.flush();
    }

    /**
     * this method connect the client to the smtp server configured in the configSMTP file
     * and send the ehlo command
     * @throws IOException
     */
    public void connect() throws IOException {
        socket = new Socket(serverAddress, serverPort);
        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

        LOG.info(br.readLine());

        String currentLine;
        sendMessage(SMTPProtocol.CMD_EHLO + "prankApplication" + "\r\n");
        do{
            currentLine = br.readLine();
            LOG.info(currentLine);
        }
        while (!currentLine.startsWith("250 "));
    }

    /**
     * this method is used to disconnect the client by sending the command BYE
     * @throws IOException
     */
    public void disconnect() throws IOException {
        sendMessage(SMTPProtocol.CMD_BYE);
        socket.close();
        br.close();
        pw.close();
    }
}
