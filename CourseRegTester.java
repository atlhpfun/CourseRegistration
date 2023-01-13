import java.util.NoSuchElementException;

// TODO file header
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    CourseRegTester
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
 * This class implements unit test methods to check the correctness of Course,  CourseIterator, 
 * CourseQueue and CourseReg classes in P10.
 * 
 * Be aware that all methods in this class will be run against not only your code, but also our own
 * working and broken implementations to verify that your tests are working appropriately!
 */
public class CourseRegTester {
  
  /**
   * START HERE, and continue with testCompareTo() after this.
   * 
   * This method must test the Course constructor, accessor, and mutator methods, as well as its
   * toString() implementation. The compareTo() method will get its own test.
   * 
   * @see Course
   * @return true if the Course implementation is correct; false otherwise
   */
  public static boolean testCourse() {
    try {
	  Course test = new Course("", -1, 0, -1);
	  return false;
    }
    catch(IllegalArgumentException e) {
    	
    }
    Course test = new Course("CS", 300, 3, 12);
    Course test2 = new Course("CS", 300, 3, 12);
    if(test.getNumCredits() != 3) {
    	return false;
    }
    if(test.equals(test2) == false) {
    	return false;
    }
    if(!test.toString().equals("CS 300 (12 seats)")) {
    	return false;
    }
    try {
    	test.setProfessor(null, 6);
    }
    catch(Exception e) {
    	return false;
    }
    try {
    	test.setProfessor("Kacem", -1);
    	return false;
    }
    catch(IllegalArgumentException e) {
    	
    }
    
    try {
    	test.setSeatsAvailable(-1);
    	return false;
    }
    catch(IllegalArgumentException e) {
    	
    }
    
	  return true; // TODO: complete this test
  }
  
  /**
   * This method must test the Course compareTo() implementation. Be sure to test ALL FOUR levels
   * of the comparison here!
   * 
   * Once you complete this test, finish the Course implementation if you have not done so already,
   * then move to testCourseQueue() and testEnqueueDequeue().
   * 
   * @see Course#compareTo(Course)
   * @return true if the compareTo() implementation is correct; false otherwise
   */
  public static boolean testCompareTo() {
	  //major
	  {
		  Course test = new Course("CS", 300, 3, 12);
		  Course test2 = new Course("Biz", 300, 3, 12);
		  if(test.compareTo(test2) < 0) {
			  return false;
		  }
	  }
	  //seats
	  {
		  Course test = new Course("CS", 300, 3, 0);
		  Course test2 = new Course("CS", 300, 3, 12);
		  if(test.compareTo(test2) > 0) {
			  return false;
		  }
	  }
	  //professor
	  {
		  Course test = new Course("CS", 300, 3, 12);
		  Course test2 = new Course("CS", 300, 3, 12);
		  test.setProfessor("Kacem", 4);
		  if(test.compareTo(test2) <= 0) {
			  return false;
		  }
	  }
	  //rating
	  {
		  Course test = new Course("CS", 300, 3, 12);
		  Course test2 = new Course("CS", 300, 3, 12);
		  test.setProfessor("Kacem", 4);
		  test2.setProfessor("Kacem", 4.5);
		  if(test.compareTo(test2) >= 0) {
			  return false;
		  }
	  }
	  //equal
	  {
		  Course test = new Course("CS", 300, 3, 12);
		  Course test2 = new Course("CS", 300, 3, 12);
		  if(test.compareTo(test2) != 0) {
			  return false;
		  }
	  }
	  return true; // TODO: complete this test
  }
  
  /**
   * This method must test the other methods in CourseQueue (isEmpty, size, peek). Verify normal 
   * cases and error cases, as well as a filled and re-emptied queue.
   * 
   * Once you have completed this method, implement the required methods in CourseQueue and verify
   * that they work correctly.
   * 
   * @see CourseQueue
   * @return true if CourseQueue's other methods are implemented correctly; false otherwise
   */
  public static boolean testCourseQueue() {
	  try {
		  
	  
	  CourseQueue test = new CourseQueue(2);
	  if(test.isEmpty() == false) {
		  return false;
	  }
	  if(test.size() != 0) {
		  return false;
	  }
	  Course tester = new Course("CS", 300, 3, 12);
	  try {
		  test.peek();
		  return false;
	  }
	  catch(NoSuchElementException e) {
		  
	  }
	  test.enqueue(tester);
	  if(test.isEmpty()) {
		  return false;
	  }
	  if(test.size() != 1) {
		  return false;
	  }
	  if(!test.peek().equals(tester)) {
		  return false;
	  }
	  Course tester2 = new Course("CS", 300, 3, 12);
	  tester2.setProfessor("a", 3);
	  test.enqueue(tester2);
	  if(!test.peek().equals(tester2)) {
		  return false;
	  }
	  test.dequeue();
	  if(!test.peek().equals(tester)) {
		  return false;
	  }
	  }
	  catch(Exception e) {
		  return false;
	  }
	  
    return true; // TODO: complete this test
  }
  
