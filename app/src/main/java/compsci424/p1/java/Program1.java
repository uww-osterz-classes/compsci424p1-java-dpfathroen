/* COMPSCI 424 Program 1
 * Name: Derya Fathroen
 * 
 * This is a template. Program1.java *must* contain the main class
 * for this program. Otherwise, feel free to edit these files, even
 * these pre-written comments or my provided code. You can add any
 * classes, methods, and data structures that you need to solve the
 * problem and display your solution in the correct format.
 */
package compsci424.p1.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main class for this program. The required steps have been copied
 * into the main method as comments. Feel free to add more comments to
 * help you understand your code, or for any other reason. Also feel
 * free to edit this comment to be more helpful for you.
 */
public class Program1 {
    // Declare any class/instance variables that you need here.

    /**
     * @param args command-line arguments, which can be ignored
     */
    public static void main(String[] args) {

        // 1. Ask the user to enter commands of the form "create N",
        //    "destroy N", or "end", where N is an integer between 0 
        //    and 15.

        Scanner scanner = new Scanner(System.in);
        List<String[]> commands = new ArrayList<>();
        System.out.println("Enter commands ('create N', 'destroy N', or 'end'), where N is an integer between 0 and 15:");

        // 2. While the user has not typed "end", continue accepting
        //    commands. Add each command to a list of actions to take 
        //    while you run the simulation.
        // 3. When the user types "end" (or optionally any word that 
        //    is not "create" or "destroy"), stop accepting commands 
        //    and complete the following steps.
        //
        // Hint: Steps 2 and 3 refer to the same loop. ;-)

        String input;
        while (!(input = scanner.nextLine()).equalsIgnoreCase("end")) {
            // Split the input into action and pid, validating against "create" and "destroy" actions
            String[] parts = input.trim().split("\\s+");
            if (parts.length == 2 && ("create".equalsIgnoreCase(parts[0]) || "destroy".equalsIgnoreCase(parts[0]))) {
                try {
                    int pid = Integer.parseInt(parts[1]);
                    if (pid >= 0 && pid <= 15) { 
                        commands.add(parts); // Add valid command
                    } 
                    else {
                        System.out.println("PID must be between 0 and 15. Please try again.");
                    }
                } 
                catch (NumberFormatException e) {
                    System.out.println("Invalid PID. Please enter a valid number between 0 and 15.");
                }
            } 
            else {
                System.out.println("Invalid command. Please enter 'create N', 'destroy N', or 'end'.");
            }
        }

        // 4. Create an object of the Version 1 class and an object of
        //    the Version 2 class.

        Version1 version1 = new Version1();
        Version2 version2 = new Version2();

        // 5. Run the command sequence once with the Version 1 object, 
        //    calling its showProcessTree method after each command to
        //    show the changes in the tree after each command.
        
        for (String[] command : commands) {
            int pid = Integer.parseInt(command[1]);
            if ("create".equalsIgnoreCase(command[0])) {
                version1.create(pid);
            } 
            else if ("destroy".equalsIgnoreCase(command[0])) {
                version1.destroy(pid);
            }
            version1.showProcessInfo();  // process info after each command
        }

        // 6. Repeat step 5, but with the Version 2 object.
        System.out.println();
        for (String[] command : commands) {
            int pid = Integer.parseInt(command[1]);
            if ("create".equalsIgnoreCase(command[0])) {
                version2.create(pid);
            } 
            else if ("destroy".equalsIgnoreCase(command[0])) {
                version2.destroy(pid);
            }
            version2.showProcessInfo();  // process info after each command
        }

        // 7. Store the current system time in a variable
        long startTimeV1 = System.currentTimeMillis();

        // ... then run the command sequence 200 times with Version 1.

        for (int i = 0; i < 200; i++) {
            version1 = new Version1(); // reinitialize
            for (String[] command : commands) {
                int pid = Integer.parseInt(command[1]);
                if ("create".equalsIgnoreCase(command[0])) {
                    version1.create(pid);
                } 
                else if ("destroy".equalsIgnoreCase(command[0])) {
                    version1.destroy(pid);
                }
            }
        }

        // ... After this, store the new current system time in a
        //     second variable. Subtract the start time from the end 
        //     time to get the Version 1 running time, then display 
        //     the Version 1 running time.

        long endTimeV1 = System.currentTimeMillis();
        System.out.println();
        System.out.println("Version 1 running time after 200 iterations: " + (endTimeV1 - startTimeV1) + " ms");

        // 8. Repeat step 7, but with the Version 2 object.

        long startTimeV2 = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            version2 = new Version2(); // reinitialize
            for (String[] command : commands) {
                int pid = Integer.parseInt(command[1]);
                if ("create".equalsIgnoreCase(command[0])) {
                    version2.create(pid);
                } 
                else if ("destroy".equalsIgnoreCase(command[0])) {
                    version2.destroy(pid);
                }
            }
        }
        long endTimeV2 = System.currentTimeMillis();
        System.out.println("Version 2 running time after 200 iterations: " + (endTimeV2 - startTimeV2) + " ms");
        scanner.close();
    }
}
