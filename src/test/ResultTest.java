package test;

import core.log.Logger;
import core.module.ModuleFactory;
import core.result.Result;
import core.util.JsonUtil;
import test.bean.ResultApi1;

public class ResultTest {
    private final JsonUtil jsonUtil = ModuleFactory.getFactory().jsonUtil();
    private final Logger logger = ModuleFactory.getFactory().loggerFactory().getLogger(this.getClass());

    public static void main(String[] args) {
        ResultTest resultTest = new ResultTest();
        resultTest.test();
    }

    public void test() {
        Result result = new Result(true, "R0000", "R0000");

        //check message config in Language file
        //2022/07/08 15:31:55.362LoggerImpl.print:test.ResultTest.test:24:
        //[Result prRs1:={"success":false,"key":"R0002","message":"[R0002] Success {0}/{1}","map":{},"stacks":["test.ResultTest.test:22","test.ResultTest.test:23"]}]|
        result = new Result(false, "R0002", "R0002");
        Result prRs1 = new Result("en", result);
        logger.infos("Result prRs1: ", jsonUtil.to(prRs1));

        //check argument in message content
        //2022/07/08 15:31:55.372LoggerImpl.print:test.ResultTest.test:29:
        //[Result prRs2:={"success":false,"key":"R0002","message":"[R0002] Success param_1/param_2","arguments":["param_1","param_2"],"map":{},"stacks":["test.ResultTest.test:27","test.ResultTest.test:28"]}]|
        result = new Result(false, "R0002", "R0002", "param_1", "param_2");
        Result prRs2 = new Result("en", result);
        logger.infos("Result prRs2: ", jsonUtil.to(prRs2));

        //response object in last layer
        //2022/07/08 15:31:55.377LoggerImpl.print:test.ResultTest.test:35:
        //[TestResponse res:={"name":"responseLastLayer","success":false,"key":"R0002","message":"[R0002] Éxito param_1/param_2","arguments":["param_1","param_2"],"map":{},"stacks":["test.ResultTest.test:27","test.bean.ResultApi1.\u003cinit\u003e:21"]}]|
        result.put("className", "responseLastLayer");
        ResultApi1 res = new ResultApi1(result);
        res.setName((String) result.get("className"));
        logger.infos("TestResponse res: ", jsonUtil.to(res));
    }
}
