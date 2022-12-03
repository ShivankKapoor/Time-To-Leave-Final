import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public mainFrame(User who) throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Time To Leave!");
		setBounds(100, 100, 1300, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		String name = who.getEmail();
		int atSign = name.indexOf('@');
		name = name.substring(0, atSign);
		JLabel nameLabel ;
		if(name.length()>9){
			name="";
			nameLabel = new JLabel("Time To Leave");
		}else{
		 	nameLabel = new JLabel("Welcome " + name);
		}
		nameLabel.setFont(new Font("Hoefler Text", Font.BOLD, 40));
		nameLabel.setFont(new Font("Hoefler Text", Font.BOLD, 40));
		
		JButton addClassButton = new JButton("Add Event");
		addClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton logoutButton = new JButton("Logout");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton removeEventButton = new JButton("Remove Event");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(500)
					.addComponent(nameLabel, GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
					.addGap(388))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, 329, 329)
								.addComponent(addClassButton, GroupLayout.DEFAULT_SIZE, 329, 329))
							.addGap(27))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(removeEventButton, GroupLayout.PREFERRED_SIZE, 329, 329)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 811, GroupLayout.PREFERRED_SIZE)
					.addGap(17))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(nameLabel)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(194)
							.addComponent(addClassButton, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(removeEventButton)
							.addGap(14)
							.addComponent(logoutButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(308))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 576, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		String[] columnName = {"ID#","Name","Building Name","Time","Travel Time To Next Event"};
		String[][] rows = who.getScheduleInfo();
		table = new JTable(rows,columnName);
		table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
		table.getColumn("ID#").setPreferredWidth(3);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
		this.setResizable(false);
		this.setVisible(true);
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				login x = new login();
			}
		});

		addClassButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addEvent x = new addEvent(who);
			}
		});
		removeEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveEvent x = new RemoveEvent(who);
			}
		});
	}
	
}
