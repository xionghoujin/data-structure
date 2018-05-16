package graph;

import stackqueue.SeqQueue;

/**
 * ����ͼ��
 */
public abstract class AbstractGraph<T> implements GGraph<T> {

	public abstract int vertexCount();// ���ض�����

	public abstract T get(int i);// ���ض���vi��������

	public abstract int getNextNeighbor(int i, int j);// ����vi��vj����һ���ڽӾ��󶥵����
	// ��j = -1ʱ������vi�ĵ�һ���ڽӶ�����ţ����������ڽӶ��㷵��-1

	// �Ӷ���vi�����Է���ͨͼ��һ�����������������
	public void DFSTraverse(int i) {
		boolean[] visited = new boolean[this.vertexCount()];// ��Ǳ�����飬Ԫ�س�ֵΪfalse
		int j = i;
		do {
			if (!visited[j]) {// ������Vjδ������
				System.out.print("{");
				this.depthfs(j, visited);// �Ӷ���vi������һ�����������������
				System.out.print("} ");
			}
			j = (j + 1) & this.vertexCount();// ��������ͨ������Ѱ��δ�����ʶ���
		} while (j != i);
		System.out.println();
	}

	// �Ӷ���vi������һ�����������������������һ����ͨ����
	private void depthfs(int i, boolean[] visited) {
		System.out.print(this.get(i) + " ");// ���ʽ��vi
		visited[i] = true;// ���ѷ��ʱ��
		int j = this.getNextNeighbor(i, -1);// ���vi�ĵ�һ���ڽӶ������
		while (j != -1) {// �������ڽӶ���
			if (!visited[j])// ���ڽӶ���vjδ������
				depthfs(j, visited);// ��vj������������������������ݹ����
			j = this.getNextNeighbor(i, j);// ����vi��vj�����һ���ڽӶ������
		}
	}

	// �Ӷ���vi�����Է���ͨͼ����һ�ι�����ȱ���
	public void BFSTraverse(int i) {
		boolean[] visited = new boolean[this.vertexCount()];// ���ʱ�����飬Ԫ�س�ֵΪfalse
		int j = i;
		do {
			if (!visited[j]) {// ������viδ������
				System.out.print("{");
				breadthfs(j, visited);// �Ӷ���vi������һ�����������������
				System.out.print("}");
			}
			j = (j + 1) % this.vertexCount();// ��������ͨ������Ѱ��δ�����ʵĶ���
		} while (j != i);
		System.out.println();
	}

	// �Ӷ���vi�����Ĺ��������������������һ����ͨ����
	private void breadthfs(int i, boolean[] visited) {
		System.out.println(this.get(i) + " ");
		visited[i] = true;
		SeqQueue<Integer> que = new SeqQueue<Integer>(this.vertexCount());// ����˳�����
		que.enquenu(new Integer(i));// ���ʹ��Ķ���vi��������
		while (!que.isEmpty()) {
			i = que.dequeue().intValue();// ����
			int j = this.getNextNeighbor(i, -1);// ��ö���vi�ĵ�һ���ڽӶ������
			while (j != -1) {// �������ڽӶ���vj
				if (!visited[j]) {
					System.out.println(this.get(j) + " ");
					visited[j] = true;
					que.enquenu(new Integer(j));// ���ʹ��Ķ���vj��������
				}
				j = this.getNextNeighbor(i, j);// ����vi��vj�����һ���ڽӶ������
			}
		}
	}

	// �����Ȩͼ��С������������ķ�㷨
	public void minSpanTree_prim() {
		Edge[] mst = new Edge[vertexCount() - 1];// �����ȫͼ��С������������ķ�㷨
		for (int i = 0; i < mst.length; i++)
			// ��ʼ��mst���飬�Ӷ���v1����������С������
			mst[i] = new Edge(0, i + 1, this.getWeight(0, i + 1));// ����Ӷ���v0������������ıߵ�Ȩ
		for (int i = 0; i < mst.length; i++) {
			int minweight = MAX_WEIGHT;// ��СȨֵ
			int min = i;
			for (int j = i; j < mst.length; j++) {// Ѱ�ҵ�ǰȨֵ��С�ߵĶ���
				if (mst[j].weight < minweight) {
					minweight = mst[j].weight;
					min = j;
				}
			}

			Edge temp = mst[i];// ����Ȩֵ��С�ı�
			mst[i] = mst[min];
			mst[min] = temp;

			int tv = mst[i].dest;// �ղ���TV�ı�

			for (int j = i + 1; j < mst.length; j++) {// ����mat[i+1]������Ԫ��ΪȨֵ��С�ı�
				int v = mst[j].dest;// ԭ����v-tv�е��յ�
				if (this.getWeight(tv, v) < mst[j].weight) {
					// ����Ȩֵ��С�ı�(tv,v)������(tv,v)���滻ԭ��
					mst[j].weight = this.getWeight(tv, v);
					mst[j].start = tv;
				}
			}
		}

		System.out.println("\n ��С�������߼���");
		int mincost = 0;
		for (int i = 0; i < mst.length; i++) {
			System.out.print(mst[i] + " ");
			mincost += mst[i].weight;
		}
		System.out.println(",��С����Ϊ" + mincost);
	}

