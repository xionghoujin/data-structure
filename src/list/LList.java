package list;

/**
 * ���Ա�ӿ�
 */
public interface LList<T> {

	boolean isEmpty();// �ж����Ա��Ƿ�Ϊ��

	int length();// �������Ա���

	T get(int i);// ���ص�i��Ԫ��

	void set(int i, T x);// ���õ�i��Ԫ��ֵΪx

	void insert(int i, T x);// ����x��Ϊ��i��Ԫ��

	void append(T x);// �����Ա�������xԪ��

	T remove(int i);// ɾ����i��Ԫ�ز����ر�ɾ��Ԫ��

	void removeall();// ɾ�����Ա�����Ԫ��

	T search(T key);// �������Ա��״γ��ֵĹؼ���Ϊkey��Ԫ��
}