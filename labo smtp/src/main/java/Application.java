import config.ConfigurationManager;
import model.prank.Prank;
import model.prank.PrankGenerator;
import protocol.ClientImpl;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) throws IOException {
        ConfigurationManager cm = new ConfigurationManager();
        PrankGenerator pg = new PrankGenerator(cm);

        ClientImpl client = new ClientImpl(cm.getServerAddress(), cm.getServerPort());
        List<Prank> pranks = pg.generatePranks();
        for(Prank p : pranks){
            client.sendPrank(p);
        }
    }
}
