package tree;

/**
 * Hufman��
 */
public class HuffmanTree {

	private int leafNum;// Ҷ�ӽ�����
	private TriElement[] huftree;// HuffmanTree�Ľڵ�����

	public HuffmanTree(int[] weight) {
		int n = weight.length;// n��Ҷ�ӽڵ�
		this.leafNum = n;
		this.huftree = new TriElement[2 * n - 1];// n��Ҷ�ӽڵ��Huffman������2n-1�����
		// ��������ʼ������n�����
		for (int i = 0; i < n; i++)
			this.huftree[i] = new TriElement(weight[i]);
		for (int i = 0; i < n - 1; i++) {// ����n-1��2�Ƚ�㣬ÿ��ѭ������1��2�Ƚ��
			int min1 = Integer.MAX_VALUE, min2 = min1;// ��С�ʹ���СȨֵ
			int x1 = -1, x2 = -1;// ��¼�����޸�ĸ����СȨֵ�Ľڵ��±�
			for (int j = 0; j < n + i; j++) {
				if (huftree[j].data < min1 && huftree[j].parent == -1) {
					min2 = min1;
					x2 = x1;
					min1 = huftree[j].data;// min1������СȨֵ
					x1 = j;// x1������СȨֵ�����±�
				} else if (huftree[j].data < min2 && huftree[j].parent == -1) {
					min2 = huftree[j].data;
					x2 = j;// x2������СȨֵ�����±�
				}
			}
			huftree[x1].parent = n + i;// ���ҳ�������Ȩֵ��С�������ϲ�Ϊһ������
			huftree[x2].parent = n + i;
			this.huftree[n + i] = new TriElement(huftree[x1].data
					+ huftree[x2].data, -1, x1, x2);
		}
	}

	public String toString() {
		String str = "Huffman���Ľ������:\n";
		for (int i = 0; i < this.huftree.length && huftree[i] != null; i++) {
			str += "��" + i + "��  " + this.huftree[i].toString() + "\n";
		}
		return str;
	}

	// ���ص�ǰhufman���Ĺ���������
	public String[] hufmanCodes() {
		String[] hufcodes = new String[this.leafNum];
		for (int i = 0; i < this.leafNum; i++) {// ��n������Hufman����
			hufcodes[i] = "";
			int child = i;
			int parent = huftree[child].parent;
			while (parent != -1) {// ��Ҷ�������ֱ�����ڵ�ѭ��
				if (huftree[parent].left == child)
					hufcodes[i] = "0" + hufcodes[i];// ���ӱ���Ϊ0
				else
					hufcodes[i] = "1" + hufcodes[i];// �Һ��ӱ���Ϊ1
				child = parent;
				parent = huftree[child].parent;
			}
		}
		return hufcodes;
	}
}