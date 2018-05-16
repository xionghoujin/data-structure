package stackqueue.test;

import stackqueue.LinkedStack;
import stackqueue.SeqStack;

/**
 * ����ջ���б��ʽ����
 */
public class Expresstion {

	public static void main(String[] args) {
		String expstr = "121+10*(53-49+20)/((35-25)*2+10)+11";
		String postfix = toPostfix(expstr);
		System.out.println("expstr   " + expstr);
		System.out.println("postfix   " + postfix);
		System.out.println("value   " + value(postfix));
	}

	/**
     * ����expstr�ĺ�׺���ʽ
     */
	public static String toPostfix(String expstr) {
        SeqStack<String> stack = new SeqStack<String>(expstr.length());// ���������ջ��˳��ջ���غ�׺���ʽ
        String postfix = "";// ���غ�׺���ʽ
        int i = 0;
		while (i < expstr.length()) {
			char ch = expstr.charAt(i);
			switch (ch) {
                case '+':// ����+,-����ջ��Ԫ�رȽ�
                case '-':
				while (!stack.isEmpty() && !stack.get().equals("("))
					postfix += stack.pop();
				stack.push(ch + "");
				i++;
				break;
                case '*':// ����*��/��ջ��Ԫ�رȽ�
                case '/':
				while (!stack.isEmpty()
						&& (stack.get().equals("*") || stack.get().equals("/")))
					postfix += stack.pop();
				stack.push(ch + "");
				i++;
				break;
                case '(':// ���������ţ���ջ
                    stack.push(ch + "");
				i++;
				break;
			case ')':
                String out = stack.pop();// ���������ţ���ջ����ջ�շ���null
                while (out != null && !out.equals("(")) {// �жϳ�ջ�ַ��Ƿ�Ϊ������
                    postfix += out;
					out = stack.pop();
				}
				i++;
				break;
			default:
				while (i < expstr.length() && ch >= '0' && ch <= '9') {
                    postfix += ch;// ��������ʱ�����뵽��׺���ʽ
                    i++;
					if (i < expstr.length())
						ch = expstr.charAt(i);
				}
                postfix += " ";// ��ӿո���Ϊ��ֵ֮��ķָ���
            }
		}
		while (!stack.isEmpty())
			postfix += stack.pop();
		return postfix;
	}

	/**
     * �����׺���ʽ��ֵ
     */
	public static int value(String postfix) {
        // ����������ջ����ʽջ
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
		int i = 0, result = 0;
        // �������׺���ʽ���ַ�
        while (i < postfix.length()) {
			char ch = postfix.charAt(i);
            if (ch >= '0' && ch <= '9') {// ���������ַ�
                result = 0;
				while (ch != ' ') {
					result = result * 10 + Integer.parseInt(ch + "");
					i++;
					ch = postfix.charAt(i);
				}
				i++;
                stack.push(new Integer(result));// ��������ջ
            } else {
                int y = stack.pop().intValue();// ��ջ����������
                int x = stack.pop().intValue();
				switch (ch) {
				case '+':
					result = x + y;
					break;
				case '-':
					result = x - y;
					break;
				case '*':
					result = x * y;
					break;
				case '/':
                    result = x / y;// ����������Ϊ0ʱ���׳��쳣
                    break;
				}
                stack.push(new Integer(result));// ��������ջ
                i++;
			}
		}
        return stack.pop().intValue();// ����������
    }
}