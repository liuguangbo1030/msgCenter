package com.msg.mc.web;

import com.msg.mc.kafka.MsgProducer;
import com.msg.mc.util.HttpResultUtil;
import com.msg.mc.service.ISendService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletRequest;

/**
 * @author cloudy
 * @version 1.0
 * @company
 * @date 2016/8/29.
 */
@Controller
@RequestMapping("/send")
public class SendController {

    private Logger logger = LoggerFactory.getLogger(SendController.class);
    @Autowired
    private ISendService sendService;

    @Autowired
    private MsgProducer msgProducer;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public String sendMessage(HttpServletRequest req) {
        return HttpResultUtil.successResult();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest req) {
        return HttpResultUtil.successResult();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        msgProducer.sendMessage();
        model.addAttribute("name", "zhangsan");
        model.addAttribute("content", "freemarker测试！");
        model.addAttribute("date", "2017-06-03 12:11:00");
        return "email";

        //return HttpRequestUtil.doGet("http://127.0.0.1:9010/send/index").toJSONString();
    }

}
