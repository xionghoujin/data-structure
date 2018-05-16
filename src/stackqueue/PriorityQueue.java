package stackqueue;

import list.SortedSinglyLinkedList;

/**
 * 
 * ��������
 */
public class PriorityQueue<T extends Comparable<T>> implements QQueue<T> {

	private SortedSinglyLinkedList<T> list;// ʹ����������洢����Ԫ��

	// ����ն���
	public PriorityQueue() {
		this.list = new SortedSinglyLinkedList<T>();
	}

	// �ж϶����Ƿ�Ϊ�գ������򷵻�true
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	// /Ԫ��x��ӣ��ն�������ӣ�����Ԫ�ش�С�����ڵ������ʵ�λ��
	public void enquenu(T x) {
		list.insert(x);
	}

	@Override
	// ���ӣ����ض�ͷԪ�أ������пգ�����null��remove�������ض�ͷԪ�أ�ɾ����ͷԪ��
	public T dequeue() {
		return list.remove(0);
	}

	// ���ض�������Ԫ�ص������ַ���
	public String toString() {
		return list.toString();
	}
}