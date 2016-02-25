package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/4/2016.
 */
public class SpiralBug extends Bug {
    private int sideLength;
    private int currentStep;

    public SpiralBug(Location myLoc, GridWorld MyWorld, int sideLength) {
        super(myLoc, MyWorld);
        this.sideLength=sideLength;
        currentStep=0;
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\spiralBug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void act() {
        GridWorld world=getMyWorld();
        Location oldloc=getMyLoc();
        Location nextMove=getMyLoc().getLocInDirection(getDirection());
        System.out.println("Nextmove"+ nextMove);
        if(getMyWorld().isValidLoc(nextMove)&&getMyWorld().getActor(nextMove)==null){
            getMyWorld().moveActor(this,nextMove);
            world.addActor(new Flower(oldloc, world));
            setMyLoc(nextMove);
            currentStep++;
            if (currentStep == sideLength) {
                turnRight();
                turnRight();
                currentStep=0;
            }

        } else   {//he couldn't move
        turnRight();turnRight();
            currentStep=0;

        }

    }
}
