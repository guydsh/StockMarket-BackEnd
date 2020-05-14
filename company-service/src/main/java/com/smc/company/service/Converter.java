package com.smc.company.service;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S source);
}
