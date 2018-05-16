package array;

/**
 * ʮ������洢��ϵ��������
 */
public class CrossLinkedSparseMatrix {

	private int rows, columns;// ���������������
	private CrossNode[] rowheads, columnshead;// ��ָ���������ָ�����飬Ԫ��������ʮ������ڵ�

	
	
	//
	public CrossLinkedSparseMatrix(int rows, int columns) {// ����rows��columns�������
		if (rows <= 0 || columns <= 0)
			throw new IllegalArgumentException("��������������������");
		this.rows = rows;
		this.columns = columns;
		this.rowheads = new CrossNode[this.rows];// ������ָ������Ŀ�˳���Ԫ����nul
		this.columnshead = new CrossNode[this.columns];// ������ָ������Ŀ�˳���Ԫ����null
	}

	public CrossLinkedSparseMatrix(int rows, int columns, Triple[] elems) {
		this(rows, columns);
		for (int i = 0; i < elems.length; i++) {
			this.set(elems[i].row, elems[i].column, elems[i].value);
		}
	}

	// ���ؾ����i�е�j�е�Ԫ��
	public int get(int i, int j) {
		if (i < 0 || i >= this.rows || j < 0 || j >= this.columns)
			throw new IndexOutOfBoundsException("����Ԫ�ص��л���Խ��");
		CrossNode p = this.rowheads[i];// ��õ�i�е�����
		while (p != null) {// ������������˳�����
			if (p.data.column == j)// ���ҵ����
				return p.data.value;
			p = p.right;

		}
		return 0;// û���ҵ��򷵻�0
	}

	// ����Ԫ�����þ���Ԫ��
	public void set(Triple elem) {
		this.set(elem.row, elem.column, elem.value);
	}

	// ���þ����row�е�column��Ԫ��ֵΪvalue
	public void set(int row, int column, int value) {
		if (value == 0)
			return;
		if (rows >= this.rows || column >= this.columns)
			throw new IllegalArgumentException("��Ԫ����л������Խ��");
		// �����ڵ�row���������в���ָ����Ԫ�飬����ģ������У��е������в��뵽��Ԫ��Ľ��
		Triple elem = new Triple(row, column, value);
		CrossNode rhead = this.rowheads[row];// rheadָ���row�е�����ĵ�һ�����
		if (rhead == null || rhead.data.column > column) {
			this.rowheads[row] = new CrossNode(elem, rhead, null);
			insertColumnHead(this.rowheads[row]);// ���ýڵ���뵽�еĵ�����
			return;
		}
		CrossNode front = null, p = rhead;
		while (p != null && p.data.column <= column) {// �������뵥������˳�����
			if (p.data.column == column) {// ���ҵ������ľ���Ԫ��ֵ
				p.data.value = value;
				return;
			}
			front = p;// front��p��ǰ�����
			p = p.right;
		}
		front.right = new CrossNode(elem, p, null);// ��fornt֮�������Ԫ���㣬�м��β����
		insertColumnHead(front.right);// ���ý����뵽�еĵ�����
	}

	// ����node�ڵ㵽��Ӧ�е�������
	private void insertColumnHead(CrossNode node) {
		Triple elem = node.data;
		CrossNode chead = this.columnshead[elem.column];// ��õ�column�е�����
		if (chead == null || chead.data.row > elem.row) {// �ձ�����ͷ����
			this.columnshead[elem.column] = node;
			if (chead != null)
				node.down = chead.down;
		} else {// �м�����β����
			CrossNode front = chead, p = front.down;// front��p��ǰ�������
			while (p != null && p.data.row <= elem.row) {// ����������˳�����
				front = p;
				p = p.down;
			}
			front.down = node;// ��node�����뵽front֮���м��β����
			node.down = p;
		}
	}

	// ��ǰ������smat������ӣ�this+=smat
	public void add(CrossLinkedSparseMatrix smat) {
		if (this.rows != smat.rows || this.columns != smat.columns)
			throw new IllegalArgumentException("����������ͬ���������");
		for (int i = 0; i < this.rows; i++) {// ��Ӳ��ϲ�������������
			CrossNode rhead = this.rowheads[i];// ��ȡ��ǰ�����i�е�����
			CrossNode q = smat.rowheads[i];// ��ò��������i�е�����
			if (q == null)
				continue;
			if (rhead == null || rhead.data.column > q.data.column) {// �ձ��ͷ����
				rhead = new CrossNode(new Triple(q.data), rhead, null);
				this.rowheads[i] = rhead;
				insertColumnHead(rhead);
				q = q.right;
			}
			CrossNode front = null, p = rhead;// �м��β����
			while (p != null && q != null) {
				if (p.data.column == q.data.column) {// ��������ʾ��ͬ��λ��
					p.data.value += q.data.value;// ����Ԫ��ֵ������Ԫ��ֵ���
					if (p.data.value == 0)// ���Ԫ��ֵΪ0
						if (front == null) {
							this.rowheads[i] = p.right;
							removeColumnHeads(p);// ����Ӧ�ĵ�������ɾ��node���
							p = p.right;
						} else {
							front.right = p.right;// ��Ӻ�Ԫ�ز���Ҫ�洢��ɾ��p���
							removeColumnHeads(p);// ����Ӧ�еĵ�������ɾ��node���
							p = front.right;
						}
					else {
						front = p;// front��p��ǰ�����
						p = p.right;
					}
					q = q.right;
				} else if (p.data.column < q.data.column) {
					front = p;
					p = p.right;// ��ǰ����Ԫ��ֵС��p����ƶ�����ֵԪ��
				} else {
					// ��ֵq��㲢���뵽front���֮�󣬸�ֵԪ��
					front.right = new CrossNode(new Triple(q.data), p, null);
					q = q.right;
					insertColumnHead(front.right);
				}
			}

			while (q != null) {// ��smat����������ʣ���㸴�Ʋ����뵽��ǰ����β
				front.right = new CrossNode(new Triple(q.data), null, null);
				insertColumnHead(front.right);
				front = front.right;
				q = q.right;
			}
		}

	}

	// ����Ӧ�еĵ�������ɾ��node�ڵ�
	private void removeColumnHeads(CrossNode node) {
		Triple elem = node.data;
		CrossNode chead = this.columnshead[elem.column];// ��õ�column�е�����
		if (chead.data.row == elem.row) {// ͷɾ������chead!=null
			this.columnshead[elem.column] = node.down;// ɾ��ͷ���
		} else {
			CrossNode front = chead, p = front.down;// front��p��ǰ�����
			while (p != null && p.data.row < elem.row) {// ������������˳�����
				front = p;
				p = p.down;
			}
			if (p != null && p.data.row == elem.row)// pΪ���ҵ����
				front.down = node.down;// ɾ��front֮���node��㣬�м��βɾ��
		}
	}
}