//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class recursively finds the number of paths between two paths, and outputs all the steps between the two points
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
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Scanner;
/**
 * This class recursively finds the number of paths between two paths, and outputs all the steps between the two points
 */
public class PathUtils {
	/**
	 * Constructor for PathUtils. It is empty
	 */
	public PathUtils() {
		
	}
	/**
	 * Recursively counts the number of paths between two Intersections
	 * @param start - the starting Intersection
	 * @param end - the ending Intersection
	 * @return the number of unique paths between the two Intersections, such that one only travels north and east
	 */
	public static int countPaths(Intersection start, Intersection end) {
		int count = 0; // creates and initializes the counter
		if(start.getX() > end.getX() || start.getY() > end.getY()) {
			//if statement checks if start exceeds end coordinates, returns 0 if so
			return count;
		}
		if(start.equals(end)) {
			//if statement checks if start equals end, returns 1 if so
			return 1;
		}
		if(start.getX() <= end.getX() && start.getY() <= end.getY()) {
			//If statement checks if start is less than or equal to end.
			count = count + countPaths(start.goEast(), end); //Creates new pathway that goes east, if it exceeds end coordinate, will return 0
			// if pathway reaches end, return 1 will go all the way back through the recursion and increment count
			count = count + countPaths(start.goNorth(), end);	// Creates new pathway that goes north, if it exceeds end coordinates, will return 0
		}
		return count; //returns count at the end, represents number of paths between Intersections
	}
	/**
	 * Recursively creates a Path ArrayList of the Intersections from starting intersection to the ending.
	 * @param start - the starting Intersection
	 * @param end - the ending Intersection
	 * @return - the Path ArrayList of the Intersections
	 */
	public static ArrayList <Path> findAllPaths(Intersection start, Intersection end){
		ArrayList <Path> paths = new ArrayList <Path> (); // Path Arraylist to be returned
		if(start.equals(end)) { // if start is same as end, adds the end intersection to the empty arrayList
			Path hold = new Path();
			hold.addHead(end);
			paths.add(hold);
			return paths; //returns paths to the previous call of findAllPaths
		}
		if(start.getX() <= end.getX() && start.getY() <= end.getY()) { // Checks that starts coordinates are less than or equal to end, then
			paths.addAll(findAllPaths(start.goEast(), end)); // Adds return Arraylist to current calls arraylist when going east
			paths.addAll(findAllPaths(start.goNorth(), end));// Adds return Arraylist to current calls arraylist when going east
		}
		for(int i = 0; i < paths.size(); i++) {
			paths.get(i).addHead(start); //Loops through arraylist and adds the starting coordinate
		}
		return paths; // returns the arraylist
		
	}
}
