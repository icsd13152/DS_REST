
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author petro
 */
//klash pou dhmiourgei xrhstes sthn vash me katallhla annotations gia na perastoun sthn vash
@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(generatedId = true)
    int id;
    @DatabaseField
    private String name;
    @DatabaseField
    private String username;
    @DatabaseField
    private String password;
    @DatabaseField
    private String birth;
    @DatabaseField
    private String sex;
    @DatabaseField
    private String country;
    @DatabaseField
    private String city;
    @DatabaseField
    private String email;

    public User() {
    }

    public User(String username, String name, String password, String email, String birth, String sex, String country, String city) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.sex = sex;
        this.country = country;
        this.city = city;
        this.email = email;
    }
    public User(String username,String password){
        this.username=username;
        this.password=password;
    }

    public void setId(int id) {
        this.id = id;
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
        return "User{" + "name=" + name + ", username=" + username + ", password=" + password + ", birth=" + birth + ", sex=" + sex + ", country=" + country + ", city=" + city + ", email=" + email + '}';
    }
}
