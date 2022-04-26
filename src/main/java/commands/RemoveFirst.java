package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import util.Request;
import util.Response;

import static util.TextFormat.errText;
import static util.TextFormat.successText;

public class RemoveFirst extends Command{
    private final CollectionManagerInterface collectionManager;

    public RemoveFirst(CollectionManager collectionManager) {
        super("remove_first", "remove the first element from the collection.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {
        if (collectionManager.getCollection().size() == 0)
            return new Response(errText("Collection is empty!"));
        collectionManager.remove(collectionManager.getCollection().get(0));
        return new Response(successText("First element successfully deleted!"));
    }
}
