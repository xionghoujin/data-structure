package stackqueue.test;

/**
 * ��n�׳�
 * 
 * @author Administrator
 *
 */
public class Factorial {

	public static void main(String[] args) {
		int n = 5;
		System.out.println(n + "!=" + factorial(n));
	}

    // ��׳�n�����ݹ鷽��
    public static int factorial(int n) {
		if (n < 0)
            throw new IllegalArgumentException("n=" + n);// �׳���Ч�����쳣
        if (n == 0 || n == 1)
			return 1;
		return n * factorial(n - 1);
	}
}
