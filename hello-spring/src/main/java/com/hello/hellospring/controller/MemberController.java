package com.hello.hellospring.controller;

import com.hello.hellospring.service.MemberServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberServive memberServive;

    @Autowired
    public MemberController(MemberServive memberServive) {
        this.memberServive = memberServive;
    }
}
