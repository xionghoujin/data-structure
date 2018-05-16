package array.test;

import array.DownTriangleMatrix;

/**
 * ��֤��������ࣨ�����Ǿ���
 */
public class DownTriangleMatrix_ex {

	public static void main(String[] args) {
		int m1[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
		DownTriangleMatrix mata = new DownTriangleMatrix(4, m1);
		System.out.println("A" + mata.toString());
		int m2[] = { 1, 0, 1, 0, 0, 1 };
		DownTriangleMatrix matb = new DownTriangleMatrix(4, m2);
		matb.set(3, 3, 1);
		System.out.println("B" + matb.toString());
		mata.add(matb);
		System.out.println("A+=B" + mata.toString());
	}
}