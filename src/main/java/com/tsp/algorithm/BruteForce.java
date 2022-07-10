package com.tsp.algorithm;

import java.util.HashMap;
import com.tsp.step.Step;
import com.tsp.utils.PressEnterToContinue;
public class BruteForce extends Algorithm {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public BruteForce() {
        super();
        // init pseudoStep
        pseudoStep.put(0, "function findTour(pos, checker)\n");
        pseudoStep.put(1, "   if every node has been visited: return cost[pos][0]\n"  + "   ans = ∞\n\n");
        pseudoStep.put(2, "   for every unvisited node V\n" + "      ans = min(ans,findTour(v,mask|(1<<V))+cost[pos][v])\n\n");
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
        stepList.add(new Step(0,"Visited= "+Integer.toString(visited,2)+", Positsion= "+ position + ", Checker= " + Integer.toString(checker,2)));

        //khi đã duyệt hết tất cả các đỉnh hay chưa
        if(checker == visited)
        {
            //step1
            stepList.add(new Step(1,"Every node has been visited. Returning the cost between the last and the original vertex: "+graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight()));

            return graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight();
        }

        int ans = Integer.MAX_VALUE;

        for(int city=0;city<graph.getVertices().size();city++){

            if((checker&(1<<city))==0){
                //step2
                stepList.add(new Step(2,"going from "+ position+ " to " +city));

                int weight=graph.getEdge(Integer.toString(position),Integer.toString(city)).getWeight();

                int newAns = weight + findTour(visited,checker|(1<<city),city);

                ans = Math.min(ans,newAns);

            }
        }

        //step3
        stepList.add(new Step(3,"The  current cost is: "+ans));

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

}

