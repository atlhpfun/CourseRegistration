//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models each coordinate in a Path. 
//			It also allows for creating a new coordinate that is +1 in any direction
// Course:   CS 300 Fall 2022
//
// Author:   Rohan Balachander
// Email:   rbalachander@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Vincent Gallegos
// Partner Email:   vggallegos@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   __x_ Write-up states that pair programming is allowed for this assignment.
//   __x_ We have both read and understand the course Pair Programming Policy.
//   __x_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         
// Online Sources:  
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * This class models an intersection in a street block 
 */
public class Intersection {
	private final int x; // x coordinate
	private final int y; // y coordinate
	/**
	 * Constructor for the class. Takes input x y and creates an Intersection
	 * @param x - input x
	 * @param y - input y
	 */
	public Intersection(int x, int y) {
		this.x = x;
		this.y = y;
	}
	/**
	 * Gets the X coordinate
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}
	/**
	 * Gets the y coordinate
	 * @return - the y coordinate
	 */
	public int getY() {
		return y;
	}
	/**
	 * Converts the coordinate into a coordinate point form
	 * @return the string of the coordinate point
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	/**
	 * Checks if two Intersections are equal. They are equal if they have the same x and y coordinate
	 * @return - returns true if they are equal, false otherwise
	 */
	public boolean equals(Object o) {
		if(o instanceof Intersection) {
			if(((Intersection) o).getX() == this.getX() && ((Intersection) o).getY() == this.getY()) {
				return true;
			}
		}
		return false;
	}
	/**
	 * Creates a new intersection one point north
	 * @return the new intersection
	 */
	public Intersection goNorth() {
		Intersection northern = new Intersection(x, y + 1);
		return northern;
	}
	/**
	 * Creates a new intersection one point south
	 * @return the new intersection
	 */
	public Intersection goSouth() {
		Intersection southern = new Intersection(x, y - 1);
		return southern;
	}
	/**
	 * Creates a new intersection one point east
	 * @return the new intersection
	 */
	public Intersection goEast() {
		Intersection eastern = new Intersection(x + 1, y);
		return eastern;
	}
	/**
	 * Creates a new intersection one point western
	 * @return the new intersection
	 */
	public Intersection goWest() {
		Intersection western = new Intersection(x - 1, y);
		return western;
	}
}
