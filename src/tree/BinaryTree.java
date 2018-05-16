package tree;

import stackqueue.LinkedQueue;
import stackqueue.LinkedStack;

/**
 * �������࣬ʵ��BinaryTree<T>�ӿڣ�����Tָ������Ԫ������
 */
public class BinaryTree<T> implements BinaryTTree<T> {

	public BinaryNode<T> root;// ���ڵ㣬���ṹΪ��������

	private int i;// ��Ϊcreate(T[] prelist)�����ĳ�Ա����ʹ��

	public BinaryTree() {
		this.root = null;
	}

	// ���ȸ����к��и����й��������
	public BinaryTree(T[] prelist, T[] inlist) {
		this.root = create(prelist, inlist, 0, 0, prelist.length);
	}

	// �Ա������������ȸ����й���һ�Ŷ�����
	public BinaryTree(T[] prelist) {
		this.root = create(prelist);
	}

	// �Ա������������ȸ����й���һ���Ӷ�����,�����ĸ�ֵ��prelist[i],���������������ĸ��ڵ�
	private BinaryNode<T> create(T[] prelist) {
		BinaryNode<T> p = null;
		if (i < prelist.length) {
			T elem = prelist[i];
			i++;
			if (elem != null) {// ����elme="^"����ΪT��һ����String
				p = new BinaryNode<T>(elem);// ����Ҷ�ӽ��
				p.left = create(prelist);// ����p��������
				p.right = create(prelist);// ����p��������
			}
		}
		return p;
	}

	// ���������ĸ��ڵ�(���ȸ����к��и����й��������)
	private BinaryNode<T> create(T[] prelist, T[] inlist, int preStart,
			int inStart, int n) {
		if (n <= 0)
			return null;
		BinaryNode<T> root = new BinaryNode<T>(prelist[preStart]);// �����ֵ
		int i = inStart;
		while (i < n) {// ���и������в��Ҹ�ֵ���ڵ�λ��
			if (prelist[preStart] == inlist[i]) {
				root.left = create(prelist, inlist, preStart + 1, inStart, i);// ����������
				root.right = create(prelist, inlist, preStart + i + 1, inStart
						+ i + 1, n - i - 1);// ����������
				break;
			}
			i++;
		}
		return root;
	}

