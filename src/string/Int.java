package string;

/**
 * ģ������
 */
public class Int {

	public static void main(String[] args) {
		int n = 254;
		System.out.println(Int.toBinaryString(n));
		System.out.println(Int.toHexString(n));
		System.out.println(Int.toString(n, 8));
	}

	// ת��Ϊ������
	public static String toBinaryString(int n) {
		String str = "";
		// һ��intռ32λ������һλ����λ��0���
		for (int i = 0x80000000; i != 0; i >>>= 1)
			str += (n & i) == 0 ? '0' : '1';
		return str;// �����ַ���
	}

	// ת��Ϊʮ������
	public static String toHexString(int n) {
		String str = "";
		while (n > 0) {
			int k = n % 16;// ��16ȡ�෨����������str�ַ���
			str = (char) (k <= 9 ? k + '0' : k + 'A' - 10) + str;// ��0-9��10-15ת����'0'-'9','A'-'F'
			n /= 16;
		}
		return str;
	}

	// ת��Ϊ�������
	public static String toString(int n, int radix) {
		String str = "";
		while (n > 0) {
			int k = n % radix;
			str += (char) (k <= 9 ? k + '0' : k - 10 + 'A');
			n /= radix;
		}
		return str;
	}
}