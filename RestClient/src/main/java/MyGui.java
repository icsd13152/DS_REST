
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


/**
 *
 * @author petro
 */
public class MyGui extends JFrame {

    private JPanel row1, row2, row3, row4, row5, row6, row7, row8, row9;
    private JLabel uname, uusername, usex, dbirth, ucountry, ucity, upass, umail;
    private JTextField name, username, password, birth, sex, country, city, email;
    private JButton signup,login;

    public MyGui() {
        super("Hello");
        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();
        row5 = new JPanel();
        row6 = new JPanel();
        row7 = new JPanel();
        row8 = new JPanel();
        row9 = new JPanel();

        uname = new JLabel("enter your name: ");
        uusername = new JLabel("enter your username: ");
        upass = new JLabel("enter your password: ");
        usex = new JLabel("enter your sex: ");
        dbirth = new JLabel("enter your date of birth: ");
        ucountry = new JLabel("enter your country: ");
        ucity = new JLabel("enter your city: ");
        umail = new JLabel("enter your email: ");
        signup = new JButton("Sign up");
        login = new JButton("login");
        name = new JTextField(20);
        username = new JTextField(20);
        password = new JTextField(20);
        birth = new JTextField(20);
        sex = new JTextField(20);
        country = new JTextField(20);
        city = new JTextField(20);
        email = new JTextField(20);

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container pane = getContentPane();

        GridLayout glayout = new GridLayout(11, 2);
        pane.setLayout(glayout);
        FlowLayout layout = new FlowLayout();
        row1.setLayout(layout);
        row1.add(uname);
        row1.add(name);
        pane.add(row1);
        row2.setLayout(layout);
        row2.add(uusername);
        row2.add(username);
        pane.add(row2);
        row3.setLayout(layout);
        row3.add(upass);
        row3.add(password);
        pane.add(row3);
        row4.setLayout(layout);
        row4.add(umail);
        row4.add(email);
        pane.add(row4);
        row5.setLayout(layout);
        row5.add(usex);
        row5.add(sex);
        pane.add(row5);
        row6.setLayout(layout);
        row6.add(ucountry);
        row6.add(country);
        pane.add(row6);
        row7.setLayout(layout);
        row7.add(ucity);
        row7.add(city);
        pane.add(row7);
        row8.setLayout(layout);
        row8.add(dbirth);
        row8.add(birth);
        pane.add(row8);
        row9.setLayout(layout);
        row9.add(signup);
        row9.add(login);
        pane.add(row9);

        setContentPane(pane);
        pack();

        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (name.getText().equals("") || username.getText().equals("") || password.getText().equals("")
                        || sex.getText().equals("") || birth.getText().equals("")
                        || city.getText().equals("")
                        || country.getText().equals("") || email.getText().equals("")) {
                    JOptionPane.showMessageDialog(pane, "fill all gaps", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    SimpleDateFormat s = new SimpleDateFormat("dd/mm/yyyy");
                    User u = new User(name.getText(), username.getText(), password.getText(), birth.getText(), sex.getText(), country.getText(), city.getText(), email.getText());
                    InstaFace i=new InstaFace(username.getText());
                    setVisible(false);

                }
            }
        }
        );
          login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

               LoginForm l=new LoginForm();
            }
        }
        );

        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                int Answer = JOptionPane.showConfirmDialog(null, "Are you sure to close this window?", "Sure for Exit ?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (Answer == JOptionPane.YES_OPTION) {

                    System.exit(1);
                } else {
                    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });

    }

}
