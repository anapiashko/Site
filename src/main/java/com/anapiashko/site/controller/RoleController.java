package com.anapiashko.site.controller;

import com.anapiashko.site.domain.Role;
import com.anapiashko.site.domain.Views;
import com.anapiashko.site.repos.RoleRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepo roleRepo;

    @Autowired
    public RoleController(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @GetMapping
    @JsonView(Views.FullRole.class)
    public Iterable<Role> list() {
        return roleRepo.findAll();
    }

    @GetMapping("/{id}")
    @JsonView(Views.FullRole.class)
    public Role getOne(@PathVariable("id") Role role){
        return role;
    }

    @PostMapping
    public Role create(@RequestBody Role role){
        return roleRepo.save(role);
    }
    @PutMapping("/{id}")
    public Role update(@PathVariable("id")Role roleFromDb,
                          @RequestBody Role role) {
        BeanUtils.copyProperties(role,roleFromDb,"id");
        return roleRepo.save(roleFromDb);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Role role) {
        roleRepo.delete(role);
    }
}
