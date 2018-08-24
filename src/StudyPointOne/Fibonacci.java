package StudyPointOne;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author malik
 */
/*
Link to exercise: https://docs.google.com/document/d/13f18buNu2WgTocikJ2Quj6TaetkujWouRCaibq2VCu8/edit
*/
public class Fibonacci 
{
    public static void main(String[] args) throws InterruptedException 
    {
        //The list of numbers that must be processed. This is s1 in the exercise figure
        ArrayBlockingQueue<Integer> numbersToTake = new ArrayBlockingQueue(40);
        //A list that holds the size of all numbers together
        List<Integer> limit = new ArrayList<>();
        //Adding the numbers
        numbersToTake.add(4);
        numbersToTake.add(5);
        numbersToTake.add(8);
        numbersToTake.add(12);
        numbersToTake.add(21);
        numbersToTake.add(22);
        numbersToTake.add(34);
        numbersToTake.add(35);
        numbersToTake.add(36);
        numbersToTake.add(37);
        numbersToTake.add(42);
        numbersToTake.add(44);
        //The list of numbers that must be processed. This is s2 in the exercise figure
        ArrayBlockingQueue<Integer> calculatedNumbers = new ArrayBlockingQueue(40);
        //Running through the numbersToTake list, and adding their numbers into the limit so it can have a size
        for(Integer theLimit : numbersToTake) 
        {
            limit.add(theLimit);
        }
        
        ExecutorService executor = Executors.newCachedThreadPool();
        //Create and start the four Producers (P1-P4)
        executor.execute(new FibonacciProducer(numbersToTake, calculatedNumbers)); 
        executor.execute(new FibonacciProducer(numbersToTake, calculatedNumbers)); 
        executor.execute(new FibonacciProducer(numbersToTake, calculatedNumbers));
        executor.execute(new FibonacciProducer(numbersToTake, calculatedNumbers));
        //Create and start the single Consumer Thead (c1)
        executor.execute(new FibonacciConsumer(calculatedNumbers, limit));
        
        executor.shutdown();
        executor.awaitTermination(5,TimeUnit.SECONDS);
        System.out.println("Closing Down");
    }
}
