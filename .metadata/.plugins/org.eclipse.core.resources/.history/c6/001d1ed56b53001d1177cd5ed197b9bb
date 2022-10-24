
public class Timer extends Thread{
	Correct correct = new Correct();
	
	@Override
	public void run() {
			for(int i=5; i>=0; i--) {
				if(correct.isSolution()==false) {
				}
				if(i==0) {
					System.out.println("시간초과 마지막문제 화이팅");
					correct.setSolution(false);
				}else {
					try {	Thread.sleep(1000);	} catch (InterruptedException e) {	e.printStackTrace();	}
				finally{
					
				}
				}
				
			}
	}
}
