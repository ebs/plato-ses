package gr.ebs.plato.service.data;

import javax.ejb.Stateless;

@Stateless
public class HelloService {

	public String sayHello() {
        String message = "Hello";
        return message;
    }
	
}
