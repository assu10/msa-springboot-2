package com.assu.study.chap03.service;

import java.io.IOException;
import java.io.OutputStream;

public interface Printer<T> {
    void print(OutputStream out, T t) throws IOException;
}
