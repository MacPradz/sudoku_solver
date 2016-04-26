package services;

import java.util.*;

/**
 * Created by uc198829 on 15/4/2016.
 */
public class Field {
    private static final List<Integer> FULL_SET_OF_POSSIBLE_VALUES = new ArrayList<Integer>() {
        {
            add(Integer.valueOf(1));
            add(Integer.valueOf(2));
            add(Integer.valueOf(3));
            add(Integer.valueOf(4));
            add(Integer.valueOf(5));
            add(Integer.valueOf(6));
            add(Integer.valueOf(7));
            add(Integer.valueOf(8));
            add(Integer.valueOf(9));
        }
    };
    private int id;
    private List<Integer> possibleValues = new ArrayList<>();

    public Field(int id, int value) {
        this.id = id;
        if ( value == 0 ) {
            this.possibleValues = new ArrayList<>(FULL_SET_OF_POSSIBLE_VALUES);
        } else {
            this.possibleValues.add(Integer.valueOf(value));
        }
    }

    public int getValue() throws Exception {
        if ( isDefinite() )
            return possibleValues.get(0);
        else
            throw new Exception("Value not definite yet");
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public int getId() {
        return id;
    }

    public boolean isDefinite() {
        return possibleValues.size() == 1;
    }

    public boolean updatePossibleValues(Set<Integer> valuesToBeRemovedFromPossibleValues) {
        return possibleValues.removeAll(valuesToBeRemovedFromPossibleValues);
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Field field = (Field) o;

        if ( id != field.id ) return false;
        if ( !possibleValues.equals(field.possibleValues) ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + possibleValues.hashCode();
        return result;
    }
}


