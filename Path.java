//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class models the Path taken between two Intersection points, where
//				the only moves are north and east. The path is held in an arraylist of intersections
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
 * This class models the Path taken between two Intersection points
 */
public class Path {
	private ArrayList <Intersection> intersections; // ArrayList of Intersections between two Intersections
	/**
	 * Constructor for the class Path. It initializes the ArrayList as empty
	 */
	public Path() {
		intersections = new ArrayList <Intersection> ();
	}
	/**
	 * Gets the first index of the ArrayList
	 * @return the first index of the ArrayList
	 * @throws NoSuchElementException if the arrayList is empty
	 */
	public Intersection getHead() throws NoSuchElementException{
		if(intersections.size() == 0) {
			throw new NoSuchElementException ("ArrayList first index is empty");
		}
		else {
			return intersections.get(0);
		}
	}
	/**
	 * Gets the last index of the ArrayList
	 * @return the last index of the ArrayList
	 * @throws NoSuchElementException if the arrayList is empty
	 */
	public Intersection getTail() throws NoSuchElementException{
		if(intersections.size() == 0) {
			throw new NoSuchElementException ("ArrayList last index is empty");
		}
		else {
			return intersections.get(intersections.size() - 1);
		}
	}
	/**
	 * Adds a Intersection to the end of the ArrayList. The instance is only valid if it is 1 point directly north or east of the current tail.
	 * @param toAdd - the Intersection instance to be added to the ArrayList
	 * @throws IllegalArgumentException if the Intersection is not a valid point to add
	 */
	public void addTail(Intersection toAdd) throws IllegalArgumentException{
		if(intersections.size() == 0) {
			intersections.add(toAdd);
		}
		else if(toAdd.getX() == this.getTail().getX() && toAdd.getY() - 1 == this.getTail().getY()) {
			intersections.add(toAdd);
		}
		else if(toAdd.getX() - 1 == this.getTail().getX() && toAdd.getY() == this.getTail().getY()) {
			intersections.add(toAdd);
		}
		else {
			throw new IllegalArgumentException("Intersection to add is not valid");
		}
	}
	/**
	 * Adds a Intersection to the end of the ArrayList. The instance is only valid if it is 1 point directly west or south of the current head.
	 * @param toAdd - the Intersection instance to be added to the ArrayList
	 * @throws IllegalArgumentException if the Intersection is not a valid point to add
	 */
	public void addHead(Intersection toAdd) throws IllegalArgumentException{
		if(intersections.size() == 0) {
			intersections.add(0, toAdd);
		}
		else if(toAdd.getX() == this.getHead().getX() && toAdd.getY() + 1 == this.getHead().getY()) {
			intersections.add(0, toAdd);
		}
		else if(toAdd.getX() + 1 == this.getHead().getX() && toAdd.getY() == this.getHead().getY()) {
			intersections.add(0, toAdd);
		}
		else {
			throw new IllegalArgumentException("Intersection to add is not valid");
		}
	}
	/**
	 * Converts ArrayList of intersections into a string output, with each point surrounded by brackets, and arrays pointing from point to point
	 * @return String representation of arraylist
	 */
	@Override
	public String toString() {
		String toReturn = intersections.get(0).toString();
		if(intersections.size() == 0) {
			return "Empty";
		}
		else {
			for(int i = 1; i < intersections.size(); i++) {
				toReturn = toReturn + "->" + intersections.get(i).toString();
			}
		}
		return toReturn;
	}
}
