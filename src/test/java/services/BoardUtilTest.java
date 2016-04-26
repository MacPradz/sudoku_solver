package services;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class BoardUtilTest {
    private Field testedField;

    @Before
    public void setUp() throws Exception {
        this.testedField = new Field(15, 0);
    }

    @Test
    public void testGenerateFieldId() throws Exception {
        assertEquals(15, BoardUtil.generateFieldId(1, 5));
    }

    @Test
    public void testGetListOfIdInRowOfField() {
        List<Integer> expected = Arrays.asList(new Integer[]{10, 11, 12, 13, 14, 15, 16, 17, 18});
        List<Integer> actual = BoardUtil.getListOfIdInRow(testedField);
        assertEquals("should return a list of IDs of fields in the same row as the tested field", expected, actual);
    }

    @Test
    public void testGetListOfIdInRowOfId() {
        List<Integer> expected = Arrays.asList(new Integer[]{10, 11, 12, 13, 14, 15, 16, 17, 18});
        List<Integer> actual = BoardUtil.getListOfIdInRow(15);
        assertEquals("should return a list of IDs of fields in the same row as the given ID", expected, actual);
    }

    @Test
    public void testGetListOfIdInColOfField() {
        List<Integer> expected = Arrays.asList(new Integer[]{5, 15, 25, 35, 45, 55, 65, 75, 85});
        List<Integer> actual = BoardUtil.getListOfIdInCol(testedField);
        assertEquals("should return a list of IDs of fields in the same column as the tested field", expected, actual);
    }

    @Test
    public void testGetListOfIdInColOfId() {
        List<Integer> expected = Arrays.asList(new Integer[]{5, 15, 25, 35, 45, 55, 65, 75, 85});
        List<Integer> actual = BoardUtil.getListOfIdInCol(15);
        assertEquals("should return a list of IDs of fields in the same column as the given ID", expected, actual);
    }

    @Test
    public void testGetListOfIdInBoxOfField() {
        List<Integer> expected = Arrays.asList(new Integer[]{3, 4, 5, 13, 14, 15, 23, 24, 25});
        List<Integer> actual = BoardUtil.getListOfIdInBox(testedField);
        assertEquals("should return a list of IDs of fields in the same box as the tested field", expected, actual);
    }

    @Test
    public void testGetListOfIdInBoxOfId() {
        List<Integer> expected = Arrays.asList(new Integer[]{3, 4, 5, 13, 14, 15, 23, 24, 25});
        List<Integer> actual = BoardUtil.getListOfIdInBox(15);
        assertEquals("should return a list of IDs of fields in the same box as the given ID", expected, actual);
    }

    @Test
    public void getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAsOfField() {
        Set<Integer> expected = new HashSet<>(Arrays.asList(new Integer[]{3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 24, 25, 35, 45, 55, 65, 75, 85}));
        Set<Integer> actual = BoardUtil.getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(testedField);
        assertEquals("should return a list of IDs of fields in the same box, row and column as the given ID", expected, actual);
    }

    @Test
    public void getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAsOfId() {
        Set<Integer> expected = new HashSet<>(Arrays.asList(new Integer[]{3, 4, 5, 10, 11, 12, 13, 14, 15, 16, 17, 18, 23, 24, 25, 35, 45, 55, 65, 75, 85}));
        Set<Integer> actual = BoardUtil.getSetOfIdsOfFieldsWhichCannotHaveTheSameValueAs(15);
        assertEquals("should return a list of IDs of fields in the same box, row and column as the given ID", expected, actual);
    }
}