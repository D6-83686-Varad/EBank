package com.app;


/* 
 * Purpose: These are import statements that bring in classes and interfaces from external libraries and frameworks that are used in the application:
 * */
import org.modelmapper.ModelMapper;
//ModelMapper is a library for mapping between objects.

import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.Conditions;
//MatchingStrategies and Conditions are used to configure how ModelMapper performs object mapping.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//SpringApplication, SpringBootApplication, and Bean are part of the Spring Boot framework and are used for bootstrapping and configuring the application.


@SpringBootApplication

//Purpose: This annotation is a convenience annotation that adds several other annotations to the class:
/*
    @Configuration: Indicates that this class contains Spring configuration.
	@EnableAutoConfiguration: Enables Spring Boot’s auto-configuration feature.
	@ComponentScan: Scans for other components, configurations, and services in the specified package (or sub-packages) and registers them as beans in the application context.
 */

//This line declares a public class named Application. It is the main entry point of the Spring Boot application.
public class Application {
	
	// This line defines the main method, which is the entry point of the Java application. It is executed when the application is started.
	public static void main(String[] args) {
		
		/*
		 * This line launches the Spring Boot application. SpringApplication.run() sets up the Spring context and performs application initialization. Application.class refers to the main class to be used for configuration, and args are command-line arguments passed to the application.
		 */
		SpringApplication.run(Application.class, args);
	}

    // equivalent to <bean id ..../> in xml file
    /*
     * The @Bean annotation marks a method as a bean definition. This method will return an instance of ModelMapper, which will be managed by the Spring container. It is equivalent to defining a bean in XML configuration files but uses Java-based configuration.
     */
    @Bean
    ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();		
		modelMapper.getConfiguration()
		.setMatchingStrategy(MatchingStrategies.STRICT)
		//This line starts configuring the ModelMapper instance. modelMapper.getConfiguration() retrieves the configuration object for the ModelMapper, and setMatchingStrategy(MatchingStrategies.STRICT) sets the matching strategy to STRICT. This means that ModelMapper will require an exact match between source and destination properties.
		.setPropertyCondition(Conditions.isNotNull());
		//This line continues the configuration by setting the property condition to Conditions.isNotNull(). This means that ModelMapper will only map properties that are not null in the source object. It skips mapping for properties with null values.
		return modelMapper;
	}
    
    @Bean
    public LoggerSingleton loggerSingleton() {
        return new LoggerSingleton();
    }


}

// Two main design patterns are used: Singleton and Builder.

/*
  1. Singleton Design Pattern:
  	The Singleton pattern ensures that a class has only one instance and provides a global point of access to that instance.
  	How It’s Used in Your Code:
  		 the @Bean annotation is used to declare the ModelMapper bean. By default, Spring Boot manages beans as singletons. This means that Spring creates only one instance of ModelMapper and provides it to all components that require it.
  		 
  	Explanation to Interviewer:
  		“In this Spring Boot application, the Singleton pattern is utilized by default through Spring’s bean management. By annotating a method with @Bean, we ensure that Spring creates only one instance of the ModelMapper class. This singleton instance is then shared across the entire application. This design pattern promotes consistency, as all parts of the application use the same ModelMapper instance, and it optimizes resource usage by avoiding the overhead of creating multiple instances.”

  2. Builder Design Pattern:
  	The Builder pattern is used to construct a complex object step by step. It separates the construction of a complex object from its representation, allowing the same construction process to create different representations.
	How It’s Used in Your Code:
		Fluent Configuration API: The ModelMapper class uses a fluent API to configure its settings. Methods like setMatchingStrategy and setPropertyCondition can be chained together, which is characteristic of the Builder pattern. This approach allows for clear, step-by-step configuration of the ModelMapper instance.
	Explanation to Interviewer:
		The Builder pattern is reflected in how the ModelMapper instance is configured. By using method chaining through the fluent API (e.g., setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull())), we’re effectively utilizing the Builder pattern. This design pattern provides a clear and expressive way to set up complex configurations, making the code more readable and maintainable. It allows us to specify multiple configuration options in a fluid manner without needing to create multiple intermediate objects or methods.”

*/
