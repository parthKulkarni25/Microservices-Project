package in.ineuron.config;

import java.util.Random;

public class IdGenerator {
	
	private static final int MIN = 1001;
	private static final int MAX = 9999;
	
	public static String generateUserid() {
		Random random = new Random();
		int randonNo = random.nextInt(MAX-MIN+1)+MIN;
		return "UD"+randonNo;
	}

}
