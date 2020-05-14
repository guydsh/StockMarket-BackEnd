package com.smc.se.service;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S source);
}
