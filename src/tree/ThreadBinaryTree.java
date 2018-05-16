package tree;

/**
 * ����������������,����Tָ���ڵ��Ԫ������
 */
public class ThreadBinaryTree<T> {

	public ThreadNode<T> root;

	// �������������������
	public ThreadBinaryTree() {
		this.root = null;
	}

	// �Ա������������ȸ����й������������������������
	public ThreadBinaryTree(T[] prelist) {
		this.root = create(prelist);
		inorderThread(this.root);
	}

	private int i;

	// �Ա������������ȸ����д�������
	private ThreadNode<T> create(T[] prelist) {
		ThreadNode<T> p = null;
		if (i < prelist.length) {
			T elem = prelist[i];
			i++;
			if (elem != null) {// ����elem="^",��ΪT��һ����String
				p = new ThreadNode<T>(elem);// ����Ҷ�ӽڵ�
				p.left = create(prelist);// ����p��������
				p.right = create(prelist);// ����p��������
			}
		}
		return p;
	}

	// �ж����������������Ƿ�Ϊ��
	public boolean isEmpty() {
		return this.root == null;
	}

	private ThreadNode<T> front = null;

	// ������������p�ڵ�Ϊ��������,p��ǰ���ڵ���front
	private void inorderThread(ThreadNode<T> p) {
		if (p != null) {
			inorderThread(p.left);// ��������p��������
			if (p.left == null) {// ��p��������Ϊ��
				p.ltag = 1;// �������������
				p.left = front;// ����p��leftΪָ��ǰ��front������
			}
			if (p.right == null)// ��p��right������Ϊ��
				p.rtag = 1;// �������������

			if (front != null && front.rtag == 1)// ����front.rightΪָ����p������
				front.right = p;//
			front = p;
			inorderThread(p.right);// ����������p��������
		}
	}

	// ����p���и���������µĺ�̽ڵ�
	public ThreadNode<T> inNext(ThreadNode<T> p) {
		if (p.rtag == 1)// ��������Ϊ��
			p = p.right;// p.right����ָ���̽ڵ������
		else {
			p = p.right;
			while (p.ltag == 0)
				// ���������ǿ�,����������
				p = p.left;// �ҵ�����ߵĺ���ڵ�
		}
		return p;
	}

	// ����p���и���������µ�ǰ�����
	public ThreadNode<T> inPrevious(ThreadNode<T> p) {
		if (p.ltag == 1)// ��������Ϊ��
			p = p.left;// p.left����ָ��ǰ����������
		else {
			p = p.left;
			while (p.rtag == 0)
				p = p.right;
		}
		return p;
	}

	// �и����������������������,�ǵݹ��㷨
	public void inOrder() {
		System.out.print("�и����������������������");
		ThreadNode<T> p = this.root;
		while (p.ltag == 0)
			// Ѱ�Ҹ�������ߵĺ���ڵ�,����һ�����ʵĽڵ�
			p = p.left;
		while (p != null) {
			System.out.print(p.data.toString() + " ");
			p = inNext(p);// ����p���и������µĺ�̽��
		}
		System.out.println();
	}

	// ����p���ȸ������µĺ�̽ڵ�
	public ThreadNode<T> preNext(ThreadNode<T> p) {
		if (p.ltag == 0)// ���������ǿ�
			p = p.left;// ���Ӿ���p�ĺ�̽ڵ�
		else { // ����,��������ֵܻ�ĳ���������ȵ��Һ���
			while (p.rtag == 1 && p.right != null)
				// Ѱ��ĳ����������
				p = p.right;// �������ĳ���������ȵ��Һ���
			p = p.right;// �Һ�����p�ĺ�̽ڵ�
		}
		return p;
	}

	// �ȸ������������������,�ǵݹ��㷨
	public void preOrder() {
		System.out.print("�ȸ����������������������,�ǵݹ�");
		ThreadNode<T> p = this.root;
		while (p != null) {
			System.out.print(p.data.toString() + "");
			p = preNext(p); // ����p���ȸ������µĺ�̽��
		}
		System.out.println();
	}

	// ����p�ں�����������µ�ǰ���ڵ�
	public ThreadNode<T> postNext(ThreadNode<T> p) {
		if (p.rtag == 0)
			p = p.right;
		else {
			while (p.ltag == 1 && p.left != null)
				p = p.left;
			p = p.left;
		}
		return p;
	}

	// ������Ϊ�����
	public void insertRoot(T x) {
		if (this.root == null)
			this.root = new ThreadNode<T>(x, null, 1, null, 1);
		else {
			ThreadNode<T> p = this.root;
			while (p.rtag == 0)
				// Ѱ��ԭ������������ڵ�p,�������ʽ��
				p = p.right;
			this.root = new ThreadNode<T>(x, this.root, 0, null, 1);
			p.right = this.root;// �޸�p�ĺ������
		}
	}

	// ����x��Ϊp�ĺ��ӽ��,��leftChildΪtrue,��������,��������Һ���
	public ThreadNode<T> insertChild(ThreadNode<T> p, T x, boolean leftChild) {
		ThreadNode<T> q = null;
		if (leftChild) {// ��������
			q = new ThreadNode<T>(x, p.left, p.ltag, p, 1);
			p.left = q;
			p.ltag = 0;
			if (q.ltag == 0) {
				ThreadNode<T> prev = inPrevious(q);// prev��q���и������µ�ǰ��
				prev.right = q;
			}
		} else {
			q = new ThreadNode<T>(x, p, 1, p.right, p.rtag);
			p.right = q;
			p.rtag = 1;
			if (q.rtag == 0) {
				ThreadNode<T> succ = inNext(q);// succ��q���и������µĺ��
				succ.left = q;// �޸ĺ�̽��succ�ĺ��
			}
		}

		return q;
	}

	// ɾ���Һ��ӽ��
	public void removeRightChild(ThreadNode<T> p) {
		if (root == null || p == null || p.rtag == 1)
			return;
		ThreadNode<T> q = p.right;// qΪ��ɾ�����
		if (q.ltag == 0) {// q������
			p.right = q.left;// ��q�����Ӷ���q��p.rtagδ��
			ThreadNode<T> prev = inPrevious(q);// prevָ��q���и������µ�ǰ��
			prev.right = q.right;// �޸�ǰ�����prev�ĺ������
			prev.rtag = q.rtag;
			if (q.rtag == 0)// qҲ���Һ���
				inNext(q).left = prev;// �޸�q��̽��inNext(q)��ǰ������
		} else {
			p.right = q.right;// ��q���Һ��Ӷ���q
			p.rtag = q.rtag;
			if (q.rtag == 0)// q���Һ���û������
				inNext(q).left = p;// �޸�q�ĺ�̽��inNext(q)��ǰ������
		}
	}

	// ɾ�������,�����Ӷ���
	public void removeRoot() {
		if (root == null)// ����������ɾ��
			return;
		ThreadNode<T> prev = inPrevious(root);// prevָ�����ǰ��
		ThreadNode<T> succ = inNext(root);// succָ����ĺ��
		if (prev != null) {
			prev.right = root.right;// ʹǰ��prev�ĺ��ָ������Һ���
			prev.rtag = root.rtag;
		}
		if (succ != null) {// ���к��ʱ
			succ.left = prev;// �޸ĺ��succ��ǰ��������succ.rtagδ��
		}
		if (root.ltag == 0)// ��������ʱ
			root = root.left;// �����Ӷ����
		else
			root = root.right;// ���Һ��Ӷ����
	}
}