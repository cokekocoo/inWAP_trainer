package tokyo.kenshuhori_in.threads;

public class MultiRunnerTester {

	public void exec() {
		Thread t1 = new Thread(new InnerThread());
		t1.start();
	}

	class InnerThread implements Runnable {
		public void run() {
			System.out.println("hori");
		}
	}

}
