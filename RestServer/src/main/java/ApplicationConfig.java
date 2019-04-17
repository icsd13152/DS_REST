
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author petro
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application {

    public ApplicationConfig() {
        initial();
    }

    @Override
    public Set<Class<?>> getClasses() {//gia na mporei na dexetai ta onomata twn klasewn sthn klash userresource
        Set<Class<?>> resources = new HashSet();
        resources.add(UserResource.class);
        return resources;
    }
//dhmiourgia tables sthn vash
    private void initial() {
        try {
            String databaseUrl = "jdbc:sqlite:C:/sqllite/myDB.db";
            ConnectionSource con = new JdbcConnectionSource(databaseUrl);
            
            Dao<User, Integer> userDao = DaoManager.createDao(con, User.class);
            Dao<Posts, Integer> postDao = DaoManager.createDao(con, Posts.class);
            Dao<FriendList, Integer> listDao = DaoManager.createDao(con, FriendList.class);
            TableUtils.createTableIfNotExists(con, User.class);
            TableUtils.createTableIfNotExists(con, Posts.class);
            TableUtils.createTableIfNotExists(con, FriendList.class);
            con.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
