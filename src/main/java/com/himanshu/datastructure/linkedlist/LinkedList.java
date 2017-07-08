package com.himanshu.datastructure.linkedlist;

import com.himanshu.poc.ds.linkedlist.Node;

/**
 * Created by himanshu on 08-07-2017.
 */
public class LinkedList {
  private Node head;
  private Node currentNode;

  public LinkedList(Node head) {
    this.head = head;
    currentNode = head;
  }

  public void add(Node node) {
    currentNode.setNext(node);
    currentNode = currentNode.getNext();
  }

  public Node getHead() {
    return head;
  }
}
