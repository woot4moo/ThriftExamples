package com.travis;

import com.woot4moo.Command;
import com.woot4moo.Job;
import com.woot4moo.Message;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Collection;

/**
 * Equivalent to QServer for discussion purposes (tomcat,jboss,etc)
 */
public class MessageClient {

    public static void main(String [] args) {


        try {
            TTransport transport;

            transport = new TSocket("localhost", 9090);
            transport.open();

            TProtocol protocol = new TBinaryProtocol(transport);
            com.woot4moo.MessageService.Client client = new com.woot4moo.MessageService.Client(protocol);
            Message message = new Message(new Job("First",false), Command.START);
            System.out.println("Client side: " + getAllJobs(client));
            System.out.println("Sending message: " + message);
            processMessage(message,client);
            System.out.println("New Client side: " + getAllJobs(client));
            transport.close();
        } catch (TException x) {
            x.printStackTrace();
        }
    }

    public static void processMessage(com.woot4moo.Message message,com.woot4moo.MessageService.Client client) throws TException{
        client.processMessage(message);
    }
    public static Collection<com.woot4moo.Job> getAllJobs(com.woot4moo.MessageService.Client client) throws TException{
        return client.getAllJobs();
    }
}
