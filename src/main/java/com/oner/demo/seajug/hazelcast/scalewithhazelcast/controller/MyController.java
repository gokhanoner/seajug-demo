package com.oner.demo.seajug.hazelcast.scalewithhazelcast.controller;


import com.oner.demo.seajug.hazelcast.scalewithhazelcast.model.MyResult;
import com.oner.demo.seajug.hazelcast.scalewithhazelcast.services.MyComputationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/rest")
public class MyController {

    @Autowired
    MyComputationService computationService;

    @RequestMapping(method = RequestMethod.GET, path = "/fib/{input}")
    MyResult fibonacci(@PathVariable long input) {
        long start = System.currentTimeMillis();
        long result = computationService.fib(input);
        long end = System.currentTimeMillis();

        return MyResult.of(input, result, end - start);
    }
}
