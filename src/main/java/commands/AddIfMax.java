package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import data.LabWork;
import util.Request;
import util.Response;


import static util.TextFormat.*;

public class AddIfMax extends Command{

    private final CollectionManagerInterface collectionManager;

    public AddIfMax(CollectionManager collectionManager) {
        super("add_if_max", "add new element to the collection, if it`s greater, than biggest element of this collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {

        LabWork labWork = command.getLabWork();
        if (collectionManager.getMax() != null && labWork.compareTo(collectionManager.getMax()) <= 0)
            return new Response(helpText("Labwork isn't best!"));
        collectionManager.add(labWork);
        return new Response(successText("LabWork is successfully added!"));
    }
}
