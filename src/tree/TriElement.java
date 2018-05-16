package tree;

/**
 * ��������������������
 */
public class TriElement {

	int data;// ������,��ʾȨֵ
	int parent, left, right;// ��ĸ�ڵ����,�Һ��ӽ���±�

	public TriElement(int data, int parent, int left, int right) {
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
	}

	public TriElement(int data) {
		this(data, -1, -1, -1);
	}

	public TriElement() {
		this(0);
	}

	public String toString() {
		return "(" + this.data + ", " + this.parent + ", " + this.left + ", "
				+ this.right + ")";
	}
}
