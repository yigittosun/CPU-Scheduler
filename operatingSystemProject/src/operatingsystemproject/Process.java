package operatingsystemproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Process {

    public int NP = 3;
    public int TQ;
    public int[] Period = new int[100];
    public int[] RT = new int[100];
    public int[] DL = new int[100];
    ArrayList<Integer> BTime = new ArrayList<Integer>();
    public int[] WTime = new int[100];
    public int[] CTime = new int[100];
    public int[] ATime = new int[100];
    Scanner a = new Scanner(System.in);
    ArrayList<Transaction> processOB = new ArrayList<>();
    SpecialChoose customComparator = new SpecialChoose();

    ArrayList<String> PN = new ArrayList<String>();
    ArrayList<Integer> Temp = new ArrayList<Integer>();
    ArrayList<String> PN2 = new ArrayList<String>();

    public void readFile() throws FileNotFoundException {
        File file =  new File("C:\\Users\\Pc\\Desktop\\osproject\\test1.txt");
        Scanner sc = new Scanner(file);
        int checker = 0;
        while (sc.hasNextLine()){
            if(checker%2==0){
                PN.add(sc.nextLine());
            }
            else {
                BTime.add(Integer.parseInt(sc.nextLine()));
            }
           checker++;
        }
//        processOB.forEach(process->{
//            System.out.println(process.getValue());
//        });
//        for(int i=0;i<islemOb.size();i++){
//            System.out.println(processOB.get(i).getName() + ": " + processOB.get(i).getValue());
//        }
//        for(processing process: processOB){
//            System.out.println(process + ": " + process.getValue());
//        }
//        processOB.forEach( process -> {
//            System.out.println(process + ": " + process.getValue());
//        });
//       System.out.println(processOB.get(0).getName() + ": " + processOB.get(0).getValue() );


        for(int r = 0; r<PN.size();r++){
            System.out.println(PN.get(r));
        }
        for (int t = 0; t<BTime.size();t++){
            System.out.println(BTime.get(t));
        }
    }
    public void Getprocess(int selected_algorithm)
    {
        NP = PN.size();

        if (selected_algorithm == 3)
        {
            System.out.println("Enter Time Quantum: ");
            TQ = Integer.parseInt(a.nextLine());
            if (TQ < 1)
            {
                System.out.print("Invalid Input: ");
                System.out.print("\n");
                System.exit(0);
            }
        }
        for (int i = 0; i < NP; i++)
        {
            System.out.print("\n");
            System.out.print("Process ");
            System.out.print(PN.get(i));
            System.out.print(":");
            System.out.print("\n");

            System.out.print("Burst Time: "+BTime.get(i));
             if (selected_algorithm == 3)
            {

                ATime[i] = 0;
                System.out.print("Burst Time: ");
                RT[i] = BTime.get(i);
            }
        }
    }

    public int max(int a,int b, int c)
    {
        int max;
        int lcom;
        int count;
        int flag = 0;
        if (a >= b && a >= c)
        {
            return max = a;
        }
        else if (b >= a && b >= c)
        {
            return max = b;
        }
        else if (c >= a && c >= b)
        {
            return max = c;
        }
        else return 0;
    }
    public int GOT(int selected_algorithm)
    {

            int sum = 0;
            for (int i = 0; i < NP; i++)
            {
                sum += BTime.get(i);
            }
            return sum;

    }

    public void takyazdır(int[] process_list, int cycles,int SelectedAlgorithm)
    {
        System.out.print("\n");
        System.out.print("Scheduling:");
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Time: ");
        for (int i = 1; i <= cycles; i++)
        {
            if (i < 10)
            {
                System.out.print("| 0");
                System.out.print(i);
                System.out.print(" ");
            }
            else
            {
                System.out.print("| ");
                System.out.print(i);
                System.out.print(" ");
            }
        }
        System.out.print("|");
        System.out.print("\n");

        for (int i = 0; i < NP; i++)
        {
            if(SelectedAlgorithm==1 || SelectedAlgorithm==3){
                System.out.print(PN.get(i));
            }
            else if(SelectedAlgorithm==2){
                System.out.print(PN2.get(i));
            }

            for (int j = 0; j < cycles; j++)
            {
                if (process_list[j] == i + 1)
                {
                    System.out.print("|xxxx");
                }
                else
                {
                    System.out.print("|    ");
                }
            }
            System.out.print("|");
            System.out.print("\n");
        }

    }
    public void Round(int time)
    {
        int i;
        int current_time = 0;
        int TP;
        int flag = 0;
        int count = 0;
        int total_wait = 0;
        int total_end = 0;
        int[] process_list = new int[time];
        int[] process_cycle = new int[100];
        process_cycle[0]=-1;
        float avg_wait;
        float avg_end;

        TP = NP;

        System.out.print("\nProcess ID\t\tBurst Time\t Wait Time\t Completion Time\n");
        current_time = 0;

        for (i = 0; TP != 0;)
        {
            process_cycle[count] = current_time;
            count++;
            if (RT[i] <= TQ && RT[i] > 0)
            {
                current_time += RT[i];
                RT[i] = 0;
                flag = 1;
            }
            else if (RT[i] > 0)
            {
                RT[i] -= TQ;
                current_time += TQ;
            }

            if (RT[i] == 0 && flag == 1)
            {
                TP--;
                CTime[i] = current_time - ATime[i];
                System.out.printf("\nProcess[%d]\t\t%d\t\t %d\t\t\t %d", i + 1, BTime.get(i), current_time - ATime[i] - BTime.get(i), current_time - ATime[i]);
                total_wait = total_wait + current_time - ATime[i] - BTime.get(i);
                total_end = total_end + current_time - ATime[i];
                flag = 0;
            }
            if (i == NP - 1)
            {
                i = 0;
            }
            else if (ATime[i + 1] <= current_time)
            {
                i++;
            }
            else
            {
                i = 0;
            }
        }
        avg_wait = (float)total_wait / NP;
        avg_end = (float)total_end / NP;
        System.out.printf("\n\nAverage Waiting Time:\t%f", avg_wait);
 
        System.out.printf("\nAverage Completion Time:\t%f\n", avg_end);

        
        int proc = 0;
        int[] update_process_cycle = new int[100];
        for (i = 0,count = 0; i < time; i++)
        {
            if (i == time - 1)
            {
                update_process_cycle[count] = process_cycle[i];
            }
            else if (process_cycle[i] != process_cycle[i + 1])
            {
                update_process_cycle[count] = process_cycle[i];
                count++;
            }
        }

        update_process_cycle[count] = current_time; 

        for (i = 1; i < count + 2; i++)
        {
            for (int j = 0; j < (update_process_cycle[i] - update_process_cycle[i - 1]); j++)
            {
                process_list[j + update_process_cycle[i - 1]] = proc + 1;
            }

            proc++;

            if (proc == NP)
            {
                proc = 0;
            }
            while (CTime[proc] < update_process_cycle[i])
            {
                proc++;
                if (proc > 4)
                {
                    proc = 0;
                }
            }
        }


        takyazdır(process_list,time,3);
    }
    public void FCFS(int time)
    {
        int[] process_list = new int[time];
        int[] execution_time = new int[NP];

        int accsum;
        int total_wait_time = 0;
        int total_completion_time = 0;
        double average_wait_time = 0.0F;
        double average_completion_time = 0.0F;

        
        for (int g = 0; g<BTime.size();g++){
            System.out.println(BTime.get(g));
        }

        WTime[0] = 0; 
        for (int i = 1; i < NP; i++)
        {
            WTime[i] = 0;
            for (int j = 0; j < i; j++)
            {
                WTime[i] += BTime.get(j);
            }
        } 


        for (int c = 0; c< BTime.size();c++){
            total_completion_time = total_completion_time + BTime.get(c);
            CTime[c]= CTime[c] + total_completion_time;

        }

        System.out.print("\n");
        System.out.print("Process\t\tBurst Time\tWaiting Time\tCompletion Time");

        for (int i = 0; i < NP; i++)
        {
            total_wait_time += WTime[i];
            total_completion_time += CTime[i];
            System.out.print("\n");
            System.out.print(PN.get(i));

            System.out.print("\t\t   ");
            System.out.print(BTime.get(i));
            System.out.print("\t\t    ");
            System.out.print(WTime[i]);
            System.out.print("\t\t    ");
            System.out.print(CTime[i]);
        }

        average_wait_time = total_wait_time * 1.0 / NP;
        average_completion_time = total_completion_time * 1.0 / NP;
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Average Waiting Time: ");
        System.out.print(average_wait_time);
        System.out.print("\n");
        System.out.print("Average Complettion Time: ");
        System.out.print(average_completion_time);
        System.out.print("\n");

        int proc = 0;
        
        for (int i = 0; i < time; i++)
        {
            if (BTime.set(proc,BTime.get(proc)-1) < 1)
            {
                proc++;
                BTime.set(proc,BTime.get(proc)-1);
            }
            process_list[i] = proc + 1; 
        }
        takyazdır(process_list,time,1);
    }

    public void shortest(int time)
    {
        int[] process_list = new int[time];
        int[] execution_time = new int[NP];

        int accsum;
        int total_wait_time = 0;
        int total_completion_time = 0;
        double average_wait_time = 0.0F;
        double average_completion_time = 0.0F;

        
        for (int g = 0; g<BTime.size();g++){
            System.out.println(BTime.get(g));
        }

       for (int y =0; y<BTime.size();y++){
            Temp.add(BTime.get(y));

        }

        Collections.sort(BTime);
        WTime[0] = 0; 
        for (int i = 1; i < NP; i++)
        {
            WTime[i] = 0;
            for (int j = 0; j < i; j++)
            {
                WTime[i] += BTime.get(j);
            }
        } 

        for (int c = 0; c< BTime.size();c++){
            total_completion_time = total_completion_time + BTime.get(c);
            CTime[c]= CTime[c] + total_completion_time;
        }
        System.out.print("\n");
        System.out.print("Process\t\tBurst Time\tWaiting Time\tCompletion Time");
        int v = 0;
        int e = 0;
        int counter = 0;



        while(counter<BTime.size()) {
            if (Temp.get(v) == BTime.get(e)) {
                PN2.add(e,PN.get(v));
                e++;
                v=0;
                counter++;

            }
            else
            {
                v++;
            }
        }

        for (int i = 0; i < NP; i++)
        {
            total_wait_time += WTime[i];
            total_completion_time += CTime[i];
            System.out.print("\n");

            System.out.print(PN2.get(i));

            System.out.print("\t\t   ");
            System.out.print(BTime.get(i));
            System.out.print("\t\t    ");
            System.out.print(WTime[i]);
            System.out.print("\t\t    ");
            System.out.print(CTime[i]);
        }

        average_wait_time = total_wait_time * 1.0 / NP;
        average_completion_time = total_completion_time * 1.0 / NP;
        System.out.print("\n");
        System.out.print("\n");
        System.out.print("Average Waiting Time: ");
        System.out.print(average_wait_time);
        System.out.print("\n");
        System.out.print("Average Complettion Time: ");
        System.out.print(average_completion_time);
        System.out.print("\n");

        int proc = 0;
        
        for (int i = 0; i < time; i++)
        {
            if (BTime.set(proc,BTime.get(proc)-1) < 1)
            {
                proc++;
               BTime.set(proc,BTime.get(proc)-1);
            }
            process_list[i] = proc + 1; 
        }
        takyazdır(process_list,time,2);
    }
}
