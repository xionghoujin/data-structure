package list;

/**
 * �������ҿɼӵĵ�����
 */
public class PolySLinkedList<T extends Comparable<T> & Addible<T>> extends
		SortedSinglyLinkedList<T> {

	// Ĭ�Ϲ��췽��
	public PolySLinkedList() {
		super();
	}

	// ����Ŀ��ָ������ʽ����ֵ
	public PolySLinkedList(T terms[]) {
		super(terms);
	}

	public PolySLinkedList(PolySLinkedList<T> polylist) {
		super(polylist);
	}

	// ���������������ӵķ���
	public void add(PolySLinkedList<T> polylist) {
		Node<T> front = this.head, p = front.next;
		Node<T> q = polylist.head.next;
		while (p != null && q != null) {
			if (p.data.compareTo(q.data) == 0) {// �����С��ͬ
				p.data.add(q.data);
				if (p.data.removeable()) {// ��Ӻ��Ԫ������ɾ������
					front.next = p.next;// ɾ�����Ԫ�ز���Ҫ�洢��ɾ��p�ڵ�
					p = front.next;
				} else {
					front = p;
					p = p.next;
				}
				q = q.next;
			} else if (p.data.compareTo(q.data) < 0) {
				front = p;
				p = p.next;
			} else {
				front.next = new Node<T>(q.data, p);
				q = q.next;
			}
		}

		while (q != null) {
			front.next = new Node<T>(q.data, p);
			front = front.next;
			q = q.next;
		}
	}
}