  /**
   * This method must test the enqueue and dequeue methods in CourseQueue. Verify normal cases and
   * error cases, as well as filling and emptying the queue.
   * 
   * You may also test the percolate methods directly, though this is not required.
   * 
   * Once you have completed this method, implement the enqueue/dequeue and percolate methods in
   * CourseQueue and verify that they work correctly, then move on to testCourseIterator().
   * 
   * @see CourseQueue#enqueue(Course)
   * @see CourseQueue#dequeue()
   * @return true if the CourseQueue enqueue/dequeue implementations are correct; false otherwise
   */
  public static boolean testEnqueueDequeue() {
	try {
	CourseQueue test = new CourseQueue(5);
	Course one = new Course("CS", 300, 3, 12);
	Course two = new Course("Biz", 300, 3, 12);
	Course three = new Course("CS", 300, 3, 0);
	Course four = new Course("Biz", 300, 3, 0);
	Course five = new Course("CS", 300, 3, 12);
	Course six = new Course("Biz", 300, 3, 12);
	five.setProfessor("Kacem", 5);
	test.enqueue(two);
	if(!test.peek().equals(two)) {
		System.out.println("breh");
		return false;
	}
	test.enqueue(three);
	if(!test.peek().equals(three)) {
		test.print();
		return false;
	}
	test.enqueue(one);
	if(!test.peek().equals(one)) {
		System.out.println("bruhh");
		return false;
	}
	test.enqueue(five);
	if(!test.peek().equals(five)) {
		System.out.println("bruhhh");
		return false;
	}
	test.enqueue(four);
	if(!test.peek().equals(five)) {
		System.out.println("bruh");
		return false;
	}
	try {
		test.enqueue(four);
		return false;
	}
	catch(IllegalStateException e) {
		
	}
	if(!test.dequeue().equals(five)) {
		return false;
	}
	test.dequeue();
	if(!test.peek().equals(three)) {
		return false;
	}
	test.dequeue();
	test.dequeue();
	test.dequeue();
	if(test.isEmpty() == false) {
		return false;
	}
	
	test.enqueue(one);
	test.enqueue(four);
	test.enqueue(six);
	
    return true; // TODO: complete this test
	}
	catch(Exception e) {
		return false;
	}
  }
  
  /**
   * This method must test the CourseIterator class. The CourseIterator iterates through a deep copy
   * of a CourseQueue in decreasing order of priority, returning each Course object in turn.
   * 
   * Once you have completed this method, implement the CourseIterator class and make CourseQueue
   * into an Iterable class. Verify that this works correctly, and then move on to the final test
   * method: testCourseReg().
   * 
   * @see CourseIterator
   * @return true if the CourseIterator implementation is correct; false otherwise
   */
  public static boolean testCourseIterator() {
	try {
    CourseQueue queue = new CourseQueue(5);
	Course one = new Course("CS", 300, 3, 12);
	Course two = new Course("Biz", 300, 3, 12);
	Course three = new Course("CS", 300, 3, 0);
	Course four = new Course("Biz", 300, 3, 0);
	Course five = new Course("CS", 300, 3, 12);
	Course six = new Course("Biz", 300, 3, 12);
	queue.enqueue(one);
	queue.enqueue(two);
	queue.enqueue(three);
	queue.enqueue(four);
	queue.enqueue(five);
	CourseIterator iterator = new CourseIterator(queue);
	if(!iterator.next().equals(one)) {
		return false;
	}
	iterator.next();
	iterator.next();
	iterator.next();
	iterator.next();
	try {
		iterator.next();
		return false;
	}
	catch(NoSuchElementException e) {
		
	}
	return true;
	}
	catch(Exception e) {
		return false;
	}
  }
  
  /**
   * This method must test the constructor and three methods of the CourseReg class (setCreditLoad,
   * add, and getRecommendedCourses). Verify normal cases and error cases.
   * 
   * Once you have completed this method, implement CourseReg and verify that it works correctly.
   * Then you're DONE! Save and submit to gradescope, and enjoy being DONE with programming
   * assignments in CS 300 !!!
   * 
   * @see CourseReg
   * @return true if CourseReg has been implemented correctly; false otherwise
   */
  public static boolean testCourseReg() {
	  
	  return true;
  }
  
  /**
   * This method calls all test methods defined by us; you may add additional methods to this if
   * you like. All test methods must be public static boolean.
   * 
   * @return true if all tests in this class return true; false otherwise
   */
  public static boolean runAllTests() {
    boolean testVal = true;
    
    // test Course
    System.out.print("testCourse(): ");
    if (!testCourse()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test compareTo
    System.out.print("testCompareTo(): ");
    if (!testCompareTo()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseIterator
    System.out.print("testCourseIterator(): ");
    if (!testCourseIterator()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue enqueue/dequeue
    System.out.print("testEnqueueDequeue(): ");
    if (!testEnqueueDequeue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseQueue
    System.out.print("testCourseQueue(): ");
    if (!testCourseQueue()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    // test CourseReg
    System.out.print("testCourseReg(): ");
    if (!testCourseReg()) {
      System.out.println("FAIL");
      testVal = false;
    } else { System.out.println("pass"); }
    
    return testVal;
  }
  
  /**
   * Calls runAllTests() so you can verify your program
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests();
  }
}
