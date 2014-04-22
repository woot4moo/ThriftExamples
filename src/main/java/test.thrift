namespace java com.travis
namespace py com.travis

typedef i32 int // We can use typedef to get pretty names for the types we are using
service MultiplicationService
{
        int multiply(1:int n1, 2:int n2),
}

/**
* The commands that a Job knows about
**/
enum Command{
    /** Start the job */
    START,
    /** Stop the job */
    STOP
}

/**
* Represents a job that can be run
**/
struct Job{
    /** the name of this job*/
    1: required string name;
    /** is the job currently running */
    2: required bool isRunning = false;
}
/**
* A message sent by the server to the client.  This should
* be processed per the client specification
**/
struct Message{
    1: required Job job;
    2: required Command command;
}

/**
* Provides a list of acceptable values for the result of an action
**/
enum Status{
    /** this action resulted in failure */
    FAILURE,
    /** this action resulted in success */
    SUCCESS
}
/**
* Handles incoming messages
**/
service MessageService{
    /**
    *  Attempts to process a message
    **/
    Status processMessage(1: Message message);
    /**
    * Returns a set of all known jobs
    **/
    set<Job> getAllJobs();
}