package list;

/**
 * ��ͷ���ĵ�����
 */
public class SinglyLinkedList<T> implements LList<T> {

	public Node<T> head;// ͷָ�룬ָ�������ͷ���

	// Ĭ�Ϲ��췽������յ���������ͷ��㣬data��nextֵ��Ϊnull
	public SinglyLinkedList() {
		this.head = new Node<T>();
	}

	// �Խ��Ԫ�ع��쵥����
	public SinglyLinkedList(T[] element) {
		this();
		Node<T> rear = this.head;
		for (int i = 0; i < element.length; i++) {
			rear.next = new Node<T>(element[i], null);// β���룬�����ڵ�����rear�ڵ�֮��
			rear = rear.next;
		}
	}

	// ����������
	public SinglyLinkedList(SinglyLinkedList<T> list) {
		this();
		Node<T> p = list.head.next;
		Node<T> rear = this.head;
		while (p != null) {
			rear.next = new Node<T>(p.data, null);
			rear = rear.next;
			p = p.next;
		}
	}

	@SuppressWarnings("unused")
	// ��ָ�����鹹�쵥�����ݹ鷽��
	private Node<T> create(T[] elements, int i) {
		Node<T> p = null;
		if (i < elements.length) {
			p = new Node<T>(elements[i], null);
			p.next = create(elements, i + 1);
		}
		return p;
	}

	@SuppressWarnings("unused")
	// ���Ƶ������ݹ��㷨
	private Node<T> copy(Node<T> p) {
		Node<T> q = null;
		if (p != null) {
			q = new Node<T>(p.data, null);
			q.next = copy(p.next);
		}
		return q;
	}

	@Override
	// �жϵ������Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.head.next == null;
	}

	@Override
	// ���ص�������
	public int length() {
		int i = 0;
		Node<T> p = this.head.next;// p�ӵ��ı��һ���ڵ㿪ʼ
		while (p != null) {// ��������δ����
			i++;
			p = p.next;// p�������̽ڵ�
		}
		return i;
	}

	// �õ���i��Ԫ�أ���iָ�������Ч���򷵻�null
	public T get(int i) {
		if (i > 0) {
			Node<T> p = this.head.next;
			for (int j = 0; p != null && j < i; j++)
				p = p.next;
			if (p != null) {
				return p.data;// pָ���i�����
			}
		}
		return null;
	}

	@Override
	// ���õ�i��Ԫ��ֵΪx����iָ�������Ч���׳����Խ���쳣
	public void set(int i, T x) {
		if (x == null)
			return;// �������ÿն���
		if (i >= 0) {
			Node<T> p = this.head.next;
			for (int j = 0; p != null && j < i; j++)
				p = p.next;
			if (p != null)
				p.data = x;// pָ���i�����
		} else
			throw new IndexOutOfBoundsException(i + "");
	}

	@Override
	// ��x������������Ϊi�ڵ�ǰ
	public void insert(int i, T x) {
		if (x == null)
			return;// ���ܲ���ն���
		Node<T> p = this.head;// pָ��ͷ���
		// Ѱ�Ҳ���λ��
		for (int j = 0; p.next != null && j < i; j++)
			p = p.next;// ѭ��ֹͣʱ,pָ���i-1�ڵ�����һ���ڵ�
		// ����x��Ϊp�ڵ�
		p.next = new Node<T>(x, p.next);
	}

	@Override
	// �ڵ����������Ӷ���
	public void append(T x) {
		insert(Integer.MAX_VALUE, x);
	}

	@Override
	// ɾ�����Ϊi�Ľڵ㣬�������ɹ����򷵻ر�ɾ�����󣬷��򷵻�null
	public T remove(int i) {
		if (i >= 0) {
			Node<T> p = this.head;
			// ��λ����ɾ���ڵ�i��ǰ���ڵ�
			for (int j = 0; p.next != null && j < i; j++)
				p = p.next;
			if (p.next != null) {
				T old = p.next.data;// ���ԭ����
				p.next = p.next.next;// ɾ��p�ĺ�̽ڵ�
				return old;
			}
		}
		return null;
	}

	@Override
	// ɾ������������Ԫ�أ�java�Զ����ո������ռ�õ��ڴ�ռ�
	public void removeall() {
		this.head.next = null;
	}

	// ɾ���״γ��ֵ�ֵΪx�Ľڵ㣬��û�ҵ�ָ���ڵ���ڵ㲻ɾ��
	public void remove(T x) {
		if (this.head.next == null || x == null)
			return;
		Node<T> front = this.head, p = front.next;
		while (p != null && !p.data.equals(x)) {
			front = p;
			p = p.next;
		}
		if (p != null)
			front.next = p.next;// ͷɾ�����м�/βɾ��
	}

	// ˳����ҹؼ���ΪkeyԪ�أ������״γ��ֵ�Ԫ�أ������Ҳ��ɹ��򷵻�null
	public T search(T key) {
		if (key == null)
			return null;
		Node<T> p = this.head.next;
		while (p != null) {
			if (p.data.equals(key))
				return p.data;
			p = p.next;
		}
		return null;
	}

	// �ж����Ա��Ƿ�ؼ���Ϊkey��Ԫ��
	public boolean contain(T key) {
		return this.search(key) != null;// �Բ��ҽ������жϽ��
	}

	// ���ص���������Ԫ�ص������ַ���
	public String toString() {
		String str = "(";
		Node<T> p = this.head.next;
		while (p != null) {
			str += p.data.toString();
			if (p.next != null)
				str += " , ";// �������һ���ڵ�ʱ��ӷָ���
			p = p.next;
		}
		return str + ")";// �ձ���
	}

	// ������ıȽ�
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof SinglyLinkedList))
			return false;
		Node<T> p = this.head.next;

		@SuppressWarnings("unchecked")
		Node<T> q = ((SinglyLinkedList<T>) obj).head.next;
		while (p != null && q != null && p.data.equals(q.data)) {
			p = p.next;
			q = q.next;
		}
		return p == null && q == null;
	}

	// �Ƚ������������Ƿ���ȣ��ݹ鷽��
	@SuppressWarnings("unused")
	private boolean equals(Node<T> p, Node<T> q) {
		if (p == null || q == null)
			return true;
		return p != null && q != null && p.data.equals(q.data)
				&& p.next.equals(q.next);

	}
}