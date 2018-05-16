package array;

/**
 * ˫����ʾ�Ĺ������
 */
public class GenList<T> implements GGenList<T> {

	public GenListNode<T> head;// ͷָ��,ָ��ͷ���

	// ����չ��������ͷ��㣬3�����Ϊnull
	public GenList() {
		this.head = new GenListNode<T>();
	}

	// ���������������ṩԭ�ӳ�ֵ
	public GenList(T[] atoms) {// �������ṩԭ�ӳ�ֵ,�㷨ͬ������,������ʡ��
		this();
		GenListNode<T> rear = this.head;
		for (int i = 0; i < atoms.length; i++) {
			rear.next = new GenListNode<T>(atoms[i], null, null);
			rear = rear.next;
		}
	}

	@Override
	// �жϹ�����Ƿ�Ϊ��
	public boolean isEmpty() {
		return head.next == null;
	}

	@Override
	// ���ع������
	public int length() {
		int i = 0;
		GenListNode<T> rear = this.head.next;
		while (rear != null) {
			i++;
			rear = rear.next;
		}
		return i;
	}

	@Override
	// ���ع������ȣ��ݹ鷽��
	public int depth() { // ���ع�������,�ݹ鷽��
		int max = 1;
		GenListNode<T> p = this.head.next;
		while (p != null) {
			if (p.child != null) {
				int d = p.child.depth();// �ݹ���ã������ӱ����
				if (max <= d)// ��ס����ӱ����
					max = d + 1;// ��ǰ��������Ϊ�ӱ���ȼ�1
			}
			p = p.next;
		}
		return max;
	}

	// ���ع��������Ԫ�ض�Ӧ���ַ�������ʽΪ"(,)",���������㷨���ݹ鷽��
	public String toString() {// �ݹ����,�����ӱ�����ӱ������ַ���
		String str = "(";
		GenListNode<T> p = this.head.next;
		while (p != null) {
			if (p.child == null)
				str += p.data.toString();
			else
				str += p.child.toString();// �ݹ���ã������ӱ�����ӱ������ַ���
			if (p.next != null)
				str += ",";
			p = p.next;
		}
		return str + ")";// �ձ���()
	}

	// ����ԭ����Ϊ��i��Ԫ��
	public GenListNode<T> insert(int i, T x) {// ����ԭ����Ϊ��i��Ԫ��,�㷨ͬ������
		if (x == null)
			return null;
		GenListNode<T> p = this.head;
		for (int j = 0; j < i && p.next != null; j++) {
			p = p.next;
		}
		if (p != null)
			p.next = new GenListNode<T>(x, null, p.next);
		return p.next;
	}

	// �����ӱ���Ϊ��i��Ԫ��
	public GenListNode<T> insert(int i, GenList<T> glist) {
		if (glist == null)
			return null;
		GenListNode<T> p = this.head;
		for (int j = 0; p.next != null && j < i; j++) {
			p = p.next;
		}
		p.next = new GenListNode<T>(null, glist, p.next);
		return p.next;
	}

	// �ڹ�����������ԭ�ӽڵ�
	public void append(T x) {
		this.insert(Integer.MAX_VALUE, x);
	}

	// �ڹ������������ӱ�
	public void append(GenList<T> glist) {
		this.insert(Integer.MAX_VALUE, glist);
	}
}
