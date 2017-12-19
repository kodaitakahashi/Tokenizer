package com.github.kodaitakahashi.dto;

import com.github.kodaitakahashi.common.Result;

import java.util.List;

public class JumanppResult extends Result{
    private boolean isOption;

    public JumanppResult(){
        isOption = false;
    }

    public boolean isOption() {
        return isOption;
    }

    public void setOption(boolean option) {
        isOption = option;
    }
}
