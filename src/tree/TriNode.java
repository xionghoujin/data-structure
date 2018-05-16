package tree;

/**
 * �������������������࣬����Eָ������Ԫ������
 */
public class TriNode<T> {

	public T data;// ������,�洢����Ԫ��
	public TriNode<T> parent, left, right;// ��,�ֱ�ָ��ĸ�ڵ�,�Һ���������
	public int level;// �ڵ�Ĳ��

	// �����㣬�����ֱ�ָ��Ԫ�أ���ĸ��㣬����Һ��ӽ��
	public TriNode(T data, TriNode<T> parent, TriNode<T> left,
			TriNode<T> right, int level) {
		super();
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.level = level;
	}

	// ����ָ����Ҷ�ӽڵ�
	public TriNode(T data) {
		this(data, null, null, null, 0);
	}

	public TriNode() {
		this(null, null, null, null, 0);
	}
}