// Rusheel Chande
// May 17 2023

// This program allocates a given relief budget across different locations
// to maximize the number of people helped, considering the cost of helping
// each location.

import java.util.*;

public class Client {
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        // List<Location> scenario = createRandomScenario(10, 10, 100, 1000, 100000);
        List<Location> scenario = createSimpleScenario();
        System.out.println(scenario);
        
        double budget = 2000;
        Allocation allocation = allocateRelief(budget, scenario);
        printResult(allocation, budget);
    }

    // This method maximizes the # of people aided using the given relief budget.
    // Pre-conditions: The budget must be non-negative, and the provided list of 
    //                 Location objects cannot be null.
    // Post-conditions: Returns an Allocation object representing the optimal distribution 
    //                  of the relief budget. In the event of equal aid provided by two allocations, 
    //                  the allocation with lower cost is preferred.
    public static Allocation allocateRelief(double budget, List<Location> sites) {
        return allocateReliefDecider(budget, sites, new Allocation());
    }

    // This method is a recursive helper method which decides on the allocation of relief
    // across different locations.
    // Pre-conditions: The total budget given must be non-negative. A given list of 
    //                 Location objects sites where relief needs to be allocated cannot be null
    //                 The given current allocation of relief, currAllocation, cannot be null.
    // Post-conditions: Returns an Allocation object that represents the optimal allocation of the 
    //                  relief budget across the remaining sites.
    private static Allocation allocateReliefDecider(double budget, List<Location> sites, 
                                                    Allocation currAllocation) {
        if (sites.isEmpty() || budget <= 0) {
            return currAllocation;
        } else {
            Location location = sites.get(0);
            List<Location> remainingSites = new ArrayList<>(sites.subList(1, sites.size()));

            Allocation notChoosing = allocateReliefDecider(budget, remainingSites, currAllocation);
            
            if (location.getCost() > budget) {
                return notChoosing;
            }

            Allocation choosing = allocateReliefDecider(budget - location.getCost(), remainingSites, 
                                                        currAllocation.withLoc(location));
            
            return highestAllocation(choosing, notChoosing);
        }
    }

    // This method compares two Allocation objects and determines the highest allocation.
    // Pre-conditions: The given first and second Allocation object, firstAllocation and 
    //                 secondAllocation, to be compared, cannot be null.
    // Post-conditions: Returns the Allocation object that results in aiding more people.
    //                  If both allocations aid the same number of people, it returns the allocation
    //                  with the least cost
    private static Allocation highestAllocation(Allocation firstAllocation, Allocation secondAllocation) {
        int firstPeople = firstAllocation.totalPeople();
        int secondPeople = secondAllocation.totalPeople();
        double firstCost = firstAllocation.totalCost();
        double secondCost = secondAllocation.totalCost();

        if (firstPeople > secondPeople) {
            return firstAllocation;
        } else if (secondPeople > firstPeople) {
            return secondAllocation;
        } else if (firstCost < secondCost) {
            return firstAllocation;
        } else {
            return secondAllocation;
        }
    }

    // PROVIDED HELPER METHODS - **DO NOT MODIFY ANYTHING BELOW THIS LINE!**

    public static void printResult(Allocation alloc, double budget) {
        System.out.println("Result: ");
        System.out.println("  " + alloc);
        System.out.println("  People helped: " + alloc.totalPeople());
        System.out.printf("  Cost: $%.2f\n", alloc.totalCost());
        System.out.printf("  Unused budget: $%.2f\n", (budget - alloc.totalCost()));
    }

    public static List<Location> createRandomScenario(int numLocs, int minPop, int maxPop, double minCostPer, double maxCostPer) {
        List<Location> result = new ArrayList<>();

        for (int i = 0; i < numLocs; i++) {
            int pop = rand.nextInt(minPop, maxPop + 1);
            double cost = rand.nextDouble(minCostPer, maxCostPer) * pop;
            result.add(new Location("Location #" + i, pop, round2(cost)));
        }

        return result;
    }

    public static List<Location> createSimpleScenario() {
        List<Location> result = new ArrayList<>();

        result.add(new Location("Location #1", 50, 500));
        result.add(new Location("Location #2", 100, 700));
        result.add(new Location("Location #3", 60, 1000));
        result.add(new Location("Location #4", 20, 1000));
        result.add(new Location("Location #5", 200, 900));

        return result;
    }    

    private static double round2(double num) {
        return Math.round(num * 100) / 100.0;
    }
}
