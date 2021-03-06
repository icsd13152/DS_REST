
import java.util.Date;



/**
 *
 * @author petro
 */
public class Posts {
    
   
    private int id;
   
    private String username;
   
    private String post;
    private String locationUser;
    private String date;

    public Posts() {
    }

    public Posts( String username, String post,String locationUser,String date) {
        this.id = id;
        this.username = username;
        this.post = post;
        this.locationUser=locationUser;
        this.date=date;
    }

    public String getLocationUser() {
        return locationUser;
    }
     

    public void setLocationUser(String locationUser) {
        this.locationUser = locationUser;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPost(String post) {
        this.post = post;
    }

     public void setDate(String date) {
        this.date = date;
    }
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPost() {
        return post;
    }

    public String getDate() {
        return date;
    }
    
    

    @Override
    public String toString() {
        return "Posts{" + "id=" + id + ", username=" + username + ", post=" + post + ", locationUser=" + locationUser + ", date=" + date + '}';
    }

 


}
