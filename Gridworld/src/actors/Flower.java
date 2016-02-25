package actors;

import javafx.scene.image.Image;
import world.GridWorld;
import world.Location;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Created by dongsoo on 2/4/2016.
 */
public class Flower extends Actor {
    public Flower(Location myLoc, GridWorld world) {
        super(myLoc, world);
        try {
            super.setImage(new Image(new FileInputStream(".\\images\\flower.png")));
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
    @Override
    public void act(){

    }
}
