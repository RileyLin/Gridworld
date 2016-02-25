package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/11/2016.
 */
public class DancingBug extends BoxBug {

    private int turns[];
    private int index;//where I am in the turns array
    private int currentStep;
    private int sideLength;

    public DancingBug(Location myLoc, GridWorld MyWorld, int sideLength, int turns[]) {
        super(myLoc, MyWorld, sideLength);
        index =0;
        this.turns=turns;
        this.sideLength=sideLength;
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\dancingBug.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void act(){
            GridWorld myWorld=getMyWorld();
            Location oldLoc = getMyLoc();
            Location nextMove = getMyLoc().getLocInDirection(getDirection());
            System.out.println("NextMove: " + nextMove);
            if(getMyWorld().isValidLoc(nextMove) && (getMyWorld().getActor(nextMove) == null||myWorld.getActor(nextMove) instanceof Flower)) {
                getMyWorld().moveActor(this, nextMove);
                myWorld.addActor(new Flower(oldLoc, myWorld));
                setMyLoc(nextMove);
                currentStep++;
                if(index<turns.length){
                if (currentStep == sideLength) {
                   for(int i=0;i<turns[index];i++){
                       turnRight();
                   }
                    currentStep=0;
                    index++;

                }
            }else {
                    index=0;
                }
            } else {//he couldn't move
                turnRight();
                currentStep = 0;

            }

        }

    }

