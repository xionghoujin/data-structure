package list;

/**
 * һԪ����ʽ�Ĵ洢
 */
public class Polynomial {

	private PolySLinkedList<TermX> list;// ����ʽ��������

	// Ĭ�Ϲ������������յ�����
	public Polynomial() {
		this.list = new PolySLinkedList<TermX>();
	}

	// ���췽������������ָ������ʽ����ֵ
	public Polynomial(TermX terms[]) {
		this.list = new PolySLinkedList<TermX>(terms);
	}

	// ����ʽ��ȿ���
	public Polynomial(Polynomial poly) {
		this();// �����յ�����ֻ��ͷ���
		Node<TermX> p = poly.list.head.next;
		Node<TermX> rear = this.list.head;
		while (p != null) {
			rear.next = new Node<TermX>(new TermX(p.data), null);
			rear = rear.next;
			p = p.next;
		}
	}

	// ���캯�������ݶ�����ʽ���м���
	public Polynomial(String polystr) {
		this();
		if (polystr == null || polystr.length() == 0)
			return;
		Node<TermX> rear = this.list.head;
		int start = 0, end = 0;// ���start-end���Ӵ�Ϊһ��
		while (start < polystr.length() && end < polystr.length()) {
			int i = polystr.indexOf('+', end + 1);// �����ַ�+���ַ����д�end+1��ʼ�����
			if (i == -1)// δ�ҵ�ָ���ַ�
				i = polystr.length();
			int j = polystr.indexOf('-', end + 1);
			if (j == -1)
				j = polystr.length();
			end = i < j ? i : j;// endΪ��һ��+��-�ŵ����
			rear.next = new Node<TermX>(
					new TermX(polystr.substring(start, end)), null);
			// β���룬�����start-end���Ӵ���Ϊһ���������ڵ㴴��Ԫ�ض���
			rear = rear.next;
			start = end;
		}
	}

	// ����һ������ʽ���ڱ�����ʽ��
	public void add(Polynomial poly) {
		this.list.add(poly.list);
	}

	// ����������ʽ�������
	public Polynomial plus(Polynomial poly) {
		Polynomial cpoly = new Polynomial(this);// ���
		cpoly.add(poly);
		return cpoly;// ���ض�������
	}

	// ���ض���ʽ�������ַ���
	public String toString() {
		String str = "";
		Node<TermX> p = this.list.head.next;
		while (p != null) {
			str += p.data.toString();
			p = p.next;
		}
		return str;
	}

	public boolean equals(Object obj) {
		return this == obj || obj instanceof Polynomial
				&& this.list.equals(((Polynomial) obj).list);
	}

}