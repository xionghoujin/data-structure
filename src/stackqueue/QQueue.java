package stackqueue;

/**
 * ���нӿ�
 */
public interface QQueue<T> {

	boolean isEmpty();// �ж϶����Ƿ��

	void enquenu(T x);// Ԫ��x���

	T dequeue();// ����,���ض�ͷԪ��
}
