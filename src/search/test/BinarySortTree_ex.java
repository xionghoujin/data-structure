package search.test;

import search.BinarySortTree;

/**
 *
 * �����������Ĳ���Ͳ��Ҳ���
 */
public class BinarySortTree_ex {

	public static void main(String[] args) {
		BinarySortTree<Integer> bstree = new BinarySortTree<Integer>();// ����ն���������
		int[] value = { 54, 18, 66, 87, 36, 12, 54, 81, 15, 76, 57, 6, 40, 99,
				85, 99 };
		for (int i = 0; i < value.length; i++) {
			bstree.insert(new Integer(value[i]));
		}

		bstree.inOrder();// �и���������������������õ��ؼ����������е�����Ԫ������
		Integer key = new Integer(value[value.length - 1]);
		System.out.println("����" + key + ","
				+ (bstree.search(key) != null ? "" : "��") + "�ɹ�");
	}
}
