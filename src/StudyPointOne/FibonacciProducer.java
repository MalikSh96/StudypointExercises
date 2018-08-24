package StudyPointOne;

import java.util.concurrent.BlockingQueue;

/**
 *
 * @author malik
 */
public class FibonacciProducer implements Runnable
{
    BlockingQueue<Integer> numbersToTake;
    BlockingQueue<Integer> calculatedNumbers;

    public FibonacciProducer(BlockingQueue<Integer> numbersToTake, BlockingQueue<Integer> calculatedNumbers) 
    {
        this.numbersToTake = numbersToTake;
        this.calculatedNumbers = calculatedNumbers;
    }
    
    @Override
    public void run() 
    {
        boolean moreNumbersToFetch = true;
        
        while(moreNumbersToFetch)
        {
            Integer numb;
            
            numb = numbersToTake.poll();
            if(numb == null)
            {
                moreNumbersToFetch = false;
            }
            else
            {
                calculatedNumbers.add(fib(numb));
            }
            
        }
    }
    
    private int fib(int n)
    {
        if((n == 0) || (n == 1))
        {
            return n;
        }
        else
        {
            return fib(n - 1) + fib(n - 2);
        }
    }
}
