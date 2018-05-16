package tree.test;

import tree.Tree;
import tree.TreeNode;

/**
 * ����һ�ų�������ɭ��
 */
public class Tree_city {

	public static void main(String[] args) {
		Tree<String> tree = make();
		System.out.println(tree.toString());
	}

	public static Tree<String> make() {
		Tree<String> tree = new Tree<String>();
        tree.root = new TreeNode<String>("�й�");
        tree.insertLastChild(tree.root, "������");
        tree.insertLastChild(tree.root, "�Ϻ���");
        TreeNode<String> js = tree.insertLastChild(tree.root, "����ʡ");
        tree.insertLastChild(js, "�Ͼ���");
        tree.insertLastChild(js, "������");
        TreeNode<String> zj = tree.insertLastSibling(js, "�㽭ʡ");
        tree.insertLastChild(zj, "������");
        tree.insertLastChild(zj, "������");
        tree.insertLastChild(zj, "������");
        TreeNode<String> korea = tree.insertLastSibling(tree.root, "����");
        tree.insertLastChild(korea, "�׶�");
        TreeNode<String> usa = tree.insertLastSibling(korea, "����");
        tree.insertLastChild(usa, "��ʢ��");
        return tree;
	}
}