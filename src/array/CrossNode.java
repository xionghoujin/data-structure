package array;

/**
 * ʮ����������
 */
public class CrossNode {

	Triple data;// �������ʾ��Ԫ�飬Ĭ�Ϸ���Ȩ��
	CrossNode right, down;// rightָ���е���һ����㣬downָ���е���һ�����

	// �����㣬dataָ��Ԫ�أ�rightָ���е���һ����㣬dowmָ���е���һ�����
	public CrossNode(Triple data, CrossNode right, CrossNode down) {
		this.data = data;
		this.right = right;
		this.down = down;
	}
}
