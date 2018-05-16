package stackqueue.test;

import stackqueue.PriorityQueue;

/**
 * ģ�����ϵͳ���̵���(���ö���)
 */

public class Process_ex {

	public static void main(String[] args) {
		Process process[] = { new Process("A", 4), new Process("B", 3),
				new Process("C", 5), new Process("D", 4), new Process("E", 10),
				new Process("F", 1) };
        PriorityQueue<Process> que = new PriorityQueue<Process>();// ����һ�����ȶ���
        System.out.println("��ӽ���");
        for (int i = 0; i < process.length; i++) {
            que.enquenu(process[i]);// �������
            System.out.print(process[i] + " ");
		}
        System.out.println("\n���Խ���");
        while (!que.isEmpty())
			System.out.print(que.dequeue().toString() + " ");
		System.out.println();
	}
}

/**
 * ����
 */
class Process implements Comparable<Process> {

    private String name;// ������
    private int priority;// ���ȼ�

	public Process(String name, int priority) {
		super();
		this.name = name;
		this.priority = priority;
	}

	public String toString() {
		return "(" + this.name + "," + this.priority + ")";
	}

    // �Ƚ��������̵Ĵ�С��Լ����������Ĺ���
    public int compareTo(Process p) {
		return this.priority - p.priority;
	}
}