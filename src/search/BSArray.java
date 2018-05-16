package search;

/**
 * �۰���Ҷ�Ӧ����
 */
public class BSArray {

	// ���������е�value�����У��۰���ҹؼ���ΪkeyԪ�أ����ҵ������±꣬���򷵻�-1
	public static <T> int binarySearch(Comparable<T>[] value, T key) {
		return binarySearch(value, 0, value.length - 1, key);
	}

	// �ڴ�begin��end��Χ�ڣ����������е�value�����У��۰���ҹؼ���Ϊkey��Ԫ��
	public static <T> int binarySearch(Comparable<T>[] value, int begin,
			int end, T key) {
		if (key != null)
			while (begin <= end) {// �߽���Ч
				int mid = (begin + end) / 2;// �м�λ�ã���ǰ�Ƚ�Ԫ��λ��
				System.out.println(value[mid] + "? ");
				if (value[mid].compareTo(key) == 0)// ����Ƚϴ�С
					return mid;// ���ҳɹ�
				if (value[mid].compareTo(key) > 0)// ��������С
					end = mid - 1;// ���ҷ�Χ��С��ǰ���
				else
					begin = mid + 1;// ���ҷ�Χ��С������
			}
		return -1;// ���Ҳ��ɹ�
	}
}
