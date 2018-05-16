package search;

/**
 * 
 * ����Ĺؼ��ֱ���
 */
public class KeyWords {

	public static void main(String[] args) {
		String str = "final";
		System.out.println(str + (isKeywords(str) ? "" : "��") + "�ǹؼ���");
		str = "length";
		System.out.println(str + (isKeywords(str) ? "" : "��") + "�ǹؼ���");
	}

	// �ؼ��ֱ�
	private static String[] keywords = { "abstract", "assert", "boolean",
			"break", "byte", "case", "catch", "char", "class", "continue",
			"default", "do", "double", "else", "extends", "false", "final",
			"finally", "float", "for", "if", "implements", "import",
			"instanceof", "int", "interface", "long", "native", "new", "null",
			"package", "private", "protected", "public", "return", "short",
			"static", "super", "switch", "synchronized", "this", "throw",
			"throws", "transient", "true", "try", "void", "volatile", "while" };

	// ������˽�г�Ա��
	private static class IndexItem implements Comparable<IndexItem> {

		String first;// �ؼ��ֵ�����ĸ
		int start, end;// ����ĸ��ͬ�Ĺؼ��ֿ��������е�ʼĩ�±�

		public IndexItem(String first, int start, int end) {
			super();
			this.first = first;
			this.start = start;
			this.end = end;
		}

		public String toString() {
			return "(" + this.first + "," + this.start + "," + this.end + ")";
		}

		// Լ������������Ƚϴ�С�Ĺ��򣬰�����ĸ�Ƚϴ�С
		public int compareTo(IndexItem item) {
			return this.first.compareTo(item.first);
		}
	}

	private static IndexItem index[];// ������

	static {// ��̬��ʼ��
		index = new IndexItem[26];
		int i = 0, j = 0;
		for (i = 0; i < index.length && j < keywords.length; i++) {
			char ch = (char) ('a' + i);
			if (keywords[j].charAt(0) > ch)
				index[i] = new IndexItem(ch + "", -1, -1);
			else {
				int start = j++;
				while (j < keywords.length && keywords[j].charAt(0) == ch)
					j++;
				index[i] = new IndexItem(ch + "", start, j - 1);
			}
		}
	}

	// �ж�str�Ƿ�Ϊjava�ؼ���
	public static boolean isKeywords(String str) {
		int pos = str.charAt(0) - 'a';// ����ĸ��Ӧ�����������
		if (pos < 0 || pos > 26)
			return false;
		int begin = index[pos].start;// ���������ҷ�Χ���½�
		if (begin == -1)
			return false;
		int end = index[pos].end;// ���������ҷ�Χ���Ͻ�
		return BSArray.binarySearch(keywords, begin, end, str) >= 0;// �۰���������ָ����Χ
	}
}