package com.github.kodaitakahashi.common;

import java.io.IOException;
import java.util.List;

public interface Tokenizer {

     List<? extends Result> tokenizer(String sentence) throws IOException, InterruptedException;


}
