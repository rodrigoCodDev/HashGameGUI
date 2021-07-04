package application;

import enums.RoundGamer;

public class UI {
    // USER INTERFACE

    // STATIC VARIABLES
    protected static boolean drawGame = false;

    //Table of game
    public static String[][] table = {
        {"{ }", "{ }", "{ }"},
        {"{ }", "{ }", "{ }"},
        {"{ }", "{ }", "{ }"}
    };

    
    // STATIC METHODS
    
    // This method set the symbol of RoundGamer on position on the table
    public static boolean setCoordenatesOnTable(int[] position, RoundGamer gamer) {
        boolean win = true;
        String gamerSimbol = gamer.equals(RoundGamer.PLAYER) ? "{X}" : "{O}";
        
        table[position[0]] [position[1]] = gamerSimbol;
        win = validateWin(gamerSimbol);
        
        return win;
    }
    
    // This method verify if the position in table is empty
    public static boolean isEmptyOnTable(int[] position) {
        return table[position[0]][position[1]].equals("{ }");
    }

    // This method checks if the game is a draw
    public static boolean checkDrawGame() {
        int cont = 0;
        
        for (int i = 0; i < table.length; i++) {
            
            for (int j = 0; j < table[i].length; j++) {
                if (!table[i][j].equals("{ }")) {
                    cont++;
                }
            }
        }
        
        if (cont == 9) {
            // Draw game
            drawGame = true;
        }
        
        return drawGame;
    }
    
    // This method validate the Win of one RoundGamer
    private static boolean validateWin(String gamerSimbol) {
        int simbols = 0;
        
        // Search on rows
        for (int i = 0; i < table.length; i++) {
            
            simbols = 0;
            for (int j = 0; j < table[i].length; j++) {
                if (table[i][j].equals(gamerSimbol)) {
                    simbols++;
                }
            }
            
            if (simbols == 3) {
                // The RoundGamer wins
                return true;
            }
        }
        
        // Search on rows
        simbols = 0;
        for (int i = 0; i < table.length; i++) {
            
            simbols = 0;
            for (int j = 0; j < table[i].length; j++) {
                if (table[j][i].equals(gamerSimbol)) {
                    simbols++;
                }
            }
            
            if (simbols == 3) {
                // The RoundGamer wins
                return true;
            }
        }
        
        // Search on diagonals
        simbols = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i][i].equals(gamerSimbol)) {
                simbols++;
            }
        }
        
        if (simbols == 3) {
            // The RoundGamer wins
            return true;
        }
        
        
        simbols = 0;
        int di = 2; // Inversor (decrescent i)
        for (int i = 0; i < table.length; i++) {
            if (table[i][di].equals(gamerSimbol)) {
                simbols++;
            }
            
            di--;
        }
        
        if (simbols == 3) {
            // The RoundGamer wins
            return true;
        }
        
        // The game continue
        return false;
    }

}
