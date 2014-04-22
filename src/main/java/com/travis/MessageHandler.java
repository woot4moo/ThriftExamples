package com.travis;

import org.apache.thrift.TException;

import java.util.Set;

/**
 * responsible for handling messages
 */
public class MessageHandler implements  com.travis.MessageService.Iface {
    @Override
    public com.travis.Status processMessage(com.travis.Message message) throws TException {
        return null;
    }

    @Override
    public Set<com.travis.Job> getAllJobs() throws TException {
        return null;
    }
}
