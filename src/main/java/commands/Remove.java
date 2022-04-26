package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import util.Request;
import util.Response;

import static util.TextFormat.errText;
import static util.TextFormat.successText;

public class Remove extends Command{
    private final CollectionManagerInterface collectionManager;

    public Remove(CollectionManager collectionManager) {
        super("remove_by_id", "remove an element from the collection by ID.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {

        if (!collectionManager.remove(collectionManager.getLabworkById(Integer.parseInt(command.getArg()))))
            return new Response(errText("LabWork with this id does not exist!"));
        return new Response(successText("LabWork has been removed!"));
    }
}
