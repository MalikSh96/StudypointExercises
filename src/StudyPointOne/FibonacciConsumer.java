package StudyPointOne;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author malik
 */
public class FibonacciConsumer implements Runnable 
{
    private long sum = 0;
    private Integer number;
    BlockingQueue<Integer> calculatedNumbers;
    List<Integer> limit;

    public FibonacciConsumer(BlockingQueue<Integer> calculatedNumbers, List<Integer> limit) 
    {
        this.calculatedNumbers = calculatedNumbers;
        this.limit = limit;
    }

    @Override
    public void run() 
    {
        /*
        Using a for loop that runs through the limit list, to keep track of how many numbers we originally had in
        numbersToTake list, so that we can avoid getting stuck using the .take()
        */
        for(Integer theLimit : limit) 
        {
            try 
            {
                //Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
                number = calculatedNumbers.take();
                //adding numbers
                sum += number;
                System.out.println("Number " + number);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(FibonacciConsumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Sum " + sum);
    }
}
