package config;

import model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * this class implement the configuration manager, which is used to manage all config :
 *  - smtp server address/port
 *  - number of groups that will be pranked
 *  - list of all kind of victims(normal/CC/BCC)
 *
 * @author olivier kopp, florent piller
 */

public class ConfigurationManager  implements IConfigurationManager{

    String serverAddress;
    int serverPort;
    int numberOfGroups;
    List<Person> victims = null;
    List<Person> ccVictims = null;
    List<Person> bccVictims = null;
    List<String> messages = null;

    private final static String separator = "=="; //separator for message

    public ConfigurationManager() throws IOException {
        loadAddresses("./config/victimsConfig.utf8");
        messages = loadMessages("./config/messagesConfig.utf8");
        loadProperties("./config/configSMTP.utf8");
    }

    /**
     * load addresses and store them in the differents victims lists
     * @param filename
     * @return
     * @throws IOException
     */
    public void loadAddresses(String filename) throws IOException {
        victims = new ArrayList<Person>();
        ccVictims = new ArrayList<Person>();
        bccVictims = new ArrayList<Person>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String currentLine;
        currentLine = br.readLine();
        if(currentLine == null){
            return;
        }
        if(!currentLine.equals("victims")){
            System.err.println("no victims found, please insure that atleast one victim is in config file");
            return;
        }
        currentLine = br.readLine();
        while (currentLine != null && !currentLine.equals("ccVictims") && !currentLine.equals("bccVictims")){
            victims.add(new Person(currentLine));
            currentLine = br.readLine();
        }
        if(currentLine == null){
            return;
        }
        if(currentLine.equals("ccVictims")){
            currentLine = br.readLine();
            while (currentLine != null && !currentLine.equals("bccVictims")){
                ccVictims.add(new Person(currentLine));
                currentLine = br.readLine();
            }
        }
        if(currentLine == null){
            return;
        }
        if(currentLine.equals("bccVictims")){
            currentLine = br.readLine();
            while (currentLine != null){
                bccVictims.add(new Person(currentLine));
                currentLine = br.readLine();
            }
        }
        br.close();
    }

    /**
     * Load all message in the config file and store them in a list
     * @param filename
     * @return
     * @throws IOException
     */
    public List<String> loadMessages(String filename) throws IOException {
        ArrayList<String> retList = new ArrayList<String>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String currentLine;
        StringBuilder currentMessage = new StringBuilder();
        currentLine = br.readLine();
        while (currentLine != null) {
            while (currentLine != null && !currentLine.equals(separator)) {
                currentMessage.append(currentLine + "\r\n");
                currentLine = br.readLine();
            }
            retList.add(currentMessage.toString());
            currentMessage = new StringBuilder();
            currentLine = br.readLine();
        }
        br.close();
        return retList;
    }

    /**
     * load properties from configSMTP file and store them locally
     * @param filename
     * @throws IOException
     */
    public void loadProperties(String filename) throws IOException {
        FileInputStream fs = new FileInputStream(filename);
        Properties p = new Properties();
        p.load(fs);
        this.serverAddress = p.getProperty("serverAddress");
        this.serverPort = Integer.parseInt(p.getProperty("serverPort"));
        this.numberOfGroups = Integer.parseInt(p.getProperty("numberOfGroups"));
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<Person> getCcVictims() {
        return ccVictims;
    }

    public List<Person> getBccVictims() {
        return bccVictims;
    }

    public List<String> getMessages(){
        return messages;
    }

    public int getNumbersOfGroupes() {
        return numberOfGroups;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

}
