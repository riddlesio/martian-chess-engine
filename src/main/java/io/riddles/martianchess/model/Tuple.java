package io.riddles.martianchess.model;

/**
 * ${PACKAGE_NAME}
 *
 * This file is a part of martianchess
 *
 * Copyright 2016 - present Riddles.io
 * For license information see the LICENSE file in the project root
 *
 * @author Niko
 */
public class Tuple<A, B> {

    private A fst;
    private B snd;

    public Tuple(A fst, B snd) {
        this.fst = fst;
        this.snd = snd;
    }

    A getFirst() {
        return fst;
    }

    B getSecond() {
        return snd;
    }
}