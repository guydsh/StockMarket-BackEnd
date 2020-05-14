package com.smc.sp.service;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S source);
}
