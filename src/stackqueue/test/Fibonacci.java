package stackqueue.test;

public class Fibonacci {

	public static void main(String[] args) {
		for (int i = 0; i <= 24; i++)
			System.out.println(fib(i) + " ");
		System.out.println();
	}

    // ����Fibonacci���еĵ�n��ݹ��㷨
    public static int fib(int n) {
		if (n < 0)
            throw new IllegalArgumentException("n=" + n);// �׳���Ч�����쳣
        if (n == 0 || n == 1)
			return n;
		return fib(n - 2) + fib(n - 1);
	}
}
