package com.github.kodaitakahashi.common;

public class Context {

    public enum COMMAND_STATUS{
        SUCCESS(0);


        private int responce;

        COMMAND_STATUS(int responce) {
            responce = responce;
        }

        public int getResponce() {
            return responce;
        }
    }
}
