package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/1/2016.
 */
public class Bug extends Actor {

    public Bug(Location myLoc, GridWorld world) {
        super(myLoc, world);
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\bug.png")));
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
            myWorld.addActor(new Flower(oldLoc,myWorld));
            setMyLoc(nextMove);
        }
        else {
            super.act(); //turn baby!!!!
        }
    }

}
