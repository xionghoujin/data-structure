package list.test;

import list.SeqList;

/**
 * ����˳�����Լɪ������
 */

public class Josephus {
    // ����Լɪ�򻷲���⣬����ָ�������ȣ���ʼλ�ã�����
    public Josephus(int number, int start, int distance) {
        // ����˳���洢Լɪ�򻷵�Ԫ�أ�Ԫ���������ַ��������췽������ָ��˳�������
        SeqList<String> list = new SeqList<String>(number);

		for (int i = 0; i < number; i++) {
            list.append((char) ('A' + i) + "");// ����ַ�������
        }

        System.out.println("Լɪ��(" + number + "," + start + "," + distance);
        System.out.println(list.toString());// ���˳���������ַ���
        int i = start;// ��¼��ʼλ��
        while (list.length() > 1) {// ����һ������ʱѭ��
            i = (i + distance - 1) % list.length();// ������ѭ�����ɱ仯��˳���ɿ������νṹ
            System.out.print("�h��" + list.remove(i).toString() + ",   ");// ɾ��ָ��λ�ö���
            System.out.println(list.toString());
		}

        System.out.println("���������" + list.get(0).toString());
    }

	public static void main(String[] args) {
		new Josephus(5, 0, 2);
	}
}
