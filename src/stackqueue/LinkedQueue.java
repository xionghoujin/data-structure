package stackqueue;

/**
 * ��ʽ�����࣬ʵ�ֶ��нӿ�
 */
public class LinkedQueue<T> implements QQueue<T> {

	// front��rear�ֱ�ָ���ͷ�Ͷ�β
	private Node<T> front, rear;

	// ����ն���
	public LinkedQueue() {
		this.front = this.rear = null;
	}

	@Override
	// �ж϶����Ƿ�Ϊ�գ������򷵻�true
	public boolean isEmpty() {
		return this.front == null && this.rear == null;
	}

	@Override
	// Ԫ��x��ӣ��ն��������
	public void enquenu(T x) {
		if (x == null)
			return;// �ն��������
		Node<T> q = new Node<T>(x, null);
		if (this.front == null)
			this.front = q;// �նԲ���
		else
			this.rear.next = q;// �����ڶ���֮β
		this.rear = q;
	}

	@Override
	// ���ӣ����ض�ͷԪ�أ������п��򷵻�null
	public T dequeue() {
		if (isEmpty())
			return null;
		T temp = this.front.data;// ȡ�ö�ͷԪ��
		this.front = this.front.next;// ɾ����ͷԪ��
		if (this.front == null)
			this.rear = null;
		return temp;
	}
}
