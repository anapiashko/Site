package com.anapiashko.site.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView(Views.Id.class)
    private Integer id;

    @JsonView(Views.IdPosition.class)
    private String position;

    @JsonView(Views.FullRole.class)
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Role() {
    }

    public Role(String position, Integer count) {
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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + getId();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Role role = (Role)obj;
        return id==role.id && (position == role.position ||
                position != null && position.equals(role.getPosition())) &&count == role.count;
    }
}
