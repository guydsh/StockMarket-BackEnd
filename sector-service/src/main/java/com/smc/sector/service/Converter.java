package com.smc.sector.service;

@FunctionalInterface
public interface Converter<S, T> {
    T convert(S source);
}
