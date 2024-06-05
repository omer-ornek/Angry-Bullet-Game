/**
 * This code aims to create a primitive angry bullets  game
 * Your aim is to hit any target by adjusting the velocity and angle of shot while not hitting an obstacle.
 * One can start the game via pressing the space key or start again by pressing key r
 * @author Omer Taha Ornek, Student ID: 2022400117
 * @since Date : 09.03.2024
 */
import java.awt.Color;
import java.awt.event.KeyEvent;
public class AngryBulletGame {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(1600,800); // setting canvas
        StdDraw.setXscale(0,1600); // Range of x-axis of the canvas
        StdDraw.setYscale(0,800); // Range of y-axis of the canvas
        final double gravity = 9.80665;
        int m = 0;  // to detect if key r is pressed, m returns tho zero
        while (true) {  // The loop that continues forever
            double x0 = 120 , y0 = 120; // the coordinates of the top-right the shooting platform
            final double bulletVelocity = 180; // velocity of the bullet
            final double bulletAngle = 45.0; // initial angle of the canon
            double[][] obstacleArray = {
                    {1200, 0, 60, 220},
                    {1000, 0, 60, 160},
                    {600, 0, 60, 80},
                    {600, 180, 60, 160},
                    {220, 0, 120, 180}
                    // My obstacle array
                    /*{250,0,80,200},
                    {220,420,70,70},
                    {550,70,130,50},
                    {1000,150,150,90},
                    {725,500,75,100}*/
            };
            double[][] targetArray = {
                    {1160, 0, 30, 30},
                    {730, 0, 30, 30},
                    {150, 0, 20, 20},
                    {1480, 0, 60, 60},
                    {340, 80, 60, 30},
                    {1500, 600, 60, 60}
                    // My target array
                    /*{335,140,50,35},
                    {220,490,70,50},
                    {690,0,70,85},
                    {800,500,40,150},
                    {900,10,75,45},
                    {1300,30,70,90}*/
            };
            int lenObstacle = obstacleArray.length; // finding the number of obstacles
            int lenTarget = targetArray.length; // finding the number of targets
            if (m == 0) { // Start the game with initial conditions if the key r
                StdDraw.clear(Color.WHITE); // Clear the canvas to white to initialize conditions
                Color obstacleColor = Color.darkGray; // Sets the obstacle color to gray
                StdDraw.setPenColor(obstacleColor); // Sets the pencil color to obstacle color
                for (int i =0; i <lenObstacle; i++) {
                    double centerX = obstacleArray[i][0] + obstacleArray[i][2]/2; // x coordinate of the center of the obstacle
                    double centerY = obstacleArray[i][1] + obstacleArray[i][3]/2; // y coordinate of the center of the obstacle
                    double halfWidth = obstacleArray[i][2]/2 , halfHeight = obstacleArray[i][3]/2; // half width and half height of the obstacle
                    StdDraw.filledRectangle(centerX,centerY, halfWidth,halfHeight); // drawing the obstacles to the canvas
                }
                Color targetColor = StdDraw.PRINCETON_ORANGE; // setting the target color to princeton orange
                StdDraw.setPenColor(targetColor); // setting the pencil color to target color
                for ( int i = 0; i < lenTarget; i++) {
                    double centerX = targetArray[i][0] + targetArray[i][2]/2; // x coordinate of the center of the target
                    double centerY = targetArray[i][1] + targetArray[i][3]/2; // y coordinate of the center of the target
                    double halfWidth = targetArray[i][2]/2 , halfHeight = targetArray[i][3]/2;
                    StdDraw.filledRectangle(centerX,centerY, halfWidth,halfHeight); // drawing the target to canvas
                }
                Color platformColor = StdDraw.BLACK;
                StdDraw.setPenColor(platformColor);
                StdDraw.filledRectangle(60,60,60,60); // drawing the shooting platform to the canvas
                StdDraw.setPenColor(Color.WHITE);
                String bulletAngleInitial = String.format("a: %3.1f", bulletAngle); // adds a decimal 0 to bullet angle
                String bulletVelocityInitial = String.format("v: %3.1f", bulletVelocity); // adds a decimal 0 to bullet velocity
                StdDraw.text(60,60,bulletAngleInitial ); // writes the bullet angle to the shooting platform
                StdDraw.text(60,40,bulletVelocityInitial); // writes the bullet velocity to the shooting platform
                double lastAngle = bulletAngle , lastVelocity = bulletVelocity;
                boolean gameRunning = true;
                while (gameRunning) {
                    if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) { // checks if the left arrow key is pressed
                        lastVelocity -= 1; // decrements the velocity 1
                        StdDraw.pause(80); // pauses to increase precision while adjusting velocity
                        StdDraw.enableDoubleBuffering();
                        String bulletAngleString = String.format("a: %3.1f", lastAngle); // updates the angle
                        String bulletVelocityString = String.format("v: %3.1f", lastVelocity); // updates the velocity
                        StdDraw.clear(Color.WHITE); //clears the canvas to delete old canon
                        StdDraw.setPenColor(obstacleColor); // sets the pencil color to obstacle color
                        for (int i = 0; i < lenObstacle; i++) {
                            double centerX = obstacleArray[i][0] + obstacleArray[i][2] / 2; // finds the x coordinate of the center of the obstacle
                            double centerY = obstacleArray[i][1] + obstacleArray[i][3] / 2; // finds the y coordinate of the center of the obstacle
                            double halfWidth = obstacleArray[i][2] / 2, halfHeight = obstacleArray[i][3] / 2; // finds half width and half height
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight); // prints the obstacles to the canvas
                        }
                        StdDraw.setPenColor(targetColor); // sets pencil color to target color
                        for (int i = 0; i < lenTarget; i++) {
                            double centerX = targetArray[i][0] + targetArray[i][2] / 2; // finds the x coordinate of the center of the target
                            double centerY = targetArray[i][1] + targetArray[i][3] / 2; // finds the y coordinate of the center of the target
                            double halfWidth = targetArray[i][2] / 2, halfHeight = targetArray[i][3] / 2; // finds half width and half height
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight); // prints the target to the canvas
                        }
                        StdDraw.setPenColor(platformColor); // sets the pencil color to platform color
                        StdDraw.filledRectangle(60, 60, 60, 60); // prints the platform
                        StdDraw.setPenColor(Color.WHITE);
                        StdDraw.text(60, 60, bulletAngleString); // prints the angle
                        StdDraw.text(60, 40, bulletVelocityString); // prints the velocity
                        StdDraw.disableDoubleBuffering();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                        lastVelocity += 1; // increments the velocity 1
                        StdDraw.pause(80); // to increase precision while increasing
                        StdDraw.enableDoubleBuffering();
                        String bulletAngleString = String.format("a: %3.1f", lastAngle);
                        String bulletVelocityString = String.format("v: %3.1f", lastVelocity);
                        StdDraw.clear(Color.WHITE);
                        StdDraw.setPenColor(obstacleColor);
                        for (int i = 0; i < lenObstacle; i++) {
                            double centerX = obstacleArray[i][0] + obstacleArray[i][2] / 2;
                            double centerY = obstacleArray[i][1] + obstacleArray[i][3] / 2;
                            double halfWidth = obstacleArray[i][2] / 2, halfHeight = obstacleArray[i][3] / 2;
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
                        }
                        StdDraw.setPenColor(targetColor);
                        for (int i = 0; i < lenTarget; i++) {
                            double centerX = targetArray[i][0] + targetArray[i][2] / 2;
                            double centerY = targetArray[i][1] + targetArray[i][3] / 2;
                            double halfWidth = targetArray[i][2] / 2, halfHeight = targetArray[i][3] / 2;
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
                        }
                        StdDraw.setPenColor(platformColor);
                        StdDraw.filledRectangle(60, 60, 60, 60);
                        StdDraw.setPenColor(Color.WHITE);
                        StdDraw.text(60, 60, bulletAngleString);
                        StdDraw.text(60, 40, bulletVelocityString);
                        StdDraw.disableDoubleBuffering();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_UP)) {
                        lastAngle += 1;
                        StdDraw.pause(80);
                        StdDraw.enableDoubleBuffering();
                        String bulletAngleString = String.format("a: %3.1f", lastAngle);
                        String bulletVelocityString = String.format("v: %3.1f", lastVelocity);
                        StdDraw.clear(Color.WHITE);
                        StdDraw.setPenColor(obstacleColor);
                        for (int i = 0; i < lenObstacle; i++) {
                            double centerX = obstacleArray[i][0] + obstacleArray[i][2] / 2;
                            double centerY = obstacleArray[i][1] + obstacleArray[i][3] / 2;
                            double halfWidth = obstacleArray[i][2] / 2, halfHeight = obstacleArray[i][3] / 2;
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
                        }
                        StdDraw.setPenColor(targetColor);
                        for (int i = 0; i < lenTarget; i++) {
                            double centerX = targetArray[i][0] + targetArray[i][2] / 2;
                            double centerY = targetArray[i][1] + targetArray[i][3] / 2;
                            double halfWidth = targetArray[i][2] / 2, halfHeight = targetArray[i][3] / 2;
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
                        }
                        StdDraw.setPenColor(platformColor);
                        StdDraw.filledRectangle(60, 60, 60, 60);
                        StdDraw.setPenColor(Color.WHITE);
                        StdDraw.text(60, 60, bulletAngleString);
                        StdDraw.text(60, 40, bulletVelocityString);
                        StdDraw.disableDoubleBuffering();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
                        lastAngle -= 1;
                        StdDraw.pause(80);
                        StdDraw.enableDoubleBuffering();
                        String bulletAngleString = String.format("a: %3.1f", lastAngle);
                        String bulletVelocityString = String.format("v: %3.1f", lastVelocity);
                        StdDraw.clear(Color.WHITE);
                        StdDraw.setPenColor(obstacleColor);
                        for (int i = 0; i < lenObstacle; i++) {
                            double centerX = obstacleArray[i][0] + obstacleArray[i][2] / 2;
                            double centerY = obstacleArray[i][1] + obstacleArray[i][3] / 2;
                            double halfWidth = obstacleArray[i][2] / 2, halfHeight = obstacleArray[i][3] / 2;
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
                        }
                        StdDraw.setPenColor(targetColor);
                        for (int i = 0; i < lenTarget; i++) {
                            double centerX = targetArray[i][0] + targetArray[i][2] / 2;
                            double centerY = targetArray[i][1] + targetArray[i][3] / 2;
                            double halfWidth = targetArray[i][2] / 2, halfHeight = targetArray[i][3] / 2;
                            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
                        }
                        StdDraw.setPenColor(platformColor);
                        StdDraw.filledRectangle(60, 60, 60, 60);
                        StdDraw.setPenColor(Color.WHITE);
                        StdDraw.text(60, 60, bulletAngleString);
                        StdDraw.text(60, 40, bulletVelocityString);
                        StdDraw.disableDoubleBuffering();
                    }
                    if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                        gameRunning = false; // starts the game by breaking the while loop
                    }
                    double bulletAngleRadian = Math.toRadians(lastAngle); // turns degree into radians
                    double lineX1 = x0 + bulletVelocity * Math.cos(bulletAngleRadian) / 2; // x coordinate of  the canon
                    double lineY1 = y0 + bulletVelocity * Math.sin(bulletAngleRadian) / 2; // y coordinate of  the canon
                    StdDraw.setPenColor(Color.black);
                    StdDraw.setPenRadius(0.004);
                    StdDraw.line(120, 120, lineX1, lineY1); // prints the canon
                }
                double usedVelocity = lastVelocity/1.2; // adjusting the velocity for a better result
                double velocityX = usedVelocity*Math.cos(Math.toRadians(lastAngle)); // velocity in x-axis
                double velocityY = usedVelocity*Math.sin(Math.toRadians(lastAngle)); // velocity in y-axis
                double maxX = x0; // the position of the bullet in the x-axis
                double time = 0;
                double bulletRadius = 4;
                double oldX = 120; // left x coordinate for the line
                double oldY = 120; // left y coordinate for the line
                StdDraw.setPenColor(Color.BLACK);
                while (maxX < 1600) {
                    double currentX, currentY; // create a second variable set for the center of the ball
                    currentX = 120 + velocityX * time; // x coordinate after 0.025 second passed
                    currentY = 120 + velocityY * time - gravity * Math.pow(time, 2); // y coordinate after 0.025 second passed
                    StdDraw.filledCircle(currentX, currentY, bulletRadius); // draws the bullet
                    StdDraw.line(oldX, oldY, currentX, currentY); // draws the line between old and current coordinates
                    oldX = currentX; // updates the value
                    oldY = currentY; // updates the value
                    maxX = currentX; // updates the value
                    StdDraw.pause(20); // to slow down the game
                    if (maxX > 1600) {
                        StdDraw.text(175, 750, "Max X reached. Press 'r' to shoot again.");
                        break;
                    } else if (currentY <= 0) {
                        StdDraw.text(175, 750, "Hit the ground. Press 'r' to shoot again.");
                        break;
                    }
                    for (int k = 0; k < lenObstacle; k++) {
                        double obstacleLeftMostXCoordinate = obstacleArray[k][0];
                        double obstacleRightMostXCoordinate = obstacleArray[k][0] + obstacleArray[k][2];
                        double obstacleTopSideYCoordinate = obstacleArray[k][1] + obstacleArray[k][3];
                        double obstacleBottomSideYCoordinate = obstacleArray[k][1];
                        if (currentY <= obstacleTopSideYCoordinate && currentY >= obstacleBottomSideYCoordinate
                                && currentX <= obstacleRightMostXCoordinate && currentX >= obstacleLeftMostXCoordinate) {
                            // Checking if the bullet's current coordinates are inside an obstacle, or basically hitting
                            StdDraw.text(175, 750, "Hit an obstacle. Press 'r' to shoot again.");
                            maxX = 2000; // to break the while loop assign maxX to 2000
                            break;
                        }
                    }
                    for (int k = 0; k < lenTarget; k++) {
                        double targetBottomMostYCoordinate = targetArray[k][1];
                        double targetTopMostYCoordinate = targetArray[k][1] + targetArray[k][3];
                        double targetLeftSideXCoordinate = targetArray[k][0];
                        double targetRightSideXCoordinate = targetArray[k][0] + targetArray[k][2];
                        if (currentX >= targetLeftSideXCoordinate && currentX <= targetRightSideXCoordinate
                                && currentY <= targetTopMostYCoordinate && currentY >= targetBottomMostYCoordinate) {
                            // Checking if the bullet's current coordinates are inside a target, or basically hitting
                            StdDraw.text(175, 750, "Congratulations: You hit the target!");
                            maxX = 2000;
                            break;
                        }
                    }
                    time += 0.12;
                }
            }
            else if (StdDraw.isKeyPressed(KeyEvent.VK_R)) {
                m = -1; // when R key is pressed m is assigned to -1 so that after adding 1 it will be zero and the game starts again
            }
            m = m + 1; // increments m by 1 to prevent starting again without pressing r
        }
    }
}