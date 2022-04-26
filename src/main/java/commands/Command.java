package commands;

import util.Request;
import util.Response;

import static util.TextFormat.helpText;

public abstract class Command implements CommandInterface{

    private final String name;
    private final String description;

    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getDescription() {
        switch (name) {
            case "add":
            case "add_if_max":
                return String.format("%-40s", name.toUpperCase() + " {element}") + ": " + description;
            case "count_less_than_personal_qualities_minimum":
                return String.format("%-40s", name.toUpperCase() + " personalQualitiesMinimum") + ": " + description;
            case "execute_script":
                return String.format("%-40s", name.toUpperCase() + " file_name") + ": " + description;
            case "remove_by_id":
                return String.format("%-40s", name.toUpperCase() + " id") + ": " + description;
            case "remove_all_by_difficulty":
                return String.format("%-40s", name.toUpperCase() + " difficulty") + ": " + description;
            case "update":
                return String.format("%-40s", name.toUpperCase() + " id {element}") + ": " + description;
            default:
                return String.format("%-40s", name.toUpperCase()) + ": " + description;
        }
    }

    @Override
    public String toString() {
        return helpText(description);
    }

    @Override
    public abstract Response execute(Request request);
}
