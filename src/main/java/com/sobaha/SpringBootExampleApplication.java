package com.sobaha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class SpringBootExampleApplication {

	private final CustomerRepository customerRepository;

	public SpringBootExampleApplication(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootExampleApplication.class, args);
//		System.out.println("Hello prithibi");
	}

	@GetMapping
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	record NewCustomerRequest(String name, String email, Integer age){

	}

	@PostMapping
	public void addCustomer(@RequestBody NewCustomerRequest request){
		Customer customer = new Customer();
		customer.setName(request.name());
		customer.setEmail(request.email());
		customer.setAge(request.age());
		customerRepository.save(customer);
	}

	@DeleteMapping("{customerId}")
	public void deleteCustomer(@PathVariable("customerId") Integer id){
		customerRepository.deleteById(id);
	}
//	@GetMapping("/greet")
//	public GreetResponse greet(){
//		GreetResponse response = new GreetResponse("Hello World!", List.of("Java", "C++", "Python", "Kotlin", "Javascript"), new Person("Sobaha", 23, 100000));
//		return response;
//	}
//
//	record Person(String name, int age, double savings){
//
//	}
//	record GreetResponse(String message, List<String> progLang, Person person ){}

}
