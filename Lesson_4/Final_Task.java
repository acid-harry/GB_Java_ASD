//Необходимо превратить собранное на семинаре дерево поиска в полноценное левостороннее красно-черное дерево. И реализовать в нем метод добавления новых элементов с балансировкой.
//Красно-черное дерево имеет следующие критерии:
//• Каждая нода имеет цвет (красный или черный)
//• Корень дерева всегда черный
//• Новая нода всегда красная
//• Красные ноды могут быть только левым ребенком
//• У краной ноды все дети черного цвета
//Соответственно, чтобы данные условия выполнялись, после добавления элемента в дерево необходимо произвести балансировку, благодаря которой все критерии выше станут валидными. 
//Для балансировки существует 3 операции – левый малый поворот, правый малый поворот и смена цвета.


import java.util.Scanner;

class Node {
  Node left, right;
  int data;
  boolean color;

  Node(int data) {
    this.data = data;
    left = null;
    right = null;
    color = true;
  }
}

public class LLRBTREE {
  private static Node root = null;

  Node rotateLeft(Node myNode) {
    System.out.printf("поворот влево!!\n");
    Node child = myNode.right;
    Node childLeft = child.left;

    child.left = myNode;
    myNode.right = childLeft;

    return child;
  }

  Node rotateRight(Node myNode) {
    System.out.printf("вращение вправо\n");
    Node child = myNode.left;
    Node childRight = child.right;

    child.right = myNode;
    myNode.left = childRight;

    return child;
  }

  boolean isRed(Node myNode) {
    if (myNode == null) {
      return false;
    }
    return myNode.color;
  }

  void swapColors(Node node1, Node node2) {
    boolean temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
  }

  Node insert(Node myNode, int data) {
    if (myNode == null) {
      return new Node(data);
    }

    if (data < myNode.data) {
      myNode.left = insert(myNode.left, data);
    } else if (data > myNode.data) {
      myNode.right = insert(myNode.right, data);
    } else {
      return myNode;
    }

    if (isRed(myNode.right) && !isRed(myNode.left)) {
      myNode = rotateLeft(myNode);
      swapColors(myNode, myNode.left);
    }

    if (isRed(myNode.left) && isRed(myNode.left.left)) {
      myNode = rotateRight(myNode);
      swapColors(myNode, myNode.right);
    }

    if (isRed(myNode.left) && isRed(myNode.right)) {
      myNode.color = !myNode.color;
      myNode.left.color = false;
      myNode.right.color = false;
    }

    return myNode;
  }

  void inorder(Node node) {
    if (node != null) {
      inorder(node.left);
      char c = '●';
      if (!node.color)
        c = '◯';
      System.out.print(node.data + "" + c + " ");
      inorder(node.right);
    }
  }

  public static void main(String[] args) {
    LLRBTREE tree = new LLRBTREE();
    Scanner scan = new Scanner(System.in);

    char ch;
    do {
      System.out.println("Введите целое число");
      int num = scan.nextInt();
      root = tree.insert(root, num);

      tree.inorder(root);
      System.out.println("\nВы хотите продолжить? (введите y или n)");
      ch = scan.next().charAt(0);
    } while (ch == 'Y' || ch == 'y');

    scan.close();
  }
}