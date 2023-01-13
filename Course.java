// TODO file header
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    Course
//Course:   CS 300 Fall 2022
//
//Author:   Rohan Balachander
//Email:    rbalachander@wisc.edu 
//Lecturer: Mouna Kacem
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//_x__ Write-up states that pair programming is allowed for this assignment.
//_x__ We have both read and understand the course Pair Programming Policy.
//_x__ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Persons:         (identify each by name and describe how they helped)
//Online Sources:  (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class models a university-level course. It contains all relevant information for displaying
 * and ranking courses by their enrollment priority.
 */
public class Course implements Comparable <Course>/* TODO: this must implement an interface */ {
  
  // This field is used to help determine priority of courses in the compareTo method below, as
  // courses within your major department are considered higher priority.
  // For simplicity, please leave this as CS. You may change it for your own purposes later.
  private static final String MAJOR_DEPT = "CS";

  // data fields
  private final String DEPT_NAME;  // department offering this course (e.g. MATH, ART, LING, etc)
  private final int COURSE_NUM;    // number of this course (e.g. 101, 300, etc)
  private final int NUM_CREDITS;   // number of credits this course is worth (1-5)
  private int numSeatsAvailable;   // number of seats available in this course (0 or more)
  private String profName;         // the name of this course's professor, if known
  private double profRating;       // this professor's RateMyProfessor rating (0-5), if known
  
  /**
   * Creates a new Course with the given information. Professor information is not included in
   * this constructor.
   * 
   * @param deptName    department offering this course (must not be blank or null)
   * @param courseNum   number of this course (must be positive; 0 is not a valid course number)
   * @param numCredits  number of credits this course is worth (must be 1-5)
   * @param seats       number of seats currently available (must be non-negative; 0 or greater)
   * @throws IllegalArgumentException if any of the arguments do not fulfill their requirements
   */
  public Course(String deptName, int courseNum, int numCredits, int seats) throws IllegalArgumentException{
    // TODO: update these and complete the constructor according to the description above
    if(deptName == null || deptName.isBlank()|| courseNum <= 0 || numCredits < 1 || numCredits > 5 || seats < 0) {
    	throw new IllegalArgumentException("Invalid inputs for Constructor.");
    }
    this.DEPT_NAME = deptName;
    this.COURSE_NUM = courseNum;
    this.NUM_CREDITS = numCredits;
    this.numSeatsAvailable = seats;
  }
  
  
  ////////////////////////////// PROVIDED: ACCESSOR METHODS ////////////////////////////////////
  
  /**
   * Returns the number of credits this course is worth; used by the priority queue to provide
   * the top N credits worth of courses
   * @author hobbes
   * @return the number of credits this course is worth
   */
  public int getNumCredits() {
    return this.NUM_CREDITS;
  }
  
  /**
   * Determines whether two objects represent the same course by comparing department, number, 
   * credits, and professor (if known).
   * @author hobbes
   * @param o an object to compare this course to
   * @return true if o represents a course with the same department, number, credits, and professor
   *   (if known). number of seats available is NOT considered.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Course) {
      Course other = (Course) o;
      if (this.profName==null || other.profName==null) {
        return this.DEPT_NAME.equals(other.DEPT_NAME) && this.COURSE_NUM==other.COURSE_NUM &&
          this.NUM_CREDITS==other.NUM_CREDITS && this.profName==other.profName;
      }
      else {
        return this.DEPT_NAME.equals(other.DEPT_NAME) && this.COURSE_NUM==other.COURSE_NUM && 
            this.NUM_CREDITS==other.NUM_CREDITS && this.profName.equals(other.profName) && 
            this.profRating==other.profRating;
      }
    }
    return false;
  }
  
  /**
   * Creates a String representation of this course, e.g. "CS 300 (closed) with Hobbes (1.4)"
   * @author hobbes
   * @return a String representing this Course
   */
  @Override
  public String toString() {
    String retval = DEPT_NAME+" "+COURSE_NUM;
    
    // seats information
    if (numSeatsAvailable == 0) retval += " (closed)";
    else retval += " ("+numSeatsAvailable+" seats)";
    
    // prof information if available
    if (profName != null) retval += " with "+profName+" ("+profRating+")";
    
    return retval;
  }
  
