package com.travis;

import com.woot4moo.MessageService;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * equivalent to the unix service.
 */
public class MessageServer {
    public static MessageHandler handler;

    public static com.woot4moo.MessageService.Processor processor;

    public static void main(String [] args) {
        try {
            handler = new MessageHandler();
            processor = new com.woot4moo.MessageService.Processor(handler);

            Runnable simple = new Runnable() {
                public void run() {
                    simple(processor);
                }
            };

            new Thread(simple).start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public static void simple(MessageService.Processor processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(9090);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

            System.out.println("Starting the simple message server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
