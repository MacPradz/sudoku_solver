import services.Board;
import services.Field;

import java.util.List;

/**
 * Created by uc198829 on 15/4/2016.
 */
public class Main {
    public static void main(String[] args) {
        byte[][] initSetup = {
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
        byte[][] initSetup2 = {
                {0,0,0  ,2,0,5  ,0,0,0},
                {0,4,0  ,0,0,7  ,0,0,0},
                {1,2,0  ,3,0,0  ,5,0,6},

                {0,0,4  ,1,0,3  ,0,0,0},
                {2,0,9  ,4,7,0  ,0,5,0},
                {7,0,0  ,5,8,2  ,0,4,0},

                {0,9,0  ,6,0,0  ,4,0,0},
                {0,7,0  ,0,5,4  ,0,2,0},
                {0,1,3  ,0,0,0  ,6,0,0}
        };

        Board board = new Board(initSetup2);
        boolean gotUpdates = true;
        while ( gotUpdates ){
            gotUpdates=ex(board);
            System.out.println();
        }

        if ( board.isSolved() ) {
            System.out.println("solved");
        }else {
            System.out.println("too hard");
        }


//        for ( int i = 0; i < 90; i++ ) {
//            if ( i%10==9 ) System.out.println();
//            Field field = Board.getFieldById(i);
//            if ( field==null ) continue;
//            board.solve(field);
//            print(field);
//        }
//
//        for ( int i = 0; i < 90; i++ ) {
//            if ( i%10==9 ) System.out.println();
//            Field field = Board.getFieldById(i);
//            if ( field==null ) continue;
//            board.solve(field);
//            print(field);
//        }
//        System.out.println();
//
//        gotUpdates = true;
//        while ( gotUpdates ){
//            gotUpdates=ex(board);
//            System.out.println();
//        }
//
//        if ( board.isSolved() ) {
//            System.out.println("solved");
//        }else {
//            System.out.println("too hard");
//        }

    }

    private static boolean ex(Board board) {
        boolean boardWasUpdated = false;
        for ( int i = 0; i < 90; i++ ) {
            if ( i%10==9 ) System.out.println();
            Field field = board.getFieldById(i);
            if ( field==null ) continue;

                if ( board.updateField(field) ){
                    boardWasUpdated=true;
                }
            print(field);
        }
        return boardWasUpdated;
    }

    private static void print(Field field) {
        if ( field.isDefinite() ){
            try {
                System.out.print(field.getValue() + "\t;");
            } catch ( Exception e ) {
                e.printStackTrace();
            }
            return;
        }
        List<Integer> possibleValues = field.getPossibleValues();
        System.out.print("{");
        for ( Integer possibleValue : possibleValues ) {
            System.out.print(possibleValue + ", ");
        }
        System.out.print("};\t");
    }
}
