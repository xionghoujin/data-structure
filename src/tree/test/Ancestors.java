package tree.test;

import tree.BinaryNode;
import tree.BinaryTree;

/**
 *
 * �����������ָ�������������Ƚ��
 */
public class Ancestors {

	public static void main(String[] args) {
		String[] prelist = { "A", "B", "D", null, "G", null, null, null, "C",
				"E", null, null, "F", "H" };
		BinaryTree<String> bitree = new BinaryTree<String>(prelist);

        bitree.preOrder();//�ȸ�����
        bitree.inOrder();//�и�����
        bitree.postOrder();//�������

        System.out.println("�ڵ������ " + bitree.count() + "   �߶��� " + bitree.height());
        String value = "H";
		BinaryNode<String> find = bitree.search(value);
		if (find == null)
            System.out.println("δ�ҵ�" + value);
        else {
			BinaryNode<String> parent = bitree.getParent(find);
            System.out.println(find.data + "��������");
            while (parent != null) {
				System.out.print(parent.data + "  ");
				parent = bitree.getParent(parent);
			}
			System.out.println();
		}
	}
}