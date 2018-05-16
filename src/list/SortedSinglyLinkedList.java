package list;

/**
 * ��������
 */
public class SortedSinglyLinkedList<T extends Comparable<T>> extends
		SinglyLinkedList<T> {

	// ������������� 
	public SortedSinglyLinkedList() {
		super();
	}

	// �Խ��Ԫ�ع��쵥����
	public SortedSinglyLinkedList(T[] element) {
		super();
		if (element != null)
			for (int i = 0; i < element.length; i++) {
				this.insert(element[i]);
			}
	}

	// ������췽�����ɵ�����list������������ֱ��ѡ������
	public SortedSinglyLinkedList(SortedSinglyLinkedList<T> list) {
		super(list);// ���list������
		Node<T> srear = head;// ָ����������β
		while (srear.next != null) {// ԭ��������
			Node<T> mfront = srear, min = mfront.next;// ָ����Сֵ��㣬mfrongָ��min��ǰ��
			Node<T> pfront = min, p = min.next;// p����������pfrontָ��p��ǰ�����

			while (p != null) {
				if (p.data.compareTo(min.data) < 0) {// �Ƚϣ�min��ס��Сֵ���
					mfront = pfront;// mfront��min��ǰ�����
					min = p;
				}
				pfront = p;// pfront��p��ǰ�����
				p = p.next;
			}

			if (mfront != srear) {
				mfront.next = min.next;// ������ԭλ��ɾ��min���
				min.next = srear.next;// ��min�����뵽srear֮��
				srear.next = min;
			}
			srear = min;// srearָ����������Ľ�β
		}
	}

	// ��������Ĺ鲢�㷨
	public void merge(SortedSinglyLinkedList<T> list) {
		Node<T> front = this.head, p = head.next;// pָ��this������ĵ�һ�����
		Node<T> q = list.head.next;// qָ��list�������ĵ�һ�����
		while (p != null && q != null) {
			if (p.data.compareTo(q.data) < 0) {// �Ƚ�����������
				front = p;// frontָ��p��ǰ�����
				p = p.next;
			} else {// ��q�����뵽front���֮��
				front.next = q;
				q = q.next;
				front = front.next;
				front.next = p;
			}
		}
		if (q != null) {// ��list������ʣ���㲢�뵱ǰ����β
			front.next = q;
			list.head.next = null;// ����list����������Ϊ�յ�����
		}
	}

	// ���ظ����insert�������������ͬû�и��Ǹ����insert����
	public void insert(T x) {
		if (x == null)
			return;
		Node<T> front = this.head, p = front.next;
		while (p != null && p.data.compareTo(x) < 0) {
			front = p;
			p = p.next;
		}
		front.next = new Node<T>(x, p);
	}

	// ��֧�ָ����insert������append���������串�ǲ��׳��쳣
	public void insert(int i, T x) {
		throw new UnsupportedOperationException("insert(int i,T x)");
	}

	// ��֧�ָ÷���
	public void append(T x) {
		throw new UnsupportedOperationException("append(T x)");
	}

	// ���ظ����remove�������������ͬû�и��Ǹ����remove����
	public void remove(T x) {
		if (x == null)
			return;
		Node<T> front = this.head, p = front.next;
		while (p != null && p.data.compareTo(x) < 0) {
			front = p;
			p = p.next;
		}

		if (p != null && p.data.compareTo(x) == 0)
			front.next = p.next;
	}

	// ˳����ҹؼ���ΪkeyԪ�أ������״γ��ֵ�Ԫ�أ������Ҳ��ɹ��򷵻�null
	// keyֻ���԰����ؼ����������T���compareTo�ṩ�Ƚ϶����С����ȵ�����
	// ���Ǹ���SinglyLinkedList��ͬ������
	public T search(T key) {
		if (key == null)
			return null;
		Node<T> p = this.head.next;
		while (p != null && p.data.compareTo(key) <= 0) {
			if (p.data.compareTo(key) == 0)
				return p.data;
			p = p.next;
		}
		return null;
	}
}