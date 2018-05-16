package string;

/**
 * �滻�ɱ��ַ����Ĳ���
 */
public class ReplaceStringBuffer {

	public static void main(String[] args) {
		StringBuffer target = new StringBuffer("aaaa");
		String pattern = "a", replacement = "ab";
		System.out.println(replaceFirst(target, pattern, replacement));
		System.out.println(replaceAll(target, pattern, replacement));
		pattern = replacement;
		System.out.println(deleteFirst(target, pattern));
	}

	// �滻��һ����ָ���ַ���ƥ����Ӵ�
	public static StringBuffer replaceFirst(StringBuffer target,
			String pattern, String replacement) {
		int i = target.indexOf(pattern);
		if (i != -1) {
			target.delete(i, i + pattern.length());
			target.insert(i, replacement);
		}
		return target;
	}

	// �滻������ָ���ַ���ƥ����Ӵ�
	public static StringBuffer replaceAll(StringBuffer target, String pattern,
			String replacement) {
		int i = target.indexOf(pattern);
		while (i != -1) {
			target.delete(i, i + pattern.length());
			target.insert(i, replacement);
			i = target.indexOf(pattern, i + replacement.length());
		}
		return target;
	}

	// ɾ����ָ���ַ���ƥ����Ӵ�
	public static StringBuffer deleteFirst(StringBuffer target, String pattern) {
		int i = target.indexOf(pattern);
		if (i != -1) {
			target.delete(i, i + pattern.length());
		}
		return target;
	}

	// ɾ��������ָ���ַ���ƥ����Ӵ�
	public static StringBuffer deleteAll(StringBuffer target, String pattern) {
		int i = target.indexOf(pattern);
		while (i != -1) {
			target.delete(i, i + pattern.length());
			i = target.indexOf(pattern, i);
		}
		return target;
	}
}