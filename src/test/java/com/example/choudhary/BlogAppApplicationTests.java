package com.example.choudhary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.choudhary.EntityRepo.UserRepo;

@SpringBootTest
class BlogAppApplicationTests {
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	@Test
	public void testRepo()
	{
		String name=this.userRepo.getClass().getName();
		String packageName=this.userRepo.getClass().getPackageName();
		
		System.out.println(name);
		System.out.println(packageName);
		
	}

}
