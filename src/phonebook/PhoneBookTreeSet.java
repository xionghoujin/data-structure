package phonebook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * �绰���洢�������
 */
public class PhoneBookTreeSet extends TreeSet<Friend> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String filename;// �ļ���

	public PhoneBookTreeSet(String filename) {
        super();// ����ռ�����comparable�ӿ��ṩ�������
        this.filename = filename;
        this.readFromFile();// ��ָ���ļ��ж�ȡ������ӵ�������
    }

    // ��ָ���ļ��ж�ȡ������ӵ�������
    private void readFromFile() {
        try {
            FileInputStream fin = new FileInputStream(this.filename);// �ļ��ֽ�������
            ObjectInputStream objin = new ObjectInputStream(fin);// �����ֽ�������
            while (true)
                // ��ȡ��δ����ʱ
                try {
                    this.add((Friend) objin.readObject());// ��ȡһ��������ӵ�����
                } catch (Exception e) {
                    break;
                }// ����һ��ClassCastFoundException��EOFEException�쳣
            objin.close();
            fin.close();
		} catch (IOException e) {
            e.printStackTrace();// ָ���ļ�������ʱ������Ϊ��
        }
    }

    // �����������ж���д��ָ���ļ������ļ������ڣ������ļ�
    public void writeToFile() {
        if (!this.isEmpty())
			try {
                FileOutputStream fout = new FileOutputStream(this.filename);// �ļ��ֽ������
                ObjectOutputStream objout = new ObjectOutputStream(fout);// �����ֽ������
                Iterator<Friend> it = this.iterator();
                while (it.hasNext())
                    // ��δ�ҵ����к��Ԫ��ʦ����
                    objout.writeObject(it.next());// д��һ������
                objout.close();
                fout.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
	}

    // ����x���󣬷����״γ��ֵĶ��󣬱Ƚ�����Comparaotr�Ƚ�������
    public Friend search(Friend x, Comparator<Friend> c) {
        Iterator<Friend> it = this.iterator();
        while (it.hasNext()) {// δ�ҵ����к��Ԫ��ʱ����
            Friend f = it.next();
            if (c.compare(f, x) == 0)// �ɱȽ���cָ���ȽϹ���
                return f;
        }
        return null;// δ�ҵ�ʱ����null
    }
}