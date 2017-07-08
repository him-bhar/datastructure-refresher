package com.himanshu.datastructure.linkedlist;

import com.himanshu.poc.ds.linkedlist.Node;

/**
 * Created by himanshu on 08-07-2017.
 */
public class ReverseLinkedList {
  public static void main(String[] args) {
    Node node1 = new Node(11);
    Node node2 = new Node(12);
    Node node3 = new Node(13);
    Node node4 = new Node(14);
    Node node5 = new Node(15);

    Node head = node1;

    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4);
    node4.setNext(node5);
    node5.setNext(null);

    Node tmp = head;
    while (tmp != null) {
      System.out.print(tmp.getData()+" -> ");
      tmp = tmp.getNext();
    }
    System.out.println();

    Node reversedNodes = reverse(head);
    tmp = reversedNodes;
    while (tmp != null) {
      System.out.print(tmp.getData()+" -> ");
      tmp = tmp.getNext();
    }

  }

  private static Node reverse(Node node) {
    Node prevNode = null, tmp = null;

    while (node != null) {
      tmp = node.getNext();
      node.setNext(prevNode);
      prevNode = node;
      node = tmp;
    }
    return prevNode;
  }
}
