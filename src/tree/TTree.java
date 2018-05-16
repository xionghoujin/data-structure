package tree;

/**
 * ���ӿ�
 */
public interface TTree<T> {

	boolean isEmpty();// �ж��Ƿ����

	TreeNode<T> getChild(TreeNode<T> p, int i);// ����p�ڵ�ĵ�i�����ӽڵ�

	TreeNode<T> getLastChild(TreeNode<T> p);// ����p�ڵ�����һ�����ӽڵ�

	TreeNode<T> getLastSibling(TreeNode<T> p);// ����p�ڵ�����һ���ֵܽڵ�

	TreeNode<T> getParent(TreeNode<T> node);// ����node�ڵ�ĸ�ĸ�ڵ�

	int count();// �������Ľڵ����

	int childCount(T p);// ����p�ڵ�ĺ��ӽڵ����

	int height();// �������ĸ߶�

	TreeNode<T> search(T x);// ���Ҳ�����Ԫ��Ϊx�Ľڵ�

	void preOrder();// �ȸ����������

	void postOrder();// ������������

	void levelOrder();// �и����������
}