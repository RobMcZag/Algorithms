package com.robertozagni.algoritmi.uf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A client to interactively use / test
 * 
 * @author roberto.zagni
 */
public class ClientUnionFind {

    private static final Logger LOG = LoggerFactory.getLogger(QuickfindUF.class.getName());

    public static void main(String[] args) {
        int N = parseArgsForNumObjects(args, 10);
        UnionFind uf = new QuickfindUF(N);

        InputStream in = System.in;
        try {
            in = ClientUnionFind.class.getClassLoader().getResource("ufcommands.txt").openStream();
        } catch (IOException e) {
            LOG.error("We could not open your file.");
            e.printStackTrace();
        }
        Scanner input = new Scanner(in);
        System.out.println("<command> <p> <q> - Commands are 'union', 'connected', 'find', 'count', 'quit'.");
        while (true) {
            String command = input.next();
            if ("quit".equalsIgnoreCase(command)) {
                break;
            }
            if (command.startsWith("#")) {
                input.nextLine();
                continue;
            }
            if ("count".equalsIgnoreCase(command)) {
                System.out.format("There are %d connected components. %s%n", uf.count(), uf.toString());
                continue;
            }
            int p = input.nextInt();
            if ("find".equalsIgnoreCase(command)) {
                System.out.format("The component of %d is %d. %s%n", p, uf.find(p), uf.toString());
                continue;
            }
            int q = input.nextInt();
            if ("union".equalsIgnoreCase(command)) {
                System.out.format("Joining %d and %d. %s%n", p, q, uf.toString());
                uf.union(p, q);
                continue;
            }
            if ("connected".equalsIgnoreCase(command)) {
                System.out.format("Objects %d and %d are%s connected. %s%n",
                        p, q, uf.connected(p, q) ? "" : " not", uf.toString());
                continue;
            }
        }
        System.out.format("Done.%n");
        input.close();
    }

    /**
     * Sets the number of objects to the value read from arguments or the default passed.
     * 
     * @param args the arguments array
     */
    private static int parseArgsForNumObjects(String[] args, int N) {
        if (args.length >= 1) {
            try {
                N = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                LOG.error("The string {} is not a valid integer.", args[0]);
                usage();
            }
        }
        return N;
    }

    private static void usage() {
        LOG.info("USAGE: java {} [ number ]", ClientUnionFind.class.getName());
    }

}
