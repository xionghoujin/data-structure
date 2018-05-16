package string;

import java.io.Serializable;

/**
 * ģ������ַ���
 */
public final class MyStringBuffer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private char[] value;// �ַ����飬˽�г�Ա����
	private int len;// ������

	// ����ָ�������Ŀմ�
	public MyStringBuffer(int size) {
		this.value = new char[size < 16 ? 16 : size];
		this.len = 0;
	}

	// ��Ĭ�����������ַ�������
	public MyStringBuffer() {
		this(16);
	}

	// ���ַ��������������
	public MyStringBuffer(String str) {
		this(str.length() + 16);
		this.append(str);
	}

	// ���ر����ַ����ĳ���
	public int length() {
		return this.len;
	}

	// ����ָ��λ�õ��ַ�
	public synchronized char charAt(int i) {
		if (i < 0 || i > this.len)
			throw new StringIndexOutOfBoundsException(i);
		return this.value[i];
	}

	// ��ָ��λ������Ϊָ���ַ�
	public void setChar(int i, char ch) {
		if (i < 0 || i > this.len)
			throw new StringIndexOutOfBoundsException(i);
		this.value[i] = ch;
	}

	// ��дtoString����
	public synchronized String toString() {
		return new String(this.value, 0, this.len);
	}

	// ��ָ��λ�ò�������ַ���
	public synchronized MyStringBuffer insert(int i, MyStringBuffer str) {
		if (i < 0)
			i = 0;// ����ݴ�
		if (i > this.len)
			i = this.len;
		if (str == null)
			return this;
		char temp[] = this.value;
		if (this.value.length - this.len < str.len) {// ����ǰ���ռ䲻�㣬����������
			this.value = new char[this.value.length + str.len * 2];// ���������ַ�����ռ�
			// ��ֵ��ǰ��ǰi-1���ַ�
			for (int j = 0; j < i; j++)
				this.value[j] = temp[j];
		}
		// ��ֵ�ַ���str
		for (int j = 0; j < str.len; j++)
			this.value[i + j] = str.value[j];
		// ��ֵ�ַ���str
		for (int j = i; j < this.len; j++)
			this.value[str.len + j] = temp[j];
		this.len += str.len;
		return this;
	}

	// ��ָ��λ�ò����ַ���
	public synchronized MyStringBuffer insert(int i, String str) {
		if (i < 0)
			i = 0;
		if (i > this.len)
			i = this.len;
		if (str == null)
			return this;
		char temp[] = this.value;
		if (this.value.length - this.len < str.length()) {
			this.value = new char[this.value.length + str.length() * 2];
			for (int j = 0; j < i; j++)
				this.value[j] = temp[j];
		}
		for (int j = i; j < this.len; j++)
			this.value[str.length() + j] = temp[j];
		for (int j = 0; j < str.length(); j++)
			this.value[i + j] = str.charAt(j);
		this.len += str.length();
		return this;
	}

	// ��ָ��λ�ò��벼�����ͱ���
	public synchronized MyStringBuffer insert(int i, boolean b) {
		return this.insert(i, b ? "true" : "false");
	}

	// ��ָ���ַ�����ӵ������ַ�����β��
	public synchronized MyStringBuffer append(String str) {
		return this.insert(this.len, (str == null) ? "null" : str);
	}

	// ��begin��end-1�ĵ��Ӵ�ɾ��
	public synchronized MyStringBuffer delete(int begin, int end) {
		if (begin < 0)// ����ݴ�
			begin = 0;// �Ӵ��׿�ʼɾ��
		if (end > this.len)
			end = this.len;// ɾ������β���Ӵ�
		if (begin > end)
			throw new StringIndexOutOfBoundsException(begin - end);
		// ��end��ʼ����β���Ӵ���ǰ�ƶ�
		for (int i = 0; i < this.len - end; i++)
			this.value[begin + i] = this.value[end + i];
		this.len -= end - begin;
		return this;
	}
}