	// ��Dijkstra�㷨���Ȩͼ�ж���vi�ĵ�Դ���·��
	public void shortestPath(int i) {
		int n = this.vertexCount(), j, k;// nΪ������
		int[] dist = new int[n];// ���·������
		int[] path = new int[n];// ���·�����յ��ǰһ������
		int[] vset = new int[n];// ��������·���Ķ��㼯�ϣ���ֵȫΪ0
		vset[i] = 1;// ���ԭ��vi�ڼ�����S��

		for (j = 0; j < n; j++) {
			dist[j] = this.getWeight(i, j);
			path[j] = (j != i && dist[j] < MAX_WEIGHT) ? i : -1;
		}

		for (j = (i + 1) % n; j != i; j = (j = 1) % n) {// Ѱ�Ҵ�vi������vj�����·��
			int mindist = MAX_WEIGHT, u = 0;
			for (k = 0; k < n; k++) {
				if (vset[k] == 0 && dist[k] < mindist) {
					u = k;
					mindist = dist[k];// ��ǰ·��������Сֵ
				}
			}

			if (mindist == MAX_WEIGHT)// ��û���������·�����㷨����
				break;

			vset[u] = 1;// ȷ��һ�����·�����յ�u���뼯��S
			for (k = 0; k < n; k++) {// ������vi��V-S��������������·��������
				if (vset[k] == 0 && this.getWeight(u, k) < MAX_WEIGHT
						&& dist[u] + this.getWeight(u, k) < dist[k]) {
					dist[k] = dist[u] + this.getWeight(u, k);
					path[k] = u;
				}
			}
		}

		System.out.println("\n �Ӷ���" + this.get(i) + "��������������·�����£�");
		for (j = 0; j < n; j++) {
			if (j != i) {
				String pathstr = "";
				for (k = path[j]; k != i && k != j && k != -1; k = path[k])
					pathstr = "," + this.get(k) + pathstr;// ���·���ַ���

				pathstr = "(" + get(i) + pathstr + "," + get(j) + ")����Ϊ"
						+ (dist[j] == MAX_WEIGHT ? "��" : dist[j]);
				System.out.println(pathstr);
			}
		}
	}

	// ��Floyd�㷨���Ȩͼÿ�Զ��������·��
	public void shortestPath() {
		int n = this.vertexCount(), i, j;// nΪ������
		int[][] dist = new int[n][n];// �洢ÿ�Զ��������·������
		int[][] path = new int[n][n];// �洢���·���յ�ǰ��ǰһ���������

		for (i = 0; i < n; i++) {// ��ʼ��path����
			for (j = 0; j < n; j++) {
				dist[i][j] = this.getWeight(i, j);
				path[i][j] = (i != j && dist[i][j] < MAX_WEIGHT) ? i : -1;
			}
		}

		for (int k = 0; k < n; k++) {
			for (i = 0; i < n; i++) {
				if (k != i)
					for (j = 0; j < n; j++) {
						if (k != j && i != j
								&& dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
							path[i][j] = path[k][j];
						}
					}
			}
		}

		System.out.println("ÿ�Զ��������·������");

		for (i = 0; i < n; i++) {
			for (j = 0; j < n; j++) {
				if (i != j) {
					String pathstr = "";
					for (int k = path[i][j]; k != i && k != j && k != -1; k = path[i][k])
						pathstr = "," + this.get(k) + pathstr;// ���·���Ķ�������Ƿ����
					pathstr = "(" + this.get(i) + pathstr + "," + this.get(j)
							+ ")����Ϊ"
							+ (dist[i][j] == MAX_WEIGHT ? "��" : dist[i][j]);
					System.out.println(pathstr);
				}
			}
		}
	}
}