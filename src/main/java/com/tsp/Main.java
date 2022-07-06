package com.tsp;

import com.tsp.algorithm.Algorithm;
import com.tsp.algorithm.Approximation;
import com.tsp.algorithm.BruteForce;
import com.tsp.algorithm.DynamicProgramming;
import com.tsp.context.Context;
import com.tsp.graph.Graph;
import com.tsp.utils.PressEnterToContinue;

import java.util.Scanner;

public class Main {
    private static Graph graph = new Graph();
    private static Algorithm algorithm;
    private static final Context context = new Context();

    public static void main(String[] args) {

        // show graph
        System.out.println(graph.toString());
        // show menu
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("----------------------------------------------------");
            System.out.println("Traveling Saleman Problem");
            System.out.println("1. Create graph");
            System.out.println("2. Choose algorithm");
            System.out.println("3. Play");
            System.out.println("0. Exit");
            System.out.println("----------------------------------------------------");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    graphFunction();
                    break;
                case 2:
                    chooseAlgorithm();
                    break;
                case 3:
                    context.setAlgorithm(algorithm);
                    context.play();
                    PressEnterToContinue.run();
                    break;
                case 0:
                    return; // exit
            }
        } while (true);
    }

    public static void chooseAlgorithm() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("----------------------------------------------------");
            System.out.println("Choose algorithm");
            System.out.println("1. BruteForce");
            System.out.println("2. Dynamic Programing");
            System.out.println("3. Approximation");
            System.out.println("0. Back");
            System.out.println("----------------------------------------------------");
            int choose= scanner.nextInt();
            switch (choose) {
                case 1:
                    algorithm = new BruteForce();
                    algorithm.setGraph(graph);
                    System.out.println("BruteForce is selected");
                    PressEnterToContinue.run();
                    break;
                case 2:
                    algorithm = new DynamicProgramming();
                    algorithm.setGraph(graph);
                    System.out.println("DynamicProgramming is selected");
                    PressEnterToContinue.run();
                    break;
                case 3:
                    algorithm = new Approximation();
                    algorithm.setGraph(graph);
                    System.out.println("Approximation is selected");
                    PressEnterToContinue.run();
                    break;
                case 0:
                    return;// exit
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
    }

    public static void graphFunction() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("1. Create new Graph");
            System.out.println("2. Use example Graph");
            System.out.println("3. Add vertex");
            System.out.println("4. Add edge");
            System.out.println("5. Remove vertex");
            System.out.println("6. Remove edge");
            System.out.println("7. Show graph");
            System.out.println("0. Back");
            System.out.println("-----------------------------------------------------");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    graph = new Graph();
                    System.out.println("Create new Graph successfully");
                    PressEnterToContinue.run();
                    break;
                case 2:
                    example();
                    break;
                case 3:
                    addVertexToGraph();
                    break;
                case 4:
                    addEdgeToGraph();
                    break;
                case 5:
                    removeVertexFromGraph();
                    break;
                case 6:
                    removeEdgeFromGraph();
                    break;
                case 7:
                    System.out.println(graph.toString());
                    PressEnterToContinue.run();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    public static void example() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("----------------------------------------------------");
            System.out.println("Choose Example");
            System.out.println("1. K4");
            System.out.println("2. K5");
            System.out.println("3. K8");
            System.out.println("0. Back");
            System.out.println("----------------------------------------------------");
            int choose = scanner.nextInt();
            switch (choose) {
                case 1:
                    graph = Graph.graphK4();
                    System.out.println("Use example K4 successfully");
                    PressEnterToContinue.run();
                    break;
                case 2:
                    graph = Graph.graphK5();
                    System.out.println("Use example K5 successfully");
                    PressEnterToContinue.run();
                    break;
                case 3:
                    graph = Graph.graphK8();
                    System.out.println("Use example K8 successfully");
                    PressEnterToContinue.run();
                    break;

                case 0:
                    return;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        } while (true);
    }
    public static void addVertexToGraph() {
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        do {
            System.out.println("Do you want to add vertex to graph? (1. Yes, 0. No)");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println("Enter vertex name: ");
                    String vertexName = sc.nextLine();
                    graph.addVertex(vertexName);
                    PressEnterToContinue.run();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
        } while (choose != 0);
    }

    public static void addEdgeToGraph() {
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        do {
            System.out.println("Do you want to add edge to graph? (1. Yes, 0. No)");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println("Enter vertex name: ");
                    String vertexName = sc.nextLine();
                    System.out.println("Enter vertex name: ");
                    String vertexName2 = sc.nextLine();
                    System.out.println("Enter weight: ");
                    int weight = sc.nextInt();
                    sc.nextLine();
                    graph.addEdge(vertexName, vertexName2, weight);
                    PressEnterToContinue.run();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
        } while (choose != 0);
    }

    public static void removeVertexFromGraph() {
        Scanner sc = new Scanner(System.in);
        int choose = 0;
        do {
            System.out.println("Do you want to remove vertex from graph? (1. Yes, 0. No)");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println("Enter vertex name: ");
                    String vertexName = sc.nextLine();
                    graph.removeVertex(vertexName);
                    PressEnterToContinue.run();
                    break;
                case 0:
                    System.out.println("Exit");
                    break;
            }
        } while (choose != 0);
    }

    public static void removeEdgeFromGraph() {
        Scanner sc = new Scanner(System.in);
        int choose=0;
        do {
            System.out.println("Do you want to remove edge from graph? (1. Yes, 0. No)");
             choose = sc.nextInt();
            sc.nextLine();
            switch (choose) {
                case 1:
                    System.out.println("Enter vertex name: ");
                    String vertexName = sc.nextLine();
                    System.out.println("Enter vertex name: ");
                    String vertexName2 = sc.nextLine();
                    graph.removeEdge(vertexName, vertexName2);
                    PressEnterToContinue.run();
                    break;
                case 0:
                    return;
            }
        } while (choose != 0);
    }
}
