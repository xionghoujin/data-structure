package graph;

import list.Node;
import list.SeqList;
import list.SortedSinglyLinkedList;

/**
 * �ڽӱ��ʾ�Ĵ�Ȩͼ��
 */
public class AdjListGraph<T> extends AbstractGraph<T> {

	protected SeqList<Vertex<T>> vertexlist;// ����˳���
	int MAX_WEIGHT = 99999;// ���Ȩֵ

	// ���췽����sizeָ������˳�������
	public AdjListGraph(int size) {
		size = size < 10 ? 10 : size;
		this.vertexlist = new SeqList<Vertex<T>>(size);
	}

	// �Զ��㼯�Ϻͱ߽�Ϲ���һ��ͼ
	public AdjListGraph(T[] vertices, Edge[] edges) {
		this(vertices.length * 2);

		if (vertices != null)
			for (int i = 0; i < vertices.length; i++)
				insertVertex(vertices[i]);
		if (edges != null)
			for (int j = 0; j < edges.length; j++)
				insertEdge(edges[j]);
	}

	// ���ض�����
	public int vertexCount() {
		return this.vertexlist.length();
	}

	// ���ض���i������Ԫ�أ���iָ��Ԫ����Ч���򷵻�null
	public T get(int i) {
		return this.vertexlist.get(i).data;
	}

	// ����<vi.vj>�ߵ�Ȩֵ
	public int getWeight(int i, int j) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= 0 && j < n) {
			if (i == j)
				return 0;
			Node<Edge> p = this.vertexlist.get(i).adjlink.head.next;// ��i���ߵĵ�����ĵ�һ���ڵ�
			while (p != null) {
				if (p.data.dest == j)
					return p.data.weight;// ����<vi,vj>��Ȩֵ
				p = p.next;
			}
			return MAX_WEIGHT;
		}
		throw new IndexOutOfBoundsException("i = " + i + "j=" + j);// �׳����Խ���쳣
	}

	// ����ͼ�Ķ��㼯�Ϻ��ڽӱ������ַ���
	public String toString() {
		return "���߱�  \n" + this.vertexlist.toString() + "\n";
	}

	// ����Ԫ��Ϊx�Ķ��㣬���ظö����ڶ���˳����е����
	public int insertVertex(T x) {
		this.vertexlist.append(new Vertex<T>(x));// ˳���׷��Ԫ��,�Զ�����
		return this.vertexlist.length() - 1;
	}

	// ����һ��ȨֵΪweight�ı�<vi,vj>�����ñ��Ѵ��ڣ��򲻲���
	public void insertEdge(int i, int j, int weight) {
		if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
				&& j != i) {
			Edge edge = new Edge(i, j, weight);
			SortedSinglyLinkedList<Edge> adjlink = this.vertexlist.get(i).adjlink;// ��õ�i���ߵ�����
			Node<Edge> front = adjlink.head, p = front.next;

			while (p != null && p.data.compareTo(edge) < 0) {// Ѱ�Ҳ���λ��
				front = p;
				p = p.next;
			}

			if (p != null && p.data.compareTo(edge) == 0)// ���ñ��Ѵ��ڣ��򲻲���
				return;
			front.next = new Node<Edge>(edge, p);// ��edge�߽����뵽front���֮��
		}
	}

	// ����һ����
	public void insertEdge(Edge e) {
		insertEdge(e.start, e.dest, e.weight);
	}

	// ɾ����<vi,vj>,i��jָ���������
	public void removeEdge(int i, int j) {
		if (i >= 0 && i < vertexCount() && j >= 0 && j < vertexCount()
				&& i != j) {
			this.vertexlist.get(i).adjlink.remove(new Edge(i, j, 1));// �������������ɾ������
			// �ڵ�i���ߵ�����ɾ��ָ����㣬����������Edge��compareTo(e)��������0
		}
	}

	// ɾ����
	public void removeEdge(Edge edge) {
		removeEdge(edge.start, edge.dest);
	}

	// ɾ�����weivi�Ķ��㼰������ı�
	public void removeVertex(int i) {
		int n = vertexCount();// ɾ��֮ǰ�Ķ�����
		if (i < 0 || i > n)
			return;
		this.vertexlist.remove(i);// ������ż�1
		for (int j = 0; j < n - 1; j++) {// δɾ���ı߽�����ĳЩ�������
			Node<Edge> front = this.vertexlist.get(j).adjlink.head;
			Node<Edge> p = front.next;
			while (p != null) {
				Edge e = p.data;
				if (e.start == i || e.dest == i) {
					front.next = p.next;
					p = front.next;
				} else {
					if (e.start > i)
						e.start--;// ������ż�1
					if (e.dest > i)
						e.dest--;
					front = p;
					p = p.next;
				}
			}
		}
	}

	@Override
	// ����//����vi��vj����һ���ڽӾ��󶥵����
	// ��j = -1ʱ������vi�ĵ�һ���ڽӶ�����ţ����������ڽӶ��㷵��-1
	public int getNextNeighbor(int i, int j) {
		int n = this.vertexCount();
		if (i >= 0 && i < n && j >= -1 && j < n && j != i) {
			Node<Edge> p = this.vertexlist.get(i).adjlink.head.next;// ��õ�i���ߵ������׸����
			while (p != null) {// Ѱ����һ���ڽӶ���
				if (p.data.dest > j)// ��j=-1ʱ�����ص�һ���ڽӶ������
					return p.data.dest;// ������һ���ڽӶ������
				p = p.next;
			}
		}
		return -1;
	}
}