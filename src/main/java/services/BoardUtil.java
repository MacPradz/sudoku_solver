package services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by uc198829 on 26/4/2016.
 */
public final class BoardUtil {
    private static final int SUDOKU_SIZE = 9;

    public static int generateFieldId(int rowN, int colN) {
        return 10 * rowN + colN;
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

    public static Set<Integer> getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(Field field){
        int id = field.getId();
        return getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(id);
    }

    public static Set<Integer> getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(int id) {
        Set<Integer> nearbyFields = new HashSet<>();
        nearbyFields.addAll(BoardUtil.getListOfIdInBox(id));
        nearbyFields.addAll(BoardUtil.getListOfIdInCol(id));
        nearbyFields.addAll(BoardUtil.getListOfIdInRow(id));
        return nearbyFields;
    }
}
