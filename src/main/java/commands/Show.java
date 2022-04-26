package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import data.LabWork;
import util.Request;
import util.Response;

import java.util.ArrayList;
import java.util.Collections;

import static util.TextFormat.helpText;

public class Show extends Command{
    private final CollectionManagerInterface collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "print all elements in string representation to standard output.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {
        if (collectionManager.getCollection().size() == 0)
            return new Response(helpText("Collection is empty!"));
        return new Response(collectionManager.getCollection());
    }
}
