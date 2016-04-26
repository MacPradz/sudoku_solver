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

    public static Field getFieldById(int id) {
        return FIELDS_MAP.get(id);
    }

    public boolean solveBasedOnPossibleValuesInBox(Integer fieldId)
    {
        List<Integer> listOfIdInBox = BoardUtil.getListOfIdInBox(fieldId);
        listOfIdInBox.remove(fieldId);
        Set<Integer> valuesInBox = new HashSet<>();

        for ( Integer id : listOfIdInBox ) {
            Field fieldFromBox = FIELDS_MAP.get(id);
            if ( fieldFromBox.isDefinite() ) continue;
            valuesInBox.addAll(fieldFromBox.getPossibleValues());
        }

        Field ourField = FIELDS_MAP.get(fieldId);
        List<Integer> fromOurField = new ArrayList<Integer>(ourField.getPossibleValues());
        fromOurField.removeAll(valuesInBox);

        if ( fromOurField.size() == 1 ) {
            ourField.getPossibleValues().removeAll(valuesInBox);
            return true;
        }
        return false;
    }

    public boolean solve(Field field) {
        int fieldId = field.getId();
        return solveBasedOnPossibleValuesInBox(fieldId);
    }

    public boolean updateField(int id) {
        Field field = FIELDS_MAP.get(id);
        return updateField(field);
    }

    public boolean updateField(Field field) {
        if ( field.isDefinite() ) return false;
        int id = field.getId();
        Set<Integer> fields = BoardUtil.getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(id);
        Set<Integer> valuesUsed = getSetOfValuesWhichAreUsedAlreadyByFields(fields);
        return field.updatePossibleValues(valuesUsed);
    }

    private static Set<Integer> getSetOfValuesWhichAreUsedAlreadyByFields(Set<Integer> fieldsIds) {
        Set<Integer> valuesUsed = new HashSet<>(SUDOKU_SIZE);
        for ( int id : fieldsIds ) {
            Field field = Board.getFieldById(id);
            if ( field.isDefinite() ) {
                int value = 0;
                try {
                    value = field.getValue();
                } catch ( Exception e ) {
                    e.printStackTrace();
                }
                valuesUsed.add(value);
            }
        }
        return valuesUsed;
    }
}
