package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/2/2016.
 */
public class BoxBug extends Bug {
    private int sideLength;
    private int currentStep;

    public BoxBug(Location myLoc, GridWorld MyWorld, int sideLength) {
        super(myLoc, MyWorld);
        this.sideLength=sideLength;
        currentStep=0;
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\boxBug.png")));
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
