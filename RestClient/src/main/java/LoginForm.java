
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 *
 * @author petro
 */
public class LoginForm extends JFrame {

    private JPanel row1, row2, row3,row4;
    private JLabel pass, uusername, msg;
    private JTextField password, username;
    private JButton login;

    public LoginForm() {
        super("Login");
        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
         row4 = new JPanel();

        pass = new JLabel("enter your password: ");
        uusername = new JLabel("enter your username: ");
        msg = new JLabel("Login: ");
        password = new JTextField(20);
        username = new JTextField(20);
        login = new JButton("Login");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container pane = getContentPane();

        GridLayout glayout = new GridLayout(4, 2);
        pane.setLayout(glayout);
        FlowLayout layout = new FlowLayout();
        row1.setLayout(layout);
        row1.add(msg);
        pane.add(row1);
        row2.setLayout(layout);
        row2.add(uusername);
        row2.add(username);
        pane.add(row2);
        row3.setLayout(layout);
        row3.add(pass);
        row3.add(password);
        pane.add(row3);
        row4.setLayout(layout);
        row4.add(login);
        
        pane.add(row4);
        
        
        setContentPane(pane);
        pack();
        
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               
               
               InstaFace i=new InstaFace(username.getText());
               setVisible(false);
            }
        }
        );
        
    }

}
