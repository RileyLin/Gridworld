package actors;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 1/21/2016.
 */
public class Actor {

    private Location myLoc;
    private Image image;
    private int direction;
    private GridWorld myWorld;

    public Actor(Location myLoc, GridWorld world) {
        this.myLoc = myLoc;
        direction = Location.NORTH;
        myWorld = world;

        try {
            image = new Image(new FileInputStream(".\\images\\actor.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void draw(GraphicsContext gc) {
        if(direction == Location.NORTH)
            gc.drawImage(image, 50 + myLoc.getCol()*25, 20 + myLoc.getRow()*25);
        else if(direction == Location.EAST) {
            gc.save();
            gc.translate(75 + myLoc.getCol()*25, 20 + myLoc.getRow()*25);
            gc.rotate(90);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
        else if(direction == Location.SOUTH) {
            gc.save();
            gc.translate(75 + myLoc.getCol()*25, 45 + myLoc.getRow()*25);
            gc.rotate(180);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
        else if(direction == Location.WEST) {
            gc.save();
            gc.translate(50 + myLoc.getCol()*25, 45 + myLoc.getRow()*25);
            gc.rotate(270);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
        else if(direction == Location.NORTHEAST) {
            gc.save();
            gc.translate(63 + myLoc.getCol()*25, 18 + myLoc.getRow()*25);
            gc.rotate(45);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
        else if(direction == Location.SOUTHEAST) {
            gc.save();
            gc.translate(80 + myLoc.getCol() * 25, 33 + myLoc.getRow() * 25);
            gc.rotate(135);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
        else if(direction == Location.SOUTHWEST) {
            gc.save();
            gc.translate(62 + myLoc.getCol()*25, 48 + myLoc.getRow()*25);
            gc.rotate(225);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
        else if(direction == Location.NORTHWEST) {
            gc.save();
            gc.translate(45 + myLoc.getCol()*25, 33 + myLoc.getRow()*25);
            gc.rotate(315);
            gc.drawImage(image, 0, 0);
            gc.restore();
        }
    }

    public void act() {
        turnRight();
    }

    public void turnRight() {
        direction++;//turn
        if(direction == 9) {//there is no 5th direction, reset
            direction = Location.NORTH;
        }
    }

    public void turnLeft() {
        direction--;//turn
        if(direction == 0) {//there is no 5th direction, reset
            direction = Location.WEST;
        }
    }


    public Location getMyLoc() {
        return myLoc;
    }

    public void setMyLoc(Location myLoc) {
        this.myLoc = myLoc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public GridWorld getMyWorld() {
        return myWorld;
    }
}