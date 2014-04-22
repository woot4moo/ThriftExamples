package com.travis;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * Created by Travis on 4/6/2014.
 */
public class MultiplicationClient {
    public static void main(String [] args) {


        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            com.travis.MultiplicationService.Client client = new com.travis.MultiplicationService.Client(protocol);

            perform(client);

            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    private static void perform(com.travis.MultiplicationService.Client client) throws TException
    {

        int product = client.multiply(3,5);
        System.out.println("3*5=" + product);
    }
}
