package commands;

import collection.CollectionManager;
import util.Request;
import util.Response;

public class Info extends Command{

    private final CollectionManager collectionManager;

    public Info(CollectionManager aCollectionManager) {
        super("info", "Print information about the collection.");
        collectionManager = aCollectionManager;
    }

    @Override
    public Response execute(Request command) {
        return new Response("Information about collection:\n\n" + collectionManager.getInfo());
    }
}
