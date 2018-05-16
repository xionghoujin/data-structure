package array;

import list.Addible;

/**
 * ϡ��������Ԫ�ص���Ԫ����
 */
public class Triple implements Comparable<Triple>, Addible<Triple> {

	int row, column, value;// �кţ��кţ�Ԫ��ֵ��Ĭ�Ϸ���Ȩ��

	//���кţ��к��Լ�ֵ������Ԫ��
	public Triple(int row, int column, int value) {
		super();
		if (row < 0 || column < 0)
			throw new IllegalArgumentException("ϡ�����Ԫ����Ԫ�����/����ŷ�����");
		this.row = row;
		this.column = column;
		this.value = value;
	}

	// �������췽������ֵһ����Ԫ��
	public Triple(Triple elem) {
		this(elem.row, elem.column, elem.value);
	}

	// ������Ԫ�������ַ���
	public String toString() {
		return "(" + row + "," + column + "," + value + ")";
	}

	@Override
	// ������Ԫ��λ�ñȽ�������Ԫ��Ĵ�С����Ԫ��ֵ�޹أ�Լ����Ԫ���������
	public int compareTo(Triple elem) {
		if (this.row < elem.row || this.row == elem.row
				&& this.column < elem.column)
			return -1;
		if (this.row == elem.row && this.column == elem.column)
			return 0;
		return 1;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Triple))
			return false;
		Triple elem = (Triple) obj;
		return this.row == elem.row && this.column == elem.column
				&& this.value == elem.value;
	}

	@Override
	// ������Ԫ�����
	public void add(Triple term) {
		if (this.compareTo(term) == 0)
			this.value += term.value;
		else
			throw new IllegalArgumentException("�����ָ����ͬ,�������");
	}

	@Override
	// Լ��ɾ�����������洢ֵΪ0��Ԫ��
	public boolean removeable() {
		return this.value == 0;
	}
}
