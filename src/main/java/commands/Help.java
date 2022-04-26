package commands;

import util.Request;
import util.Response;

import java.util.Map;

/**
 * @command "display help for available commands."
 */

public class Help extends Command{

    private final Map<String, Command> commandsInfo;

    public Help(Map<String, Command> commands) {
        super("help" , "display help for available commands.");
        commandsInfo = commands;
    }


    @Override
    public Response execute(Request command) {

        StringBuilder sb = new StringBuilder();
        sb.append("List of commands:\n");


        commandsInfo.keySet().stream().map(description -> commandsInfo.get(description).getDescription() + "\n").forEach(sb::append);

        return new Response(sb.toString());
    }
}
