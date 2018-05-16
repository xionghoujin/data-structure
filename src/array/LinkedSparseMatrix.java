package array;

import list.*;

/**
 * ��Ԫ���еĵ�����洢��ϵ��������
 */
public class LinkedSparseMatrix {

	private int rows, columns;// ��������������
	private SeqList<PolySLinkedList<Triple>> list;// ��ָ��˳���,Ԫ���Ƕ���ʽ��������

	// ����rows�к�columns�е������
	public LinkedSparseMatrix(int rows, int columns) {
		if (rows <= 0 || columns <= 0)
			throw new IllegalArgumentException("��������������������");
		this.rows = rows;
		this.columns = columns;
		this.list = new SeqList<PolySLinkedList<Triple>>();// �����˳���Ԫ����null
		for (int i = 0; i < rows; i++)
			// ����rows���յ�����
			this.list.append(new PolySLinkedList<Triple>());
	}

	// ���
	public LinkedSparseMatrix(LinkedSparseMatrix smat) {
		this(smat.rows, smat.columns);
		for (int i = 0; i < this.rows; i++) {
			// ����ʽ������������Ѹ������нڵ㣬û�и���Ԫ�ض���
			PolySLinkedList<Triple> link = new PolySLinkedList<Triple>(
					smat.list.get(i));
			Node<Triple> p = link.head.next;
			while (p != null) {
				p.data = new Triple(p.data);// ����Ԫ�ض���
				p = p.next;
			}
			this.list.set(i, link);// �����ƺ�ĵ���������Ϊ˳����i��Ԫ��
		}
	}

	// ���ؾ����i�е�j�е�Ԫ��
	public int get(int i, int j) {
		if (i < 0 || i >= rows || j < 0 || j >= columns)
			throw new IndexOutOfBoundsException("����Ԫ�ص��л������Խ��");
		PolySLinkedList<Triple> link = this.list.get(i);// ��õ�i�ж���ʽ��������
		Triple find = link.search(new Triple(i, j, 0));// ������������˳����ң������ҵ����
		return (find == null) ? 0 : find.value;// û���ҵ�ʱ����0.���򷵻ؽ��Ԫ��
	}

	// ����Ԫ�����þ���Ԫ��
	public void set(Triple elem) {
		this.set(elem.row, elem.column, elem.value);
	}

	// ���þ����row�е�column��Ԫ��Ϊvalue
	public void set(int row, int column, int value) {
		if (value == 0)
			return;// ���洢ֵΪ0Ԫ��
		if (row >= this.rows || columns >= this.columns)
			throw new IllegalArgumentException("��Ԫ����л������Խ��");
		PolySLinkedList<Triple> link = this.list.get(row);// ��õ�row�ж���ʽ��������
		Node<Triple> front = link.head, p = front.next;
		while (p != null && p.data.column <= column) {// �����������н���˳�����
			if (p.data.column == column) {// ���ҵ������ľ���Ԫ��ֵ
				p.data.value = value;
				return;
			}
			front = p;
			p = p.next;
		}
		front.next = new Node<Triple>(new Triple(row, column, value), p);// ��front֮�������Ԫ��Ԫ��
	}

	// ����ϡ�������Ԫ���еĵ������ϡ����������ַ���
	public String toString() {
		String str = "��Ԫ���еĵ�����\n";
		for (int i = 0; i < this.list.length(); i++) {
			str += this.list.get(i).toString() + "\n";
		}

		str += "ϡ�����" + this.getClass().getName() + "(" + rows + " : "
				+ columns + ")  :  \n";
		for (int i = 0; i < this.list.length(); i++) {
			SortedSinglyLinkedList<Triple> link = this.list.get(i);
			Node<Triple> p = link.head.next;
			for (int j = 0; j < this.columns; j++)
				if (p != null && j == p.data.column) {
					str += String.format("%4d", p.data.value);
					p = p.next;
				} else
					str += String.format("%4d", 0);
			str += "\n";
		}
		return str;
	}

	// ��ǰ������smat������ӣ�this+=smat,�ı䵱ǰ����
	public void add(LinkedSparseMatrix smat) {
		if (this.rows != smat.rows || this.columns != smat.columns)
			throw new IllegalArgumentException("�������������ͬ���������");
		for (int i = 0; i < this.list.length(); i++) {
			this.list.get(i).add(smat.list.get(i));// ���ö���ʽ���������(+=)�㷨
		}
	}

	// ���ص�ǰ������smat��Ӻ�ľ��󣬲��ı䵱ǰ����smatc=this+smat
	public LinkedSparseMatrix plus(LinkedSparseMatrix smat) {
		LinkedSparseMatrix smatc = new LinkedSparseMatrix(this);// ���
		smatc.add(smat);
		return smatc;// ���ض�������
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof LinkedSparseMatrix))
			return false;
		LinkedSparseMatrix smat = (LinkedSparseMatrix) obj;
		return this.rows == smat.rows && this.columns == smat.columns
				&& this.list.equals(smat.list);
	}
}