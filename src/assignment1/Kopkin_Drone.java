// Sam Kopkin
// 8/28/2021
// Kopkin_Drone
// Simulation using button, drone movement in x, y, z location

package assignment1;

import java.util.Scanner;

// This class is a blueprint for controlling a drone. It uses a text based menu to simulate how a drone would handle inputs and move accordingly.
public class Kopkin_Drone {
	
	// Position variables (x axis represents East/West, z axis represents North/South)
	private static float x = 0;
	private static float y = 10;
	private static float z = 0;
	
	//
	private static String[] directions = {"North", "East", "South", "West"};
	private static int orientation = 0;
	private static String droneName = "";
	
	public static void main (String[] args) {
		droneName = "Drone 1";
		menu();
	}
	
	// Handles user/menu interaction
	public static void menu() {
		int userInput;
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		do {
			// Print out command menu to console and store user input
			printMenu();
			userInput = 0;
			userInput = input.nextInt();
			
			// Take appropriate action from user input
			switch (userInput) {
				case 1: moveUp(); break;
				case 2: moveDown(); break;
				case 3: moveForward(); break;
				case 4: moveBackward(); break;
				case 5: turnLeft(); break;
				case 6: turnRight(); break;
				case 7: printPosition(); break;
				case 8: System.out.println("Shutting down drone"); break;
				default: System.out.println("Invalid input. Try again.");
			}
		} while (userInput != 8);
	}
	
	// Moves the drone up vertically
	private static void moveUp() {
		y++;
		System.out.println("You have moved up");
	}
	
	// Moves the drone down vertically unless it is on the ground
	private static void moveDown() {
		if (y > 0) {
			y--;
			System.out.println("You have moved down");
		} else {
			System.out.println(getDroneName() + " is already at ground level and cannot descend any further");
		}
	}

	// Checks orientation and moves the drone forward in the direction it is facing
	private static void moveForward() {
		switch (getOrientation()) {
		case "North": z++; break;
		case "East": x++; break;
		case "South": z--; break;
		case "West": x--; break;
		}
		System.out.println("You have moved forward");
	}

	// Checks orientation and moves the drone backwards from the direction it is facing
	private static void moveBackward() {
		switch (getOrientation()) {
		case "North": z--; break;
		case "East": x--; break;
		case "South": z++; break;
		case "West": x++; break;
		}
		System.out.println("You have moved backward");
	}
	
	// Rotates the drone 90 degrees to the left
	private static void turnLeft() {
		if (orientation <= 0) {
			orientation = 3;
		} else {
			orientation--;
		}
		System.out.println("You have turned left");
	}
	
	// Rotates the drone 90 degrees to the right
	private static void turnRight() {
		if (orientation >= 3) {
			orientation = 0;
		} else {
			orientation++;
		}
		System.out.println("You have turned right");
	}

	// Return the X position of the drone
	private static float getX() {
		return x;
	}
	
	// Return the Y position of the drone
	private static float getY() {
		return y;
	}
	
	// Return the Z position of the drone
	private static float getZ() {
		return z;
	}
	
	// Returns the current orientation of the drone
	private static String getOrientation() {
		return directions[orientation];
	}
	
	// Returns this drone's name
	private static String getDroneName() {
		return droneName;
	}
	
	// Print the drone's current position and orientation to the console
	private static void printPosition() {
		System.out.println(getDroneName() + " [x_pos =" + getX() + ", y_pos=" + getY() + ", z_pos=" + getZ() + ", orientation=" + getOrientation() + "]");
	}
	
	// Print a text-based menu to the console
	private static void printMenu() {
		System.out.println("Which Diraction would you like to move the drone?\n"
				+ "1 - Move Up\n"
				+ "2 - Move Down\n"
				+ "3 - Move Forward\n"
				+ "4 - Move Backward\n"
				+ "5 - Turn Left\n"
				+ "6 - Turn Right\n"
				+ "7 - Display Position\n"
				+ "8 - Exit Navigation");
	}
}
