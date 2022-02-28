package cn.microboat.controller;

import cn.microboat.annotation.PermissionAnnotation;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouwei
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @PostMapping("/check")
    @PermissionAnnotation
    public JSONObject getGroupList(@RequestBody JSONObject request) {
        int i = 10/0;
        return JSON.parseObject("{\"message\":\"SUCCESS\",\"code\":200,\"data\":" + request + "}");
    }
}
