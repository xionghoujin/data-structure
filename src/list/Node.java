package list;

/**
 * ������ڵ��࣬T��ʾָ���ڵ��Ԫ������
 */
public class Node<T> {

	public T data;// �����򣬱�������Ԫ��
	public Node<T> next;// ��ַ�����ú�̽ڵ�

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	public Node() {
		this(null, null);
	}
}
