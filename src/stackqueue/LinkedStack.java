package stackqueue;

/**
 * ������洢��ջ
 */
public class LinkedStack<T> implements SStack<T> {

	private Node<T> top;// ջ����㣬ʵ��ջ�ӿ�

	// �����ջ
	public LinkedStack() {
		this.top = null;
	}

	@Override
	// �ж�ջ�Ƿ�Ϊ�գ������򷵻�true
	public boolean isEmpty() {
		return this.top == null;
	}

	@Override
	// Ԫ��x��ջ���ն�������ջ
	public void push(T x) {
		if (x != null) {
			this.top = new Node<T>(x, this.top);// ͷ���룬x�����Ϊ�µ�ջ�����
		}
	}

	@Override
	// ��ջ������ջ��Ԫ�أ���ջ�շ���null
	public T pop() {
		if (this.top == null)
			return null;
		T temp = this.top.data;// ȡջ�����
		this.top = this.top.next;// ɾ��ջ��Ԫ��
		return temp;
	}

	@Override
	// ȡջ��Ԫ�أ�δ��ջ����ջ�շ���null
	public T get() {
		return this.top == null ? null : this.top.data;
	}
}

class Node<T> {

	public T data;
	public Node<T> next;

	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}

	public Node() {
		this(null, null);
	}
}
