package util;

import data.LabWork;
import static util.FieldsValidatorInterface.*;

public interface ObjectValidator{

    default boolean validateObject(LabWork labWork) {
        try {
            return getIdCorrectStatus(String.valueOf(labWork.getId())) &&
                    getNameCorrectStatus(labWork.getName()) &&
                    getCoordinateCorrectStatus(String.valueOf(labWork.getCoordinates().getX())) &&
                    getCoordinateCorrectStatus(String.valueOf(labWork.getCoordinates().getY())) &&
                    getDateCorrectStatus(labWork.getCreationDate()) &&
                    getMinimalPointCorrectStatus(String.valueOf(labWork.getMinimalPoint())) &&
                    getPersonalQualitiesMinimumCorrectStatus(labWork.getPersonalQualitiesMinimum() == null ? "" : String.valueOf(labWork.getPersonalQualitiesMinimum())) &&
                    getDifficultyCorrectStatus(labWork.getDifficulty() == null ? "" : String.valueOf(labWork.getDifficulty())) &&
                    getAuthorCountryCorrectStatus(String.valueOf(labWork.getAuthor().getNationality())) &&
                    getNameCorrectStatus(String.valueOf(labWork.getAuthor().getName())) &&
                    getAuthorHeightCorrectStatus(String.valueOf(labWork.getAuthor().getHeight())) &&
                    getAuthorHairColorCorrectStatus(String.valueOf(labWork.getAuthor().getHairColor())) &&
                    getAuthorEyeColorCorrectStatus(String.valueOf(labWork.getAuthor().getEyeColor()));
        } catch (NullPointerException e) {
            return false;
        }
    }
}
