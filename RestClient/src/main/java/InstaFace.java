
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author petro
 */
public class InstaFace extends JFrame {

    private JPanel row1, row2, row3, row4, row5, row6, row7, row8, row9;
    private JLabel field, post, post2, locationuser, postid, postid2, friend, delfriend, upass, umail;
    private JTextField updatefield, userpost, userpost2, userlocation, idpost, userfriend, frienddel, idpost2, email;
    private JButton update, send, sendpost, deletepost, add, retrieveFriends, deletefr, editpost,recentposts;
    private JComboBox box;
    private String uname;

    public InstaFace(String uname) {
        super("Hello");
        this.uname = uname;
        row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();
        row5 = new JPanel();
        row6 = new JPanel();
        row7 = new JPanel();
         row8 = new JPanel();
        field = new JLabel("choose the field you want to update: ");
        post = new JLabel("make a post: ");
        friend = new JLabel("add a friend: ");
        delfriend = new JLabel("delete a friend: ");
        locationuser = new JLabel("make a post to a user profil: ");
        postid = new JLabel("ender the id of post to delete: ");
        postid2 = new JLabel("ender the id of post to edit: ");

        updatefield = new JTextField(20);
        userpost = new JTextField(50);
        userpost2 = new JTextField(50);
        userlocation = new JTextField(20);
        userfriend = new JTextField(20);
        frienddel = new JTextField(20);
        idpost = new JTextField(20);
        idpost2 = new JTextField(20);
        update = new JButton("update");
        send = new JButton("send");
        sendpost = new JButton("send Post");
        deletepost = new JButton("delete Post");
        recentposts = new JButton("see last 10 posts");
        retrieveFriends = new JButton("See all friends");
        deletefr = new JButton("delete friends");
        add = new JButton("add Friend");
        editpost = new JButton("edit post");
        box = new JComboBox();
        box.addItem("name");
        box.addItem("password");
        box.addItem("birth");
        box.addItem("sex");
        box.addItem("country");
        box.addItem("city");
        box.addItem("email");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Container pane = getContentPane();

        GridLayout glayout = new GridLayout(8, 2);
        pane.setLayout(glayout);
        FlowLayout layout = new FlowLayout();
        row1.setLayout(layout);
        row1.add(field);
        row1.add(updatefield);
        row1.add(box);
        row1.add(update);
        pane.add(row1);
        row2.setLayout(layout);
        row2.add(locationuser);
        row2.add(userlocation);
        pane.add(row2);
        row2.setLayout(layout);
        row2.add(userpost2);
        row2.add(sendpost);
        pane.add(row2);
        row3.setLayout(layout);
        row3.add(postid);
        row3.add(idpost);
        row3.add(deletepost);
        pane.add(row3);
        row4.setLayout(layout);
        row4.add(friend);
        row4.add(userfriend);
        row4.add(add);
        pane.add(row4);
        row5.setLayout(layout);
        row5.add(retrieveFriends);

        pane.add(row5);
        row6.setLayout(layout);
        row6.add(delfriend);
        row6.add(frienddel);
        row6.add(deletefr);

        pane.add(row6);
        row7.setLayout(layout);
        row7.add(postid2);
        row7.add(idpost2);
        row7.add(userpost);
        row7.add(editpost);
        pane.add(row7);
        row8.setLayout(layout);
        row8.add(recentposts);
        pane.add(row8);

        setContentPane(pane);
        pack();

        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                User u = new User();

                u.updateRequest(updatefield.getText(), uname, box.getSelectedItem().toString());

            }
        }
        );
        
         recentposts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                User u = new User();

                u.getRecentPost();

            }
        }
        );
        
         editpost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                User u = new User();

                u.editPost(userpost.getText(),idpost2.getText());

            }
        }
        );
        deletefr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                User u = new User();

                u.deleteFriend(frienddel.getText());

            }
        }
        );

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                FriendList l = new FriendList(userfriend.getText(), uname);
                User u = new User();

                u.addFriend(l);

            }
        }
        );

        retrieveFriends.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //FriendList l=new FriendList(userfriend.getText(),uname);
                User u = new User();

                u.getFrientList(uname);

            }
        }
        );

        sendpost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Date d=new Date();
                
                String date=d.toString();
                System.out.println(date);
                Posts p = new Posts(uname, userpost2.getText(), userlocation.getText(),date);
                System.out.println(p.toString());
                User u = new User();
                u.sendPost(p);

            }
        }
        );
        deletepost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                User u = new User();
                u.deletePost(idpost.getText());

            }
        }
        );

    }

}
