import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import static javax.swing.JOptionPane.showMessageDialog;

public class login extends JFrame {

        private JPanel contentPane;
        private JTextField usernameInput;
        private JTextField passwordInput;
        private JButton RegisterButton;

        public login() {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 450, 300);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

                setContentPane(contentPane);

                usernameInput = new JTextField();
                usernameInput.setColumns(10);

                JLabel lblNewLabel = new JLabel("Email");

                JLabel lblNewLabel_1 = new JLabel("Password");

                passwordInput = new JPasswordField();
                passwordInput.setColumns(10);

                JButton loginButton = new JButton("Login");

                RegisterButton = new JButton("Register");

                JLabel lblNewLabel_2 = new JLabel("Welcome to TTL!");
                lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 13));
                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(
                                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addContainerGap(28, Short.MAX_VALUE)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.TRAILING)
                                                                                .addGroup(gl_contentPane
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(loginButton)
                                                                                                .addGap(30)
                                                                                                .addComponent(RegisterButton,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                85,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(18))
                                                                                .addGroup(gl_contentPane
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(gl_contentPane
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.LEADING)
                                                                                                                .addComponent(lblNewLabel_1)
                                                                                                                .addComponent(lblNewLabel))
                                                                                                .addGap(44)
                                                                                                .addGroup(gl_contentPane
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.LEADING,
                                                                                                                                false)
                                                                                                                .addComponent(passwordInput)
                                                                                                                .addComponent(usernameInput,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                211,
                                                                                                                                GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(95))
                                                .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                                                .addGap(153)
                                                                .addComponent(lblNewLabel_2)
                                                                .addContainerGap(176, Short.MAX_VALUE)));
                gl_contentPane.setVerticalGroup(
                                gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE,
                                                                                25, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(37)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.BASELINE)
                                                                                .addComponent(lblNewLabel)
                                                                                .addComponent(usernameInput,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(30)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.BASELINE)
                                                                                .addComponent(lblNewLabel_1)
                                                                                .addComponent(passwordInput,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(ComponentPlacement.RELATED, 48,
                                                                                Short.MAX_VALUE)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.BASELINE)
                                                                                .addComponent(loginButton)
                                                                                .addComponent(RegisterButton))
                                                                .addGap(35)));
                contentPane.setLayout(gl_contentPane);
                this.setResizable(false);
                this.pack();
                this.setLocationRelativeTo(null);
                this.setVisible(true);

                RegisterButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                register registerWindow = new register();
                        }
                });

                loginButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String user = usernameInput.getText();
                                String pasword = passwordInput.getText();
                                User cur = null;
                                try {
                                        cur = loginToSystem(user, pasword);
                                } catch (ClassNotFoundException | IOException e1) {
                                        // TODO Auto-generated catch block
                                        // e1.printStackTrace();
                                }
                                if (cur != null) {
                                        accountDatabase data = new accountDatabase();
                                        //showMessageDialog(null, "Login Sucessfull");
                                        try {
                                                controller.createGui(cur);
                                        } catch (Exception ex) {
                                                throw new RuntimeException(ex);
                                        }
                                        dispose();
                                }
                        }
                });

        }

        public User loginToSystem(String email, String password) throws ClassNotFoundException, IOException {
                if (!(checkLength(email))) {
                        showMessageDialog(null, "Email length not Valid");
                        return null;
                }
                if (!(checkIfEmail(email))) {
                        showMessageDialog(null, "Email not Valid");
                        return null;
                }
                if (!(checkLength(password))) {
                        showMessageDialog(null, "Password not Valid");
                        return null;
                }
                accountDatabase dataBase = new accountDatabase();
                dataBase.loadData();
                User cur = dataBase.loginToAccount(email, password);
                if (cur != null) {
                        return cur;
                } else {
                        showMessageDialog(null, "No Account found");
                        return null;
                }
        }

        private static boolean checkIfEmail(String emailAddress) {
                if (!(emailAddress.contains("@"))) {
                        return false;
                }
                int atSignLocation = emailAddress.indexOf('@');
                String emailUserName = emailAddress.substring(0, atSignLocation);
                String domain = emailAddress.substring(atSignLocation + 1, emailAddress.length());
                if (domain.contains("@")) {
                        return false;
                }
                if (!(domain.contains("."))) {
                        return false;
                }
                if (containsSpecialCharacter(
                                emailUserName.substring(emailUserName.length() - 1, emailUserName.length()))
                                || containsSpecialCharacter(emailUserName.substring(0, 1))) {
                        return false;
                }

                return true;
        }

        private static boolean containsSpecialCharacter(String s) {
                return s != null && s.matches("[^A-Za-z0-9 ]");
        }

        private static boolean checkLength(String what) {
                if (what.length() < 1) {
                        return false;
                }
                return true;
        }

}