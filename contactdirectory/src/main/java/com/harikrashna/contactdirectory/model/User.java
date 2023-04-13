package com.harikrashna.contactdirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GenericGenerator(name = "native_generator", strategy = "native")
    @GeneratedValue(generator = "native_generator")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "email_id")
    private String emailId;
//    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<Mobile>  mobiles;

    public User(){

    }

    public User(String name, String occupation, String emailId) {
        super();
        this.name = name;
        this.occupation = occupation;
        this.emailId = emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public List<Mobile> getMobiles() {
        return mobiles;
    }

    public void setMobiles(List<Mobile> mobiles) {
        this.mobiles = mobiles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobiles=" + mobiles +
                '}';
    }
}
