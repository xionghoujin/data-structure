package list;

/**
 * ˳���
 */
public class SeqList<T> implements LList<T> {

	private Object[] element;// ��������
	private int len;// ˳����ȣ�����ʵ��Ԫ�س���

	public SeqList(int size) {// ��������Ϊsize�Ŀձ�
		this.element = new Object[size];
		this.len = 0;
	}

	public SeqList() {// Ĭ�Ϲ��췽��.����Ĭ�������Ŀձ�
		this(64);
	}

	// �ж��Ƿ�Ϊ�գ����շ���true
	public boolean isEmpty() {
		return this.len == 0;
	}

	// ����˳���ĳ���
	public int length() {
		return this.len;
	}

	// ���ص�iԪ�أ���iָ�������Ч�򷵻�null
	@SuppressWarnings("unchecked")
	public T get(int i) {
		if (i >= 0 && i < this.len) {
			return (T) this.element[i];
		}
		return null;
	}

	@Override
	// ���õ�i��Ԫ��Ϊx����iָ�������Ч���׳����Խ���쳣
	public void set(int i, T x) {
		if (x == null) {
			return;
		}
		if (i >= 0 && i < this.len) {
			this.element[i] = x;
		} else
			throw new IndexOutOfBoundsException(i + "");// �׳����Խ���쳣
	}

	@Override
	// ����x��Ϊ��i��Ԫ�أ����ܲ���null
	public void insert(int i, T x) {
		if (x == null)
			return;
		if (this.len == element.length) {
			Object[] temp = this.element;
			this.element = new Object[temp.length * 2];
			for (int j = 0; j < temp.length; j++)
				this.element[j] = temp[j];
		}
		if (i < 0)
			i = 0;// �±��ݴ�
		if (i > this.len)
			i = this.len;
		for (int j = this.len - 1; j >= i; j--)
			// Ԫ�غ��ƣ�ƽ���ƶ�len/2
			this.element[j + 1] = this.element[j];
		this.element[i] = x;
		this.len++;
	}

	// ��˳���������x����
	public void append(T x) {
		this.insert(this.len, x);
	}

	@Override
	// ɾ����i��Ԫ�أ��������ɹ����ر�ɾ�����󣬷��򷵻�null
	public T remove(int i) {
		if (this.len == 0 || i >= this.len || i < 0)
			return null;
		@SuppressWarnings("unchecked")
		T old = (T) this.element[i];
		for (int j = i; j < this.len - 1; j++)
			// Ԫ��ǰ�ƣ�ƽ���ƶ�len/2
			this.element[j] = this.element[j + 1];
		this.element[this.len - 1] = null;
		this.len--;
		return old;
	}

	@Override
	// ɾ��˳�������Ԫ��
	public void removeall() {
		this.len = 0;
	}

	// ɾ���״γ��ֵĹؼ���ΪkeyԪ��
	public void remove(T key) {
		if (this.len != 0 && key != null)
			this.remove(this.indexOf(key));// ����remove��int������
	}

	// ˳�����ҹؼ�����keyԪ�أ������״γ��ֵ�Ԫ�أ������Ҳ��ɹ��򷵻�-1
	// key���԰����ؼ�����������T���equals�����ṩ�Ƚ϶�����ȵ�����
	public int indexOf(T key) {
		if (key != null)
			for (int i = 0; i < this.len; i++)
				if (this.element[i].equals(key))// �������equals�����Ƚ��Ƿ����
					return i;
		return -1;
	}

	// ���ң������״γ��ֵĹؼ���Ϊkey��Ԫ��
	@SuppressWarnings("unchecked")
	public T search(T key) {
		int find = this.indexOf(key);
		return find == -1 ? null : (T) this.element[find];
	}

	// �ж����Ա��Ƿ�����ؼ���Ϊkey��Ԫ��
	public boolean contain(T key) {
		return this.indexOf(key) >= 0;// �Բ��ҽ������жϽ��
	}

	// ����˳�������Ԫ�������ַ���
	public String toString() {
		String str = "(";
		if (this.len > 0)
			str += this.element[0].toString();
		for (int i = 1; i < this.len; i++) {
			str += "," + this.element[i].toString();
		}
		return str + ")";
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof SeqList) {
			@SuppressWarnings("unchecked")
			SeqList<T> list = (SeqList<T>) obj;
			if (this.length() == list.length()) {
				for (int i = 0; i < this.length(); i++)
					// �Ƚ�ʵ�ʳ���Ԫ�أ�������������
					if (!(this.get(i).equals(list.get(i))))
						return true;
			}
		}
		return false;
	}
}