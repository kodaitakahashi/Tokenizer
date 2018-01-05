package com.github.kodaitakahashi.application.jumanpp;

import com.github.kodaitakahashi.common.Context;
import com.github.kodaitakahashi.common.Tokenizer;
import com.github.kodaitakahashi.dto.JumanppResult;
import com.github.kodaitakahashi.util.TextUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JumanppTokenizer implements Tokenizer {

    private String pathShell;
    private String pathJumanpp;

    private final int ORIGIN_INDEX = 0;
    private final int READ_INDEX = 1;
    private final String END_STR = "EOS";
    private final String OPTION = "@";

    public JumanppTokenizer(String pathShell, String pathJumanpp) {
        this.pathShell = pathShell;
        this.pathJumanpp = pathJumanpp;
    }

    /**
     * 与えた文字列をJumman++で解析する
     * @param sentence 解析を対象とする文字列
     * @return 解析した結果
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public List<JumanppResult> tokenizer(String sentence) throws IOException, InterruptedException {

        if (StringUtils.isBlank(sentence)) {
            throw new IllegalArgumentException();
        }

        String cmd = "echo \"" + sentence + "\" | " + pathJumanpp;
        String[] execCmd = {pathShell, "-c", cmd};
        Process proc = Runtime.getRuntime().exec(execCmd);
        int response = proc.waitFor();
        if (response == Context.COMMAND_STATUS.SUCCESS.getResponce()){
            return parse(TextUtil.toStirngList(proc));
        }

        return null;
    }

    private List<JumanppResult> parse(List<String> tokenizer) {
        List<JumanppResult> result = new ArrayList<>();
        for (String t : tokenizer) {
            String[] split = t.split(" ");
            if(END_STR.equals(split[ORIGIN_INDEX])) {
                continue;
            }
            JumanppResult jumanppResult = new JumanppResult();
            if(OPTION.equals(split[ORIGIN_INDEX])) {
                jumanppResult.setOption(true);
                jumanppResult.setOrigin(split[ORIGIN_INDEX] + 1);
                jumanppResult.setRead(split[READ_INDEX] + 1);
            }
            jumanppResult.setOrigin(split[ORIGIN_INDEX]);
            jumanppResult.setRead(split[READ_INDEX]);
            result.add(jumanppResult);
        }
        return result;
    }
}
