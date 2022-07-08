package test.bean;

import core.result.Result;

public class ResultApi1 extends Result{

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResultApi1() {
    }

    public ResultApi1(Result rs) {
        super(rs);
    }
}
