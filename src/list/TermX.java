package list;

/**
 * һԪ����ʽ��һ�ʵ�ֿɱȽϽӿںͿ���ӽӿ�
 */
public class TermX implements Comparable<TermX>, Addible<TermX> {

	protected int ceof, exp;// ϵ����ָ��

	public TermX(int ceof, int exp) {
		this.ceof = ceof;
		this.exp = exp;
	}

	public TermX(TermX term) {
		this(term.ceof, term.exp);
	}

	// ��ϵ��x^ָ���ļ�����ʽ����һԪ����ʽ��һ��
	public TermX(String termStr) {
		if (termStr.charAt(0) == '+') {// ȥ��+
			termStr = termStr.substring(1);
		}
		int i = termStr.indexOf('x');// û��x����ָ��Ϊ0
		if (i == -1) {
			this.ceof = Integer.parseInt(termStr);
			this.exp = 0;
		} else {
			if (i == 0)// ��x��ͷ����ϵ��Ϊ1
				this.ceof = 1;
			else {
				String sub = termStr.substring(0, i);
				if (sub.equals("-"))// ϵ��ֻ��-�ţ���ϵ��Ϊ1
					this.ceof = -1;
				else
					this.ceof = Integer.parseInt(sub);// ���ϵ��
			}
			i = termStr.indexOf('^');
			if (i == -1)
				this.exp = 1;// û��^��ָ��Ϊ1
			else
				this.exp = Integer.parseInt(termStr.substring(i + 1));
		}
	}

	@Override
	// �ӷ���+=���������
	public void add(TermX term) {
		if (this.compareTo(term) == 0)
			this.ceof += term.ceof;
		else
			throw new IllegalArgumentException("����ָ����ͬ���������");
	}

	@Override
	public boolean removeable() {
		return this.ceof == 0;
	}

	@Override
	// Լ����ָ���Ƚ������С
	public int compareTo(TermX o) {
		if (this.exp == o.exp)// �Ƚ���ȣ���ָ�����
			return 0;// �ȽϹ�����equals��ͬ
		return this.exp < o.exp ? -1 : 1;// �Ƚϴ�С���Ƚ�ָ��
	}

	@Override
	// ����һԪ����ʽ���ַ�����ʾ��ʽ
	public String toString() {
		String str = this.ceof > 0 ? "+" : "-";// ϵ���ķ���λ
		if (this.exp == 0 || this.exp > 0 && this.ceof != 1 && this.ceof != -1)
			str += Math.abs(this.ceof);// ϵ������ֵʡ��ϵ��1
		if (this.exp > 0)
			str += "x";// ָ��Ϊ0ʱ��ʡ��x^0,ֻдϵ��
		if (this.exp > 1)
			str += "^" + this.exp;// ָ��Ϊ1ʱ��ʡ��^1��ֻдx
		return str;
	}

	@Override
	// �Ƚ������Ƿ����
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof TermX))
			return false;
		TermX term = (TermX) obj;
		return this.ceof == term.ceof && this.exp == term.exp;// �ȽϹ�����compare��ͬ
	}
}
