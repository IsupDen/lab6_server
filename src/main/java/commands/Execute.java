package commands;

import util.Request;
import util.Response;

public class Execute extends Command{

    public Execute() {
        super("execute_script", "Read and execute script from entered file.");
    }

    @Override
    public Response execute(Request command) {return null;}
}