  //////////////////////////////// TODO: MUTATOR METHODS ////////////////////////////////////
  
  /**
   * Sets the name and RateMyProfessor rating of the professor for this class. If profName is
   * not null, rating must be between 0-5. If profName is null, profRating is ignored.
   * 
   * @param profName the name of this course's professor, if known; null otherwise
   * @param rating this professor's RateMyProfessor rating (0-5); unused if professor is unknown
   * @throws IllegalArgumentException if the professor is non-null AND the rating is invalid; if
   *     professor is null, no exception is thrown
   */
  public void setProfessor(String profName, double rating) throws IllegalArgumentException {
    if(profName != null && (rating < 0 || rating > 5)) {
    	throw new IllegalArgumentException("Professor name exists but rating is invalid.");
    }
    if(profName != null) {
    	this.profRating = rating;
    }
    this.profName = profName;
    
	  
  }
  
  /**
   * Sets the number of seats available in this course as additional seats are added or are taken
   * by other students thoughtlessly registering for courses you want to get into
   * 
   * @param numSeatsAvailable the number of seats currently available in the course, 0 or more
   * @throws IllegalArgumentException if the number of seats is negative
   */
  public void setSeatsAvailable(int numSeatsAvailable) throws IllegalArgumentException{
    if(numSeatsAvailable < 0) {
    	throw new IllegalArgumentException("Seats available can't be negative.");
    }
    this.numSeatsAvailable = numSeatsAvailable;
  }
  
  //////////////////////////////// TODO: INHERITED METHOD ////////////////////////////////////
  
  /**
   * Compares this course to another course to calculate its PRIORITY. Note that two completely
   * different courses may have EQUAL priority under this comparison; please use the provided 
   * equals() method to check whether two course objects are the SAME.
   * 
   * In descending order of importance:
   * 
   * 1. Courses in the MAJOR_DEPT are higher priority (greater) than courses not in MAJOR_DEPT. If 
   *    neither department is in MAJOR_DEPT, the courses are considered equivalent here.
   * 2. Courses with seats available are higher priority than courses with no seats available. A 
   *    class with 200 seats is equivalent to a class with 5 seats available.
   * 3. Courses with KNOWN professors are higher priority than courses without known professors.
   *    No comparison is made between professor names.
   * 4. Courses with higher-ranked profs are higher priority than courses with lower-ranked profs.
   * 
   * For the purposes of this program, course NUMBER is for display purposes only and is not
   * considered for priority.
   * 
   * @param otherCourse the course to compare this one to
   * @return negative if this is less than otherCourse, positive if this is greater than otherCourse,
   *   0 if this is equal to otherCourse
   */
  @Override
  public int compareTo(Course otherCourse) {
//    if(this.equals(otherCourse)) {
//    	return 0;
//    }
	 if(this.DEPT_NAME.equals(MAJOR_DEPT) && !otherCourse.DEPT_NAME.equals(MAJOR_DEPT)) {
		//System.out.println(otherCourse.DEPT_NAME);
    	return 1;
    }
    else if(!this.DEPT_NAME.equals(MAJOR_DEPT) && otherCourse.DEPT_NAME.equals(MAJOR_DEPT)) {
    	return -1;
    }
    else {
    	if(this.numSeatsAvailable > 0 && otherCourse.numSeatsAvailable == 0) {
    		return 1;
    	}
    	else if(this.numSeatsAvailable == 0 && otherCourse.numSeatsAvailable > 0) {
    		return -1;
    	}
    	else {
    		if(this.profName != null && otherCourse.profName == null) {
    			return 1;
    		}
    		else if(this.profName == null && otherCourse.profName != null) {
    			return -1;
    		}
    		else if(this.profName != null && otherCourse.profName != null){
    			if(this.profRating > otherCourse.profRating) {
    				return 1;
    			}
    			else if(this.profRating < otherCourse.profRating) {
    				return -1;
    			}
    			else {
    				return 0;
    			}
    		}
    		else {
    			return 0;
    		}
    	}
    }
  }
}
