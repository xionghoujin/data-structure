package phonebook;

import java.io.Serializable;
import java.util.Comparator;

/**
 * �绰������ʵ�ֿɱȽϣ��Ƚ��������л��ӿڣ�
 */
public class Friend implements Comparable<Friend>, Comparator<Friend>,	Serializable {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
    String name, phonecode;// �������绰����

	public Friend(String name, String phonecode) {
		this.name = name;
		this.phonecode = phonecode;
	}

    public Object[] toArray() {// ���ذ�����������г�Ա����������
        Object[] vars = new Object[2];
        vars[0] = this.name;
		vars[1] = this.phonecode;
		return vars;
	}

    public String index() {// ����������������Ϊ����
        return this.name.substring(0, 1);
    }

    public int compare(Friend f1, Friend f2) {// �Ƚ����������С
        return f1.name.compareTo(f2.name);
    }

    public int compareTo(Friend f) {// �Ƚ����������С��ʵ��comparable�ӿ�
        if (!this.name.equals(f.name))
            return f.name.compareTo(this.name);
        return f.phonecode.compareTo(this.phonecode);// ���Ƚ�����
    }
}

// �Ƚ������ַ���
class IndexComparator implements Comparator<Friend> {
	public int compare(Friend o1, Friend o2) {
		return o1.index().compareTo(o2.index());
	}
}

// �Ƚϵ绰�����ַ���
class CodeComparator implements Comparator<Friend> {
	public int compare(Friend o1, Friend o2) {
		return o1.phonecode.compareTo(o2.phonecode);
	}
}

//�Ƚ������ַ���
class NameComparator implements Comparator<Friend>{
	public int compare(Friend o1, Friend o2) {
		return o1.name.compareTo(o2.name);
	}
}