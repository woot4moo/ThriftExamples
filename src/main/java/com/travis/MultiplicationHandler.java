package com.travis;

import org.apache.thrift.TException;

/**
 * Created by Travis on 4/6/2014.
 */
public class MultiplicationHandler implements com.travis.MultiplicationService.Iface {
    @Override
    public int multiply(int n1, int n2) throws TException {
        System.out.println("Multiply(" + n1 + "," + n2 + ")");
        return n1*n2;
    }
}
