package util;

import collection.CollectionManager;
import commands.*;

import java.util.*;

public class Invoker{


    private final CollectionManager collectionManager;
    private final Map<String, Command> commands;

    public Invoker(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        commands = new HashMap<>();
        initMap();
    }

    public Response execute(Request newCommand) {
        String command = newCommand.getCommand();

        return commands.get(command).execute(newCommand);
    }

    private void initMap() {
        commands.put("help", new Help(commands));
        commands.put("add", new Add(collectionManager));
        commands.put("add_if_max", new AddIfMax(collectionManager));
        commands.put("clear", new Clear(collectionManager));
        commands.put("count_less_than_pqm", new Count(collectionManager));
        commands.put("execute_script", new Execute());
        commands.put("group_counting_by_coordinates", new Group(collectionManager));
        commands.put("info", new Info(collectionManager));
        commands.put("remove_by_id", new Remove(collectionManager));
        commands.put("remove_all_by_difficulty", new RemoveByDifficulty(collectionManager));
        commands.put("remove_first", new RemoveFirst(collectionManager));
        commands.put("reorder", new Reorder(collectionManager));
        commands.put("show", new Show(collectionManager));
        commands.put("update", new Update(collectionManager));
        commands.put("exit", new Exit());
    }
}
