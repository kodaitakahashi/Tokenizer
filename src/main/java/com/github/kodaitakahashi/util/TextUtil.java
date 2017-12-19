package com.github.kodaitakahashi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public final class TextUtil {

    public  static List<String> toStirngList(Process proc) {
        try(BufferedReader render = new BufferedReader(new InputStreamReader(proc.getInputStream()))) {
            return render.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
