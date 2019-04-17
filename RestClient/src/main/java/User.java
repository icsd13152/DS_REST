
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author petro
 */
public class User {

    private String name;
    private String username;
    private String password;
    private String birth;
    private String sex;
    private String country;
    private String city;
    private String email;

    public User() {
    }

    public User(String name, String username, String password, String birth, String sex, String country, String city, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.email = email;
        sendRequest();
      //getRequest();
        
    }
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }
     
    

    public void sendRequest() {

        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users");
       
        String input = "{\"name\":\"" + name + "\",\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"birth\":\"" + birth + "\",\"sex\":\"" + sex + "\",\"country\":\"" + country + "\",\"city\":\"" + city + "\",\"email\":\"" + email + "\"}";
        
        
        Response res = target.request(MediaType.APPLICATION_JSON).post(Entity.json(input));

        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());
            System.out.println(name);

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }

    }

    public void getRequest() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users");

        Response res = target.request(MediaType.APPLICATION_JSON).get();
        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }

    }
      public void getFrientList(String username) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/friendlist").path(username);

        Response res = target.request(MediaType.APPLICATION_JSON).get();
        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }

    }
    
      public void addFriend(FriendList l) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/friendlist");

        Response res = target.request(MediaType.APPLICATION_JSON).post(Entity.json(l));
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }

    }
    
    public void updateRequest(String field,String username,String pedio){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users").path(username).path(pedio);
       
         Response res = target.request(MediaType.APPLICATION_JSON).put(Entity.json(field));
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());
            

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
        
    }
    
    public void getRecentPost(){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/recent/posts");

        Response res = target.request(MediaType.APPLICATION_JSON).get();
        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
        
    }
    
    public void sendPost(Posts post){
        
         Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/posts");
       
       
         Response res = target.request(MediaType.APPLICATION_JSON).post(Entity.json(post));
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());
            

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
    }
     public void deletePost(String id){
        
         Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/delete_post").path(id);
       
       
         Response res = target.request(MediaType.APPLICATION_JSON).delete();
        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());
            

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
    }
     
      public void deleteFriend(String username){
        
         Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/delete_friend").path(username);
       
       
         Response res = target.request(MediaType.APPLICATION_JSON).delete();
        if (res.getStatus() != 200) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());
            

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
    }
          public void editPost(String post,String id){
        
         Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/users/edit_post").path(id);
       
       
         Response res = target.request(MediaType.APPLICATION_JSON).put(Entity.json(post));
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());
            

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
    }

     

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirth() {
        return birth;
    }

    public String getSex() {
        return sex;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "{\"name\":\"" + name + "\",\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"birth\":\"" + birth + "\",\"sex\":\"" + sex + "\",\"country\":\"" + country + "\",\"city\":\"" + city + "\",\"email\":\"" + email + "\"}";
    }

}
