package array.test;

import array.GenList;
import array.GenListNode;

/**
 *
 * ���ַ������������
 */
public class GenLiat_String {

	private static int i = 0;

	public static void main(String[] args) {
		GenList<String> glist_S = create("(and,(begin,end),(my,your,(his,her)))");
		System.out
				.print("glist_S" + glist_S + ",  length =" + glist_S.length());
		System.out.println(",   depth = " + glist_S.depth());
	}

    // ��gliststr�ַ������������ع����
    public static GenList<String> createGenList(String gliststr) {
		i = 0;
		return create(gliststr);
	}

    // ���ش�gliststr[i]��ʼ���Ӵ��������ӹ�����ݹ��㷨
    private static GenList<String> create(String gliststr) {
        i++;// ����''(
        GenList<String> glist = new GenList<>();// ����չ����ֻ��ͷ���
        GenListNode<String> p = glist.head;// ָ��ͷ���
        while (i < gliststr.length()) {
			char ch = gliststr.charAt(i);
			switch (ch) {
			case ',':
				i++;
				break;
			case '(': {
				p.next = new GenListNode<String>();
				p = p.next;
                p.child = create(gliststr);// �����ӱ��ݹ����
                break;
			}
			case ')':
				i++;
				return glist;
                default: {// �ַ�����ʾԭ��
                    int j = i + 1;
				ch = gliststr.charAt(j);
				while (ch != '(' && ch != ',' && ch != ')') {
					j++;
					ch = gliststr.charAt(j);
				}
                    p.next = new GenListNode<String>(gliststr.substring(i, j));// �������
                    p = p.next;
				i = j;
			}
			}
		}
		return null;
	}
}