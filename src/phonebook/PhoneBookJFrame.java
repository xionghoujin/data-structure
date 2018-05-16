package phonebook;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 * �绰��������
 */
public class PhoneBookJFrame extends JFrame implements ListSelectionListener,
		ActionListener, WindowListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
    private PhoneBookTreeSet book;// �绰����ʹ��һ�������ϴ洢����Friend����
    private JList<String> list;// �б��
    private DefaultListModel<String> listModel;// Ĭ���б�����
    private JTable table;// ������
    private DefaultTableModel tableModel;// Ĭ�ϱ�����
    private JComboBox<String> combobox_name;// ������Ͽ�
    private DefaultComboBoxModel<String> comboModel;// Ĭ����Ͽ�ģ�ͣ�ѡ������
    private JTextField text_code;// �绰�����ı���

	public static void main(String[] args) {
		new PhoneBookJFrame("friends.dat");
	}

	public PhoneBookJFrame(String filename) {
        super("�绰��");
        this.setBounds(300, 300, 510, 390);
        this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(this);// ע�ᴰ��ʱ�������
        Font f = new Font("΢���ź�", Font.BOLD, 15);

        JSplitPane split_h = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);// ˮƽ�ָ��
        split_h.setDividerLocation(70);// ���÷ָ�����λ��
        this.add(split_h);
        JSplitPane split_v = new JSplitPane(JSplitPane.VERTICAL_SPLIT);// ��ֱ�ָ��
        split_v.setDividerLocation(260);// ���ô�ֱ�ָ�����λ��
        this.book = new PhoneBookTreeSet(filename);
        this.listModel = new DefaultListModel<String>();// Ĭ���б��ģ��
        this.listModel.addElement("ȫ��");
        this.list = new JList<>(listModel);// �����б��
        this.list.setFont(f);
        this.list.setBackground(new Color(205, 250, 215));
		this.list.setSelectionBackground(Color.GREEN);
		this.list
                .setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);// ���õ�ѡģʽ
        this.list.addListSelectionListener(this);// �б��ע��ѡ���¼�������
        split_h.add(new JScrollPane(this.list));// ����ڹ���������
        split_h.add(split_v);

        String[] columns = {"����", "�绰����"};// �����е����ı���
        this.tableModel = new DefaultTableModel(columns, 0);// Ĭ�ϱ��ģ�ͣ�ָ���б��⣬0��
        this.table = new JTable(tableModel);// �����ձ�����б���
        this.table.setFont(f);
        this.table.setSelectionBackground(Color.green);
		this.table.setBackground(new Color(205, 250, 215));
        this.list.setSelectedIndex(0);// ѡ�пձ���һ�ִ��valueChange()����
        split_v.add(new JScrollPane(table));// �������ӽ�ȥ

        // ������������Ͽ��ı��У����ɾ�������ҵȰ�ť
        JPanel friendpanel = new JPanel(new GridLayout(2, 1));
        split_v.add(friendpanel);
		JPanel panels[] = { new JPanel(), new JPanel() };
		for (int i = 0; i < panels.length; i++)
			friendpanel.add(panels[i]);
		JLabel label1 = new JLabel(columns[0]);
		label1.setFont(f);
		panels[0].add(label1);
        this.comboModel = new DefaultComboBoxModel<String>();// Ĭ����Ͽ�ģ��
        combobox_name = new JComboBox<String>(this.comboModel);
        this.combobox_name.setFont(f);
		combobox_name.setEditable(true);
		panels[0].add(combobox_name);
		JLabel label2 = new JLabel(columns[1]);
		label2.setFont(f);
		panels[0].add(label2);
		this.text_code = new JTextField("12345678901", 12);
		panels[0].add(text_code);
        this.addIndex();// JList��ӵ绰���е���������������
        String[] buttonstr = {"���", "����������", "���绰�������", "ɾ����"};
        JButton[] buttons = new JButton[buttonstr.length];
        for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonstr[i]);
			buttons[i].setFont(f);
			buttons[i].setBackground(Color.ORANGE);
			panels[1].add(buttons[i]);
			buttons[i].addActionListener(this);
		}
		this.setVisible(true);
	}

    // JList����ӵ绰���е���������������
    private void addIndex() {
        Iterator<Friend> it = this.book.iterator();// ����һ������������
        while (it.hasNext()) {// ���к��Ԫ��,ʹ�õ�������������
            String indexstr = it.next().index();// ��ú��Ԫ������������
            if (!this.listModel.contains(indexstr)) {
                this.listModel.addElement(indexstr);// �б��ģ����Ӳ��ظ�������
                this.comboModel.addElement(indexstr);// ��Ͽ���Ӳ��ظ�������
            }
        }
	}

	@Override
	public void windowOpened(WindowEvent e) {
        // TODO �Զ����ɵķ������
    }

	@Override
	public void windowClosing(WindowEvent e) {
        this.book.writeToFile();// ���绰�������ж���д��ָ���ļ������ļ������ڣ������ļ�

	}

	@Override
	public void windowClosed(WindowEvent e) {
        // TODO �Զ����ɵķ������
    }

	@Override
	public void windowIconified(WindowEvent e) {
        // TODO �Զ����ɵķ������

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
        // TODO �Զ����ɵķ������

	}

	@Override
	public void windowActivated(WindowEvent e) {
        // TODO �Զ����ɵķ������

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
        // TODO �Զ����ɵķ������

	}

    // �����¼�������
    public void actionPerformed(ActionEvent e) {
        String name = (String) combobox_name.getSelectedItem();
		String code = text_code.getText();

        if (e.getActionCommand().equals("���")) {// ������Ӱ�ť
            Friend f = new Friend(name, code);
            if (!name.equals("") && !this.book.contains(f)) {
                this.book.add(f);// ��Ӷ���TreeSet�������ظ�Ԫ��
                String surname = f.index();
                if (list.getSelectedValue().equals(surname))
					tableModel.addRow(f.toArray());
				else {
					if (!listModel.contains(surname))
						;
                    {// �б�����Ӳ��ظ���Ԫ��
                        listModel.addElement(surname);
                        comboModel.addElement(surname);
					}
                    list.setSelectedValue(surname, true);// �����б��Ϊѡ����
                }
            } else
				JOptionPane.showMessageDialog(this,
                        "������������մ����ظ����� " + f.toString());
            return;
        }

        if (e.getActionCommand().equals("ɾ����")) {
            if (this.book.isEmpty())
                JOptionPane.showMessageDialog(this, "���գ�����ɾ��������");
            else {
                int i = table.getSelectedRow();// ���ǰѡ���к�
                if (i == -1)
                    JOptionPane.showMessageDialog(this, "��ѡ��������");
                else {
                    name = (String) table.getValueAt(i, 0);
                    int yes = JOptionPane.showConfirmDialog(null, "ɾ��\"" + name
                            + "\"�У�");// ȷ�ϵ����Ի����yes��ť
                    if (yes == 0) {
                        code = (String) table.getValueAt(i, 1);
						Friend f = new Friend(name, code);
                        this.book.remove(f);// �绰����ɾ������
                        tableModel.removeRow(i);// �������ɾ������
                        if (this.book.search(f, new IndexComparator()) == null) {// �����ϲ��ң��绰����û����ͬ����
                            listModel.removeElement(f.index());// �б����ɾ��ָ������
                            comboModel.removeElement(f.index());
                        }
					}
				}
			}
		}

        if (e.getActionCommand().equals("����������")) {
            if (!this.book.isEmpty()
                    && book.search(
					new Friend(
							(String) combobox_name.getSelectedItem(),
							""), new NameComparator()) != null) {
				for (int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
                    // ��ձ��
                    this.tableModel.removeRow(i);

				this.addAll(new Friend(
								(String) combobox_name.getSelectedItem(), ""),
						new NameComparator());
			} else
                JOptionPane.showMessageDialog(this, "û�в��ҵ�!");
        }

        if (e.getActionCommand().equals("���绰�������")) {
            if (!this.book.isEmpty()
                    && book.search(new Friend("", text_code.getText()),
					new CodeComparator()) != null) {
				for (int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
                    // ��ձ��
                    this.tableModel.removeRow(i);

				this.addAll(new Friend("", text_code.getText()),
						new CodeComparator());
			} else
                JOptionPane.showMessageDialog(this, "û�в��ҵ�!");
        }
    }

    // �б���ѡ���¼�������
    public void valueChanged(ListSelectionEvent e) {
        String surname = list.getSelectedValue();// �����б��ѡ�����������
        if (!this.book.isEmpty() && surname != null && surname != "") {// ��ѡ�����ϸ��±��
            for (int i = this.tableModel.getRowCount() - 1; i >= 0; i--)
                // ��ձ��
                this.tableModel.removeRow(i);
            if (surname == "ȫ��")
                this.addAll();// ���������ж���
            else
                this.addAll(new Friend(surname, ""), new IndexComparator());// ������ָ�����ϵ�������
        }
    }

    // ���绰������friend����ͱȽ���cָ�����������ж�����ӵ�tableModel���ģ����
    private void addAll(Friend friend, Comparator<Friend> c) {
        Iterator<Friend> it = this.book.iterator();
        while (it.hasNext()) {// ��������
            Friend f = it.next();
            if (c == null || c.compare(friend, f) == 0)// �Ƚ���cָ������ȽϹ���
                this.tableModel.addRow(f.toArray());// ������һ�У���������ָ������ֵ
        }
    }

	private void addAll() {
		this.addAll(null, null);
	}
}