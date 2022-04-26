package commands;

import collection.CollectionManager;
import collection.CollectionManagerInterface;
import util.Request;
import util.Response;

import java.util.Map;
import java.util.stream.Collectors;

import static util.TextFormat.helpText;

public class Group extends Command{
    private final CollectionManagerInterface collectionManager;

    public Group(CollectionManager collectionManager) {
        super("group_counting_by_coordinates", "group the elements of the collection by" +
                "the value of the coordinates field, display the number of elements in each group.");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {

        Map<String, Integer> map = collectionManager.getCollection().stream()
                .collect(Collectors.toMap(labWork -> labWork.getCoordinates().toString(), count -> 1, Integer::sum));
        if (map.size()==0) return new Response(helpText("Collection is empty!"));
        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));

        return new Response(sb.toString());
    }
}
