package walidbounouar.tictactoe;

import android.widget.ImageView;

/**
 * Created by walid on 2017-06-10.
 */

public class boardSpace {

    /**
     * int representing who controls this board Space
     * 0 -- no one
     * 1 -- circle
     * 2 -- cross
     */
    private int conqueror;

    /**
     * The image view associated  with this boardSpace
     */
    private ImageView imageView;

    /**
     * Position of the boardSpace
     */
    private String position;

    public boardSpace(String position, ImageView imageView) {

        this.conqueror = 0;
        this.imageView = imageView;
        this.imageView.setImageResource(0);
        this.position = position;

    }

    public int getConqueror() {
        return this.conqueror;
    }

    public void setConqueror(int conqueror) {
        this.conqueror = conqueror;
    }

    public void setImage(int imageID) {

        this.imageView.setAlpha(0f);
        this.imageView.setImageResource(imageID);
        this.imageView.animate().alpha(1f).setDuration(500);

    }

    public void setClickable(boolean clickable) {
        this.imageView.setClickable(clickable);
    }

    public boolean isClickable() {
        return this.imageView.isClickable();
    }

    public String getPosition() {
        return this.position;
    }


}








