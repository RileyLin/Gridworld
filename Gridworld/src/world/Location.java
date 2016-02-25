package world;

/**
 * Created by dongsoo on 1/21/2016.
 */
public class Location {

    public static final int NORTH = 1;
    public static final int NORTHEAST = 2;
    public static final int EAST = 3;
    public static final int SOUTHEAST = 4;
    public static final int SOUTH = 5;
    public static final int SOUTHWEST = 6;
    public static final int WEST = 7;
    public static final int NORTHWEST = 8;

    private int row;
    private int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Location getLocInDirection(int dir) {
        if(dir == NORTH) {
            return new Location(row-1,col);
        }
        else if(dir == SOUTH) {
            return new Location(row+1,col);
        }
        else if(dir == EAST) {
            return new Location(row,col+1);
        }
        else if(dir == WEST) {
            return new Location(row,col-1);
        }
        else if(dir == NORTHEAST) {
            return new Location(row-1,col+1);
        }
        else if(dir == SOUTHEAST) {
            return new Location(row+1,col+1);
        }
        else if(dir == SOUTHWEST) {
            return new Location(row+1,col-1);
        }
        else if(dir == NORTHWEST) {
            return new Location(row-1,col-1);
        }

        return null;//stupid asked for incorrect dir
    }

    @Override
    public String toString() {
        return "Location{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}