package graph;

import list.SortedSinglyLinkedList;

/**
 * �����Ԫ��
 */
public class Vertex<T> {

	public T data;// ����������
	public SortedSinglyLinkedList<Edge> adjlink;// �ö���ıߵ�����

	public Vertex(T data) {
		this.data = data;
		this.adjlink = new SortedSinglyLinkedList<Edge>();
	}

	// ���ض��㼰��ߵ�����������ַ���
	public String toString() {
		return "\n" + this.data.toString() + ":  " + this.adjlink.toString();
	}
}
