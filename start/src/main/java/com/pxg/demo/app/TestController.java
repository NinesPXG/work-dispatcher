package com.pxg.demo.app;

import com.alibaba.cola.dto.SingleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("test")
public class TestController {

    private final TestContractor testContractor;

    @GetMapping("hello")
    public SingleResponse<String> hello(HttpServletRequest request) {
        testContractor.execute();
        return SingleResponse.of("hello");
    }

}
