package com.tsp.algorithm;

import com.tsp.step.EdgeViewStep;
import com.tsp.step.Step;
import com.tsp.step.VertexViewStep;

import java.util.*;

public class Approximation extends Algorithm {


    public Approximation() {
        super();
        // init pseudoStep
        pseudoStep.put(0, "Get the MST of the (complete) graph\n\n");
        pseudoStep.put(1, "Get the DFS tour of the MST\n\n");
        pseudoStep.put(2, """
                Obtain a TSP tour from the obtained DFS tour
                skip repeated vertices and add source vertex(0) to the end of te list
                """);
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

        int[] parent = primMST();

        //step 0 in primMST

        stepList.add(new Step(0,"Creat MST of the graph"+ Arrays.toString(primMST() )));

        //end of step0


        dfs(parent,0,visitedNodes,finalAns,dfsTrack);


        //step1

        Object[] dfsTrackArray=dfsTrack.toArray();

        for (int i = 1; i < dfsTrackArray.length; i++) {
            String source= dfsTrackArray[i-1].toString();

            String destination= dfsTrackArray[i].toString();

            EdgeViewStep  edgeViewStep = new EdgeViewStep(graph.getEdge(source,destination),true);

            VertexViewStep vertexViewStep = new VertexViewStep(graph.getVertex(source), true);

            stepList.add(new Step(1,("The dfsTrack tour of the MST is "+ dfsTrack), vertexViewStep, edgeViewStep));


        }

        VertexViewStep vertexViewStep = new VertexViewStep(graph.getVertex(dfsTrackArray[dfsTrackArray.length - 1].toString()), true);

        stepList.add(new Step(1,("The dfsTrack tour of the MST is "), vertexViewStep));

        //end of step1


        finalAns.add(0);


        //step2

        int[] exsited= new int[graph.getVertices().size()];


        for (int i = 0; i < dfsTrackArray.length; i++) {

            if(exsited[dfsTrack.get(i)] ==0){

                exsited[dfsTrack.get(i)]=1;

            }

            else{


                EdgeViewStep  edgeViewStep1a = new EdgeViewStep(graph.getEdge(dfsTrackArray[i-1].toString(),dfsTrackArray[i].toString()),false);

                EdgeViewStep  edgeViewStep1b = new EdgeViewStep(graph.getEdge(dfsTrackArray[i].toString(),dfsTrackArray[i+1].toString()),false);

                EdgeViewStep  edgeViewStep1c = new EdgeViewStep(graph.getEdge(dfsTrackArray[i-1].toString(),dfsTrackArray[i+1].toString()),true);


                stepList.add(new Step(2,"Found 2-approximation TSP tour "+ finalAns ,edgeViewStep1a));

                stepList.add(new Step(2,"Found 2-approximation TSP tour "+ finalAns ,edgeViewStep1b));

                stepList.add(new Step(2,"Found 2-approximation TSP tour "+ finalAns ,edgeViewStep1c));



            }
        }

        EdgeViewStep  edgeViewStep1d = new EdgeViewStep(graph.getEdge(dfsTrackArray[dfsTrackArray.length - 1].toString(),Integer.toString(0)),true);

        stepList.add(new Step(2,"Found 2-approximation TSP tour "+ finalAns + " with cost = "+ calculate(finalAns),edgeViewStep1d));

        // end of step 2

        showStep();
    }


    public void showStep() {

        String ANSI_RESET = "\u001B[0m";

        String ANSI_RED = "\u001B[31m";

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

