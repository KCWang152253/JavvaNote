
import java.util.Queue;
import java.util.Random;

/**
 * @author KCWang
 * @version 1.0
 * @date 2025/1/25 下午10:11
 */
public  class Producer implements Runnable{

    private Queue<Product> queue;
    private int maxCapacity;
    public Producer(Queue queue,int maxCapacity){
        this.maxCapacity=maxCapacity;
        this.queue=queue;
    }

    @Override
    public void run() {

        synchronized (queue){
            while (queue.size()==maxCapacity){
                try {
                    System.out.println("生产者" + Thread.currentThread().getName() + "等待中... Queue 已达到最大容量，无法生产");
                    wait();
                    System.out.println("生产者" + Thread.currentThread().getName() + "退出等待");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(queue.size()==0){
                notifyAll();
            }
            int i = new Random().nextInt();
            queue.offer(new Product("1"));
        }

    }
}