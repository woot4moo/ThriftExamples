package com.travis;

import com.woot4moo.Command;
import com.woot4moo.Job;
import com.woot4moo.Status;
import org.apache.thrift.TException;

import java.util.HashSet;
import java.util.Set;

/**
 * responsible for handling messages
 */
public class MessageHandler implements  com.woot4moo.MessageService.Iface {

    private  Set<com.woot4moo.Job> jobs;
    private boolean initialized = false;
    private void init(){
        jobs = new HashSet<>();
        jobs.add(new com.woot4moo.Job("First", false));
        jobs.add(new com.woot4moo.Job("Second", false));
        initialized=true;
    }


    @Override
    public com.woot4moo.Status processMessage(com.woot4moo.Message message) throws TException {
        if(!initialized){
            init();
        }
        Job job = message.getJob();
        System.out.println("Server side job: "  + job);
        if (Command.START == message.getCommand()) {
            for (Job currentJob : jobs) {

                if (currentJob.getName().equals(job.getName())) {
                    currentJob.setIsRunning(true);
                    return Status.SUCCESS;
                }
            }
        } else if (Command.STOP == message.getCommand()) {
            for (Job currentJob : jobs) {
                if (currentJob.getName().equals(job.getName())) {
                    currentJob.setIsRunning(false);
                    return Status.SUCCESS;
                }
            }
        }
        return Status.FAILURE;
    }

    @Override
    public Set<com.woot4moo.Job> getAllJobs() throws TException {
        if(!initialized){
            init();
        }
        System.out.println("Server side Reported jobs: " + jobs);
        return jobs;
    }
}
