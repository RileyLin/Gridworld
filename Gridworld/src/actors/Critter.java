package actors;

import world.GridWorld;
import world.Location;

import java.util.ArrayList;

/**
 * Created by dongsoo on 2/11/2016.
 */
public class Critter extends Bug{
    public Critter(Location myLoc, GridWorld world) {
        super(myLoc, world);
    }
    public ArrayList<Actor> getNeighbors(){
        ArrayList<Actor> neighbors = new ArrayList<Actor>();
        for(int row=getMyLoc().getRow()-1;row<=getMyLoc().getRow()+1;row++){
            for(int col=getMyLoc().getCol()-1;col<=getMyLoc().getCol()+1;col++){
                Actor temp=getMyWorld().getActor(new Location(row,col));
                if(temp != null && temp!=this&&!(temp instanceof Flower)&&!(temp instanceof Critter)){
                    neighbors.add(temp);
                }
            }
        }
        return neighbors;
        //create and return an Arraylist of all the actors within one location of me
    }

    public void processActors(ArrayList<Actor> actors){
        ArrayList<Actor>neighbors=getNeighbors();
        if(actors.size()==0)
            return;
        int whoDies=(int)(Math.random()*actors.size());
      //  actors.get(whoDies).die();

        //get an arraylist of actors and do something to them(eat, chase change color)
    }
    public ArrayList<Location> getPossibleMoveLocs(){
        //create and populate an ArrayList of Locations with all the possible move locations for this critter
        ArrayList<Location> possibleMoves= new ArrayList<Location>();
        GridWorld world=getMyWorld();
        Location myLoc=getMyLoc();
        for(int row=getMyLoc().getRow()-1;row<=getMyLoc().getRow()+1;row++){
            for(int col=getMyLoc().getCol()-1;col<=getMyLoc().getCol()+1;col++){
                if(world.getActor(new Location(row,col))==null ||
                        world.getActor(new Location(row,col)) instanceof Flower){
                    if(world.isValidLoc(new Location(row,col))){
                        possibleMoves.add(new Location(row, col));
                    }
                }
            }
        }

        return possibleMoves;
    }

    public Location selectMoveLocation(ArrayList<Location> locs){
        //tkae in an ArrayList of possible move location and choose one to actually move to

        ArrayList<Location> possibleLocs=getPossibleMoveLocs();
        System.out.println("current Critterr location: " + getMyLoc());
        if(possibleLocs.size()==0){
            System.out.println("no possible moves for critter");
            return null;
        }

        int moveIndex=(int)(Math.random()*possibleLocs.size());
        System.out.println("critter next move: "+ possibleLocs.get(moveIndex));
        return possibleLocs.get(moveIndex);


    }

    @Override
    public void act() {
        ArrayList<Actor> neighbors =getNeighbors();
        processActors(neighbors);
        ArrayList<Location> possibleMoveLocs=getPossibleMoveLocs();
        Location moveLoc=selectMoveLocation(possibleMoveLocs);
        if(moveLoc!=null){
            getMyWorld().moveActor(this, moveLoc);
            setMyLoc(moveLoc);
        }
        else {
            super.act();
        }

    }
}