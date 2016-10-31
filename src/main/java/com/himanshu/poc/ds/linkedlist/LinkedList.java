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
  
  public void add(int data) {
    root = addLast(root, data);
  }
  
  public void delete(int data) {
    root = delete(root, data);
  }
  
  @Override
  public String toString() {
    return "LinkedList [root=" + root + "]";
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
  }
  

}
