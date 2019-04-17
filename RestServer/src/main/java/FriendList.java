
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author petro
 */
//klash pou dhmiourgei lista filwn sthn vash me katallhla annotations gia na perastoun sthn vash
@DatabaseTable(tableName = "FriendList")
public class FriendList {
  @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField
    private String usernameFriend;
    @DatabaseField
    private String username;

    public FriendList() {
    }

    public FriendList(String usernameFriend, String username) {
        this.usernameFriend = usernameFriend;
        this.username = username;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setUsernameFriend(String usernameFriend) {
        this.usernameFriend = usernameFriend;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public int getId() {
        return id;
    }

    public String getUsernameFriend() {
        return usernameFriend;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "FriendList{" + "id=" + id + ", usernameFriend=" + usernameFriend + ", username=" + username + '}';
    }

  
    
    

}
