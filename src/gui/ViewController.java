package gui;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import application.UI;
import enums.RoundGamer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ViewController implements Initializable {
    
    @FXML
    private Button btn00;

    @FXML
    private Button btn01;

    @FXML
    private Button btn02;

    @FXML
    private Button btn10;

    @FXML
    private Button btn11;

    @FXML
    private Button btn12;

    @FXML
    private Button btn20;

    @FXML
    private Button btn21;

    @FXML
    private Button btn22;

    @FXML
    private Label textInfo;
    
    // Matrix buttons
    private Button[][] btns = new Button[3][3];


    @FXML
    private void btnClick(ActionEvent event) {
        // PLAYER round
        boolean win = false; 
        int[] position = {0, 0};
        Button btn = (Button) event.getSource(); 
        
        // Define mark of PLAYER
        btn.setText("X");
        btn.setDisable(true);

        // Check if the PLAYER won
        position = identifyBtn(btn);
        win = UI.setCoordenatesOnTable(position, RoundGamer.PLAYER); 

        if (win) {
            // PLAYER Wins
            textInfo.setText(RoundGamer.PLAYER.name() + " WINS!!!");
            disableAllButtons();
            
        } else {
            // Check if the game is draw
            boolean drawGame = UI.checkDrawGame();
            
            if (!drawGame) {
                // Game continuation - COMPUTER round
                win = computerRound();
                
                if (win) {
                    // COMPUTER Wins
                    textInfo.setText(RoundGamer.COMPUTER.name() + " WINS!!!");
                    disableAllButtons();
                }

                // Check if the game is draw
                drawGame = UI.checkDrawGame();
                
                if (drawGame) {
                    // DRAW GAME
                    textInfo.setText("DRAW GAME!!!");    
                    disableAllButtons();
                }

            } else {
                // DRAW GAME
                textInfo.setText("DRAW GAME!!!");
                disableAllButtons();
            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formBtnMatrix();
    }

    // This method define the button from each position en matrix
    private void formBtnMatrix() {
        btns[0][0] = btn00;
        btns[0][1] = btn01;
        btns[0][2] = btn02;

        btns[1][0] = btn10;
        btns[1][1] = btn11;
        btns[1][2] = btn12;

        btns[2][0] = btn20;
        btns[2][1] = btn21;
        btns[2][2] = btn22;
    }

    // This method discovery the position in matrix from each button by id
    private int[] identifyBtn(Button btn) {
        int row = 0;
        int column = 0;

        String code = btn.getId();
        row = Character.getNumericValue(code.charAt(3));
        column = Character.getNumericValue(code.charAt(4));

        int[] position = {row, column};
        return position;
    }

    // This method controls the round of computer
    private boolean computerRound() {
        boolean win = false; 
        int[] position = {0, 0};
        Random randomGenerator = new Random();

        do {
            position[0] = randomGenerator.nextInt(3);
            position[1]= randomGenerator.nextInt(3);

            if (UI.isEmptyOnTable(position)) {
                break;
            }
        } while(true);
        
        Button btn = btns[position[0]][position[1]];

        btn.setText("O");
        btn.setDisable(true);

        win = UI.setCoordenatesOnTable(position, RoundGamer.COMPUTER);

        return win;
    }

    // This method disable all buttons of HashGame
    private void disableAllButtons() {
        for (int i = 0; i < UI.table.length; i++) {
            for (int j = 0; j < UI.table.length; j++) {
                btns[i][j].setDisable(true);
            }
        }
    };
}
