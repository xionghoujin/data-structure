package array;

import list.SeqList;

/**
 * ϡ�������Ԫ��˳���洢
 */
public class SeqSparseMatrix {

	private int rows, columns;// �洢����������
	private SeqList<Triple> list;// �洢��Ԫ���˳���

	// ����rows��columns�������
	public SeqSparseMatrix(int rows, int columns) {
		if (rows <= 0 || columns <= 0)
			// �׳���Ч�����쳣
			throw new IllegalArgumentException("��������������������");
		this.rows = rows;
		this.columns = columns;
		this.list = new SeqList<Triple>();
	}

	// ����rows��colums�о�������Ԫ��elems�ṩ�����ֵ
	public SeqSparseMatrix(int rows, int columns, Triple[] elems) {
		this(rows, columns);
		for (int i = 0; i < elems.length; i++) {
			// ���в���һ��Ԫ�ص���Ԫ��
			this.set(elems[i]);
		}
	}

	// �õ���i�е�j����
	public int get(int i, int j) {
		if (i < 0 || i >= rows || j < 0 || j >= columns)
			throw new IndexOutOfBoundsException("����Ԫ�ص��л������Խ��");
		Triple item = new Triple(i, j, 0);
		int k = 0;
		Triple elem = this.list.get(k);
		// ������˳���list��˳�����item
		while (k < this.list.length() && item.compareTo(elem) >= 0) {
			if (item.compareTo(elem) == 0)// ֻ�Ƚ���Ԫ��Ԫ��λ��
				return elem.value;// ���ҵ�(i,j)�����ص�����Ԫ��
			k++;// item�ϴ�ʱ�����
			elem = this.list.get(k);
		}
		return 0;// û���ҵ�ʱ����0
	}

	// ����Ԫ�����þ���Ԫ��
	public void set(Triple elem) {
		this.set(elem.row, elem.column, elem.value);
	}

	// ���þ����row�е�column�е�Ԫ��ֵΪvalue
	public void set(int row, int column, int value) {
		if (value == 0)
			return;// ���洢ֵΪ0Ԫ��
		if (row >= this.rows || column >= this.columns)
			throw new IllegalArgumentException("��Ԫ����л������Խ��");
		Triple elem = new Triple(row, column, value);
		int i = 0;
		// ���������Ԫ��˳����в���elem���󣬺���Ļ����
		while (i < this.list.length()) {// ��elem���ڣ������λ�þ���Ԫ��
			Triple item = this.list.get(i);
			if (elem.compareTo(item) == 0) {
				this.list.set(i, elem);// ����˳����i��Ԫ��Ϊelem
				return;
			}
			if (elem.compareTo(item) >= 0)// elem�ϴ�ʱ�����
				i++;
			else
				break;
		}
		this.list.insert(i, elem);// ����elem������Ϊ˳����i��Ԫ��
	}

	// ����ϡ�������Ԫ��˳����ϡ����������ַ���
	public String toString() {
		String str = "��Ԫ��˳���" + this.list.toString() + "\n";
		str += "ϡ����� " + this.getClass().getName() + "(" + rows + ":" + columns
				+ ")  :  \n";
		int k = 0;
		Triple elem = this.list.get(k++);// ���ص�k��Ԫ�أ���kָ�������Ч�򷵻�null
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				if (elem != null && i == elem.row && j == elem.column) {
					str += String.format("%4d", elem.value);
					elem = this.list.get(k++);
				} else
					str += String.format("%4d", 0);
			}
			str += "\n";
		}
		return str;
	}

	// ������ϡ��������
	public SeqSparseMatrix plus(SeqSparseMatrix smat) {
		if (this.rows != smat.rows || this.columns != smat.columns)
			throw new IllegalArgumentException("�������������ͬ���������");
		// ���������
		SeqSparseMatrix smatc = new SeqSparseMatrix(this.rows, this.columns);
		int i = 0, j = 0;
		// �ֱ�������������˳���
		while (i < this.list.length() && j < smat.list.length()) {
			Triple elema = this.list.get(i);
			Triple elemb = smat.list.get(j);
			if (elema.compareTo(elemb) == 0) {
				// ��������Ԫ����ͬλ�õľ���Ԫ�أ����ӦԪ��ֵ���
				if (elema.value + elemb.value != 0)
					smatc.list.append(new Triple(elema.row, elema.column,
							elema.value + elemb.value));
				i++;
				j++;
			} else if (elema.compareTo(elemb) < 0) {// ����С��Ԫ�鸴�Ƶ�smatc˳������
				smatc.list.append(new Triple(elema));// ����elemaԪ��ִ��Triple�������췽��
				i++;
			} else {
				smatc.list.append(new Triple(elemb));
				j++;
			}
		}
		// ����ǰԪ��
		while (i < this.list.length())
			smatc.list.append(new Triple(this.list.get(i++)));
		while (j < smat.list.length())
			smatc.list.append(new Triple(smat.list.get(j++)));
		return smatc;// ���ض��������
	}
}