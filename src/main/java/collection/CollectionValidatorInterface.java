package collection;

import data.LabWork;
import util.Request;
import util.Response;

import java.util.List;

public interface CollectionValidatorInterface {
    String validateCollection(List<LabWork> inputCollection, boolean hasLineErr);
}
