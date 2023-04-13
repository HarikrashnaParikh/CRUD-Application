package com.harikrashna.contactdirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;

@Entity
@Table(name = "mobile")
public class Mobile {
    @Id
    @GenericGenerator(name = "native_generator", strategy = "native")
    @GeneratedValue(generator = "native_generator")
    private int id;

    @Column(name = "mobiles")
    private String mobiles;
//    private ArrayList<String> mobiles = new ArrayList<String>();


    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User userId;


//    @JsonIgnore
    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Mobile(){};

    public Mobile(String mobiles) {
        super();
        this.mobiles = mobiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobiles() {
        return mobiles;
    }

    public void setMobiles(String mobiles) {

        this.mobiles = mobiles;
    }

    @Override
    public String toString() {
        return "Mobile{" +
                "id=" + id +
                ", mobiles='" + mobiles + '\'' +
                ", userId=" + userId.getId() +
                '}';
    }
}
