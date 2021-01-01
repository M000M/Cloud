package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entites.CommonResult;
import com.atguigu.springcloud.entites.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @RequestMapping(value = "/payment/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果: " + result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功", result);
        } else {
            return new CommonResult(404, "插入数据库失败", result);
        }
    }

    @RequestMapping(value = "/payment/get/{id}", method = RequestMethod.GET)
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果: " + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功，哈哈", payment);
        } else {
            return new CommonResult(404, "没有对应记录", payment);
        }
    }
}
