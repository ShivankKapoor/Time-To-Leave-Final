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

public class RemoveEvent extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public RemoveEvent(User cur) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JLabel removeTitleLabel = new JLabel("Remove Event");
		removeTitleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		
        String[] choices= new String[cur.numOfEvents()];

        for(int i =0;i<cur.numOfEvents();i++){
            choices[i]=i+"";
        }

		JComboBox removeBox = new JComboBox(choices);
		
		JLabel lblNewLabel = new JLabel("ID#");
		
		JButton removeButton = new JButton("Remove");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(170, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(removeBox, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
							.addGap(172))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(removeButton)
							.addGap(168))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(removeTitleLabel)
							.addGap(160))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(removeTitleLabel)
					.addGap(80)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(removeBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
					.addComponent(removeButton)
					.addGap(37))
		);
		contentPane.setLayout(gl_contentPane);
		this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    int indexToRemove = Integer.parseInt((String)removeBox.getSelectedItem());
                    cur.removeEvent(indexToRemove);
                    try {
                        accountDatabase.saveAndQuit();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    dispose();
				try {
					controller.reload(cur);
				} catch (Exception ex) {
					throw new RuntimeException(ex);
				}
			}
    });
	}
}
