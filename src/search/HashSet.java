package search;

import list.SinglyLinkedList;

/**
 * ��������ַ����ɢ�б���
 */
public class HashSet<T> {

	private SinglyLinkedList<T>[] table;// ɢ�б�ͬ��ʵ������������

	@SuppressWarnings("unchecked")
	// ����ָ��������ɢ�б�
	public HashSet(int size) {
		this.table = new SinglyLinkedList[Math.abs(size)];
		for (int i = 0; i < table.length; i++) {
			table[i] = new SinglyLinkedList<T>();
		}
	}

	// ����Ĭ��������ɢ�б�Ĭ��97��100���ڵ��������
	public HashSet() {
		this(97);
	}

	// ɢ�к�����ȷ���ؼ���Ϊkey��Ԫ�ص�ɢ�е�ַ
	private int hash(T x) {
		int key = Math.abs(x.hashCode());// ÿ�������hashcode��������int
		return key % table.length;// ������������������ɢ�б���
	}

	// ����xԪ�أ�������Ϊͬ��ʵ�����ĵ�һ���ڵ�
	public void insert(T x) {
		table[hash(x)].insert(0, x);
	}

	// ɾ��xԪ�أ�������Ϊͬ��ʵ�����ĵ�һ���ڵ�
	public void remove(T x) {
		table[hash(x)].remove(x);
	}

	// �����ҵ��Ĺؼ���ΪkeyԪ�أ������Ҳ��ɹ��򷵻�null
	public T search(T key) {
		return table[hash(key)].search(key);
	}

	// �ж�ɢ�б����Ƿ�����ؼ���ΪkeyԪ��
	public boolean contain(T key) {
		return this.search(key) == null;
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < table.length; i++) {// ����ɢ�б�����Ԫ�ص������ַ���
			if (!table[i].isEmpty())
				str += table[i].toString() + "\n";
		}
		return str;
	}
}