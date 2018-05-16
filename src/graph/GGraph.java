package graph;

/**
 * ͼ�ӿ�,ͼ������������
 */
public interface GGraph<T> {

	int MAX_WEIGHT = 99999;// ���Ȩֵ

	int vertexCount();// ���ض�����

	T get(int i); // ���ض���vi������Ԫ��

	int getWeight(int i, int j);// ����<vi,vj>�ߵ�Ȩֵ

	int insertVertex(T x);// ����Ԫ��ֵx�Ķ���,���ض������

	void insertEdge(int i, int j, int weight);// ����һ��ȨֵΪweight<vi,vj>

	void removeVertex(int i);// ɾ������vi��������ı�

	void removeEdge(int i, int j);// ɾ������<vi,vj>

	int getNextNeighbor(int i, int j);// ����vi��vj�����һ����ᶥ�����

	void DFSTraverse(int i);// �Ӷ���vi������ͼ����һ�����������������

	void BFSTraverse(int i);// �Ӷ���vj������ͼ����һ�ι�����ȱ���
}