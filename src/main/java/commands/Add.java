package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import util.Request;
import util.Response;

import static util.TextFormat.successText;

public class Add extends Command{

    private final CollectionManagerInterface collectionManager;

    public Add(CollectionManager collectionManager) {
        super("add", "add new element to the collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {

        collectionManager.add(command.getLabWork());
        return new Response(successText("LabWork is successfully added!"));
    }
}
