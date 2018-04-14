package config;

import model.mail.Person;

import java.io.IOException;
import java.util.List;

public interface IConfigurationManager {

    void loadAddresses(String filename) throws IOException;

    List<String> loadMessages(String filename) throws IOException;

    List<Person> getVictims();

    List<Person> getCcVictims();

    List<Person> getBccVictims();

    List<String> getMessages();

    int getNumbersOfGroupes();

    String getServerAddress();

    int getServerPort();
}
