import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;


public class addEvent extends JFrame {

	private JPanel contentPane;
	private JTextField nameInput;
	private JTextField buildingInput;


	/**
	 * Create the frame.
	 */
	public addEvent(User who) {

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		this.setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		nameInput = new JTextField();
		nameInput.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name:");
		
		JLabel lblNewLabel = new JLabel("Add Event");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		
		buildingInput = new JTextField();
		buildingInput.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Building Name:");
		
		JLabel lblNewLabel_2 = new JLabel("Time:");
		
		String[] hourChoices = {"1","2","3","4","5","6","7","8","9","10","11","12"};
		String[]minChoice={"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20",
				"21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40"
				,"41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59"};
		String[]amPmChoice = {"AM","PM"};
		
		JComboBox hourComboBox = new JComboBox(hourChoices);
		
		JLabel lblNewLabel_3 = new JLabel(":");
		
		JComboBox minComboBox = new JComboBox(minChoice);
		
		JComboBox amPmBox = new JComboBox(amPmChoice);
		
		JButton submitButton = new JButton("Add Event");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(41, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblNewLabel_2)
									.addComponent(nameLabel)
									.addComponent(lblNewLabel_1))
								.addGap(27)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(buildingInput, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
									.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(hourComboBox, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(minComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(amPmBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGap(41))
							.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel)
								.addGap(180)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(submitButton)
							.addGap(156))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(nameLabel))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(buildingInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(hourComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3)
						.addComponent(minComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(amPmBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
					.addComponent(submitButton)
					.addGap(19))
		);
		contentPane.setLayout(gl_contentPane);
        this.setVisible(true);
		
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String name = nameInput.getText();
					String buildingString = buildingInput.getText();
					String hour = (String)hourComboBox.getSelectedItem();
					String min = (String)minComboBox.getSelectedItem();
					String amPm = (String)amPmBox.getSelectedItem();
					who.addEvent(new Event(name, buildingString, hour, min, amPm));
					try {
						accountDatabase.saveAndQuit();
						dispose();
						controller.reload(who);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
	});
		
	}
}