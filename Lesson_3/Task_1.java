//Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор).

class ListNode {
    int val;
    ListNode next;
  
    ListNode(int val) {
      this.val = val;
      this.next = null;
    }
  }
  
  public class ReverseLinkedList {
    public static ListNode reverse(ListNode head) {
      ListNode prev = null;
      ListNode current = head;
      ListNode next = null;
  
      while (current != null) {
        next = current.next;
        current.next = prev;
        prev = current;
        current = next;
      }
  
      return prev;
    }
  
    public static void printList(ListNode head) {
      ListNode current = head;
  
      while (current != null) {
        System.out.print(current.val + " ");
        current = current.next;
      }
  
      System.out.println();
    }
  
    public static void main(String[] args) {
      ListNode head = new ListNode(1);
      head.next = new ListNode(2);
      head.next.next = new ListNode(3);
      head.next.next.next = new ListNode(4);
      head.next.next.next.next = new ListNode(5);
  
      System.out.println("Исходный список:");
      printList(head);
  
      ListNode reversedHead = reverse(head);
  
      System.out.println("Развернутый список:");
      printList(reversedHead);
    }
  }