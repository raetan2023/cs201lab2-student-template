import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    // write your codes here
    public Node<E> findNode(E e) {
        Node<E> curr = head;
        while (curr != null) {
            E ele = curr.getElement();

            if (ele.equals(e)) {
                return curr;
            }

            curr = curr.getNext();
        }
        return null;
    }

    public void swap(){
        if (!isEmpty()) {
            // collect all the elements in nodes of LL
            List<Node<E>> sortedNodes = new ArrayList<>();
            Node<E> curr = head;
            while (curr != null) {
                sortedNodes.add(curr);
                curr = curr.getNext();
            }

            // sort elements
            sortedNodes.sort((a, b) -> a.getElement().compareTo(b.getElement()));
            
            // now that eles are sorted, we can map each node in the LL to its appropriate node (with matching ele) and store in a hashmap
            Map<Node<E>, Node<E>> replacements = new HashMap<>();
            for (int i = 0; i < size; i++) {
                replacements.put(sortedNodes.get(i), sortedNodes.get(size - 1 - i));
            }

            // make list of nodes in final order to be returned
            List<Node<E>> replacedNodes = new ArrayList<>();
            curr = head;
            while (curr != null) {
                replacedNodes.add(replacements.get(curr));
                curr = curr.getNext();
            }

            //link em up!
            for (int i = 0; i <= size - 1; i++) {
                Node<E> node = replacedNodes.get(i);
                if (i == 0) {
                    head = node;
                }
                if (i == size - 1) {
                    node.setNext(null);
                    tail = node;
                    return;
                }
                node.setNext(replacedNodes.get(i+1));
            }

        }

    }
   
}

