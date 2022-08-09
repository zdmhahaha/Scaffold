package com.smartj.web.controller;

import com.smartj.web.common.result.ResultAble;
import com.smartj.web.common.result.ResultBody;
import com.smartj.web.request.PersonParam;
import com.smartj.web.vo.Person;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("main")
public class MainController {

    @GetMapping("/getPerson")
    @ResultBody
    @ResultAble
    public Person getPerson() {
        Person person = new Person();
        person.setAge(10);
        person.setName("张三");
        person.setMale(false);
        return person;
    }


    @PostMapping("/getPerson2")
    @ResultBody
    public Person getPerson2(@Validated @RequestBody PersonParam person) throws Exception {
        Person p = new Person();
        p.setName(person.getName());
        p.setAge(person.getAge());
        p.setMale(person.isMale());
        return p;
    }


    @GetMapping("getData")
    @ResultBody
    public String getData() {
        return null;
    }


    public static void main(String[] args) {
        System.out.println(MainController.class.isAnnotationPresent(Deprecated.class));
    }

}
