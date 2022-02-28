package cn.microboat.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouwei
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/getTest")
    public JSONObject aopTest() {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }

    @PostMapping("/postTest")
    public JSONObject aopTest2(@RequestParam("id") String id) {
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}
