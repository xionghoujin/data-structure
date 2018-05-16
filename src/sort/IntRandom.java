package sort;

/**
 * ��������н�������
 */
public class IntRandom {

	// ����n���������������
	public static int[] random(int n) {
		int[] table = new int[n];
		for (int i = 0; i < n; i++) {
			table[i] = (int) (Math.random() * 100);
		}
		return table;
	}

    public static void print(int[] table) {
        System.out.print(table[0]);
        for (int i = 1; i < table.length; i++) {
            System.out.print(" , " + table[i]);
        }
        System.out.println();
    }

	public static void main(String[] args) {
		int[] table = random(6);
		System.out.print("�ؼ������У�");
        print(table);
        ArraySort.insertSort(table);
    }
}