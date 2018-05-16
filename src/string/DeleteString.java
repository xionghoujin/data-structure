package string;

/**
 * ����ɾ����ָ���ַ���ƥ����Ӵ�
 */
public class DeleteString {

	// ���ؽ�target�����׸���patternƥ����Ӵ�ɾ������ַ���
	public static String deleteFirst(String target, String pattern) {
		int i = target.indexOf(pattern);
		if (i == -1)
			return target;
		return target.substring(0, i) + target.substring(i + pattern.length());
	}

	// ���ؽ�target����������patternƥ����Ӵ�ɾ������ַ���
	public static String deleteAll(String target, String pattern) {
		int i = target.indexOf(pattern);
		while (i != -1) {
			target = target.substring(0, i)
					+ target.substring(i + pattern.length());
			i = target.indexOf(pattern, i);
		}
		return target;
	}

	public static void main(String[] args) {
		String target = "ababdabcdabcabc";
		String pattern = "abc";
		String replacement = "xy";
		System.out.println(target.indexOf(pattern));
		System.out.println(target.replaceFirst(pattern, replacement));
		System.out.println(target.replaceAll(pattern, replacement));
		System.out.println(deleteFirst(target, pattern));
		System.out.println(deleteAll(target, pattern));
	}
}