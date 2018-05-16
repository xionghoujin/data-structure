package array;

/**
 * �����˫����ʾ�Ľڵ���
 */
public class GenListNode<T> {

	public T data;// ������
	public GenList<T> child;// ��ַ��ָ���ӱ�
	public GenListNode<T> next;// ��ַ��ָ���̽��

	// �����㣬dataָ��Ԫ�أ�childָ���ӱ�nextָ���̽��
	public GenListNode(T data, GenList<T> child, GenListNode<T> next) {
		this.data = data;
		this.child = child;
		this.next = next;
	}

	public GenListNode(T data) {
		this(data, null, null);
	}

	public GenListNode() {
		this(null, null, null);
	}
}