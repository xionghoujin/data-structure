package stackqueue.test;

import list.SeqList;
import stackqueue.SeqQueue;

public class PrimeRing {

	public static void main(String[] args) {
		new PrimeRing(10);
	}

	/**
     * ��1-n������
     */
	public PrimeRing(int n) {
        SeqList<Integer> ring = new SeqList<Integer>(n);// ����һ��˳���洢������
        ring.append(new Integer(1));// 1��ӵ���������
		SeqQueue<Integer> que = new SeqQueue<>(n);// ����һ������
		for (int i = 2; i <= n; i++)
            // 2-nȫ�����
            que.enquenu(new Integer(i));
		int i = 0;
		while (!que.isEmpty()) {
            int k = que.dequeue();// ȫ������
            if (isPrime(ring.get(i) + k)) {// �ж��Ƿ�Ϊ����
                i++;
                ring.append(k);// k��ӵ���������
            } else
                que.enquenu(k);// k�ٴ����
        }
        System.out.println("������:" + ring.toString());

	}

	/**
     * k�ٴ����
     */
	public boolean isPrime(int k) {
		if (k == 2)
			return true;
		if (k < 2 || k > 2 && k % 2 == 0)
			return false;
		int j = (int) Math.sqrt(k);
		if (j % 2 == 0)
			j--;
		while (j > 2 && k % j != 0)
			j -= 2;
		return j < 2;
	}
}
