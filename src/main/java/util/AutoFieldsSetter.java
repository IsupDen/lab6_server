package util;

import java.time.LocalDateTime;

public class AutoFieldsSetter {

    private int lastId;

    public AutoFieldsSetter(int lastId) {this.lastId = lastId;}

    public Request setFields(Request command) {
        if (command.getLabWork() != null) {
            command.getLabWork().setId(++lastId);
            command.getLabWork().setCreationDate(LocalDateTime.now());
        }

        return command;
    }

}
