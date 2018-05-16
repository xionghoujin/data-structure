package search;

import tree.BinaryNode;
import tree.BinaryTree;

/**
 * ���������� */
public class BinarySortTree<T extends Comparable<T>> extends BinaryTree<T> {

	// ����ն���������
	public BinarySortTree() {
		super();
	}

	// ��values����Ԫ�����β��빹��һ�Ŷ���������
	public BinarySortTree(T[] values) {
		super();
		for (int i = 0; i < values.length; i++) {
			this.insert(values[i]);
		}
	}

	// ���Ҳ������״γ��ֵĹؼ���Ϊkey��Ԫ�ؽڵ㣬�����Ҳ��ɹ����򷵻�null���ǵݹ��㷨
	public BinaryNode<T> search(T key) {
		if (key == null)
			return null;
		BinaryNode<T> p = this.root;
		while (p != null && p.data.compareTo(key) != 0)
			// ��û�������
			if (p.data.compareTo(key) > 0)// ��key��С
				p = p.left;// ����������
			else
				p = p.right;// ����������
		return p;
	}

	// ����Ԫ��x�ڵ㣬������ؼ����ظ�Ԫ�غͿ�Ԫ��
	public void insert(T x) {
		if (x == null)// ���ܲ���ն���
			return;
		if (root == null)// �������ڵ�
			root = new BinaryNode<T>(x);
		else { // ����x����rootΪ���Ķ�����������
			BinaryNode<T> p = this.root, parent = null;
			while (p != null) {
				parent = p;
				if (x.compareTo(p.data) == 0)// �������ظ��ؼ����ظ���Ԫ��
					return;
				if (x.compareTo(p.data) < 0)
					p = p.left;
				else
					p = p.right;
			}
			p = new BinaryNode<T>(x);// ����Ҷ�ӽڵ�
			if (x.compareTo(parent.data) < 0)// p��Ϊparent������
				parent.left = p;
			else
				parent.right = p;// p��Ϊp���Һ���

		}
	}

	// ɾ��Ԫ��Ϊx�Ľڵ㡣����ɾ���ڵ㣬��û��ɾ������null
	public BinaryNode<T> remove(T x) {
		if (root == null || x == null)
			return null;
		return remove(x, root, null);
	}

	// ����pΪ����������ɾ��Ԫ��Ϊx�Ľڵ㣬parent��p�ĸ�ĸ�ڵ㣬����ɾ���ڵ㣬�ݹ��㷨
	private BinaryNode<T> remove(T x, BinaryNode<T> p, BinaryNode<T> parent) {
		if (p == null)
			return null;
		if (x.compareTo(p.data) < 0)
			return remove(x, p.left, p);// ��p��������ɾ��x���ݹ����
		if (x.compareTo(p.data) > 0)
			return remove(x, p.right, p);// ��p����������ɾ��x���ݹ����

		if (p.left != null && p.right != null) {// �ҵ���ɾ�����p��p��2�Ƚ��
			BinaryNode<T> insucc = p.right;// Ѱ��p���и������µĺ�̽��insucc
			while (insucc.left != null)
				insucc = insucc.left;
			p.data = insucc.data;// �Ժ�̽��ֵ�滻
			return remove(p.data, p.right, p);
		}

		if (parent == null) {// p��1�Ȼ�Ҷ�ӽ��
			if (p.left != null)
				root = p.left;
			else
				root = p.right;
			return p;// ����ɾ�����p
		}

		if (p == parent.left) {
			// p��1�Ȼ�Ҷ�ӽ�㣬p��parent������
			if (p.left != null)
				parent.left = p.left;
			else
				parent.left = p.right;
		} else {// p��parent���Һ���
			if (p.left != null)
				parent.right = p.left;
			else
				parent.right = p.right;
		}
		return p;
	}
}