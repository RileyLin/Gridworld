package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/8/2016.
 */
public class zbug extends Actor {
    private int sideLength = 0;
    private int currentStep = 0;
    private int turns = 0;

    public zbug(Location myLoc, GridWorld world, int sideLength) {
        super(myLoc, world);
        this.sideLength = sideLength;
        currentStep = 0;
        setDirection(3);
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\zBug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void act() {
        GridWorld myWorld = getMyWorld();
        Location oldLoc = getMyLoc();
        Location nextMove = getMyLoc().getLocInDirection(getDirection());
        System.out.println("Nextmove" + nextMove);

        if(turns!=3){
            if(turns==0||turns==2){
                if(getDirection()==3){
                if(getMyWorld().isValidLoc(nextMove) && (getMyWorld().getActor(nextMove) == null||myWorld.getActor(nextMove) instanceof Flower)) {
                    getMyWorld().moveActor(this, nextMove);
                    myWorld.addActor(new Flower(oldLoc,myWorld));
                    setMyLoc(nextMove);
                    currentStep++;
                    if(currentStep==sideLength){
                        turns++;
                        currentStep=0;
                    }
                }
            }
            else setDirection(3);}
            else if(turns==1){
                if(this.getDirection()==Location.SOUTHWEST){
                if(getMyWorld().isValidLoc(nextMove) && (getMyWorld().getActor(nextMove) == null||myWorld.getActor(nextMove) instanceof Flower)) {
                    getMyWorld().moveActor(this, nextMove);
                    myWorld.addActor(new Flower(oldLoc,myWorld));
                    setMyLoc(nextMove);
                    currentStep++;
                    if(currentStep==sideLength){
                        turns++;
                        currentStep=0;
                    }}
            }else{
                    setDirection(Location.SOUTHWEST);
                }

        }

}}}