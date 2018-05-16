package tree.test;

import tree.Tree;
import tree.TreeNode;

/**
 * ���Ĺ�����ʾ������
 */
public class Tree_GenList {

	private static int i;

	public static void main(String[] args) {
        String glist = "�й�(����,�Ϻ�,����(�Ͼ�,����,����),�㽭(����,����,����),�㶫(����)),����(�׶�),����(��ʢ��)";
        System.out.println(createByGenList(glist).toGenListString());

	}

    // �Թ�����ʾ������
    public static Tree<String> createByGenList(String glist) {
		Tree<String> tree = new Tree<String>();
		i = 0;
		if (glist.length() > 0)
			tree.root = create(glist);
		return tree;
	}

    // �Թ�����ʾ����һ�����������������ֵ��glist[i]�����������ĸ���㣬�ݹ����
    public static TreeNode<String> create(String glist) {
		TreeNode<String> p = null;
		int j = i + 1;
		if (j < glist.length()) {
			char ch = glist.charAt(j);
            while (ch != '(' && ch != ',' && ch != ')') {// ʶ���ַ�����Ϊ���Ԫ��ֵ
                j++;
				ch = glist.charAt(j);
			}
		}
        p = new TreeNode<String>(glist.substring(i, j));// �������
        i = j;
        if (i < glist.length() && glist.charAt(i) == '(') {// ��������������
            i++;// ����'('
            p.child = create(glist);// �����������ݹ����
        }
        if (i < glist.length() && glist.charAt(i) == ',') {// ����','��������һ���ֵ�����
            i++;// ����','
            p.sibling = create(glist);// ������һ���ֵ��������ݹ����
        }
        if (i < glist.length() && glist.charAt(i) == ')')// �����������������
            i++;// ����')'
        return p;// �մ�����nul
    }
}