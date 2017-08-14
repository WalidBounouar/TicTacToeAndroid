package walidbounouar.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    /**
     * The boardSpaces. Follow classic x/y coordinates, e.g. bottomLeft is (0,0), bottom
     * is (1,0), etc.
     */
    private boardSpace[][] boardSpaces;

    /**
     * The scores of the players
     */
    private int player1Score;
    private int player2Score;

    /**
     * The turn
     */
    private int turn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.resetBoardSpaces();

        this.player1Score = 0;
        this.player2Score = 0;

        this.turn = 0;

    }

    public int getPlayer1Score() {
        return this.player1Score;
    }

    public void addToPlayer1Score() {
        this.player1Score +=1;
    }

    public int getPlayer2Score() {
        return this.player2Score;
    }

    public void addToPlayer2Score() {
        this.player2Score +=1;
    }

    public void resetBoardSpaces() {

        this.boardSpaces = new boardSpace[3][3];

        this.boardSpaces[0][0] = new boardSpace("bottomLeft", (ImageView) findViewById(R.id.bottomLeft));
        this.boardSpaces[0][0].setClickable(true);
        this.boardSpaces[1][0] = new boardSpace("bottom", (ImageView) findViewById(R.id.bottom));
        this.boardSpaces[1][0].setClickable(true);
        this.boardSpaces[2][0] = new boardSpace("bottomRight", (ImageView) findViewById(R.id.bottomRight));
        this.boardSpaces[2][0].setClickable(true);

        this.boardSpaces[0][1] = new boardSpace("middleLeft", (ImageView) findViewById(R.id.middleLeft));
        this.boardSpaces[0][1].setClickable(true);
        this.boardSpaces[1][1] = new boardSpace("middle", (ImageView) findViewById(R.id.middle));
        this.boardSpaces[1][1].setClickable(true);
        this.boardSpaces[2][1] = new boardSpace("middleRight", (ImageView) findViewById(R.id.middleRight));
        this.boardSpaces[2][1].setClickable(true);

        this.boardSpaces[0][2] = new boardSpace("topLeft", (ImageView) findViewById(R.id.topLeft));
        this.boardSpaces[0][2].setClickable(true);
        this.boardSpaces[1][2] = new boardSpace("top", (ImageView) findViewById(R.id.top));
        this.boardSpaces[1][2].setClickable(true);
        this.boardSpaces[2][2] = new boardSpace("topRight", (ImageView) findViewById(R.id.topRight));
        this.boardSpaces[2][2].setClickable(true);

    }

    public void capture(View view) {

        boardSpace clickedSpace = this.getClickedSpace(view);

        if (clickedSpace.isClickable()) {
            clickedSpace.setClickable(false);
        } else {
            clickedSpace.setClickable(true);
        }

        if (this.turn == 0) {
            clickedSpace.setImage(R.drawable.circle);
            clickedSpace.setConqueror(1);
        } else {
            clickedSpace.setImage(R.drawable.cross);
            clickedSpace.setConqueror(2);
        }

        this.turn = (this.turn + 1) % 2 ;

        int winner = this.gameWon();

        if (winner == 1) {
            this.player1Score += 1;
            this.resetBoardSpaces();
            this.updateScreenScore(view);
        } else if (winner == 2) {
            this.player2Score += 1;
            this.resetBoardSpaces();
            this.updateScreenScore(view);
        }
    }


    private boardSpace getClickedSpace(View view) {

        boardSpace clickedSpace = null; //should always change on switch

        switch(view.getId()) {
            case R.id.bottomLeft :
                clickedSpace = this.boardSpaces[0][0];
                break;
            case R.id.bottomRight :
                clickedSpace = this.boardSpaces[2][0];
                break;
            case R.id.bottom :
                clickedSpace = this.boardSpaces[1][0];
                break;
            case R.id.middleLeft :
                clickedSpace = this.boardSpaces[0][1];
                break;
            case R.id.middleRight :
                clickedSpace = this.boardSpaces[2][1];
                break;
            case R.id.middle :
                clickedSpace = this.boardSpaces[1][1];
                break;
            case R.id.topLeft :
                clickedSpace = this.boardSpaces[0][2];
                break;
            case R.id.topRight :
                clickedSpace = this.boardSpaces[2][2];
                break;
            case R.id.top :
                clickedSpace = this.boardSpaces[1][2];
                break;
        }

        return clickedSpace;

    }

    public void resetButtonAction(View view) {
        this.resetBoardSpaces();
    }

    private int gameWon() {

        int winner = 0;

        if (this.boardSpaces[0][0].getConqueror() == this.boardSpaces[1][0].getConqueror()
                && this.boardSpaces[0][0].getConqueror() == this.boardSpaces[2][0].getConqueror()
                && this.boardSpaces[0][0].getConqueror() != 0) {
            winner = this.boardSpaces[0][0].getConqueror();
        } else if (this.boardSpaces[0][1].getConqueror() == this.boardSpaces[1][1].getConqueror()
                && this.boardSpaces[0][1].getConqueror() == this.boardSpaces[2][1].getConqueror()
                && this.boardSpaces[0][1].getConqueror() != 0) {
            winner = this.boardSpaces[0][1].getConqueror();
        } else if (this.boardSpaces[0][2].getConqueror() == this.boardSpaces[1][2].getConqueror()
                && this.boardSpaces[0][2].getConqueror() == this.boardSpaces[2][2].getConqueror()
                && this.boardSpaces[0][2].getConqueror() != 0) {
            winner = this.boardSpaces[0][2].getConqueror();
        } else if (this.boardSpaces[0][0].getConqueror() == this.boardSpaces[0][1].getConqueror()
                && this.boardSpaces[0][0].getConqueror() == this.boardSpaces[0][2].getConqueror()
                && this.boardSpaces[0][0].getConqueror() != 0) {
            winner = this.boardSpaces[0][0].getConqueror();
        } else if (this.boardSpaces[1][0].getConqueror() == this.boardSpaces[1][1].getConqueror()
                && this.boardSpaces[1][0].getConqueror() == this.boardSpaces[1][2].getConqueror()
                && this.boardSpaces[1][0].getConqueror() != 0) {
            winner = this.boardSpaces[1][0].getConqueror();
        } else if (this.boardSpaces[2][0].getConqueror() == this.boardSpaces[2][1].getConqueror()
                && this.boardSpaces[2][0].getConqueror() == this.boardSpaces[2][2].getConqueror()
                && this.boardSpaces[2][0].getConqueror() != 0) {
            winner = this.boardSpaces[2][0].getConqueror();
        } else if (this.boardSpaces[0][0].getConqueror() == this.boardSpaces[1][1].getConqueror()
                && this.boardSpaces[0][0].getConqueror() == this.boardSpaces[2][2].getConqueror()
                && this.boardSpaces[0][0].getConqueror() != 0) {
            winner = this.boardSpaces[0][0].getConqueror();
        } else if (this.boardSpaces[2][0].getConqueror() == this.boardSpaces[1][1].getConqueror()
                && this.boardSpaces[2][0].getConqueror() == this.boardSpaces[0][2].getConqueror()
                && this.boardSpaces[2][0].getConqueror() != 0) {
            winner = this.boardSpaces[2][0].getConqueror();
        }

        return winner;

    }

    public void updateScreenScore(View view) {

        TextView player1Score = (TextView) findViewById(R.id.player1Score);
        TextView player2Score = (TextView) findViewById(R.id.player2Score);

        player1Score.setText(String.valueOf(this.player1Score));
        player2Score.setText(String.valueOf(this.player2Score));

    }

    public void resetScore(View view) {

        this.player1Score = 0;
        this.player2Score = 0;

        TextView player1Score = (TextView) findViewById(R.id.player1Score);
        TextView player2Score = (TextView) findViewById(R.id.player2Score);

        player1Score.setText("0");
        player2Score.setText("0");
    }

}
