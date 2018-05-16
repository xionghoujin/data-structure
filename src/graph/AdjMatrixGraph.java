package graph;

import list.SeqList;

/**
 * �ڽӾ����ʾ�Ĵ�Ȩͼ��
 */
public class AdjMatrixGraph<T> extends AbstractGraph<T> {

	protected SeqList<T> vertexlist;// ˳���洢ͼ�Ķ��㼯��
	protected int[][] adjmatrix; // ͼ���ڽӾ���
	private final int MAX_WEIGHT = 99999;// ���Ȩֵ

	// �����ͼ��sizeָ��˳�����������ڽӾ����ά���������
	public AdjMatrixGraph(int size) {
		size = size < 10 ? 10 : size;
		this.vertexlist = new SeqList<T>(size);
		this.adjmatrix = new int[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;// �ߵ�ȨֵΪ0�����Ȩֵ
	}

	// �Զ��㼯�Ϻͱ߼��Ϲ���ͼ
	public AdjMatrixGraph(T[] vertices, Edge[] edges) {
		this(vertices.length);

		if (vertices != null)// �����ͼ��û�ж���û�б�
			for (int i = 0; i < vertices.length; i++)
				insertVertex(vertices[i]);

		if (edges != null)// ��edges==null�������ͼû�б�
			for (int j = 0; j < edges.length; j++)
				insertEdge(edges[j]);
	}

	// ���ض���ĸ���
	public int vertexCount() {
		return this.vertexlist.length();// ���ض���˳����Ԫ�ظ���
	}

	// ���ض���vi������Ԫ�أ���iָ�������Ч�򷵻�null
	public T get(int i) {
		return this.vertexlist.get(i);
	}

	// ����<vi,vj>��Ȩֵ
	public int getWeight(int i, int j) {
		return this.adjmatrix[i][j];
	}

	// ����ͼ�Ķ��㼯�Ϻ��ڽӾ��������ַ���
	public String toString() {
		String str = "���㼯�ϣ�" + this.vertexlist.toString() + "\n �ڽӾ���  \n";
		int n = this.vertexCount();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				str += this.adjmatrix[i][j] == MAX_WEIGHT ? "    ��" : "     "
						+ this.adjmatrix[i][j];
			str += "\n";
		}

		return str;
	}

	// ����һ������
	public int insertVertex(T x) {
		this.vertexlist.append(x);// ˳���׷��Ԫ�أ��Զ���������
		if (this.vertexCount() > this.adjmatrix.length) {// ����ά�����������㣬������
			int temp[][] = adjmatrix, i, j;
			this.adjmatrix = new int[temp.length * 2][temp.length * 2];// ��ά��������2��
			for (i = 0; i < temp.length; i++) {
				for (j = 0; j < temp.length; j++)
					// ����ԭ�ڽӾ���
					this.adjmatrix[i][j] = temp[i][j];// ����ԭ�ڽӾ���
				for (j = temp.length; j < temp.length * 2; j++)
					this.adjmatrix[i][j] = MAX_WEIGHT;
			}

			// ��ʼ��������ڽӾ���
			for (i = temp.length; i < temp.length * 2; i++)
				for (j = 0; j < temp.length * 2; j++)
					this.adjmatrix[i][j] = (i == j) ? 0 : MAX_WEIGHT;
		}
		return this.vertexlist.length() - 1;// ���ز��붥������
	}

	// ����һ����
	public void insertEdge(int i, int j, int weight) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= 0 && j < n && i != j
				&& this.adjmatrix[i][j] == MAX_WEIGHT)
			this.adjmatrix[i][j] = weight;
	}

	// ����һ����
	public void insertEdge(Edge edge) {
		this.insertEdge(edge.start, edge.dest, edge.weight);
	}

	// ɾ��һ����
	public void removeEdge(int i, int j) {
		if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
				&& i != j) {
			this.adjmatrix[i][j] = MAX_WEIGHT;// ���øñߵ�ȨֵΪ�����
		}
	}

	// ɾ������vi
	public void removeVertex(int i) {
		int n = this.vertexCount();// ������
		if (i < 0 || i > n)
			return;
		this.vertexlist.remove(i);// ɾ��˳���ĵ�i��Ԫ�أ��������Ѽ�һ
		for (int j = 0; j < i; j++)
			for (int k = i + 1; k < n; k++)
				this.adjmatrix[j][k - 1] = this.adjmatrix[j][k];// Ԫ������һ��
		for (int j = i + 1; j < n; j++)
			for (int k = 0; k < i; k++)
				this.adjmatrix[j - 1][k] = this.adjmatrix[j][k];// Ԫ������һ��

		for (int j = i + 1; j < n; j++)
			for (int k = i + 1; k < n; k++)
				this.adjmatrix[j - 1][k - 1] = this.adjmatrix[j][k];// Ԫ��������һ�л�һ��
	}

	@Override
	// ����//����vi��vj����һ���ڽӾ��󶥵����
	// ��j = -1ʱ������vi�ĵ�һ���ڽӶ�����ţ����������ڽӶ��㷵��-1
	public int getNextNeighbor(int i, int j) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && j != i)
			for (int k = j + 1; k < n; k++)
				if (this.adjmatrix[i][k] > 0// ��j=-1ʱ��k��0��ʼѰ����һ���ڽӾ���
						&& this.adjmatrix[i][k] < MAX_WEIGHT)
					return k;
		return -1;
	}
}