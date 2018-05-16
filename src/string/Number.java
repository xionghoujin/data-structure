package string;

/**
 * ������
 */
public class Number {

	public static void main(String[] args) {
		String s = "-12345";
		System.out.println("parseInt(\"" + s + " \")=" + parseInt(s));
		s = "-12345.67E-2";
		System.out.println("parseInt(\"" + s + " \")=" + parseDouble(s));
	}

	// ���������ַ���s��ʾ������ֵ
	public static int parseInt(String s) {
		int x = 0, i = 0;
		int sign = s.charAt(0) == '-' ? -1 : 1;// ����λ����ס�������
		if (s.charAt(0) == '+' || s.charAt(0) == '-')// ��������λ
			i++;// ��ס��ǰ�ַ����
		while (i < s.length()) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
				x = x * 10 + s.charAt(i++) - '0';// ��ס��ǰ�������ֵ
			else
				throw new NumberFormatException(s);// �׳���ֵ��ʽ���쳣
		}
		return x * sign;// ��������ֵ�����������ַ�
	}

	// ����ʵ���ַ�����ʾ�ĸ�����ֵ
	public static double parseDouble(String s) {
		int n = s.length(), i = 0;
		int sign = s.charAt(0) == '-' ? -1 : 1;
		double x = 0, power = 10.0E0;// power��ʾ����Ϊ10����
		if (s.charAt(0) == '+' || s.charAt(0) == '-')// ��������λ
			i++;

		while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9')
			// �����������ֵ
			x = x * 10 + s.charAt(i++) - '0';
		if (i < n && s.charAt(i) == '.') {
			i++;
			while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {// ���С������ֵ
				x += (s.charAt(i) - '0') / power;
				i++;
				power *= 10;
			}
		}
		x *= sign;

		if (i < n && (s.charAt(i) == 'E' || s.charAt(i) == 'e')) {// �������
			i++;
			power = (s.charAt(i) == '-') ? 0.1 : 10;// ����ķ���λ����ָ����������������
			if (s.charAt(i) == '+' || s.charAt(i) == '-')
				i++;
			int exp = 0;
			while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9')
				exp = exp * 10 + s.charAt(i++) - '0';// ���ָ���ľ���ֵ

			for (int j = 0; j < exp; j++)
				x *= power;
		}
		return x;
	}
}