// TODO file header
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title:    CourseQueue
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Array-based heap implementation of a priority queue containing Courses. Guarantees the
 * max-heap invariant, so that the Course at the root should have the highest score, and all
 * children always have a score lower than or equal to their parent's.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class CourseQueue implements Iterable<Course>, PriorityQueueADT <Course>/* TODO: add PriorityQueueADT<Course> once Course is Comparable*/ {
  
  // data fields
  private Course[] queue; // array max-heap of courses representing this priority queue
  private int size;       // number of courses currently in this priority queue
  
  /**
   * Creates a new, empty CourseQueue with the given capacity
   * 
   * @param capacity the capacity of this CourseQueue
   * @throws IllegalArgumentException if the capacity is not a positive integer
   */
  public CourseQueue(int capacity) throws IllegalArgumentException{
	  if(capacity < 1) {
		  throw new IllegalArgumentException("Capacity is not positive");
	  }
	  size = 0;
	  queue = new Course[capacity];
	  // TODO complete this constructor, initializing ALL data fields
  }
  
  /**
   * Returns a deep copy of this CourseQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate 
   * courses. Only the instance of the heap (including the array and its size) will be duplicated.
   * 
   * @return a deep copy of this CourseQueue, which has the same capacity and size as this queue.
   */
  public CourseQueue deepCopy() {
    CourseQueue hold = new CourseQueue(queue.length);
    for(int i = 0; i < size; i++) {
    	hold.enqueue(queue[i]);
    }
    return hold;
  }
  
  /**
   * Returns an Iterator for this CourseQueue which proceeds from the highest-priority to the
   * lowest-priority Course in the queue. Note that this should be an iterator over a DEEP COPY
   * of this queue.
   * 
   * @see CourseIterator
   * @return an Iterator for this CourseQueue
   */
  @Override
  public Iterator<Course> iterator() {
    CourseIterator hold = new CourseIterator(this.deepCopy());
    return hold;
  }
  
  ///////////////////////////// TODO: PRIORITY QUEUE METHODS //////////////////////////////////
  // Add the @Override annotation to these methods once this class implements PriorityQueueADT!
  
  /**
   * Checks whether this CourseQueue is empty
   * 
   * @return {@code true} if this CourseQueue is empty
   */
  @Override
  public boolean isEmpty() {
    if(size == 0) {
    	return true;
    }
    else {
    	return false;
    }
  }
  
  /**
   * Returns the size of this CourseQueue
   * 
   * @return the size of this CourseQueue
   */
  @Override
  public int size() {
    return this.size; // TODO complete this method
  }
  
  /**
   * Adds the given Course to this CourseQueue and use the percolateUp() method to
   * maintain max-heap invariant of CourseQueue. Courses should be compared using 
   * the Course.compareTo() method.
   * 
   * 
   * @param toAdd Course to add to this CourseQueue
   * @throws NullPointerException if the given Course is null
   * @throws IllegalStateException with a descriptive error message if this CourseQueue is full
   */
  public void enqueue(Course toAdd) throws NullPointerException, IllegalStateException {
   if(toAdd == null) {
	   throw new NullPointerException("toAdd is null");
   }
   if(queue.length == size) {
	   throw new IllegalStateException("CourseQueue is full");
   }
   size++;
   queue[size - 1] = toAdd;
   percolateUp(size - 1);
  }
  
  /**
   * Removes and returns the Course at the root of this CourseQueue, i.e. the Course
   * with the highest priority. Use the percolateDown() method to maintain max-heap invariant of
   * CourseQueue. Courses should be compared using the Course.compareTo() method.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException with a descriptive error message if this CourseQueue is
   *                                empty
   */
  public Course dequeue() throws NoSuchElementException {
    if(this.isEmpty()) {
    	throw new NoSuchElementException("CourseQueue is empty");
    }
    Course priorityTop = queue[0];
    queue[0] = queue[size - 1];
    size--;
    if(size != 0) {
    	percolateDown(0);
    }
    return priorityTop;
    
  }
  
  /**
   * Returns the Course at the root of this CourseQueue, i.e. the Course with
   * the highest priority.
   * 
   * @return the Course in this CourseQueue with the highest priority
   * @throws NoSuchElementException if this CourseQueue is empty
   */
  public Course peek() throws NoSuchElementException {
	  if(this.isEmpty()) {
		  throw new NoSuchElementException("CourseQueue is empty");
	  }
    return queue[0]; // TODO complete this method
  }
  
  ///////////////////////////// TODO: QUEUE HELPER METHODS //////////////////////////////////
  
  /**
   * Restores the max-heap invariant of a given subtree by percolating its root down the tree. If 
   * the element at the given index does not violate the max-heap invariant (it is higher priority 
   * than its children), then this method does not modify the heap. 
   * 
   * Otherwise, if there is a heap violation, then swap the element with the correct child and 
   * continue percolating the element down the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int index) throws IndexOutOfBoundsException {
    int rightChild = index * 2 + 2;
    int leftChild = index * 2 + 1;
    if(leftChild > size - 1) {
    	return;
    }
    if(rightChild > size - 1) {
    	if(queue[leftChild].compareTo(queue[index]) > 0) {
    		Course hold = queue[index];
        	queue[index] = queue[leftChild];
        	queue[leftChild] = hold;
    	}
    }
    else {
    	if(queue[leftChild].compareTo(queue[rightChild]) >= 0 && queue[leftChild].compareTo(queue[index]) > 0) {
    		Course hold = queue[index];
        	queue[index] = queue[leftChild];
        	queue[leftChild] = hold;
        	percolateDown(leftChild);
    	}
    	else if(queue[rightChild].compareTo(queue[leftChild]) > 0 && queue[rightChild].compareTo(queue[index]) > 0) {
    		Course hold = queue[index];
        	queue[index] = queue[rightChild];
        	queue[rightChild] = hold;
        	percolateDown(rightChild);
    	}
    } 
  }
  
  /**
   * Restores the max-heap invariant of the tree by percolating a leaf up the tree. If the element 
   * at the given index does not violate the max-heap invariant (it is lower priority than its 
   * parent), then this method does not modify the heap.
   * 
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * This method may be implemented iteratively or recursively.
   * 
   * @param index index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int index) throws IndexOutOfBoundsException {
    if(index == 0) {
    	return;
    }
	if(index < 0 || index > size - 1) {
    	throw new IndexOutOfBoundsException("Index out of bounds");
    }
    int parentIndex = (index - 1) / 2;
    if(queue[index].compareTo(queue[parentIndex]) > 0) {
    	Course hold = queue[index];
    	queue[index] = queue[parentIndex];
    	queue[parentIndex] = hold;
    	percolateUp(parentIndex);
    }
  }
  public void print() {
	  for(int i = 0; i < size; i ++) {
		  System.out.println(queue[i].toString());
	  }
  }
  ////////////////////////////// PROVIDED: TO STRING ////////////////////////////////////
  
  /**
   * Returns a String representing this CourseQueue, where each element (course) of the queue is 
   * listed on a separate line, in order from the highest priority to the lowest priority.
   * 
   * @author yiwei
   * @see Course#toString()
   * @see CourseIterator
   * @return a String representing this CourseQueue
   */
  @Override
  public String toString() {
    StringBuilder val = new StringBuilder();
    
    for (Course c : this) {
      val.append(c).append("\n");
    }
    
    return val.toString().trim();
  }

}
