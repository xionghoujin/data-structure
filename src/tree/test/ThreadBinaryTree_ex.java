package tree.test;

import tree.ThreadBinaryTree;

/**
 *
 * �������������������������и����ȸ��������
 */
public class ThreadBinaryTree_ex {

	public static void main(String[] args) {
		String[] prelist = { "A", "B", "D", null, null, "E", "G", null, null,
				null, "C", "F", null, "H", null, null, "K" };
		ThreadBinaryTree<String> tbitree = new ThreadBinaryTree<String>(prelist);
        tbitree.inOrder();//�и�����
        tbitree.preOrder();//�ȸ�����
    }
}
