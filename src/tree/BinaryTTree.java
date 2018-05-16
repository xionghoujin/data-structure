package tree;

/**
 * �������ӿڣ�������������������
 */
public interface BinaryTTree<T> {

	boolean isEmpty();// �ж϶������Ƿ�Ϊ��

	int count();// �ж϶������Ľڵ����

	int height();// ���ض������ĸ߶�

	void preOrder();// �ȸ��������������

	void inOrder();// �и�����������

	void postOrder();// ����������������

	void levelOrder();// ����α���������

	BinaryNode<T> search(T key);// ���Ҳ������״γ��ֹؼ���ΪkeyԪ�صĽڵ�

	BinaryNode<T> getParent(BinaryNode<T> node);// ����node�ĸ�ĸ�ڵ�

	void insertRoot(T x);// ����Ԫ��x��Ϊ���ڵ�

	BinaryNode<T> insertChild(BinaryNode<T> node, T x, boolean leftChild);// ���뺢�ӽڵ�

	void removeChild(BinaryNode<T> p, boolean leftChild);// ɾ��p�ڵ�����������

	void removeAll();// ɾ��������
}
