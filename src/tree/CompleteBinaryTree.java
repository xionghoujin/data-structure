package tree;

/**
 * 
 * ���������ʾ����ȫ�������࣬�̳ж�������
 */

public class CompleteBinaryTree<T> extends BinaryTree<T> {

	// ����ն�����
	public CompleteBinaryTree() {
		super();
	}

	// ����ȫ�������Ĳ�����й�����ȫ������,levellistָ���������
	public CompleteBinaryTree(T[] levellist) {
		this.root = create(levellist, 0);
	}

	// ������levellist[i]Ϊ����һ������ȫ������,���������������ĸ��ڵ�
	public BinaryNode<T> create(T[] levellist, int i) {
		BinaryNode<T> p = null;
		if (i < levellist.length) {
			p = new BinaryNode<T>(levellist[i]);
			p.left = create(levellist, 2 * i + 1);
			p.right = create(levellist, 2 * i + 2);
		}
		return p;
	}
}
