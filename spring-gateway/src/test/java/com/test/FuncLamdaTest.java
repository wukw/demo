package com.test;

import java.util.function.Function;

public class FuncLamdaTest {
    public static void main(String[] args) {
        Function<Integer,Integer> f = i->i+1;
        System.out.println(f.apply(5));
    }
}
