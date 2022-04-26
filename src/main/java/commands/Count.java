package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import data.LabWork;
import util.Request;
import util.Response;

import java.util.ArrayList;

import static util.TextFormat.helpText;
import static util.TextFormat.successText;

public class Count extends Command{
    private final CollectionManagerInterface collectionManager;

    public Count(CollectionManager collectionManager) {
        super("count_less_than_pqm", "print the number of elements whose "
                + "personalQualitiesMinimum field value is less than the specified one.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request command) {

        if (collectionManager.getCollection().size() == 0)
            return new Response(helpText("Collection is empty!"));
        return new Response(successText("Amount of elements: " +
                        collectionManager.getCollection().stream()
                        .filter(labWork -> labWork.comparePQM(Integer.parseInt(command.getArg())) < 0).count()));
    }
}
