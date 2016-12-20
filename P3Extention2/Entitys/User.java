package Entitys;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class User {

    @Id//Represents the primary key field
    @Column(name="USER_ID")//Represents the foreign key field
    public int userID;
    public String groupName;

    ///////////CONSTRUCTORS///////////
    public User() {}

    public User(int userID, String groupName) {
        this.userID = userID;
        this.groupName = groupName;
    }

    ///////////GETTERS AND SETTERS///////////
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getGroupName() {return groupName;}

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
