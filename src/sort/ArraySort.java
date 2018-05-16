package sort;

/**
 * �ṩ���������㷨
 */
public class ArraySort {

    // ��������------------ֱ�Ӳ�������(�ȶ�)
    public static void insertSort(int[] table) {
        for (int i = 1; i < table.length; i++) {// n-1��ɨ��
            int temp = table[i], j;// ÿ�˽�table[i]���뵽ǰ������������
            // ��ǰ��ϴ�Ԫ������ƶ�
            for (j = i - 1; j >= 0 && temp < table[j]; j--)
                table[j + 1] = table[j];// tempֵ�������λ��
            table[j + 1] = temp;
		}
	}


    // ��������---------------ϣ������(���ȶ�)
    public static void shellSort(int[] table) {
        for (int delta = table.length / 2; delta > 0; delta /= 2) {// ������ɨ�������������������
            for (int i = delta; i < table.length; i++) {// һ�˷������飬ÿ�����ֱ�Ӳ�������
                int temp = table[i], j;
                // ÿ��Ԫ�����deltaԶ��Ѱ�Ҳ���λ��
                for (j = i - delta; j >= 0 && temp < table[j]; j -= delta)
					table[j + delta] = table[j];
                table[j + delta] = temp;// ����Ԫ��
            }
		}
	}

    // ��������-------ð������(�ȶ�)
    public static void bubbleSort(int[] table) {
        boolean exchange = true;// �Ƿ񽻻��ı��
        for (int i = 1; i < table.length && exchange; i++) {// �н���ʱ�ٽ�����һ�ˣ����n-1��
            exchange = false;// �ٶ�Ԫ��δ����
            for (int j = 0; j < table.length - 1; j++) {// һ�˱Ƚϣ�����
                if (table[j] > table[j + 1]) {
					int temp = table[j];
					table[j] = table[j + 1];
					table[j + 1] = temp;
                    exchange = true;// �н���
                }
			}
		}
	}

    // ��������------------��������(���ȶ�)
    public static void quickSort(int[] table) {
		quickSort(table, 0, table.length - 1);
	}

    // һ�˿�������begin,endָ�����е��½���Ͻ磬�ݹ��㷨
    private static void quickSort(int[] table, int begin, int end) {
        if (begin < end) {// ������Ч
            int i = begin, j = end;
            int vot = table[i];// ��һ��ֵ��Ϊ��׼ֵ
            while (i != j) {// һ������
                while (i < j && vot <= table[j])
                    // �Ӻ���ǰѰ����Сֵ
                    j--;
				if (i < j)
                    table[i++] = table[j];// ��Сֵ��ǰ�˶�

				while (i < j && table[i] <= vot)
                    // ��ǰ���Ѱ�ҽϴ�ֵ
                    i++;
				if (i < j)
                    table[j--] = table[i];// �ϴ�Ԫ������ƶ�
            }
            table[i] = vot;// ��׼ֵ��������λ��
            quickSort(table, begin, j - 1);// ǰ�������������򣬵ݹ����
            quickSort(table, i + 1, end);// ��������������򣬵ݹ����
        }
	}

    // ѡ������-------------ֱ��ѡ������(���ȶ�)
    public static void selectSort(int[] table) {
        for (int i = 0; i < table.length - 1; i++) {// n-1������
            int min = i;// ���i��Ԫ����С
            for (int j = i + 1; j < table.length; j++) {// ���������в�����Сֵ
                if (table[j] < table[min])
					min = j;
			}
            if (min != i) {// ��������СԪ�ؽ�����ǰ��
                int temp = table[i];
				table[i] = table[min];
				table[min] = temp;
			}
		}
	}

    // ѡ������-------------------������(���ȶ�)
    public static void heapSort(int[] table) {
		int n = table.length;
        // ������С��
        for (int j = n / 2 - 1; j >= 0; j--)
			sift(table, j, n - 1);
        for (int j = n - 1; j > 0; j--) {// ÿ�˽���С�ѽ��������棬�ٵ����ɶ�
            int temp = table[0];
			table[0] = table[j];
			table[j] = temp;
			sift(table, 0, j - 1);
		}
	}

    // ����beginΪ����������������С�ѣ�begin��end�������½���Ͻ�
    private static void sift(int[] table, int begin, int end) {
        int i = begin, j = 2 * i + 1;// iΪ�����ĸ���jΪi���ĸ�
        int temp = table[i];// ��õ�i��Ԫ�ص�ֵ

        while (j <= end) {// �ؽ�Сֵ���ӽ������ɸѡ
            if (j < end && table[j] < table[j + 1])// ����Ԫ�رȽ�
                j++;
            if (temp > table[j]) {// ����ĸ���ϴ�
                table[i] = table[j];// ���ӽ���еĽ�Сֵ����
                i = j;// i��j����һ��
                j = 2 * i + 1;
			} else
				break;
		}
        table[i] = temp;// ��ǰ������ԭ��ֵ�������λ��
    }

    // һ�ι鲢
    private static void merge(int[] X, int[] Y, int m, int r, int n) { // һ�ι鲢
        int i = m, j = r, k = m;

		while (i < r && j < r + n && j < X.length)
            // ��X���������������й鲢��Y��
            if (X[i] < X[j])// ��Сֵ���Ƶ�Y��
                Y[k++] = X[i++];
			else
				Y[k++] = X[j++];
        // ��ǰһ��������ʣ��Ԫ�ظ��Ƶ�Y��
        while (i < r)
			Y[k++] = X[i++];
		while (j < r + n && j < X.length)
            // ����һ��������ʣ��Ԫ�ظ��Ƶ�Y��
            Y[k++] = X[j++];
	}

    // һ�˹鲢
    private static void mergepass(int[] X, int[] Y, int n) {
		int i = 0;
		while (i < X.length - 2 * n + 1) {
			merge(X, Y, i, i + n, n);
			i += 2 * n;
		}
		if (i + n < X.length)
            merge(X, Y, i, i + n, n);// ��һ�ι鲢
        else
			for (int j = i; j < X.length; j++)
                // ��Xʣ��Ԫ�ظ��Ƶ�Y��
                Y[j] = X[j];
	}

    // �鲢����---------------------------------(�ȶ�)
    public static void mergeSort(int[] X) {
        int[] Y = new int[X.length];// Y���鳤��ͬX����
        int n = 1;// ������������г��ȣ���ֵΪ1
        while (n < X.length) {
            mergepass(X, Y, n);// һ�����򣬽�X�����и������й鲢��Y��
            n *= 2;// �����г��ȼӱ�
            if (n < X.length) {
                mergepass(Y, X, n);// ��Y������������ٹ鲢��X��
                n *= 2;
			}
		}
	}
}