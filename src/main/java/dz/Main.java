package dz;


import java.util.HashSet;
import java.util.Set;


/**
 *
 *
 */
public class Main {


    public static void main(String[] args) {


        dz.MyThread t[] = new dz.MyThread[args.length];
        for (int i = 0; i < args.length; i++) {
            t[i] = new dz.MyThread(args[i]);
        }


        for (int i = 0; i < args.length; i++) {


            t[i].start();
            System.out.println(t[i].getName() +
                    "started read" + args[i]);


        }
    }
}
