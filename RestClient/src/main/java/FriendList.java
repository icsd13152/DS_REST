/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author petro
 */
public class FriendList {
    
    
    private String usernameFriend;
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    private String username;

    public FriendList() {
    }

    public FriendList(String usernameFriend, String username) {
        this.usernameFriend = usernameFriend;
        this.username = username;
    }

    public void setUsernameFriend(String usernameFriend) {
        this.usernameFriend = usernameFriend;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameFriend() {
        return usernameFriend;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "FriendList{" + "usernameFriend=" + usernameFriend + ", username=" + username + '}';
    }
    
}
