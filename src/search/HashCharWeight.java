package search;

/**
 * ����ɢ�б�洢ͳ�ƽ��
 */
public class HashCharWeight {

	public static void main(String[] args) {
		new HashCharWeight("public class");
	}

	private HashSet<CharCount> hashset;

	public HashCharWeight(String text) {
        hashset = new HashSet<>(text.length());// ������ɢ�б�
        for (int i = 0; i < text.length(); i++) {// �ַ����Ҽ���
			CharCount key = new CharCount(text.charAt(i), 1);
			CharCount find = hashset.search(key);// ���ң�find���ò��ҵ���Ԫ��
			if (find == null)
				hashset.insert(key);// ɢ�б����
			else
				find.add(1);// ��Ӧ�ַ�������1
		}
		System.out.println("�ַ����ִ��� �� " + hashset.toString());
	}

    /**
     * �ַ�������ִ���
     */
    private final class CharCount {
        char character;// �ַ�
        int count;// ���ִ���

        public CharCount(char character, int count) {
            super();
            this.character = character;
            this.count = count;
        }

        // �����ַ�������ִ��������ַ�������ʽΪ�����ַ������ִ�������
        public String toString() {
            return "(" + this.character + "," + this.count + ")";
        }

        public void add(int n) {
            this.count += n;
        }

        // ����ɢ���룬����Object���hashcode�����������ַ�����������ɢ�б��е�λ��
        public int hashCode() {
            return (int) this.character;
        }

        // �Ƚ����������Ƿ���ȣ�����Object���equals����
        public boolean equals(Object obj) {
            return obj == this || obj instanceof CharCount
                    && this.character == ((CharCount) obj).character;// ���Ƚ��ַ��Ƿ����
        }
    }
}

