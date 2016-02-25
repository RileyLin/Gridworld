package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/3/2016.
 */
public class CircleBug extends Actor {
    private int sideLength;
    private int currentStep;


    public CircleBug(Location myLoc, GridWorld world,int sideLength) {
        super(myLoc,world);
        this.sideLength = sideLength;
        currentStep = 0;
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\circleBug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void act() {
        GridWorld myWorld=getMyWorld();
        Location oldLoc = getMyLoc();
        Location nextMove = getMyLoc().getLocInDirection(getDirection());
        System.out.println("NextMove: " + nextMove);
        if(getMyWorld().isValidLoc(nextMove) && (getMyWorld().getActor(nextMove) == null||myWorld.getActor(nextMove) instanceof Flower)) {
            getMyWorld().moveActor(this, nextMove);
            myWorld.addActor(new Flower(oldLoc, myWorld));
            setMyLoc(nextMove);
            currentStep++;
            if (currentStep == sideLength) {
                turnRight();
                currentStep = 0;
            }
        } else {//he couldn't move
            turnRight();
            turnRight();
            currentStep = 0;

        }
    }
}