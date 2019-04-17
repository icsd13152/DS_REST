
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Response;

/**
 *
 * @author petro
 */
//se auto to path vriskontai oles oi http methodoi. H default methodos einai h GET gia tous xrhstes
@Path("/users")
public class UserResource {

    Connection conn, con;
    Statement stm, stm2;
    HashMap<Integer, User> users = new HashMap<>();
    HashMap<Integer, FriendList> users2 = new HashMap<>();
    HashMap<Integer, User> users3 = new HashMap<>();
    HashMap<Integer, Posts> posts = new HashMap<>();

    @GET
    @Produces("application/json")
    public Response getUserInJson() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = conn.createStatement();
            ResultSet s = stm.executeQuery("select * from users");
            while (s.next()) {
                int id = s.getInt("id");
                String name = s.getString("name");
                String username = s.getString("username");
                String password = s.getString("password");
                String sex = s.getString("sex");
                String email = s.getString("email");
                String country = s.getString("country");
                String city = s.getString("city");
                String birth = s.getString("birth");
                User u = new User(username, name, password, birth, email, sex, country, city);
                System.out.println(u);
                users.put(id, u);
            }
            conn.close();
            return Response.status(200).entity(users).build();//epistrefei 200 ws status OK
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }

    @GET
    @Path("friendlist/{username}")
    @Produces("application/json")
    public Response getfriendListInJson(@PathParam("username") String username2) {//me to annotation pathparam pernw ap oto url to username
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = conn.createStatement();
            stm2 = conn.createStatement();
            ResultSet s = stm.executeQuery("select * from users");
            ResultSet s2 = stm2.executeQuery("select * from friendlist where username='" + username2 + "'");
            while (s.next()) {
                int id = s.getInt("id");
                String name = s.getString("name");
                String username = s.getString("username");
                String password = s.getString("password");
                String sex = s.getString("sex");
                String email = s.getString("email");
                String country = s.getString("country");
                String city = s.getString("city");
                String birth = s.getString("birth");

                User u = new User(username, name, password, birth, email, sex, country, city);
                //System.out.println(u);
                users.put(id, u);
            }

            while (s2.next()) {
                int id = s2.getInt("id");
                String username = s2.getString("username");
                String friend = s2.getString("usernameFriend");
                FriendList l = new FriendList(friend, username);

                //System.out.println(l.toString());
                users2.put(id, l);
            }
            for (HashMap.Entry<Integer, User> entry : users.entrySet()) {
                for (HashMap.Entry<Integer, FriendList> entry2 : users2.entrySet()) {

                    if (entry.getValue().getUsername().equals(entry2.getValue().getUsernameFriend())) {
                        User u = new User(entry.getValue().getUsername(), entry.getValue().getName(),
                                entry.getValue().getPassword(), entry.getValue().getBirth(),
                                entry.getValue().getEmail(), entry.getValue().getSex(),
                                entry.getValue().getCountry(), entry.getValue().getCity());
                        //System.out.println("mplaaaaaaaaaaaaaaaaaaaaaa"+u);
                        users3.put(entry.getKey(), u);
                    }

                }
            }
            //System.out.println(users3);
            conn.close();
            return Response.status(200).entity(users3).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(200).entity(users3).build();
    }

    @GET
    @Path("recent/posts")
    @Produces("application/json")
    public Response getPostsInJson() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = conn.createStatement();
            ResultSet s = stm.executeQuery("select * from posts order by id desc limit 10; ");
            while (s.next()) {
                int id = s.getInt("id");
                String d = s.getString("date");

                String username = s.getString("username");
                String locationUser = s.getString("locationUser");
                String post = s.getString("post");

                Posts p = new Posts(username, post, locationUser, d);
                posts.put(id, p);
            }
            
            conn.close();
            return Response.status(200).entity(posts).build();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createUserJson(User u) {
        try {

            Class.forName("org.sqlite.JDBC");
            ConnectionSource conn = new JdbcConnectionSource("jdbc:sqlite:C:/sqllite/myDB.db");
            Dao<User, Integer> userdao = DaoManager.createDao(conn, User.class);

            userdao.create(u);

            conn.close();

            return Response.status(201).entity("ok").build();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }
//me thn methodo put kanw update enos stoixeiou
    @PUT
    @Path("/{username}/{pedio}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUser(@PathParam("username") String username, @PathParam("pedio") String pedio, String field) {
        try {
            Class.forName("org.sqlite.JDBC");

            con = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = con.createStatement();//dhmiourgia statement gia query sth vash
            ResultSet s = stm.executeQuery("select * from users ");
            String uname = s.getString("username");
            while (s.next()) {
                uname = s.getString("username");
                if (username.equals(uname)) {
                    if (pedio.equals("password")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET password= '" + field + "' WHERE username ='" + username + "'");

                    } else if (pedio.equals("sex")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET sex= '" + field + "' WHERE username ='" + username + "'");
                        break;
                    } else if (pedio.equals("name")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET name= '" + field + "' WHERE username ='" + username + "'");
                        break;
                    } else if (pedio.equals("email")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET email= '" + field + "' WHERE username ='" + username + "'");
                        break;
                    } else if (pedio.equals("birth")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET birth= '" + field + "' WHERE username ='" + username + "'");
                        break;
                    } else if (pedio.equals("country")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET country= '" + field + "' WHERE username ='" + username + "'");
                        break;
                    } else if (pedio.equals("city")) {
                        ResultSet s2 = stm.executeQuery("UPDATE users SET city= '" + field + "' WHERE username ='" + username + "'");
                        break;
                    }

                }
            }

            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(201).entity("ok").build();
    }

    @POST
    @Path("/posts")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createPostJson(Posts p) {
        try {

            Class.forName("org.sqlite.JDBC");
            ConnectionSource conn = new JdbcConnectionSource("jdbc:sqlite:C:/sqllite/myDB.db");
            Dao<Posts, Integer> postdao = DaoManager.createDao(conn, Posts.class);

            postdao.create(p);

            conn.close();

            return Response.status(201).entity(p).build();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }
//me thn methodo delete svinw apo th vash
    @DELETE
    @Path("/delete_post/{id}")
    @Produces("application/json")

    public Response deletePosts(@PathParam("id") int id) {
        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = con.createStatement();
            ResultSet s = stm.executeQuery("delete from posts where id=" + id + "");

            con.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(200).entity("ok").build();
    }

    @POST
    @Path("/friendlist")
    @Produces("application/json")
    @Consumes("application/json")
    public Response createFriendListJson(FriendList l) {
        try {

            Class.forName("org.sqlite.JDBC");
            ConnectionSource conn = new JdbcConnectionSource("jdbc:sqlite:C:/sqllite/myDB.db");
            Dao<FriendList, Integer> listdao = DaoManager.createDao(conn, FriendList.class);

            listdao.create(l);

            conn.close();

            return Response.status(201).entity(l).build();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }

    @DELETE
    @Path("/delete_friend/{username}")
    @Produces("application/json")

    public Response deleteFriend(@PathParam("id") String username) {
        try {

            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = con.createStatement();
            ResultSet s = stm.executeQuery("delete from friendlist where usernameFriend=" + username + "");

            con.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(200).entity("ok").build();
    }

    @PUT
    @Path("/edit_post/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateUser(@PathParam("id") int id, String post) {
        try {
            Class.forName("org.sqlite.JDBC");

            con = DriverManager.getConnection("jdbc:sqlite:C:/sqllite/myDB.db");
            stm = con.createStatement();
            ResultSet s = stm.executeQuery("UPDATE Posts SET post= '" + post + "' WHERE id ='" + id + "'");

            con.close();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(201).entity("ok").build();
    }

}
