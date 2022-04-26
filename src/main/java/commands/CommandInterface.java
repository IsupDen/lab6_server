package commands;

import util.Request;
import util.Response;

public interface CommandInterface {
    String getDescription();
    Response execute(Request request);
}
