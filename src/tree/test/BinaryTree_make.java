package tree.test;

import tree.BinaryNode;
import tree.BinaryTree;

/**
 * ���첢����������
 */
public class BinaryTree_make {

	public static void main(String[] args) {
		BinaryTree<String> bitree = make();
        bitree.postOrder();//�ȸ�����
        bitree.inOrder();//�и�����
        bitree.postOrder();//�������
    }

	public static BinaryTree<String> make() {
		BinaryTree<String> bitree = new BinaryTree<String>();
		BinaryNode<String> child_f, child_d, child_b, child_c;
		child_d = new BinaryNode<String>("D", null, new BinaryNode<String>("G"));
		child_b = new BinaryNode<String>("B", child_d, null);
		child_f = new BinaryNode<String>("F", new BinaryNode<String>("H"), null);
		child_c = new BinaryNode<String>("C", new BinaryNode<String>("E"),
				child_f);
		bitree.root = new BinaryNode<String>("C", child_b, child_c);
		return bitree;
	}
}