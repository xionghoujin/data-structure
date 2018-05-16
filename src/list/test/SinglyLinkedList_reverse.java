package list.test;

import list.Node;
import list.SinglyLinkedList;

/**
 * �������������ת
 */
public class SinglyLinkedList_reverse {

	public static <T> void reverse(SinglyLinkedList<T> list) {
		Node<T> p = list.head.next, succ = null, front = null;
		while (p != null) {
            succ = p.next;// ����succ��p�ڵ�ĺ�̽ڵ�
            p.next = front;// p.nextָ��p�ڵ��ǰ���ڵ�
            front = p;
            p = succ;// p�����һ��
        }
        list.head.next = front;//��head�Ƶ�β��
    }

	public static void main(String[] args) {
		String values[] = { "A", "B", "C", "D", "E", "F" };
		SinglyLinkedList<String> list = new SinglyLinkedList<String>(values);
		System.out.println("list :  " + list.toString());
		reverse(list);
        System.out.println("��ת�� : " + list.toString());
    }
}
