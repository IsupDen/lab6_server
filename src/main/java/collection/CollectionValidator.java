package collection;

import data.LabWork;
import util.ObjectValidator;
import util.Response;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static util.TextFormat.errText;
import static util.TextFormat.successText;

public class CollectionValidator implements CollectionValidatorInterface, ObjectValidator {

    CollectionManagerInterface collectionManger;

    public CollectionValidator(CollectionManager collectionManager) {
        this.collectionManger = collectionManager;
    }

    @Override
    public String validateCollection(List<LabWork> inputCollection, boolean hasLineErr) {
        AtomicBoolean hasErr = new AtomicBoolean(hasLineErr);
        inputCollection.forEach(labWork -> {
            if (validateObject(labWork)) collectionManger.add(labWork);
            else hasErr.set(true);
        });

        if (hasErr.get()) return errText("CSV file has been broken!(not all objects was loaded)");
        else return successText("Collection was loaded!");
    }
}
