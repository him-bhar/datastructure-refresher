package com.himanshu.poc.ds.linkedlist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A sample linkedlist implementation, exposing various basic features
 * @author himanshu
 *
 */
public class LinkedList {
  private static Logger logger = LoggerFactory.getLogger(LinkedList.class);
  private Node root;
  
  private Node addLast(Node node, int data) {
    if (node == null) {
      logger.info("Node is being created now with data: {}", data);
      Node n = new Node(data);
      return n;
    } else {
      node.setNext(addLast(node.getNext(), data));
      return node;
    }
  }
  
  private Node delete(Node node, int data) {
    if (node == null) {
      logger.info("No matching node found");
      return null;
    }
    if (node.getData() == data) {
      logger.info("Node is being deleted now with data: {}", data);
      return node.getNext();
    } else {
      node.setNext(delete(node.getNext(), data));
      return node;
    }
  }
  
  //It can be done through for-loop but trying it via recursion
  private Node deleteAtPos(Node node, int currentPos, int posToDelete) {
    if (currentPos == posToDelete) {
      return node.getNext();
    } else {
      ++currentPos;
      node.setNext(deleteAtPos(node.getNext(), currentPos, posToDelete));
      return node;
    }
  }
  
  private int findLength(Node node) {
    if (node == null) {
      return 0;
    } else {
      return 1+findLength(node.getNext());
    }
  }
  
  public void add(int data) {
    root = addLast(root, data);
  }
  
  public void delete(int data) {
    root = delete(root, data);
  }
  
  public void deleteAtPos(int pos) {
    root = deleteAtPos(root, 0, pos);
  }
  
  public int length() {
    return findLength(root);
  }
  
  @Override
  public String toString() {
    return "LinkedList [root=" + root + "]";
  }
  
  public void swapNodes(int dataX, int dataY) {
    Node prevX = null, currX = root;
    while (currX != null && currX.getData() != dataX) {
      prevX = currX;
      currX = currX.getNext();
    }
    
    Node prevY = null, currY = root;
    while (currY != null && currY.getData() != dataY) {
      prevY = currY;
      currY = currY.getNext();
    }
    
    if(currX == null || currY == null) {
      System.out.println("No matching nodes to swap. Exiting");
      return;
    }
    
    if (prevX != null) {
      prevX.setNext(currY);
    } else {
      root = currY;
    }
    
    if (prevY != null) {
      prevY.setNext(currX);
    } else {
      root = currX;
    }
    
    //Swap next pointers
    Node temp = currX.getNext();
    currX.setNext(currY.getNext());
    currY.setNext(temp);
  }
  
  private Node reverse(Node node) {
    Node prev = null;
    Node current = node;
    Node next = null;  //Just a holder
    while (current != null) {
      next = current.getNext();
      current.setNext(prev);
      prev = current;
      current = next;
    }
    node = prev;
    return node;
  }
  
  public void reverse() {
    root = reverse(root);
  }
  
  public Node sortedMerge(Node a, Node b) {
    Node resultRoot = new Node(Integer.MIN_VALUE);
    Node currX = a;
    Node currY = b;
    Node resultNode = resultRoot;
    while (currX != null && currY != null) {
      if (currX.getData() < currY.getData()) {
        resultNode.setNext(currX);
        currX = currX.getNext();
        
      } else {
        resultNode.setNext(currY);
        currY = currY.getNext();
      }
      resultNode = resultNode.getNext();
    }
    if (currX != null) {
      resultNode.setNext(currX);
      currX = currX.getNext();
      resultNode = resultNode.getNext();
    }
    if (currY != null) {
      resultNode.setNext(currY);
      currY = currY.getNext();
      resultNode = resultNode.getNext();
    }
    return resultRoot;
  }
  
  public Node sortedMerge(Node b) {
    Node a = this.root;
    return sortedMerge(a, b);
  }
  
  public int detectAndRemoveLoop(Node node) {
    Node slow = node;
    Node fast = node;
    while (slow != null && fast != null && fast.getNext() != null) {
      slow = slow.getNext();
      fast = fast.getNext().getNext();
      
      if (fast == slow) {
        System.out.println("Found the loop");
        removeLoop(slow, node);
        return 1;
      }
    }
    return 0;
  }
  
  private void removeLoop(Node loopNode, Node node) {
    Node ptr1 = loopNode;
    Node ptr2 = loopNode;
    
    int loopNodeCount = 0;
    
    //Taking ptr1 as base, we iterated prt2 one element at a time to reach it back to ptr1. 
    //This completes 1 complete loop in linkedList
    while (ptr2.getNext() != ptr1) {
      ptr2 = ptr2.getNext();
      loopNodeCount++;
    }
    
    //Both pointers pointing to root right now.
    ptr1 = root;
    ptr2 = root;
    
    //Moving one pointer k steps ahead.
    for (int i=0;i<loopNodeCount;i++) {
      ptr2 = ptr2.getNext();
    }
    
    //Move both pointers at the same pace. They will meet at loop starting node
    while (ptr2 != ptr1) {
      ptr2 = ptr2.getNext();
      ptr1 = ptr1.getNext();
    }
    
    //Get pointer to the last node
    ptr2 = ptr2.getNext();
    while (ptr2.getNext() != ptr1) {
        ptr2 = ptr2.getNext();
    }
    
    /* Set the next node of the loop ending node
    to fix the loop */
    ptr2.setNext(null);
    
  }

  public static void main(String[] args) {
    LinkedList lList = new LinkedList();
    lList.add(10);
    lList.add(20);
    lList.add(30);
    lList.add(5);
    System.out.println(lList);
    lList.delete(10);
    System.out.println(lList);
    lList.delete(30);
    System.out.println(lList);
    lList.delete(5);
    System.out.println(lList);
    lList.delete(21);
    System.out.println(lList);
    lList.delete(20);
    System.out.println(lList);
    
    lList = new LinkedList();
    
    lList.add(10);
    lList.add(20);
    lList.add(30);
    lList.add(5);
    lList.add(31);
    lList.add(23);
    lList.add(40);
    lList.deleteAtPos(2);
    System.out.println(lList);
    lList.deleteAtPos(0);
    System.out.println(lList);
    System.out.println(lList.length());
    
    lList.swapNodes(20, 31);
    System.out.println(lList);
    
    lList.reverse();
    System.out.println(lList);
    lList = new LinkedList();
    
    LinkedList a = new LinkedList();
    a.add(10);
    a.add(20);
    a.add(30);
    
    LinkedList b = new LinkedList();
    b.add(11);
    b.add(25);
    b.add(27);
    b.add(31);
    
    Node sortedMerged = a.sortedMerge(b.root);
    System.out.println(sortedMerged);
    
    a = new LinkedList();
    b = new LinkedList();
    
    lList = new LinkedList();
    lList.add(10);
    lList.add(20);
    lList.add(30);
    lList.add(5);
  }
  

}
