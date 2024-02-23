/* COMPSCI 424 Program 1
 * Name: Derya Fathroen
 */
package compsci424.p1.java;

import java.util.LinkedList;
import java.util.List;

/**
 * The process control block structure that is used to track a
 * process's parent and children (if any) in Version 1.
 */
public class Version1PCB {
    int pid; // process ID
    int parentPid; // parent process ID
    List<Integer> children; // list of children process IDs

    public Version1PCB(int pid, int parentPid) {
        this.pid = pid;
        this.parentPid = parentPid;
        this.children = new LinkedList<>();
    }

    // Additional methods or modifications can be added as needed.
}
