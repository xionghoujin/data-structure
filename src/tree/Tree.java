package tree;

/**
 * ���࣬����Tָ������Ԫ������
 *
 */

public class Tree<T> implements TTree<T> {

	public TreeNode<T> root;// ���ڵ�,�ڵ�ṹ�����ĺ����ֵ�����

	@Override
	public boolean isEmpty() {
		return this.root == null;
	}

	@Override
	public TreeNode<T> getChild(TreeNode<T> p, int i) {
		// TODO �Զ����ɵķ������
		return null;
	}

	// ����p�ڵ�����һ������
	public TreeNode<T> getLastChild(TreeNode<T> p) {
		if (p == null || p.child == null)// pû�к���
			return null;
		p = p.child;
		while (p.sibling != null)
			// p�����ֵ����������һ���ֵܽڵ�
			p = p.sibling;
		return p;
	}

	// ����p�ڵ�����һ���ֵ�
	public TreeNode<T> getLastSibling(TreeNode<T> p) {
		if (p == null || p.sibling == null)
			return null;
		while (p.sibling != null)
			// p�����ֵ����������һ���ֵܽڵ�
			p = p.sibling;
		return p;
	}

	@Override
	public TreeNode<T> getParent(TreeNode<T> node) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	// �������Ľ�����
	public int count() {
		return count(root);
	}

	// �����Խ��pΪ���������Ľڵ����
	public int count(TreeNode<T> p) {
		if (p == null)
			return 0;
		return 1 + count(p.child) + count(p.sibling);
	}

	@Override
	public int childCount(T p) {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public int height() {
		// TODO �Զ����ɵķ������
		return 0;
	}

	@Override
	public TreeNode<T> search(T x) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void preOrder() {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void postOrder() {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void levelOrder() {
		// TODO �Զ����ɵķ������

	}

	// ������ڵ�
	public void insertRoot(T x) {
		this.root = new TreeNode<T>(x, this.root, null);
	}

	// �����ֵܽڵ�
	public TreeNode<T> insertLastSibling(TreeNode<T> p, T x) {
		if (p == null)
			return null;
		while (p.sibling != null)
			// p�����ֵ����������һ���ڵ�
			p = p.sibling;
		p.sibling = new TreeNode<T>(x);// �������һ���ڵ�
		return p.sibling;
	}

	// ���뺢�ӽڵ�
	public TreeNode<T> insertLastChild(TreeNode<T> p, T x) {
		if (p == null)
			return null;
		if (p.child == null) {
			p.child = new TreeNode<T>(x);
			return p.child;
		} else
			return insertLastSibling(p.child, x);
	}

	// �ȸ�������������������ĺ������ʾ�ַ���
	public String toString() {
		return toString(root, "");
	}

	// �ȸ����������pΪ��������,tab����ָ��������,���������ĺ������ʾ��
	public String toString(TreeNode<T> p, String tab) {
		if (p == null)
			return "";
		return tab + p.data.toString() + "\n" + toString(p.child, tab + "\t")
				+ toString(p.sibling, tab);
	}

	// ��������ɭ�ֵĹ�����ʾ�ַ���
	public String toGenListString() {
		return toGenListString(this.root);
	}

	// ������p�ڵ�Ϊ���������Ĺ�����ʾ
	public String toGenListString(TreeNode<T> p) {
		if (p == null)
			return "";// ���ؿ�������ʾ
		String str = p.data.toString();
		if (p.child != null)
			str += "(" + toGenListString(p.child) + ")";
		if (p.sibling != null)
			str += "," + toGenListString(p.sibling);
		return str;
	}
}