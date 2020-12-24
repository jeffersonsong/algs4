package edu.princeton.cs.algs4.fundamentals.stack;

import edu.princeton.cs.algs4.utils.io.StdIn;
import edu.princeton.cs.algs4.utils.io.StdOut;

public class Evaluate {
    public static void main(final String[] args) {
        final Stack<String> ops = new LinkedStack<>();
        final Stack<Double> vals = new LinkedStack<>();

        while (!StdIn.isEmpty()) {
            final String s = StdIn.readString();
            if (s.equals("(")) {
            } else if (s.equals("+")) {
                ops.push(s);
            } else if (s.equals("*")) {
                ops.push(s);
            } else if (s.equals(")")) {
                final String op = ops.pop();
                if (op.equals("+")) {
                    vals.push(vals.pop() + vals.pop());
                } else if (op.equals("*")) {
                    vals.push(vals.pop() * vals.pop());
                }
            } else {
                vals.push(Double.parseDouble(s));
            }
        }

        StdOut.println(vals.pop());
    }
}
