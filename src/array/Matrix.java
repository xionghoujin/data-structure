package array;

/**
 * ������
 */
public class Matrix {

	private int element[][];// �洢����Ķ�ά����

	public Matrix(int m, int n) {
		// ��m��nΪ������java���׳��������쳣NegativeArraySizeException
		this.element = new int[m][n];
	}

	// ����һ��n��n���㷽��
	public Matrix(int n) {
		this(n, n);
	}

	// ����m��n������mat�ṩԪ��
	public Matrix(int m, int n, int mat[][]) {
		this(m, n);
		// matԪ�ز���ʱ���㣬���Զ���Ԫ��
		for (int i = 0; i < mat.length && i < m; i++)
			for (int j = 0; j < mat[i].length && j < n; j++)
				this.element[i][j] = mat[i][j];
	}

	// ���ؾ����i�е�j�е�Ԫ��ֵ
	public int get(int i, int j) {
		return this.element[i][j];
	}

	// ���þ����i�е�j�е�Ԫ��ֵΪvalue
	public void set(int i, int j, int value) {
		this.element[i][j] = value;
	}

	// ���ؾ�������Ԫ�ص������ַ���
	public String toString() {
		String str = "����Matrix(" + this.element.length + ","
				+ this.element[0].length + ") : \n";
		for (int i = 0; i < this.element.length; i++) {
			for (int j = 0; j < this.element[i].length; j++)
				str += String.format("%4d", this.element[i][j]);
			str += "\n";
		}
		return str;
	}

	// ��ǰ������mat��ӣ�this+=mat,����ӦԪ����ӣ��ı䵱ǰ����
	public void add(Matrix mat) {
		if (this.element.length != mat.element.length
				&& this.element[0].length != mat.element[0].length)
			throw new IllegalArgumentException("��������ͬ���������");// ��Ч�����쳣
		for (int i = 0; i < this.element.length; i++) {
			for (int j = 0; j < this.element[i].length; j++) {
				this.element[i][j] += mat.element[i][j];
			}
		}
	}
}