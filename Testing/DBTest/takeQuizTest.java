package DBTest;

import org.junit.Test;

import DBQuizActivityContollers.DBQuizTake;

public class takeQuizTest {
	@Test
	public void addTakeQuizActivityTest(){
		DBQuizTake z = new DBQuizTake();
		
		z.addActivity(1, 69, 100, 9);
	}
}
