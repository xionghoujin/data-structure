package tree;

/**
 * �Ա������������ȸ����й��������
 */
public class TriBinaryTree<T> implements BinaryTTree<T> {

	public TriNode<T> root;// ���ڵ�
	private int i;

	//
	public TriBinaryTree() {
		this.root = null;
	}

	// �Ա������������ȸ����й���һ�Ŷ�����
	public TriBinaryTree(T[] prelist) {
		this.root = create(prelist, 1, null);
	}

	// �Ա������������ȸ����й���һ���Ӷ�����,�����ĸ�ֲ��prelist[i],���Ϊlevel
	// parentָ��ĸ�ڵ�,���������������ĸ��ڵ�
	private TriNode<T> create(T[] prelist, int level, TriNode<T> parent) {
		TriNode<T> p = null;
		if (i < prelist.length) {
			T elem = prelist[i++];
			if (elem != null) {
				p = new TriNode<T>(elem, parent, null, null, level);
				p.left = create(prelist, level + 1, p);
				p.right = create(prelist, level + 1, p);
			}
		}
		return p;
	}

	// ������p�ڵ㣨���Ϊlevel��Ϊ�������������нڵ�Ĳ��
	public void setLevel(TriNode<T> p, int level) {
		if (p != null) {
			p.level = level;
			setLevel(p.left, level + 1);
			setLevel(p.right, level + 1);
		}
	}

	// �ж����Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.root == null;
	}

	// ����ڵ�ĸ���
	public int count() {
		return count(root);
	}

	public int count(TriNode<T> p) {
		if (p == null)
			return 0;
		return 1 + count(p.left) + count(p.right);
	}

	// �������ĸ߶�
	public int height() {
		return height(root);
	}

	public int height(TriNode<T> p) {
		if (p == null)
			return 0;
		int lh = height(p.left);
		int rh = height(p.right);
		return lh >= rh ? lh + 1 : rh + 1;
	}

	@Override
	public void preOrder() {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void inOrder() {
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

	@Override
	public BinaryNode<T> search(T key) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public BinaryNode<T> getParent(BinaryNode<T> node) {
		// TODO �Զ����ɵķ������
		return null;
	}

	// ����Ԫ��x��Ϊ���ڵ�,ԭ���ڵ���Ϊ������
	public void insertRoot(T x) {
		this.root = new TriNode<T>(x, null, this.root, null, 1);
		if (this.root.left != null)
			this.root.left.parent = this.root;
		setLevel(this.root.left, 2);// ����ԭ�����Ϊ�������������н��Ĳ��
	}

	// ����Ԫ��x��Ϊp�ڵ������,��leftChildΪtrue,����ڵ���Ϊ����,������Ϊ���Һ���
	public TriNode<T> insertChild(TriNode<T> p, T x, boolean leftChild) {
		if (p == null || x == null)
			return null;
		TriNode<T> q = null;
		if (leftChild) {// ����x�ڵ���Ϊp������,pԭ���ӳ�Ϊx������
			q = new TriNode<T>(x, p, p.left, null, p.level + 1);
			if (p.left != null)
				p.left.parent = q;// ԭp���ӽڵ���¸�ĸ�ڵ���q
			p.left = q;
		} else {// ����ڵ�x�ڵ���Ϊp���Һ���,p��ԭ�Һ��ӳ�Ϊx���Һ���
			q = new TriNode<T>(x, p, null, p.right, p.level + 1);
			if (p.right != null)
				p.right.parent = q;// ԭp�Һ��ӽڵ���¸�ĸ�ڵ���q
			p.right = q;
		}
		setLevel(q, p.level + 1);// �����Բ���ڵ�Ϊ�������������нڵ�Ĳ��
		return q;// ���ز���ڵ�
	}

	@Override
	public BinaryNode<T> insertChild(BinaryNode<T> node, T x, boolean leftChild) {
		// TODO �Զ����ɵķ������
		return null;
	}

	@Override
	public void removeChild(BinaryNode<T> p, boolean leftChild) {
		// TODO �Զ����ɵķ������

	}

	@Override
	public void removeAll() {
		// TODO �Զ����ɵķ������

	}

	private TriNode<T> deep;// ˽�б���,��������������

	// ���������һ��ֱ����·��
	public void printDiameter() {
		deep = this.root;
		String path = ")";
		deepest(this.root);
		while (deep != root) {
			path = "," + deep.data.toString() + path;
			deep = deep.parent;
		}
		if (this.root != null)
			path = deep.data.toString() + path;
		System.out.println("��������ֱ��(�Ӹ�������Ҷ�ӽڵ�) : (" + path);
	}

	// ����pΪ����������Ѱ������Ҷ�ӽڵ�,��deepָ���״γ��ֵ�����Ҷ�ӽڵ�
	private void deepest(TriNode<T> p) {
		if (p != null) {
			if (p.level > deep.level)
				deep = p;
			deepest(p.left);
			deepest(p.right);
		}
	}
}