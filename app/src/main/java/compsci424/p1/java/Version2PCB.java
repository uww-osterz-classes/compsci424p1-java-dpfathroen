/* COMPSCI 424 Program 1
 * Name: Derya Fathroen
 */
package compsci424.p1.java;

/**
 * The process control block structure that is used to track a
 * process's parent, first child, younger sibling, and older sibling
 * (if they exist) in Version 2.
 */
public class Version2PCB {
    int pid; // process ID
    int parentPid; // parent Process ID
    Integer firstChild; // PID of first child process
    Integer youngerSibling; // PID of younger sibling process
    Integer olderSibling; // PID of older sibling process

    public Version2PCB(int pid, int parentPid) {
        this.pid = pid;
        this.parentPid = parentPid;
        this.firstChild = null; 
        this.youngerSibling = null; 
        this.olderSibling = null; 
    }

    // Additional methods or modifications can be added as needed.
}