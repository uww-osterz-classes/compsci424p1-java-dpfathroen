/* COMPSCI 424 Program 1
 * Name: Derya Fathroen
 */
package compsci424.p1.java;

/** 
 * Implements the process creation hierarchy for Version 2, which does
 * not use linked lists.
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
public class Version2 {
    // Declare any class/instance variables that you need here.
    private Version2PCB[] pcbArray = new Version2PCB[16];

    /**
     * Default constructor. Use this to allocate (if needed) and
     * initialize the PCB array, create the PCB for process 0, and do
     * any other initialization that is needed. 
     */
    public Version2() {
        pcbArray[0] = new Version2PCB(0, -1);
    }
    
    /**
     * Creates a new child process of the process with ID parentPid. 
     * @param parentPid the PID of the new process's parent
     * @return 0 if successful, not 0 if unsuccessful
     */
    public int create(int parentPid) {
        // If parentPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for parentPid in the PCB array:
        // 1. Allocate and initialize a free PCB object from the array
        //    of PCB objects

        // 2. Connect the new PCB object to its parent, its older
        //    sibling (if any), and its younger sibling (if any)

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc. return 0; often means "success" or "terminated normally"

        if (parentPid < 0 || parentPid >= pcbArray.length || pcbArray[parentPid] == null) {
            return -1; // Error: parentPid not in the process hierarchy
        }

        for (int i = 1; i < pcbArray.length; i++) {
            if (pcbArray[i] == null) { // free PCB slot
                pcbArray[i] = new Version2PCB(i, parentPid);
                // step 2
                if (pcbArray[parentPid].firstChild == null) {
                    pcbArray[parentPid].firstChild = i; // process becomes first child
                } 
                else {
                    int sibling = pcbArray[parentPid].firstChild;
                    while (pcbArray[sibling].youngerSibling != null) {
                        sibling = pcbArray[sibling].youngerSibling;
                    }
                    pcbArray[sibling].youngerSibling = i; // Set as younger sibling
                    pcbArray[i].olderSibling = sibling; // Link back to older sibling
                }
                return 0; // Success
            }
        }
        return -1; // No free PCB slots available
    }

    /**
     * Recursively destroys the process with ID parentPid and all of
     * its descendant processes (child, grandchild, etc.).
     * @param targetPid the PID of the process to be destroyed
     * @return 0 if successful, not 0 if unsuccessful
     */
    
    public int destroy (int targetPid) {
        // If targetPid is not in the process hierarchy, do nothing; 
        // your code may return an error code or message in this case,
        // but it should not halt

        // Assuming you've found the PCB for targetPid in the PCB array:
        // 1. Recursively destroy all descendants of targetPid, if it
        //    has any, and mark their PCBs as "free" in the PCB array 
        //    (i.e., deallocate them)

        // 2. Adjust connections within the hierarchy graph as needed to
        //    re-connect the graph

        // 3. Deallocate targetPid's PCB and mark its PCB array entry
        //    as "free"

        // You can decide what the return value(s), if any, should be.
        // If you change the return type/value(s), update the Javadoc. return 0; often means "success" or "terminated normally"

        //step 1
        if (targetPid < 0 || targetPid >= pcbArray.length || pcbArray[targetPid] == null) {
            return -1; // // process not exist
        }

        Integer childPid = pcbArray[targetPid].firstChild;
        while (childPid != null) {
            Integer nextChild = pcbArray[childPid].youngerSibling;
            destroy(childPid); // recursively destroy descendants
            childPid = nextChild;
        }

        // step 2
        if (pcbArray[targetPid].olderSibling != null) {
            pcbArray[pcbArray[targetPid].olderSibling].youngerSibling = pcbArray[targetPid].youngerSibling;
        }
        if (pcbArray[targetPid].youngerSibling != null) {
            pcbArray[pcbArray[targetPid].youngerSibling].olderSibling = pcbArray[targetPid].olderSibling;
        }
        if (pcbArray[targetPid].parentPid != -1 && pcbArray[pcbArray[targetPid].parentPid].firstChild == targetPid) {
            pcbArray[pcbArray[targetPid].parentPid].firstChild = pcbArray[targetPid].youngerSibling;
        }

        // step 3
        pcbArray[targetPid] = null;
        return 0; // works
   }

   /**
    * Traverse the process creation hierarchy graph, printing
    * information about each process as you go. See Canvas for the
    * *required* output format. 
    *         
    * You can directly use "System.out.println" statements (or
    * similar) to send the required output to stdout, or you can
    * change the return type of this function to return the text to
    * the main program for printing. It's your choice. 
    */
    public void showProcessInfo() {
        for (int i = 0; i < pcbArray.length; i++) {
            if (pcbArray[i] != null) {
                System.out.print("Process " + i + ": parent is " + pcbArray[i].parentPid);
                if (pcbArray[i].firstChild != null) {
                    System.out.print(", first child is " + pcbArray[i].firstChild);
                }
                if (pcbArray[i].youngerSibling != null) {
                    System.out.print(", younger sibling is " + pcbArray[i].youngerSibling);
                }
                if (pcbArray[i].olderSibling != null) {
                    System.out.print(", older sibling is " + pcbArray[i].olderSibling);
                }
                System.out.println();
            }
        }
    }

   /* If you need or want more methods, feel free to add them. */

}
