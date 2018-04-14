package protocol;

import model.prank.Prank;

import java.io.IOException;

/**
 * @author olivier kopp, florent piller
 */

public interface IClient {

    public void sendPrank(Prank p) throws IOException;

}
