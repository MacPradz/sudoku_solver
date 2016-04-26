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
    public void testSolve() throws Exception {
        board.solveBasedOnPossibleValuesInBox(42);

    }
}