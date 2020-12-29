package edu.princeton.cs.algs4.fundamentals.set;

public class SETImplTest extends SETBaseTest{
    @Override
    protected SET<String> createSET() {
        return SETImpl.create();
    }
}