	// �ж϶������Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.root == null;
	}

	// �ж϶������Ľڵ����
	public int count() {
		return count(root);
	}

	// �����Խ��pΪ���������Ľڵ����
	public int count(BinaryNode<T> p) {
		if (p == null)
			return 0;
		return 1 + count(p.left) + count(p.right);
	}

	// ���ض������ĸ߶�
	public int height() {
		return height(root);
	}

	// ������p���Ϊ���������߶ȣ�����������
	public int height(BinaryNode<T> p) {
		if (p == null)
			return 0;
		int lh = height(p.left);// �����������ĸ߶�
		int rh = height(p.right);// �����������ĸ߶�
		return lh >= rh ? lh + 1 : rh + 1;// ��ǰ�����߶�Ϊ�ϸ������ĸ߶ȼ�1
	}

	// �ȸ��������������
	public void preOrder() {
		System.out.print("�ȸ��������������  ");
		preOrder(root);// �����ȸ���������������ĵݹ鷽��
		System.out.println();
	}

	// �ȸ����������p���Ϊ�����Ӷ��������ݹ鷽��
	public void preOrder(BinaryNode<T> p) {
		if (p != null) {// ����������Ϊ��
			System.out.print(p.data.toString() + " ");// ���ʵ�ǰ���
			preOrder(p.left);// ���ȸ����������ǰ����������
			preOrder(p.right);// ���ȸ����������ǰ����������
		}
	}

	// �и�����������
	public void inOrder() {
		System.out.print("�и��������������  ");
		inOrder(root);// �����и���������������ĵݹ鷽��
		System.out.println();
	}

	// �и����������p���Ϊ�����Ӷ��������ݹ����
	public void inOrder(BinaryNode<T> p) {
		if (p != null) {
			inOrder(p.left);// �и�����������������ݹ����
			System.out.print(p.data.toString() + " ");
			inOrder(p.right);// �и�����������������ݹ����
		}
	}

	// ����������������
	public void postOrder() {
		System.out.print("����������������  ");
		postOrder(root);// ���ú����������������ĵݹ鷽��
		System.out.println();
	}

	// ������������p���Ϊ�����Ӷ��������ݹ����
	public void postOrder(BinaryNode<T> p) {
		if (p != null) {
			postOrder(p.left);
			postOrder(p.right);
			System.out.print(p.data.toString() + " ");
		}
	}

	// ����α���������
	public void levelOrder() {
		LinkedQueue<BinaryNode<T>> que = new LinkedQueue<BinaryNode<T>>();
		BinaryNode<T> p = this.root;
		System.out.println("��α�����");
		while (p != null) {
			System.out.print(p.data + "");
			if (p.left != null)
				que.enquenu(p.left); // p�����ӽڵ����
			if (p.right != null)
				que.enquenu(p.right); // p���Һ��ӽڵ����
			p = que.dequeue(); // pָ����Խڵ�,�����пշ���null
		}
		System.out.println();
	}

	// ���Ҳ������״γ��ֹؼ���ΪkeyԪ�صĽڵ�
	public BinaryNode<T> search(T key) {
		return search(root, key);
	}

	// ����pΪ���������в��Ҳ������״γ��ֵĹؼ���Ϊkey��Ԫ�ؽ�㣬��δ�ҵ����򷵻�null
	public BinaryNode<T> search(BinaryNode<T> p, T key) {
		if (p == null || key == null)
			return null;
		if (p.data.equals(key))
			return p;// ���ҳɹ��������ҵ����
		BinaryNode<T> find = search(p.left, key);// ���������в��ң��ݹ����
		if (find == null)// ������������δ�ҵ�
			find = search(p.right, key);// ��������������в��ң��ݹ����
		return find;// ���ز��ҽ��
	}

	// ����node�ĸ�ĸ�ڵ�
	public BinaryNode<T> getParent(BinaryNode<T> node) {
		if (root == null || node == root)
			return null;
		return getParent(root, node);
	}

	// ����pΪ���������в��Ҳ�����node���ĸ�ĸ���
	public BinaryNode<T> getParent(BinaryNode<T> p, BinaryNode<T> node) {
		if (p == null)
			return null;
		if (p.left == node || p.right == node)
			return p;
		BinaryNode<T> find = getParent(p.left, node);
		if (find == null)
			find = getParent(p.right, node);
		return find;
	}

	// ���ض������Ĺ�����ʾ�ַ���
	public String toGenListString() {
		return "�������Ĺ�����ʾ:" + toGenListString(this.root) + "\n";
	}

	// ���ؿ�������ʾ
	public String toGenListString(BinaryNode<T> p) {
		if (p == null)
			return "^";// ���ؿ�������ʾ
		String str = p.data.toString();
		if (p.left != null || p.right != null)// ��Ҷ��㣬������
			str += "(" + toGenListString(p.left) + ","
					+ toGenListString(p.right) + ")";// �ݹ����
		return str;
	}

	@Override
	// ����Ԫ��x��Ϊ���ӣ�������Ϊ�Һ���
	public void insertRoot(T x) {
		root = new BinaryNode<T>(x, root, null);
	}

	@Override
	// ����Ԫ��x��Ϊp���ĺ��ӣ���leftChildΪtrue����������Ϊ���ӣ�������Ϊ�Һ���
	public BinaryNode<T> insertChild(BinaryNode<T> p, T x, boolean leftChild) {
		if (p == null || x == null)
			return null;
		if (leftChild) {
			// ����x��Ϊp�����ӣ�pԭ���ӳ�Ϊx����
			p.left = new BinaryNode<T>(x, p.left, null);
			return p.left;// ���ز�����
		}
		p.right = new BinaryNode<T>(x, null, p.right);// ����x�����Ϊp���Һ���
		return p.right;
	}

	@Override
	// ɾ��p�ڵ�����������,��leftChildΪtrue,��ɾ��������,����ɾ��������
	public void removeChild(BinaryNode<T> p, boolean leftChild) {
		if (p != null)
			if (leftChild)
				p.left = null;
			else
				p.right = null;
	}

	@Override
	// ɾ��������
	public void removeAll() {
		this.root = null;
	}

	// �и���������������ķǵݹ����
	public void inOrderTraverse() {
		System.out.print("�и�����������Ǳ�����");
		LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();
		BinaryNode<T> p = this.root;
		while (p != null || !stack.isEmpty()) {
			if (p != null) {
				stack.push(p);// p�����ջ
				p = p.left;// ����������
			} else {// pΪ����ջ�ǿ�
				p = stack.pop();// pָ���ջ���
				System.out.println(p.data + " ");// ���ʽ��
				p = p.right;// ����������
			}
		}
	}

	// �ȸ���������������ķǵݹ����
	public void preOrderTraverse() {
		System.out.println("�ȸ��������(�ǵݹ�)");
		LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();
		BinaryNode<T> p = this.root;
		while (p != null || !stack.isEmpty())
			if (p != null) {
				System.out.println(p.data + " ");// ���ʽ��
				stack.push(p);// ��p��ջ
				p = p.left;
			} else {
				p = stack.pop();
				p = p.right;
			}
	}
}