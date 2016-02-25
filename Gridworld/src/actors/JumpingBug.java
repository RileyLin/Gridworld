package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/9/2016.
 */
public class JumpingBug extends Actor {

    public JumpingBug(Location myLoc, GridWorld world) {
        super(myLoc, world);
        setDirection(3);
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\jumpingBug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void act(){
        GridWorld myWorld=getMyWorld();
        Location oldLoc = getMyLoc();
        Location nextMove = getMyLoc().getLocInDirection(getDirection());
        if(getDirection()==Location.NORTH)
            nextMove=new Location(getMyLoc().getRow()-2,getMyLoc().getCol());
        else if(getDirection()==Location.NORTHEAST)
            nextMove=new Location(getMyLoc().getRow()-2,getMyLoc().getCol()+2);
       else if(getDirection()==Location.EAST)
            nextMove=new Location(getMyLoc().getRow(),getMyLoc().getCol()+2);
        else if(getDirection()==Location.SOUTHEAST)
            nextMove=new Location(getMyLoc().getRow()+2,getMyLoc().getCol()+2);
        else if(getDirection()==Location.SOUTH)
            nextMove=new Location(getMyLoc().getRow()+2,getMyLoc().getCol());
        else if(getDirection()==Location.SOUTHWEST)
            nextMove=new Location(getMyLoc().getRow()+2,getMyLoc().getCol()-2);
        else if(getDirection()==Location.WEST)
            nextMove=new Location(getMyLoc().getRow(),getMyLoc().getCol()-2);
        else if(getDirection()==Location.NORTHWEST)
            nextMove=new Location(getMyLoc().getRow()-2,getMyLoc().getCol()-2);
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

