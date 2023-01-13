//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    This class tests the PathUtils class methods. It makes sure that the outputs ouput as they should
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
 * This class tests the PathUtils class methods
 */
public class PathUtilsTester {
	/**
	 * Constructor for PathUtilsTester. This constructor is empty
	 */
	public PathUtilsTester() {
		
	}
	/**
	 * Tests the case of countPaths() when there are no valid Paths. 
	 * For example, when the start position is Intersection(1, 1) and the 
	 * ending position is Intersection(0, 1), there should be no valid Paths, 
	 * so countPaths() should return 0.
	 * @return true if method works, false otherwise
	 */
	public static boolean testCountPathsNoPath() {
		Intersection start = new Intersection(1 ,1); //Creates starting intersection larger than the end
		Intersection end = new Intersection(0, 1);
		if(PathUtils.countPaths(start, end) == 0) { // makes sure that size of paths is 0 since start cannot be bigger than end
			return true;
		}
		else {
			return false; // return false if countPaths isn't working
		}
	
	}
	/**
	 * Tests the case of countPaths() when there is a single valid Path.
	 *  For example, when the start position is Intersection(1, 1) and the 
	 *  ending position is Intersection(1, 2), there should be a single Path, 
	 *  so countPaths() should return 1.
	 * @return true if method works, false otherwise
	 */
	public static boolean testCountPathsOnePath() {
		Intersection start = new Intersection(1 ,1); // creates new start intersection 1 point east of end point
		Intersection end = new Intersection(2, 1);
	//	System.out.println(PathUtils.countPaths(start, end));
		if(PathUtils.countPaths(start, end) == 1) { // makes sure that number of paths is 1 since there is only 1 path possible
			return true;
		}
		else {
			return false; //returns false if countPaths isn't working
		}
	}
	/**
	 * Tests the case of countPaths() when there are multiple possible paths. 
	 * For example, when the start position is Intersection(0, 0) and the 
	 * ending position is Intersection(1, 2), 
	 * there should be three possible Paths, so countPaths() should return 3.
	 * @return true if method works, false otherwise
	 */
	public static boolean testCountPathsRecursive() {
		Intersection start = new Intersection(0, 0); // creates start intersection many points away from end 
		Intersection end = new Intersection(1, 2);
	//	System.out.println(PathUtils.countPaths(start, end));
		if(PathUtils.countPaths(start, end) == 3) { //Makes sure that number of paths is 3, since there are only 3 ways to go
			
			return true;
		}
		else {
			return false; // returns false if countPaths isn't working
		}
	}
/**
 * Tests the case of findAllPaths() when there are no valid Paths. 
 * For example, when the start position is Intersection(1, 1) and the 
 * ending position is Intersection(0, 1), there should be no valid Paths, 
 * so findAllPaths() should return an empty ArrayList.
 * @return true if method works, false otherwise
 */
	public static boolean testFindAllPathsNoPath() {
		Intersection start = new Intersection(0, 1); // creates start larger than end
		Intersection end = new Intersection(0, 0);
	//	System.out.println(PathUtils.findAllPaths(start, end));
		if(PathUtils.findAllPaths(start, end).size() == 0) { // makes sure returned arraylist is empty, because no path from start to end
			
			return true; 
		}
		else {
			return false; // returns false if findAllPaths isn't working
		}
	}
/**
 * Tests the case of findAllPaths() when there is a single valid Path.
 *  For example, when the start position is Intersection(1, 1) and the 
 *  ending position is Intersection(1, 2), there should be a single Path. 
 *  For each of your cases, ensure that there is only a single path,
 *   and that the Path exactly matches what you expect to see.
 * @return true if method works, false otherwise
 */
	public static boolean testFindAllPathsOnePath() {
		Intersection start = new Intersection(0, 0); // creates start 1 point east of end
		Intersection end = new Intersection(1, 0);
		ArrayList <Path> tester = PathUtils.findAllPaths(start, end); //arraylist holds output of findAllPaths
	//	System.out.println(tester.get(0));
		if(tester.size() == 1 && tester.get(0).toString().equals("(0,0)->(1,0)")) { //Checks that arraylist size is 1 and arraylist index 0 value is equal to expected output
			return true;
		}
		else {
			return false; // returns false if findAllPaths isn't working
		}
	}
	/**
	 * Tests the case of findAllPaths() when there are multiple possible paths. 
	 * For example, when the start position is Intersection(0, 0) and the ending position is 
	 * Intersection(1, 2), there should be three possible Paths. 
	 * For each of your cases, ensure that there is both the correct number of Paths, and 
	 * that the returned Paths exactly match what you expect to see.
	 * @return true if the method works, false otherwise
	 */
	public static boolean testFindAllPathsRecursive() {
		Intersection start = new Intersection(0, 0); // creates start Intersection many points smaller than end Intersection
		Intersection end = new Intersection(1, 2);
	//	System.out.println(PathUtils.findAllPaths(start, end));
		ArrayList <Path> tester = PathUtils.findAllPaths(start, end);//arraylist holds output of findAllPaths
		if(tester.size() == 3) { // makes sure arraylist size is 3, since thats how many unique paths there are
			ArrayList <String> superArray = new ArrayList <String> (); // creates super arraylist that contains all expected outputs
			superArray.add("(0,0)->(1,0)->(1,1)->(1,2)");
			superArray.add("(0,0)->(0,1)->(1,1)->(1,2)");
			superArray.add("(0,0)->(0,1)->(0,2)->(1,2)");
			if(superArray.contains(tester.get(0).toString()) &&	superArray.contains(tester.get(1).toString()) && superArray.contains(tester.get(2).toString())){
				//if statement checking that smaller arraylist contains all expected outputs that the super arraylist holds
				return true;
			}
			else {
				return false; // returns false if not all strings are contained
			}
		}
		else {
			return false; // returns false if size is not correct
		}
	}
	/**
	 * Main method of class
	 * @param args - input 
	 */
	public static void main(String[] args) {
		//Outputs all boolean methods to test PathUtils
		System.out.println(PathUtilsTester.testCountPathsNoPath());
		System.out.println(PathUtilsTester.testCountPathsOnePath());
		System.out.println(PathUtilsTester.testCountPathsRecursive());
		System.out.println(PathUtilsTester.testFindAllPathsNoPath());
		System.out.println(PathUtilsTester.testFindAllPathsOnePath());
		System.out.println(PathUtilsTester.testFindAllPathsRecursive());
		//given code for driver method
		try (Scanner keyboard = new Scanner(System.in)) {
			int startX, startY, endX, endY;
			String input = "Y";
			while (input.equalsIgnoreCase("Y")) {
				System.out.print("Enter starting X coordinate: ");
				startX = keyboard.nextInt();
				System.out.print("Enter starting Y coordinate: ");
				startY = keyboard.nextInt();
				System.out.print("Enter ending X coordinate: ");
				endX = keyboard.nextInt();
				System.out.print("Enter ending Y coordinate: ");
				endY = keyboard.nextInt();
				Intersection start = new Intersection(startX, startY);
				Intersection end = new Intersection(endX, endY);
				System.out.println("Number of paths from start to end: "
				+ PathUtils.countPaths(start, end));
				System.out.println("List of possible paths:");
				for (Path p : PathUtils.findAllPaths(start, end)) {
				System.out.println(p);
		}
		do {
			System.out.print("Try another route? (Y/N): ");
			input = keyboard.next();
		} while (!input.equalsIgnoreCase("Y")
				&& !input.equalsIgnoreCase("N"));
			}
		}
	}
}
