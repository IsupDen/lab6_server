package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import util.Request;
import util.Response;

import static util.TextFormat.successText;

public class Clear extends Command{
    private final CollectionManagerInterface collectionManager;

    public Clear(CollectionManager collectionManager) {
        super("clear", "clear the collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        collectionManager.clear();
        return new Response(successText("Successful!"));
    }
}
