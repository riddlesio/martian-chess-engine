package io.riddles.martianchess.model;

/**
 * Created by joost on 4/18/17.
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