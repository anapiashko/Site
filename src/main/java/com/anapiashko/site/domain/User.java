package com.anapiashko.site.domain;

import javax.persistence.*;

@Entity
@Table(name = "position")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String position;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private Integer count;

    public String getCountRole(){
        return count == null? "1": count.toString();
    }

    public User() {
    }

    public User(String position,Integer count) {
        this.position = position;
        this.count = count+1;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

}
