package com.tsp.algorithm;

import java.util.ArrayList;

import com.tsp.graph.Edge;
import com.tsp.step.EdgeViewStep;
import com.tsp.step.Step;
import com.tsp.step.VertexViewStep;


public class BruteForce extends Algorithm {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    ArrayList<Edge> stackEdges = new ArrayList<>();


    public BruteForce() {
        super();
        // init pseudoStep
        pseudoStep.put(0, "function findTour(pos, checker)\n");
        pseudoStep.put(1, """
                   if every node has been visited: return cost[pos][0]
                   ans = ∞

                """);
        pseudoStep.put(2, """
                   for every unvisited node V
                      ans = min(ans,findTour(v,mask|(1<<V))+cost[pos][v])

                """);
        pseudoStep.put(3, "   return ans");
    }
    @Override

    public void run() {
        stepList.clear();

        int visited=(1<<graph.getVertices().size())-1; //  exp: if graph have 4 nodes -> visited=1111, 5 nodes-> visited=11111

        int shortestPath= findTour(visited,1,0);

        showStep();

        System.out.println("quang dg ngan nhat la: "+ shortestPath);


    }

    public int findTour(int visited, int checker, int position){
        //step0
        VertexViewStep vertex0=new VertexViewStep(graph.getVertex(Integer.toString(position)), true);

        stepList.add(
                new Step(0,"Visited= "+Integer.toString(visited,2)+", Positsion= "+ position + ", Checker= " + Integer.toString(checker,2), vertex0));

        //end of step 0


        //khi đã duyệt hết tất cả các đỉnh hay chưa
        if(checker == visited)
        {
            //step1a

            stackEdges.add(graph.getEdge(Integer.toString(position),Integer.toString(0)));

            EdgeViewStep edge1a=new EdgeViewStep(stackEdges.get(stackEdges.size()-1),true);

            stepList.add(new Step(1,"Every node has been visited. Returning the cost between the last and the original vertex: "+graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight()
                    ,edge1a));

            //step1b

            EdgeViewStep edge1b=new EdgeViewStep(stackEdges.get(stackEdges.size()-1),false);

            stackEdges.remove(stackEdges.get(stackEdges.size()-1));

            stepList.add(new Step(1,"Every node has been visited. Returning the cost between the last and the original vertex: "+graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight()
                    , edge1b));

            //step1c
            VertexViewStep vertex1c=new VertexViewStep(graph.getVertex(Integer.toString(position)), false);

            EdgeViewStep edge1c=new EdgeViewStep(stackEdges.get(stackEdges.size()-1),false);

            stackEdges.remove(stackEdges.get(stackEdges.size()-1));

            stepList.add(new Step(1,"Every node has been visited. Returning the cost between the last and the original vertex: "+graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight()
                    , vertex1c,edge1c));


            //end of step 1

            return graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight();
        }

        int ans = Integer.MAX_VALUE;

        for( int city=0;city<graph.getVertices().size();city++){

            if((checker&(1<<city))==0){
                //step2

                stackEdges.add(graph.getEdge(Integer.toString(position),Integer.toString(city)));

                EdgeViewStep edge2= new EdgeViewStep(stackEdges.get(stackEdges.size()-1),true);

                stepList.add(new Step(2,"going from "+ position+ " to " +city, /*vertex2,*/ edge2));

                // end of step2

                int weight=graph.getEdge(Integer.toString(position),Integer.toString(city)).getWeight();

                int newAns = weight + findTour(visited,checker|(1<<city),city);

                ans = Math.min(ans,newAns);

            }
        }

        //step3

        VertexViewStep vertex3= new VertexViewStep(graph.getVertex(Integer.toString(position)), false);// take Id of city

        if(stackEdges.size() > 0) {

            EdgeViewStep edge3= new EdgeViewStep(stackEdges.get(stackEdges.size()-1),false);

            stepList.add(new Step(3,"The  current cost is: "+ans,vertex3, edge3));

            stackEdges.remove(stackEdges.size() - 1);
        }else{
            stepList.add(new Step(3,"The  current cost is: "+ans,vertex3));
        }

        // end of step3

        return ans;

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


}

