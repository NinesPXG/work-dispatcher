package com.pxg.demo.adapter;

import com.alibaba.cola.dto.SingleResponse;
import com.pxg.demo.application.TestContractor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class TestController {

    private final TestContractor testContractor;

    @GetMapping("hello")
    public SingleResponse<String> hello(@RequestParam Integer n) {
        testContractor.execute1(n);
        return SingleResponse.of("hello");
    }

    @GetMapping("hi")
    public SingleResponse<String> hi(@RequestParam String s) {
        testContractor.execute2(s);
        return SingleResponse.of("hi");
    }

    @GetMapping("no")
    public SingleResponse<String> no() {
        testContractor.failSecure();
        return SingleResponse.of("no");
    }

}
