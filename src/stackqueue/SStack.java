package stackqueue;

/**
 * ջ�ӿ�
 */
public interface SStack<T> {

	boolean isEmpty();// �ж�Ԫ���Ƿ�Ϊ��

	void push(T x);// Ԫ��x��ջ

	T pop();// ��ջ�����ص�ǰԪ��

	T get();// ȡջ��Ԫ�أ�δ��ջ
}
