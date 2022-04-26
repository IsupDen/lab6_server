package commands;


import util.Request;
import util.Response;

import static util.TextFormat.successText;

public class Exit extends Command{

    public Exit() {
        super("exit", "end the program (without saving it to a file).");
    }

    @Override
    public Response execute(Request command) {
        return new Response(successText("Thank you for working in this program!"));
    }
}
