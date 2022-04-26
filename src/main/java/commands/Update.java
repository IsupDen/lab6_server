package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import data.LabWork;
import util.Request;
import util.Response;

import static util.TextFormat.errText;
import static util.TextFormat.successText;

public class Update extends Command{
    private final CollectionManagerInterface collectionManager;

    public Update(CollectionManager collectionManager) {
        super("update", "update the element`s value, whose ID is equal to the given. ");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {

        LabWork labWork = collectionManager.getLabworkById(Integer.parseInt(command.getArg()));

        if (labWork != null) collectionManager.remove(labWork);
        else return new Response(errText("LabWork with this id does not exist!"));

        LabWork newLabWork = command.getLabWork();
        newLabWork.setId(Integer.parseInt(command.getArg()));
        collectionManager.add(newLabWork);

        return new Response(successText("LabWork has been updated!"));
    }
}
