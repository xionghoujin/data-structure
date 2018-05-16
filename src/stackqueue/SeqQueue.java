package stackqueue;

/**
 * ˳��ѭ�������࣬ʵ�ֶ��нӿ�
 */
public class SeqQueue<T> implements QQueue<T> {

	private Object element[];// �洢��������Ԫ�ص�����
	private int front, rear;// front��rear�ֱ�Ϊ����ͷβ�±�

	// ��������Ϊlength�Ŀն���
	public SeqQueue(int length) {
		if (length < 64)
			length = 64;// ���ö�������������Сֵ
		this.element = new Object[Math.abs(length)];
		this.front = this.rear = 0;// ���ÿն���
	}

	// ����Ĭ�������Ŀն���
	public SeqQueue() {
		this(64);
	}

	// �ж϶����Ƿ�Ϊ�գ������򷵻�true
	public boolean isEmpty() {
		return this.front == this.rear;
	}

	@Override
	// Ԫ��x��ӣ��ն��������
	public void enquenu(T x) {
		if (x == null)
			return;// �ն��������
		if (this.front == (this.rear + 1) % this.element.length) {
			Object[] temp = this.element;
			this.element = new Object[temp.length * 2];// ��������һ���������������
			int i = this.front, j = 0;
			while (i != this.rear) {// ���ն���Ԫ�ش���������Ԫ��
				this.element[j] = temp[i];
				i = (i + 1) % temp.length;
				j++;
			}
			this.front = 0;
			this.rear = j;
		}
		this.element[this.rear] = x;
		this.rear = (this.rear + 1) % this.element.length;

	}

	@Override
	// ���ӣ����ض�ͷԪ�أ������򷵻�null
	public T dequeue() {
		if (isEmpty())
			return null;// �����򷵻�null
		@SuppressWarnings("unchecked")
		T temp = (T) this.element[front];// ȡ�ö�ͷԪ��
		this.front = (this.front + 1) % this.element.length;
		return temp;
	}

	// ���ض��� ����Ԫ�ص������ַ��������ն���Ԫ�ش���
	public String toString() {
		String str = "(";
		if (!isEmpty()) {
			str = this.element[this.front].toString();
			int i = (this.front + 1) % this.element.length;
			while (i != this.rear) {
				str += "," + this.element[i].toString();
				i = (i + 1) % this.element.length;
			}
		}
		return str += ")";
	}
}