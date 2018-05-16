package graph;

/**
 * ͼ�ı�
 */
public class Edge implements Comparable<Edge> {
	public int start, dest, weight;// �ߵ������ţ��յ���ź�Ȩֵ

	public Edge(int start, int dest, int weight) {
		super();
		this.start = start;
		this.dest = dest;
		this.weight = weight;
	}

	// ���ر������ַ���
	public String toString() {
		return "(" + start + "," + dest + "," + weight + ")";
	}

	// �Ƚ������ߴ�С��Լ��������������ȹ���ֻ�Ƚ������յ㣬���Ƚ�Ȩֵ
	public int compareTo(Edge e) {
		if (this.start != e.start)
			return this.start - e.start;// ����㲻��ͬʱ���������Ĳ�ֵ
		return this.dest - e.dest;// �������ͬʱ�������յ�Ĳ�ֵ
	}
}