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
import java.awt.event.*;
import java.io.IOException;

public class register extends JFrame {

        private JPanel contentPane;
        private JTextField emailTextField;
        private JTextField passwordTextField;
        private JTextField retypedPassworTextField;

        /**
         * Create the frame.
         */
        public register() {
                // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setBounds(100, 100, 450, 350);
                contentPane = new JPanel();
                contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

                setContentPane(contentPane);

                JLabel lblNewLabel = new JLabel("Create an Account");

                JLabel lblNewLabel_1 = new JLabel("Email");
                lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);

                JLabel lblNewLabel_2 = new JLabel("Password");
                lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);

                JLabel lblNewLabel_3 = new JLabel("Retype Password");

                emailTextField = new JTextField();
                emailTextField.setColumns(10);

                passwordTextField = new JPasswordField();
                passwordTextField.setColumns(10);

                retypedPassworTextField = new JPasswordField();
                retypedPassworTextField.setColumns(10);

                JButton registerButton = new JButton("Register");
                GroupLayout gl_contentPane = new GroupLayout(contentPane);
                gl_contentPane.setHorizontalGroup(
                                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addContainerGap(46, Short.MAX_VALUE)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.LEADING)
                                                                                .addGroup(gl_contentPane
                                                                                                .createParallelGroup(
                                                                                                                Alignment.LEADING)
                                                                                                .addGroup(gl_contentPane
                                                                                                                .createSequentialGroup()
                                                                                                                .addGroup(gl_contentPane
                                                                                                                                .createParallelGroup(
                                                                                                                                                Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(lblNewLabel_3,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(lblNewLabel_2,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(lblNewLabel_1,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(43)
                                                                                                                .addGroup(gl_contentPane
                                                                                                                                .createParallelGroup(
                                                                                                                                                Alignment.LEADING,
                                                                                                                                                false)
                                                                                                                                .addComponent(retypedPassworTextField)
                                                                                                                                .addComponent(passwordTextField)
                                                                                                                                .addComponent(emailTextField,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                183,
                                                                                                                                                Short.MAX_VALUE))
                                                                                                                .addGap(63))
                                                                                                .addGroup(Alignment.TRAILING,
                                                                                                                gl_contentPane.createSequentialGroup()
                                                                                                                                .addComponent(registerButton)
                                                                                                                                .addGap(167)))
                                                                                .addGroup(Alignment.TRAILING,
                                                                                                gl_contentPane.createSequentialGroup()
                                                                                                                .addComponent(lblNewLabel)
                                                                                                                .addGap(157)))));
                gl_contentPane.setVerticalGroup(
                                gl_contentPane.createParallelGroup(Alignment.LEADING)
                                                .addGroup(gl_contentPane.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(lblNewLabel)
                                                                .addGap(50)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.BASELINE)
                                                                                .addComponent(lblNewLabel_1)
                                                                                .addComponent(emailTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addGap(31)
                                                                .addGroup(gl_contentPane
                                                                                .createParallelGroup(Alignment.LEADING)
                                                                                .addGroup(gl_contentPane
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(lblNewLabel_2)
                                                                                                .addGap(47)
                                                                                                .addGroup(gl_contentPane
                                                                                                                .createParallelGroup(
                                                                                                                                Alignment.BASELINE)
                                                                                                                .addComponent(lblNewLabel_3)
                                                                                                                .addComponent(retypedPassworTextField,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)))
                                                                                .addComponent(passwordTextField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(ComponentPlacement.RELATED, 41,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(registerButton)
                                                                .addGap(24)));
                contentPane.setLayout(gl_contentPane);
                this.setResizable(false);
                this.setAlwaysOnTop(true);
                this.pack();
                this.setLocationRelativeTo(null);
                this.setVisible(true);
                registerButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                                String emailString = emailTextField.getText();
                                String passwordString = passwordTextField.getText();
                                String retypedPassString = retypedPassworTextField.getText();
                                try {
                                        register(emailString, passwordString, retypedPassString);
                                } catch (ClassNotFoundException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                } catch (IOException e1) {
                                        // TODO Auto-generated catch block
                                        e1.printStackTrace();
                                }
                        }
                });
        }

        private boolean register(String email, String password, String retypePassword)
                        throws IOException, ClassNotFoundException {
                accountDatabase data = new accountDatabase();
                data.loadData();
                if (!(checkLength(email))) {
                        warningMsg("Email length not Valid");
                        return false;
                }
                if (!(checkIfEmail(email))) {
                        warningMsg("Email not Valid");
                        return false;
                }
                if (!(checkLength(password))) {
                        warningMsg("Password length not Valid");
                        return false;
                }
                if (!(checkLength(retypePassword))) {
                        warningMsg("Must rytpe password");
                        return false;
                }
                if (!(passwordMatches(password, retypePassword))) {
                        warningMsg("Password and Rtyped password do not match");
                        return false;
                }
                // User newUser = new User(email, password);
                boolean databaseSuccess = data.register(email, retypePassword);
                if (databaseSuccess) {
                        warningMsg("Account Created!");
                        this.dispose();
                        return true;
                } else {
                        warningMsg("Email in use");
                        return false;
                }
        }

        private static void warningMsg(String what) {
                JOptionPane optionPane = new JOptionPane();
                JDialog dialog = optionPane.createDialog("Registration Message");
                optionPane.setMessage(what);
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
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

        private static boolean passwordMatches(String password, String reTypedPassword) {
                return (password.equals(reTypedPassword));
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
}