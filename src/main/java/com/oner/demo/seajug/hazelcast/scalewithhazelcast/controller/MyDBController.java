package com.oner.demo.seajug.hazelcast.scalewithhazelcast.controller;


import com.oner.demo.seajug.hazelcast.scalewithhazelcast.model.MyPojo;
import com.oner.demo.seajug.hazelcast.scalewithhazelcast.services.MyPojoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/db")
public class MyDBController {

    @Autowired
    MyPojoRepo myPojoRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    MyPojo getSingle(@PathVariable long id) {
        MyPojo one = myPojoRepo.findOne(id);
        if (one == null) {
            one = create();
        }
        return one;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/create")
    MyPojo create() {
        MyPojo myPojo = new MyPojo();
        myPojo.setName(UUID.randomUUID().toString());
        myPojo.setAge(UUID.randomUUID().hashCode() % 60);
        return myPojoRepo.save(myPojo);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/all", "/"})
    List<MyPojo> getAll() {
        return myPojoRepo.findAll();
    }
}
