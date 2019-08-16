package com.anapiashko.site.controller;

import com.anapiashko.site.DeleteRowDB;
import com.anapiashko.site.domain.User;
import com.anapiashko.site.repos.RolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private RolesRepo rolesRepo;

    @GetMapping("/")
    public String main(Map<String,Object> model){
        return "main";
    }

    @GetMapping("/listOfRoles")
    public String roles(HashMap<String,Object> model){
        Iterable<User> roles = rolesRepo.findAll();
        model.put("roles",roles);

        return "listOfRoles";
    }

    @GetMapping("/adduser")
    public String addRole(HashMap<String,Object> model){
        return "adduser";
    }

    @PostMapping("/adduser")
    public String add(@RequestParam String position, HashMap<String, Object> model) throws SQLException, ClassNotFoundException {

//        if(position == null || position.isEmpty()){
//            model.put("message","null");
//            return "adduser";
//        }

        Iterable<User> roles = rolesRepo.findAll();

        Integer count = 0;
        for (Iterator<User> iter = roles.iterator(); iter.hasNext(); ){
            User temp_user = iter.next();
            String temp_position = temp_user.getPosition();
            if(temp_position.equals(position)){
                count = temp_user.getCount();
                break;
            }
        }

        DeleteRowDB del = new DeleteRowDB(position,count);

        User new_user = new User(position,count);
        rolesRepo.save(new_user);


        roles = rolesRepo.findAll();
        model.put("roles",roles);

        return "adduser";
    }
}
