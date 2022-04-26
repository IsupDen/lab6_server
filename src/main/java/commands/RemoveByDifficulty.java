package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import data.LabWork;
import util.Request;
import util.Response;

import java.util.ArrayList;

import static util.TextFormat.successText;

public class RemoveByDifficulty extends Command{
    private final CollectionManagerInterface collectionManager;

    public RemoveByDifficulty(CollectionManager collectionManager) {
        super("remove_all_by_difficulty", "remove all elements from the collection by difficulty.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {

        ArrayList<LabWork> labWorks = collectionManager.getCollection();
        if (command.getArg().equals("null")) {
            labWorks.removeIf(labWork -> labWork.getDifficulty() == null);
        }
        else labWorks.removeIf(labWork -> labWork.getDifficulty() != null &&
                labWork.getDifficulty().toString().equals(command.getArg().toUpperCase()));
        return new Response(successText("Objects has been removed!"));
    }
}
