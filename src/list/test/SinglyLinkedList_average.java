package list.test;

import list.Node;
import list.SinglyLinkedList;

/**
 * ���㵥�����е�ƽ��ֵ(ȥ�����ֵ����Сֵ)
 */
public class SinglyLinkedList_average {

	public static Integer[] random(int n) {
		Integer[] element = new Integer[n];
		for (int i = 0; i < n; i++)
			element[i] = new Integer((int) (Math.random() * 100));
		return element;
	}

    // ȥ����߷ֺ���ͷ֣�����ƽ��ֵ
    public static double averageExceptMaxMin(SinglyLinkedList<Integer> list) {
		if (list.isEmpty())
            throw new IllegalArgumentException("���ܶԿյ����������ƽ��ֵ��");// ��Ч�����쳣

		int sum = 0, i = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        Node<Integer> p = list.head.next;// Ҫ��headȨ�ޱ�����public
        while (p != null) {
			int value = p.data;
			sum += value;
			if (value > max)
				max = value;
			if (value < min)
				min = value;
			p = p.next;
			i++;
		}

		if (i == 1 || i == 2)
            return (double) sum / i;// ��������Ԫ�ص�ƽ��ֵ�������˳���Ϊ0�Ĵ���
        return (double) (sum - max - min) / (i - 2);// ����ȥ����߷ֺ���ͷֵ�ƽ��ֵ
    }

	public static void main(String[] args) {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(
				random(10));
		System.out.println("list :" + list.toString());
		System.out
				.println("averageExceptMaxMin:  " + averageExceptMaxMin(list));
	}
}
