package com.example.scms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class ScmsApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(ScmsApplication.class, args);
		System.out.println("Application Started !!");
	}
}
