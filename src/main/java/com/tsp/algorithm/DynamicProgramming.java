package com.tsp.algorithm;

import com.tsp.step.Step;
import com.tsp.utils.PressEnterToContinue;

import java.util.HashMap;

public class DynamicProgramming extends Algorithm {

    private HashMap<Integer, String> pseudoStep = new HashMap<Integer, String>();

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_RED = "\u001B[31m";

    public DynamicProgramming() {
        super();
        // init pseudoStep
        pseudoStep.put(0, "function findTour(pos, checker)");
        pseudoStep.put(1, " if every node has been visited: return cost[position][0]");
        pseudoStep.put(2, " if (position, checker) in memo: return memo[(pos, checker)]\n"  + " ans = ∞");
        pseudoStep.put(3, " for every unvisited node V\n" + "   ans = min(ans,findTour(v,mask|(1<<V))+cost[pos][v])");
        pseudoStep.put(4, " return memo[(pos, checker)] = ans\n");
    }

    @Override
    public void run() {

        int visited=(1<<graph.getVertices().size())-1;

        // số hoán vị của các đỉnh trong đồ thị (không tính đỉnh gốc là 0)
        int permutationNum=(1<<graph.getVertices().size());

        // dpAray: mỗi dòng tương đương 1 hoán vị
        int [][]memo=new int[permutationNum][graph.getVertices().size()];

        //khoi tao gia tri ban dau la -1, chưa có cạnh nào đc duyệt
        for(int i=0;i<permutationNum;i++)
        {
            for(int j=0;j<graph.getVertices().size();j++)
            {
                memo[i][j] = -1;
            }
        }

        int shortestPath= findTour(memo,visited,1,0);

        showStep();

        System.out.println("quang dg ngan nhat la: "+ shortestPath);

    }

    public int findTour(int[][] memo, int visited, int checker, int position){

        //step0
        stepList.add(new Step(0,"Positsion= "+ position + ", Checker= " + Integer.toString(checker,2)+", Visited= "+Integer.toString(visited,2)));

        //kiểm tra đã duyệt hết tất cả các đỉnh hay chưa
        if(checker == visited)
        {
            //step1
            stepList.add(new Step(1,"Every node has been visited. Returning the cost between the last and the original vertex: "+graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight()));

            return graph.getEdge(Integer.toString(position),Integer.toString(0)).getWeight();
        }

        /// kiểm tra xem cạnh có tồn tai trong memo
        if(memo[checker][position] != -1)
        {
            //step2
            stepList.add(new Step(2,"this current state ("+ position+ ", " + Integer.toString(checker,2)+  ") have been computed.\n"
                                                    + "return memo[checker][position]= "+memo[checker][position]));

            return memo[checker][position];
        }


        int ans = Integer.MAX_VALUE;

        // với mỗi đỉnh chưa đc duyệt
        for(int city=0;city<graph.getVertices().size();city++){

            if((checker&(1<<city))==0){

                //step3
                stepList.add(new Step(3,"going from "+ position+ " to " +city));

                int weight=graph.getEdge(Integer.toString(position),Integer.toString(city)).getWeight();

                int newAns = weight + findTour(memo,visited,checker|(1<<city),city);

                ans = Math.min(ans,newAns);
            }
        }

        //step4
        stepList.add(new Step(4,"The  current cost is: "+ans));

        memo[checker][position] = ans;
        return memo[checker][position] = ans;
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
}
