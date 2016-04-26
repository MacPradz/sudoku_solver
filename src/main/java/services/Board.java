package services;

import java.util.*;

/**
 * Created by uc198829 on 15/4/2016.
 */
public class Board {
    private static final int SUDOKU_SIZE = 9;
    private static final Map<Integer, Field> FIELDS_MAP = new HashMap<>();
    private static byte[][] currState;

    public Board(byte[][] initSetup) {
        this.currState = initSetup;
        generateFieldsMap();
    }

    private void generateFieldsMap() {
        for ( int rowN = 0; rowN < SUDOKU_SIZE; rowN++ ) {
            for ( int colN = 0; colN < SUDOKU_SIZE; colN++ ) {
                int fieldId = BoardUtil.generateFieldId(rowN, colN);
                int value = currState[rowN][colN];
                Field sf = new Field(fieldId, value);
                FIELDS_MAP.put(fieldId, sf);
            }
        }
    }

    public boolean isSolved() {
        for ( Field field : FIELDS_MAP.values() ) {
            if ( !field.isDefinite() ) {
                return false;
            }
        }
        return true;
    }

    public Field getFieldById(int id) {
        return FIELDS_MAP.get(id);
    }

    public boolean updateField(int id) {
        Field field = FIELDS_MAP.get(id);
        return updateField(field);
    }

    public boolean updateField(Field field) {
        if ( field.isDefinite() ) return false;
        int id = field.getId();
        Set<Integer> fields = BoardUtil.getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(id);
        Set<Integer> valuesUsed = getSetOfValuesWhichAreUsedByFields(fields);
        return field.updatePossibleValues(valuesUsed);
    }

    private Set<Integer> getSetOfValuesWhichAreUsedByFields(Set<Integer> fieldsIds) {
        Set<Integer> valuesUsed = new HashSet<>(SUDOKU_SIZE);
        for ( int id : fieldsIds ) {
            Field field = getFieldById(id);
            if ( field.isDefinite() ) {
                int value = field.getValue();
                valuesUsed.add(value);
            }
        }
        return valuesUsed;
    }

    public boolean solveBasedOnPossibleValuesInBox(Field field) {
        int fieldId = field.getId();
        return solveBasedOnPossibleValuesInBox(fieldId);
    }

    public boolean solveBasedOnPossibleValuesInBox(Integer fieldId) {
        List<Integer> listOfIdInBox = BoardUtil.getListOfIdInBox(fieldId);
        listOfIdInBox.remove(fieldId);
        return solveFieldBasedOnOtherUndefinedFields(fieldId, listOfIdInBox);
    }

    private boolean solveFieldBasedOnOtherUndefinedFields(Integer fieldId, List<Integer> idsOfOtherFields) {
        Set<Integer> valuesInBox = new HashSet<>();
        for ( Integer id : idsOfOtherFields ) {
            Field fieldFromBox = FIELDS_MAP.get(id);
            if ( fieldFromBox.isDefinite() ) continue;
            updateField(fieldFromBox);
            valuesInBox.addAll(fieldFromBox.getPossibleValues());
        }

        Field ourField = FIELDS_MAP.get(fieldId);
        List<Integer> fromOurField = new ArrayList<>(ourField.getPossibleValues());
        fromOurField.removeAll(valuesInBox);

        if ( fromOurField.size() == 1 ) {
            ourField.getPossibleValues().removeAll(valuesInBox);
            return true;
        }
        return false;
    }


}
