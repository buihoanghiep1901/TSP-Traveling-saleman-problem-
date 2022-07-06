package com.tsp.algorithm;

import com.tsp.step.Step;
import com.tsp.utils.PressEnterToContinue;
import java.util.*;

public class Approximation extends Algorithm {
    private HashMap<Integer, String> pseudoStep = new HashMap<Integer, String>();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public Approximation() {
        super();
        // init pseudoStep
        pseudoStep.put(0, "Get the MST of the (complete) graph");
        pseudoStep.put(1, "Get the DFS tour of the MST");
        pseudoStep.put(2, "Obtain a TSP tour from the obtained DFS tour\n" + "skip repeated vertices and add source vertex(0) to the end of te list");
    }

    @Override
    public void run() {

        stepList.clear();

        ArrayList<Integer> finalAns=new ArrayList<>();
        ArrayList<Integer> dfsTrack   =new ArrayList<>();

        boolean[] visitedNodes = new boolean[graph.getVertices().size()];

        for(int i=0;i<graph.getVertices().size();i++)
        {
            visitedNodes[i] = false;
        }

        //step 1 in primMST
        int[] parent = primMST();

        dfs(parent,0,visitedNodes,finalAns,dfsTrack);

        //step2
        stepList.add(new Step(1,"The dfsTrack tour of the MST is "+ dfsTrack));

        finalAns.add(0);

        //step3
        stepList.add(new Step(2,"Found 2-approximation TSP tour "+ finalAns + " with cost = "+ calculate(finalAns)));

        nextStep();
    }

    public void nextStep() {
        for (Step step : stepList) {
            System.out.println(step.toString());
            System.out.println("----------------------------");
            for (int i = 0; i < pseudoStep.size(); i++) {
                if (step.getId() == i) {
                    System.out.println(ANSI_RED + pseudoStep.get(i) + ANSI_RESET);
                } else {
                    System.out.println(pseudoStep.get(i));
                }
            }
            PressEnterToContinue.run();
        }
    }

    public void showStep() {
        for (Step step : stepList) {
            System.out.println(step.toString());
            System.out.println("----------------------------");
            for (int i = 0; i < pseudoStep.size(); i++) {
                if (step.getId() == i) {
                    System.out.println(ANSI_RED + pseudoStep.get(i) + ANSI_RESET);
                } else {
                    System.out.println(pseudoStep.get(i));
                }
            }

        }
    }

    public int minKey(int[] key, boolean[] mstSet) // return the index of a vertex has min value and is not visited
    {
        int min = Integer.MAX_VALUE;
        int minIndex=-1;

        for (int v = 0; v < graph.getVertices().size(); v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public int[] primMST() // getting the Minimum Spanning Tree from the given graph, using Prim's Algorithm
    {
        //step1
        stepList.add(new Step(0,"Creat MST of the graph"));

        int V= graph.getVertices().size();

        int[] parent = new int[V];// Exp: if parent[u]=v, mean v is parent of u

        int[] key = new int[V]; // Key values used to pick minimum weight edge in cut

        boolean[] mstSet = new boolean[V]; // To represent set of vertices included in MST

        // Initialize all keys as INFINITE
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Always include first 1st vertex in MST.
        key[0] = 0;

        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);

            mstSet[u] = true;

            int weight;

            for (int v = 0; v < V; v++) {

                if (graph.getEdge(Integer.toString(u), Integer.toString(v)) !=null) { // if edge is existed

                     weight = graph.getEdge(Integer.toString(u), Integer.toString(v)).getWeight();
                }else{
                    weight=0;
                }


                if (weight!= 0 && !mstSet[v] && weight < key[v]) {

                    parent[v] = u;

                    key[v] = weight;
                }
            }
        }

        return parent; // return the list of MST
    }

    void dfs(int[] parent, int startingVertex, boolean[] visitedNodes, ArrayList<Integer> finalAns,ArrayList<Integer> dfsTrack) // getting the preorder walk of the MST using DFS
    {
        // adding the node to final answer
        finalAns.add(startingVertex);

        dfsTrack.add(startingVertex);

        // checking the visited status
        visitedNodes[startingVertex] = true;

        int count = 0;
        // using a recursive call
        for(int i=0;i<graph.getVertices().size();i++)
        {
            if(i==startingVertex)
            {
                continue;
            }
            if(parent[i]==startingVertex)//  if startingVertex is parent of i
            {
                count++;
                if(count>1){
                    dfsTrack.add(startingVertex);
                }

                if(visitedNodes[i])
                {

                    continue;
                }
                dfs(parent,i,visitedNodes,finalAns,dfsTrack);
            }
        }
    }

    public int calculate(ArrayList<Integer> finalAns){
        int shortestPath = 0;

        Object[] finalAnsArray=finalAns.toArray();

        for (int i = 1; i < finalAnsArray.length; i++) {
            String source= finalAnsArray[i-1].toString();

            String destination= finalAnsArray[i].toString();

            shortestPath = shortestPath +  graph.getEdge(source,destination).getWeight();
        }

        return shortestPath;
    }
}

