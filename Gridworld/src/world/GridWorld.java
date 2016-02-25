package world;

import actors.Actor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by dongsoo on 1/21/2016.
 */
public class GridWorld {

    private Actor world[][];
    private Image ground;

    public GridWorld(int numRows, int numCols) {
        world = new Actor[numRows][numCols];

        try {
            ground = new Image(new FileInputStream(".\\images\\ground.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stepWorld() {
        ArrayList<Actor> actors = getAllActors();
        for(Actor a: actors) {
            a.act();
        }
    }

    public ArrayList<Actor> getAllActors() {

        ArrayList<Actor> actors = new ArrayList<Actor>();

        for(int row=0; row < world.length; row++) {
            for (int col = 0; col < world[row].length; col++) {
                if(world[row][col] != null) {
                    actors.add(world[row][col]);
                }
            }
        }

        return actors;
    }

    public void drawWorld(GraphicsContext gc) {
        for(int row=0; row < world.length; row++) {
            for(int col=0; col < world[row].length; col++) {

                //draw the ground first
                gc.drawImage(ground, 50 + col*25, 20 + row*25);

                //draw the actors if they are in this location
                if(world[row][col] != null) {
                    world[row][col].draw(gc);
                }
            }
        }
    }

    public void addActor(Actor a) {
        world[a.getMyLoc().getRow()][a.getMyLoc().getCol()] = a;
    }

    public boolean isValidLoc(Location loc) {
        return loc.getRow() >= 0 &&
                loc.getRow() < world.length &&
                loc.getCol() >= 0 &&
                loc.getCol() < world[loc.getRow()].length;
    }

    public Actor getActor(Location loc) {
        if(isValidLoc(loc))
            return world[loc.getRow()][loc.getCol()];

        return null;
    }

    public void moveActor(Actor actor, Location nextMove) {
        //erase the actor from the world
        world[actor.getMyLoc().getRow()][actor.getMyLoc().getCol()] = null;

        //place him in new location
        world[nextMove.getRow()][nextMove.getCol()] = actor;
    }
}