package stackqueue;

/**
 * ˳��洢��ջ,ʵ��ջ�ӿ�
 */
public class SeqStack<T> implements SStack<T> {

	private Object element[];// �洢ջ����Ԫ�ص�����
	private int top;// ջ��Ԫ���±�

	// ����Ĭ�������Ŀ�ջ
	public SeqStack(int size) {
		this.element = new Object[Math.abs(size)];
		this.top = -1;
	}

	// ����Ĭ�������Ŀ�ջ
	public SeqStack() {
		this(64);
	}

	@Override
	// �ж�ջ�Ƿ�Ϊ�գ������򷵻�true
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	// Ԫ��x��ջ����Ԫ�ز�����ջ
	public void push(T x) {
		if (x == null) {// �ն�������ջ
			return;
		}
		if (this.top == element.length - 1) {// ��ջ����������ջ����
			Object[] temp = this.element;
			this.element = new Object[temp.length * 2];// ��������һ���������������
			// ��������Ԫ��
			for (int i = 0; i < temp.length; i++)
				this.element[i] = temp[i];
		}
		this.top++;
		this.element[this.top] = x;
	}

	@SuppressWarnings("unchecked")
	@Override
	// ��ջ������ջ��Ԫ�أ���ջ���򷵻�null
	public T pop() {
		return this.top == -1 ? null : (T) element[top--];
	}

	@SuppressWarnings("unchecked")
	@Override
	// ȡջ��Ԫ�أ�δ��ջ����ջ���򷵻�null
	public T get() {
		return this.top == -1 ? null : (T) element[top];
	}
}