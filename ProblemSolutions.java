/******************************************************************
 *
 *   Arek Gubala / COMP 272-001
 *
 *   This java file contains the problem solutions for the methods lastBoulder,
 *   showDuplicates, and pair methods. You should utilize the Java Collection
 *   Framework for these methods.
 *
 ********************************************************************/

 import java.util.*;
 import java.util.PriorityQueue;
 
 public class ProblemSolutions {
 
     /**
      * Priority Queue (PQ) Game
      *
      * PQ1 Problem Statement:
      * -----------------------
      *
      * You are given an array of integers of boulders where boulders[i] is the
      * weight of the ith boulder.
      *
      * We are playing a game with the boulders. On each turn, we choose the heaviest
      * two boulders and smash them together. Suppose the heaviest two boulders have
      * weights x and y. The result of this smash is:
      *
      *    If x == y, both boulders are destroyed, and
      *    If x != y, the boulder of weight x is destroyed, and the boulder of
      *               weight y has new weight y - x.
      *
      * At the end of the game, there is at most one boulder left.
      *
      * Return the weight of the last remaining boulder. If there are no boulders
      * left, return 0.
      *
      *
      * Example 1:
      *
      * Input: boulders = [2,7,4,1,8,1]
      * Output: 1
      * Explanation:
      * We combine 7 and 8 to get 1 so the list converts to [2,4,1,1,1] then,
      * we combine 2 and 4 to get 2 so the list converts to [2,1,1,1] then,
      * we combine 2 and 1 to get 1 so the list converts to [1,1,1] then,
      * we combine 1 and 1 to get 0 so the list converts to [1] then that's the
      * value of the last stone.
      *
      * Example 2:
      *
      * Input: boulders = [1]
      * Output: 1
      *
      *
      *
      * RECOMMENDED APPROACH
      *
      * Initializing Priority Queue in reverse order, so that it gives
      * max element at the top. Taking top Elements and performing the
      * given operations in the question as long as 2 or more boulders;
      * returning the 0 if queue is empty else return pq.peek().
      */
 
     public static int lastBoulder(int[] boulders) {

         //
         // ADD YOUR CODE HERE - DO NOT FORGET TO ADD YOUR NAME / SECTION # ABOVE
         //

         // create a maxheap of the integers from boulders
         PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
         for (int i : boulders) {
             maxHeap.add(i);
         }

         // smash rocks!! as long as at least 2 rocks exist to be smashed.
         // push 2 ints off maxHeap to get the 2 biggest rocks. if the result
         // is 0, do nothing, else add the result back into maxHeap
         while (maxHeap.size() > 1){
            int bigRock = maxHeap.poll();
            int smallRock = maxHeap.poll();
            if (bigRock - smallRock > 0){
                maxHeap.add(bigRock - smallRock);
            }
         }

         // if an element exists in maxHeap when we're done, return it. else, return 0
         return maxHeap.peek() != null ? maxHeap.peek() : 0;
     }
 
 
     /**
      * Method showDuplicates
      *
      * This method identifies duplicate strings in an array list. The list
      * is passed as an ArrayList<String> and the method returns an ArrayList<String>
      * containing only unique strings that appear more than once in the input list.
      * This returned array list is returned in sorted ascending order. Note that
      * this method should consider case (strings are case-sensitive).
      *
      * For example, if the input list was: "Lion", "Dog", "Cat", "Dog", "Horse", "Lion", "CAT"
      * the method would return an ArrayList<String> containing: "Dog", "Lion"
      *
      * @param  input an ArrayList<String>
      * @return       an ArrayList<String> containing only unique strings that appear
      *               more than once in the input list. They will be in ascending order.
      */
 
     public static ArrayList<String> showDuplicates(ArrayList<String> input) {
 
         //
         //  YOUR CODE GOES HERE
         //

         /*  
         * make a frequency hashmap, keys being strings from arrayList input and values being 
         * the number  of times they appear. then, if a key has a value greater than two, 
         * add it to a new list. use a collections method to return a sorted list (in ascending order).
         */ 

         HashMap<String, Integer> map = new HashMap<>();

         // if s isn't present in map, add it with value 1. otherwise, add 1 to s's frequency.
        for (String s : input) {
            if (map.containsKey(s)){
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }

        // to make things simple and prevent duplicates in returnList, make a set of the keys.
        // if a key's value is greater than 1 (meaning it has duplicates in input), it's added to
        // returnList, which is sorted in ascending order.
        Set<String> keys = map.keySet();
        ArrayList<String> returnList = new ArrayList<>();
        for (String s : keys){
            if (map.get(s) > 1){
                returnList.add(s);
            }
        }

        Collections.sort(returnList); // sort list in ascending order
        return returnList; 
     }
 
 
     /**
      * Finds pairs in the input array that add up to k.
      *
      * @param input   Array of integers
      * @param k       The sum to find pairs for
 
      * @return an ArrayList<String> containing a list of strings. The ArrayList
      *        of strings needs to be ordered both within a pair, and
      *        between pairs in ascending order. E.g.,
      *
      *         - Ordering within a pair:
      *            A string is a pair in the format "(a, b)", where a and b are
      *            ordered lowest to highest, e.g., if a pair was the numbers
      *            6 and 3, then the string would be "(3, 6)", and NOT "(6, 3)".
      *         - Ordering between pairs:
      *            The ordering of strings of pairs should be sorted in lowest to
      *            highest pairs. E.g., if the following two string pairs within
      *            the returned ArraryList, "(3, 6)" and "(2, 7), they should be
      *            ordered in the ArrayList returned as "(2, 7)" and "(3, 6 )".
      *
      *         Example output:
      *         If the input array list was {2, 3, 3, 4, 5, 6, 7}, then the
      *         returned ArrayList<String> would be {"(2, 7)", "(3, 6)", "(4, 5)"}
      *
      *  HINT: Considering using any Java Collection Framework ADT that we have used
      *  to date, though HashSet. Consider using Java's "Collections.sort()" for final
      *  sort of ArrayList before returning so consistent answer. Utilize Oracle's
      *  Java Framework documentation in its use.
      */
 
     public static ArrayList<String> pair(int[] input, int k) {
 
         //
         //  YOUR CODE GOES HERE
         //

        // set of ints; k subtracted by each int in input to find pairs
        HashSet<Integer> set = new HashSet<>();
        for (int i : input){
            set.add(k - i);
        }

        // list of the number pairs that add up to k
        ArrayList<String> pairs = new ArrayList<>();    
        /*
        * loop through each int in input, checking if set contains that int.
        * if it does, that means we've found a number pair that'll add up to k.
        * use math library to determine which number is bigger/smaller, add that pair
        * to list, and remove that pairing from set to avoid duplicates.
        */
        int smaller = 0;
        int bigger = 0;
        for (int i : input){
            if (set.contains(i)){
                bigger = Math.max(i, k-i);
                smaller = Math.min(i, k-i);
                String pairing = "(" + smaller + ", " + bigger + ")";
                pairs.add(pairing);
                set.remove(i); set.remove(k-i);
            }
        }

        Collections.sort(pairs); // magically sort the number-pairs
        return pairs;
     }
 }
