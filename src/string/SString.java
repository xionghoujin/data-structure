package string;

/**
 * ���ӿ�
 */
public interface SString {

	int length();// ���ش��ĳ���

	char charAt(int i);// ���ص�i���ַ�

	SString concat(SString str);// ���ص�ǰstr���������ɵ��´�

	SString substring(int begin, int end);// ���ش���begin ��end - 1���Ӵ�

	void setCharAt(int i, char ch);// ���õ�i���ַ���Ϊch

	SString insert(int i, SString str);// �ڵ�i���ַ�������str��

	SString delete(int begin, int end);// ɾ��begin��end-1���Ӵ�

	int indexOf(SString pattern);// ����ģʽ�ַ����ڴ��е��״�ƥ��λ��
}
