package operatingsystemproject;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int option = 0;
        Process op = new Process();
        op.readFile();
        Scanner a = new Scanner(System.in);
        System.out.println("-----------------------------");
        System.out.println("CPU Scheduling Algorithms: ");
        System.out.println("-----------------------------");
        System.out.println("Option 1.FCFS (First Come First Served)");
        System.out.println("Option 2. Shortest Job First");
        System.out.println("Option 3. Round Robin");
        System.out.println("-----------------------------");
        System.out.println("Select > ");
        option = Integer.parseInt(a.nextLine());
        System.out.println("-----------------------------");
        System.out.print("\n");


        op.Getprocess(option);
        int OTime = op.GOT(option);

        if (option == 1)
        {
            op.FCFS(OTime);
        }
        else if (option == 2)
        {
            op.shortest(OTime);
        }
        else if (option == 3)
        {
            op.Round(OTime);
        }
        else if (option == 4)
        {
            System.out.println("FOURTH");
        }
    }

}

