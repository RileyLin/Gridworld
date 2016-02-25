package client;

import actors.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import world.GridWorld;
import world.Location;

public class Main extends Application {

    GridWorld world = new GridWorld(23, 23);

    @Override
    public void start(Stage primaryStage) throws Exception {

        world.addActor(new Bug(new Location(5, 8), world));
        world.addActor(new BoxBug(new Location(15, 15), world, 3));
        world.addActor(new SpiralBug(new Location(13, 5), world, 5));
        world.addActor(new CircleBug(new Location(18, 18), world, 3));
        world.addActor(new zbug(new Location(10, 5), world, 5));
        world.addActor(new JumpingBug(new Location(10, 10), world));
        world.addActor(new DancingBug(new Location(13, 7), world, 3, new int[]{3, 9, 5, 15}));
        world.addActor(new MamaBug(new Location(13, 7), world));
        world.addActor(new Critter(new Location(13, 7), world));
        //panes control layout of window
        StackPane root = new StackPane();
        int width = 800;
        int height = 600;
        //canvas -- a place to draw
        Canvas canvas = new Canvas(width, height);

        //GraphicsContext allow us to draw to a particular canvas
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //add the canvas to the pane,so we can draw yo
        root.getChildren().add(canvas);
        //every javafx app needs at least one scene
        Scene scene = new Scene(root, width, height);
        //every java fx app needs a app
        primaryStage.setTitle("GridWorld");
        //we just added our scene to the stage
        primaryStage.setScene(scene);
        //show the window

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                world.stepWorld();
                world.drawWorld(gc);


                //draw the world

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
