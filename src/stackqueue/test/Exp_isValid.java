package stackqueue.test;

import stackqueue.SeqStack;

/**
 * ����ջ�ж����������Ƿ�ƥ��
 */
public class Exp_isValid {

	public static String isValid(String expstr) {
        SeqStack<String> stack = new SeqStack<String>(expstr.length());// ˳��ջ
        for (int i = 0; i < expstr.length(); i++) {
			char ch = expstr.charAt(i);
			switch (ch) {
			case '(':
                stack.push(ch + "");// ��������ջ
                break;
			case ')':
                // ����������ʱ����ջ
                if (stack.isEmpty() || !stack.pop().equals("("))
                    return "����(";// �жϳ�ս�ַ��Ƿ�Ϊ������
            }
		}

        return (stack.isEmpty()) ? "" : "����)";// ���ؿմ���ʾû�д���
    }

	public static void main(String[] args) {
		String expstr = "((1+2)*3+4))(";
		System.out.println(expstr + "   " + isValid(expstr));
	}
}
