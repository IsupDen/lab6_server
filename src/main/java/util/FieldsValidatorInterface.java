package util;

import data.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface FieldsValidatorInterface {

    static boolean getIdCorrectStatus(String line) {
        try {
            return isPositiveInt(line);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    static boolean getNameCorrectStatus(String line) {
        return line != null && !line.equals("");
    }

    static boolean getCoordinateCorrectStatus(String line) {
        try {
            Integer.parseInt(line);
        } catch (NumberFormatException | NullPointerException exception) {
            return false;
        }
        return true;
    }

    static boolean getDateCorrectStatus(LocalDateTime date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateInString = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try {
            formatter.parse(dateInString);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    static boolean getMinimalPointCorrectStatus(String line) {
        try {
            return Double.parseDouble(line) > 0;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    static boolean getPersonalQualitiesMinimumCorrectStatus(String line) {
        if (line == null || line.equals("null") || line.equals("")) return true;
        return isPositiveInt(line);
    }

    static boolean getDifficultyCorrectStatus(String line) {
        if (line == null || line.equals("null") || line.equals("")) return true;
        return Difficulty.isIncludeElement(line.toUpperCase());
    }

    static boolean getAuthorHeightCorrectStatus(String line) {
        try {
            return Double.parseDouble(line) > 0;
        } catch (NumberFormatException | NullPointerException exception) {
            return false;
        }
    }

    static boolean getAuthorEyeColorCorrectStatus(String line) {
        return line != null && EyeColor.isIncludeElement(line.toUpperCase());
    }

    static boolean getAuthorHairColorCorrectStatus(String line) {
        return line != null && HairColor.isIncludeElement(line.toUpperCase());
    }

    static boolean getAuthorCountryCorrectStatus(String line) {
        return line != null && Country.isIncludeElement(line.toUpperCase());
    }

    static boolean isPositiveInt(String line) {
        try {
            return Integer.parseInt(line) > 0;
        } catch (NumberFormatException | NullPointerException exception) {
            return false;
        }
    }
}
