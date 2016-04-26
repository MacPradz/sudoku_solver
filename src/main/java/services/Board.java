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
                int fieldId = generateFieldId(rowN, colN);
                int value = currState[rowN][colN];
                Field sf = new Field(fieldId, value);
                FIELDS_MAP.put(fieldId, sf);
            }
        }
    }

    private static int generateFieldId(int rowN, int colN) {
        return 10 * rowN + colN;
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

    public static List<Integer> getListOfIdInRow(Field field) {
        int id = field.getId();
        return getListOfIdInRow(id);
    }

    public static List<Integer> getListOfIdInRow(int id) {
        List<Integer> currRowIds = new LinkedList<Integer>();
        int rowStartId = id - id % 10;
        int rowEndId = id - id % 10 + 9;
        for ( int idN = rowStartId; idN < rowEndId; idN++ ) {
            currRowIds.add(idN);
        }
        return currRowIds;
    }

    public static List<Integer> getListOfIdInCol(Field field) {
        int id = field.getId();
        return getListOfIdInCol(id);
    }

    public static List<Integer> getListOfIdInCol(int id) {
        List<Integer> currColIds = new LinkedList<Integer>();
        int colN = id % 10;
        for ( int rowN = 0; rowN < SUDOKU_SIZE; rowN++ ) {
            int idInRow = generateFieldId(rowN, colN);
            currColIds.add(idInRow);
        }
        return currColIds;
    }

    public static List<Integer> getListOfIdInBox(Field field) {
        int id = field.getId();
        return getListOfIdInBox(id);
    }

    public static List<Integer> getListOfIdInBox(int id) {
        List<Integer> currBoxIds = new LinkedList<Integer>();
        int colN = id % 10;
        int rowN = (id - colN) / 10;

        int a = colN % 3;
        int currBoxStartColN = colN - a;

        int b = rowN % 3;
        int currBoxStartRowN = rowN - b;

        for ( int currRowN = currBoxStartRowN, i = 0; i < 3; currRowN++, i++ ) {
            for ( int currColN = currBoxStartColN, j = 0; j < 3; currColN++, j++ ) {
                int currId = generateFieldId(currRowN, currColN);
                currBoxIds.add(currId);
            }
        }
        return currBoxIds;
    }

    public boolean solveBasedOnPossibleValuesInBox(Integer fieldId)
    {
        List<Integer> listOfIdInBox = getListOfIdInBox(fieldId);
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
        Set<Integer> fields = getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(id);
        Set<Integer> valuesUsed = getSetOfValuesWhichAreUsedAlreadyByFields(fields);
        return field.updatePossibleValues(valuesUsed);
    }

    private static Set<Integer> getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(int id) {
        Set<Integer> nearbyFields = new HashSet<>();
        nearbyFields.addAll(getListOfIdInBox(id));
        nearbyFields.addAll(getListOfIdInCol(id));
        nearbyFields.addAll(getListOfIdInRow(id));
        return nearbyFields;
    }

    private static Set<Integer> getSetOfValuesWhichAreUsedAlreadyByFields(Set<Integer> fields) {
        Set<Integer> valuesUsed = new HashSet<>(SUDOKU_SIZE);
        for ( int fid : fields ) {
            Field field = Board.getFieldById(fid);
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
