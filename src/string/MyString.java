package string;

import java.io.Serializable;

/**
 * ģ���ַ���
 */
public final class MyString implements Comparable<MyString>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final char[] value;// �ַ����飬˽�����ձ�����ֻ�ܸ�ֵһ��

	// ����մ�
	public MyString() {
		this.value = new char[0];
	}

	// ���ַ����������촮���������еĺ�������ַ����е��ַ�����
	public MyString(String original) {
		this.value = original.toCharArray();
	}

	// ��value�����start��end���������ַ�����
	public MyString(char[] value, int begin, int count) {
		this.value = new char[count];
		for (int i = begin; i < begin + count; i++) {
			this.value[i] = value[i];
		}
	}

	// ��value���鹹���ַ�������
	public MyString(char[] value) {
		this(value, 0, value.length);
	}

	// �������췽������ֵ����
	public MyString(MyString str) {
		this(str.value);
	}

	// ���ش��ĳ���
	public int length() {
		return this.value.length;
	}

	// ���ص�i���ַ�
	public char charAt(int i) {
		if (i <= 0 || i >= this.value.length)
			throw new StringIndexOutOfBoundsException(i);// �׳��ַ�������Խ���쳣
		return this.value[i];
	}

	// ���ıȽ�
	public int compareTo(MyString str) {
		for (int i = 0; i < this.value.length && i < str.value.length; i++) {
			if (this.value[i] != str.value[i])
				return this.value[i] - str.value[i];// ����������һ����ͬ�ַ��Ĳ�ֵ
		}
		return this.value.length - str.value.length;// ǰ׺�Ӵ��������������ȵĲ�ֵ
	}

	// ��дequals����xo
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof MyString) {
			MyString str = (MyString) obj;
			if (this.value.length == str.length()) {
				for (int i = 0; i < this.value.length; i++) {
					if (this.value[i] != str.value[i])
						return false;
				}
				return true;
			}
		}
		return false;
	}

	// ��дtoStirng����
	public String toString() {
		return new String(this.value);
	}

	// ��������
	public MyString concat(MyString str) {
		if (str == null || str.length() == 0)
			return this;
		char[] buffer = new char[this.value.length + str.length()];
		int i;
		for (i = 0; i < this.value.length; i++) {// ��ֵ��ǰ��
			buffer[i] = this.value[i];
		}

		for (int j = 0; j < str.length(); j++) {// ����str
			buffer[j] = str.value[j];
		}

		return new MyString(buffer);
	}

	// ���Ӵ�
	public MyString substring(int begin, int end) {
		if (begin < 0)
			begin = 0;// ����ݴ�
		if (end >= this.value.length)
			end = this.value.length;
		if (begin > end)
			throw new StringIndexOutOfBoundsException(end - begin);
		if (begin == 0 && end == this.value.length)
			return this;
		char[] buffer = new char[begin - end];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = this.value[i + begin];
		}
		return new MyString(buffer);// ���ַ����鹹�����
	}

	// ���ش�����Ŵ�begin����β���Ӵ�
	public MyString substring(int begin) {
		return this.substring(begin, this.length());
	}

	// ��ָ��λ�ÿ�ʼѰ�Ҳ����ص�һ����ָ���ַ���ģʽƥ���λ��
	public int indexOf(MyString pattern, int begin) {
		if (pattern != null && pattern.length() > 0
				&& this.length() >= pattern.length()) {
			int i = begin, j = 0;// i,j�ֱ�ΪĿ�괮��ģʽ����ǰ�ַ����±�
			while (i < this.length()) {
				if (this.charAt(i) == pattern.charAt(i)) {// ����ǰ���ַ���ȣ�������ȽϺ����ַ�
					i++;
					j++;
				} else {// ����i��j���ݣ�������һ��ƥ��
					i = i - j + 1;// Ŀ�괮�±��˻ص���һ����ƥ���Ӵ����ַ�
					j = 0;// ģʽ���±��˻ص�0
				}
				if (j == pattern.length())// һ��ƥ�������ƥ��ɹ�
					return i - j;// ����ƥ����Ӵ����
			}
		}
		return -1;
	}

	// �ӿ�ͷ���ص�һ����ָ���ַ���ģʽƥ���λ��
	public int indexOf(MyString pattern) {
		return this.indexOf(pattern, 0);
	}

	/**
	 * ����ʹ��KMP�㷨��ʵ��ģʽƥ���ַ���
	 */
	// ȷ��KMP�㷨��next����
	public static int[] getNext(String pattern) {
		int j = 0, k = -1;
		int[] next = new int[pattern.length()];
		next[0] = -1;
		while (j < pattern.length() - 1) {
			if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
				k++;
				j++;
				if (pattern.charAt(j) != pattern.charAt(k)) {
					next[j] = k;
				} else
					next[j] = next[k];
			}
		}
		return next;
	}

	// KMPģʽƥ���㷨
	public static int indexOf(String target, String pattern, int begin) {
		if (target != null && pattern != null && pattern.length() > 0
				&& target.length() >= pattern.length()) {
			int i = begin, j = 0;
			int next[] = getNext(pattern);// ����ģʽ��pattern��next����
			while (i < target.length()) {
				// ������������������ȽϺ���ַ�
				if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
					i++;
					j++;
				} else
					// ����Ŀ���ַ����±�i�����ݣ������´�ƥ��
					j = next[j];// ģʽ���±�j�˻ص�next[j]
				if (j == pattern.length())// һ��ƥ�������ƥ��ɹ�
					return i - j;// ����ƥ����Ӵ����
			}
		}
		return -1;// ƥ��ʧ��
	}

	// ���ؽ���ǰ�����׸���patternƥ����Ӵ��滻��replacement���ַ���
	public MyString replaceFirst(MyString pattern, MyString replacement) {
		int i = this.indexOf(pattern, 0);
		if (i == -1)
			return this;
		return this.substring(0, i).concat(replacement)
				.concat(this.substring(i + pattern.length()));
	}

	// ���ؽ���ǰ����������patternƥ����Ӵ��滻��replacement���ַ���
	public MyString replacAll(MyString pattern, MyString replacement) {
		MyString temp = new MyString(this);
		int i = this.indexOf(pattern, 0);
		while (i != -1) {
			temp = temp.substring(0, i).concat(replacement)
					.concat(temp.substring(i + pattern.length()));
			i = temp.indexOf(pattern, i + replacement.length());
		}
		return temp;
	}
}