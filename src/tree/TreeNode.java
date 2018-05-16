package tree;

/**
 * 
 * ���ĺ����ֵ�����ڵ��࣬����Tָ���ڵ��Ԫ������
 */
public class TreeNode<T> {

	public T data;// ������
	public TreeNode<T> child, sibling;// �����ֱ�ָ���ӣ��ֵܽ��

	// �����㣬�����ֱ�ָ��Ԫ�أ����Ӻ��ֵܽ��
	public TreeNode(T data, TreeNode<T> child, TreeNode<T> sibling) {
		super();
		this.data = data;
		this.child = child;
		this.sibling = sibling;
	}

	// ����ָ��ֵ��Ҷ�ӽ��
	public TreeNode(T data) {
		this(data, null, null);
	}

	public TreeNode() {
		this(null);
	}
}
