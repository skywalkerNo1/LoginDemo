package demo.controller;

import demo.service.AsyncSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Api(tags = "demo")
@RestController
@RequestMapping("/test")
public class demoController extends BaseController {

    @Autowired
    private AsyncSerivce asyncSerivce;

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public Map<String, Object> test() {
        asyncSerivce.getReadTest();
        asyncSerivce.getWriteTest();
        return successResult("test success!");
    }
}
