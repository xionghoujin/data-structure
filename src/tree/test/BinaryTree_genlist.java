package tree.test;

import tree.BinaryNode;
import tree.BinaryTree;

/**
 * �����Թ����������һ�Ŷ������洢�Ķ�����
 */
public class BinaryTree_genlist {
	private static int i = 0;

	public static void main(String[] args) {
		String glist = "A(B(D(^,G),^),C(E,F(H,^)))";
		BinaryTree<String> bitree = createbyGenList(glist);
		System.out.println(bitree.toGenListString());
	}

    // �Թ�����ʾ���������
    public static BinaryTree<String> createbyGenList(String glist) {
		BinaryTree<String> bitree = new BinaryTree<String>();
		i = 0;
		if (glist.length() > 0)
            bitree.root = create(glist);// ����������������ֵ��glist[0]
        return bitree;
	}

    // �Թ�����ʾ��������һ��������������ֵ��glist[i]�����ظ���㣬�ݹ���
    private static BinaryNode<String> create(String glist) {
		BinaryNode<String> p = null;
		char ch = glist.charAt(i);
        if (ch >= 'A' && ch <= 'Z') {// ��д��ĸ
            p = new BinaryNode<String>(ch + "");// ����Ҷ�ӽ��
            i++;
			if (glist.charAt(i) == '(') {
                i++;// //����������
                p.left = create(glist);// �������������ݹ����
                i++;// ����','
                p.right = create(glist);// ����������
                i++;// ����')'
            }
		}
		if (ch == '^')
            i++;// ������^��
        return p;// �մ�����null
    }
}