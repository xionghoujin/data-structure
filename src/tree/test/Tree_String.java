package tree.test;

import tree.Tree;
import tree.TreeNode;

/**
 * �Ժ������ʾ�Ĺ�������ɭ��,prelist����洢����ɭ�ֵĺ������ʾ�ַ�������
 */
public class Tree_String {

	public static void main(String[] args) {
        String[] prelist = {"�й�", "\t����", "\t�Ϻ�", "\t����", "\t\t�Ͼ�",
                "\t\t\t����", "\t\t����", "\t\t����", "\t\t\t��ɽ", "\t�㽭", "\t\t����",
                "\t\t����", "\t\t����", "\t�㶫", "\t\t����", "����", "\t�׶�", "����",
                "\t��ʢ��", "\tŦԼ"};
        Tree<String> tree = create(prelist);
		System.out.println(tree.toString());
	}

	public static Tree<String> create(String prelist[]) {
        Tree<String> tree = new Tree<String>();// ��������
        if (prelist != null && prelist.length != 0) {
            tree.root = new TreeNode<String>(prelist[0]);// �������ڵ�
            for (int i = 1; i < prelist.length; i++)
                insert(tree, prelist[i]);// ��ֵΪprelist[i]�����뵽treeɭ�ֵ����һ������
        }
		return tree;
	}

	public static void insert(Tree<String> tree, String str) {
        TreeNode<String> p = tree.getLastSibling(tree.root);// pָ��������һ���ֵ�
        if (p == null)
            p = tree.root;// pû���ֵ�ʱָ�����ѡ��ɭ�������һ����
        if (str.charAt(0) != '\t')
			tree.insertLastSibling(p, str);
		else {
			str = str.substring(1);
			while (str.charAt(0) == '\t') {
				TreeNode<String> lastChild = tree.getLastChild(p);
				if (lastChild != null)
					p = lastChild;
				str = str.substring(1);
			}
            tree.insertLastChild(p, str);// ����str��Ϊp�����һ������
        }
	}
}