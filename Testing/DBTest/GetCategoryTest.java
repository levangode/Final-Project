package DBTest;

import org.junit.Test;

import DBQuizControllers.DBQuizController;

public class GetCategoryTest {
	@Test
	public void getCatTest(){
		System.out.println(new DBQuizController().getQuizCategories());
	}
}
