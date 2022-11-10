import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.ImageObserver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JToggleButton;
import javax.swing.JList;
import java.awt.Canvas;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import java.awt.Choice;
import java.awt.Panel;
import java.awt.SystemColor;
import javax.swing.ListSelectionModel;

public class WaitingRoom extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int BUF_LEN = 128; // Windows ó�� BUF_LEN �� ����
	private Socket socket; // �������
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private String [] fruits = {"apple", "banana", "kiwi", "mango", "pear", "peach",
			"berry", "strawberry", "blackberry", "strawberry", "blackberry", "strawberry", "blackberry", "strawberry", "blackberry"};
	private JScrollPane userScrollPane, roomScrollPane;
	private JList userList;
	private JPanel contentPanel, roomlistPanel;
	private JLabel roomL;

	private ImageIcon screenImage = new ImageIcon("images/waitingBackground.png");
	private Image introBackground = screenImage.getImage();
	private ImageIcon logoImage = new ImageIcon("images/gameLogo.png");
	private JButton gameLogo = new JButton(logoImage);
	private ImageIcon startButtonBasicImage = new ImageIcon("images/startButtonBasic.png");
	private JButton startButton = new JButton(startButtonBasicImage);
	private ImageIcon startButtonEnteredImage = new ImageIcon("images/startButtonEntered.png");
	

	public WaitingRoom(String nickname, String ip_addr, String port_no)  {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setTitle("kkutu");
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPanel = new JPanel() {
			public void paintComponent(Graphics g) {
                g.drawImage(introBackground, 0, 0, this.getWidth(), this.getHeight(), null);
                setOpaque(false); // �׸��� ǥ���ϰ� ����, �����ϰ� ����
                super.paintComponent(g);
            }
		};
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		// ���� �ΰ� ���̱�
		gameLogo.setBorderPainted(false); // JButton�� border ���ֱ�
		gameLogo.setContentAreaFilled(false); // JButton�� ���뿵�� ä��� ����
		gameLogo.setFocusPainted(false); // JButton�� ���� �Ǿ��� �� ����� �׵θ� ���ֱ�
		gameLogo.setBounds(50, 10, logoImage.getIconWidth(), logoImage.getIconHeight());
		contentPanel.add(gameLogo);

		// ���� ������ ����Ʈ
		userScrollPane = new JScrollPane();
		userScrollPane.setBounds(30, 150, 200, 250);
		userList = new JList(fruits);
		userList.setFont(new Font("�����ٸ����", Font.PLAIN, 12));
		userList.setToolTipText("\uAC8C\uC784\uC811\uC18D\uC790 \uBAA9\uB85D");
		userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userList.setBackground(SystemColor.control);
		userScrollPane.setViewportView(userList);
		contentPanel.add(userScrollPane);
		
		// ����� ������
		
		
		// �游��� ��ư
		startButton.setBorderPainted(false); // JButton�� border ���ֱ�
		startButton.setContentAreaFilled(false); // JButton�� ���뿵�� ä��� ����
		startButton.setFocusPainted(false); // JButton�� ���� �Ǿ��� �� ����� �׵θ� ���ֱ�
		startButton.setBounds(23, 420, startButtonBasicImage.getIconWidth(), startButtonBasicImage.getIconHeight());
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) { // ���콺�� ��ư ���� �ö� ��
				startButton.setIcon(startButtonEnteredImage);
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) { // ���콺�� ��ư ������ ���� ��
				startButton.setIcon(startButtonBasicImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		contentPanel.add(startButton);

		// �渮��Ʈ
		roomL = new JLabel("���� �� ���");
		roomL.setBounds(254, 22, 128, 24);
		roomL.setFont(new Font("�������", Font.BOLD, 20));
		contentPanel.add(roomL);
		
		roomlistPanel = new JPanel(new GridLayout(20, 1, 10, 10)); // 20��
		roomlistPanel.setBackground(Color.WHITE);
		roomScrollPane = new JScrollPane(roomlistPanel);
		roomScrollPane.setBounds(254, 48, 405, 582);
		roomScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPanel.add(roomScrollPane);
		
	}
}
