package services;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        Field actual = board.getFieldById(15);
        assertEquals(testedField, actual);
    }

    @Test
    public void testUpdateField() throws Exception {
        List<Integer> expecteds = new ArrayList<>();
        expecteds.add(2);
        expecteds.add(8);
        expecteds.add(9);

        assertTrue(board.updateField(0));
        assertEquals(expecteds, board.getFieldById(0).getPossibleValues());
    }

    @Test
    public void testSolveBasedOnPossibleValuesInBox() throws Exception {
        Board board1 = new Board(initSetup);
        board1.updateField(65);
        assertTrue(board1.solveBasedOnPossibleValuesInBox(65));
        Field field = board1.getFieldById(65);
        int value = field.getValue();
        assertEquals(7, value);
    }

    @Test
    public void test(){
        Board board1 = new Board(initSetup);
        for ( int id = 0; id < 90; id++ ) {
            if ( id%10==0 ) System.out.println();
            Field field = board1.getFieldById(id);
            if ( field==null ) continue;
            board1.updateField(field);
            System.out.print(field.getPossibleValues() + " ; ");
        }
    }
}