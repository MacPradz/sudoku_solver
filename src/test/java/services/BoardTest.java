package services;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BoardTest {
    private Field testedField;
    private Board board;
    private final byte[][] initSetup = {
            {0,1,0  ,6,0,4  ,3,0,7},
            {3,5,6  ,0,0,0  ,0,0,0},
            {0,0,0  ,0,5,3  ,6,9,0},

            {0,8,3  ,2,6,0  ,4,0,9},
            {0,0,0  ,0,0,0  ,0,0,0},
            {4,0,5  ,0,7,8  ,2,6,0},

            {0,4,2  ,5,3,0  ,0,0,0},
            {0,0,0  ,0,0,0  ,7,2,4},
            {7,0,9  ,4,0,2  ,0,8,0}
    };


    @Before
    public void setUp() throws Exception {
        this.testedField = new Field(15, 0);
        this.board = new Board(initSetup);
    }

    @Test
    public void testGetFieldById() throws Exception {
        Field actual = Board.getFieldById(15);
        assertEquals(testedField,actual);
    }

    @Test
    public void testGetListOfIdInRowOfField() {
        List<Integer> expected = Arrays.asList(new Integer[]{10, 11, 12, 13, 14, 15, 16, 17, 18});
        List<Integer> actual = Board.getListOfIdInRow(testedField);
        assertEquals("should return a list of IDs of fields in the same row as the tested field", expected, actual);
    }

    @Test
    public void testGetListOfIdInRowOfId() {
        List<Integer> expected = Arrays.asList(new Integer[]{10, 11, 12, 13, 14, 15, 16, 17, 18});
        List<Integer> actual = Board.getListOfIdInRow(15);
        assertEquals("should return a list of IDs of fields in the same row as the given ID", expected, actual);
    }

    @Test
    public void testGetListOfIdInColOfField() {
        List<Integer> expected = Arrays.asList(new Integer[]{5, 15, 25, 35, 45, 55, 65, 75, 85});
        List<Integer> actual = Board.getListOfIdInCol(testedField);
        assertEquals("should return a list of IDs of fields in the same column as the tested field", expected, actual);
    }

    @Test
    public void testGetListOfIdInColOfId() {
        List<Integer> expected = Arrays.asList(new Integer[]{5, 15, 25, 35, 45, 55, 65, 75, 85});
        List<Integer> actual = Board.getListOfIdInCol(15);
        assertEquals("should return a list of IDs of fields in the same column as the given ID", expected, actual);
    }

    @Test
    public void testGetListOfIdInBoxOfField() {
        List<Integer> expected = Arrays.asList(new Integer[]{3, 4, 5, 13, 14, 15, 23, 24, 25});
        List<Integer> actual = Board.getListOfIdInBox(testedField);
        assertEquals("should return a list of IDs of fields in the same box as the tested field", expected, actual);
    }

    @Test
    public void testGetListOfIdInBoxOfId() {
        List<Integer> expected = Arrays.asList(new Integer[]{3, 4, 5, 13, 14, 15, 23, 24, 25});
        List<Integer> actual = Board.getListOfIdInBox(15);
        assertEquals("should return a list of IDs of fields in the same box as the given ID", expected, actual);
    }

    @Test
    public void testSolve() throws Exception {
        board.solveBasedOnPossibleValuesInBox(42);

    }
}