package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/18/2016.
 */
public class MamaBug extends Bug{
    public MamaBug(Location myLoc, GridWorld world) {
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
        Location oldoldLoc = new Location(getMyLoc().getRow(),getMyLoc().getCol()-1);
        Location oooldLoc= new Location(getMyLoc().getRow(),getMyLoc().getCol()+1);
        System.out.println("NextMove: " + nextMove);
        if(getMyWorld().isValidLoc(nextMove) && (getMyWorld().getActor(nextMove) == null||myWorld.getActor(nextMove) instanceof Flower)) {
            getMyWorld().moveActor(this, nextMove);
            myWorld.addActor(new Flower(oldLoc,myWorld));
            setMyLoc(nextMove);
        }
        else {
            double d=Math.random();
            turnRight();
            turnRight();
            //turn baby!!!!
            if(d<0.05){
            if(getMyWorld().isValidLoc(oldLoc))
                myWorld.addActor(new Bug(oldoldLoc,myWorld));
            else if(getMyWorld().isValidLoc(oooldLoc))
                myWorld.addActor(new Bug(oooldLoc,myWorld));
            }
        }
    }


}
