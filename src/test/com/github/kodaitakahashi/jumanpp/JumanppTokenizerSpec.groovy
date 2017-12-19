package com.github.kodaitakahashi.jumanpp

import com.github.kodaitakahashi.application.jumanpp.JumanppTokenizer
import spock.lang.Specification

class JumanppTokenizerSpec extends Specification{

    def sut

    def setup() {
        sut = new JumanppTokenizer("/bin/sh", "/usr/local/bin/jumanpp")
    }

    def "文字を入力すると結果が返ってくる"() {

        when:
        def actual = sut.tokenizer("小麦粉を300グラム入れます")

        then:
        for (int i = 0; i < actual.size(); i++) {
            println(actual.get(i).getOrigin())
        }
        actual.size() != 0
        actual.size() == 6
    }

    def "指定の文字を入力するとoriginとreadに値が入ること"() {

        when:
        def actual = sut.tokenizer("小麦粉")

        then:
        actual.size() == 1
        actual.get(0).getOrigin() == "小麦粉"
        actual.get(0).getRead() == "こむぎこ"
    }

    def "空文字を渡すと例外が発生する"() {
        when:
        sut.tokenizer("")

        then:
        thrown(IllegalArgumentException)
    }

    def "nullを渡すと例外が発生する"() {
        when:
        sut.tokenizer(null)

        then:
        thrown(IllegalArgumentException)
    }
}
