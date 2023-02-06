package fakerLibrary;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {
	
		@Test
		void testGeneratorDummyData() {
			
			Faker faker=new Faker();
			
			String fullName = faker.name().fullName();
			System.out.println(fullName);
			System.out.println(faker.name().firstName());
			System.out.println(faker.name().lastName());
			System.out.println(faker.name().username());
			System.out.println(faker.internet().password());
			System.out.println(faker.phoneNumber().phoneNumber());
			System.out.println(faker.internet().safeEmailAddress());
		}
}
