package com.example.demo.SpringFramework;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.demo.beans.AppConfig;
import com.example.demo.beans.City;
import com.example.demo.beans.City2;
import com.example.demo.beans.Person;
import com.example.demo.beans.Person1;
import com.example.demo.beans.Person2;
import com.example.demo.beans.Person3;
import com.example.demo.beans.Person4;
import com.example.demo.beans.Person5;
import com.example.demo.beans.Player;
import com.example.demo.beans.World;
import com.example.demo.interfaces.ITeam;

public class App {
	public static void main(String[] args) {

		int opc = 0;
		do {
			Scanner keyboard = new Scanner(System.in);
			System.out.println(
					"\n1. Hello world \n2. Config with annotations \n3. Injection by constructor \n4. Obj injection"
					+ "\n5. Nested beans \n6. Referencing beans \n7. Collections - list \n8. Use autowired"
					+ "\n9. Beans scope y lazy (just with singleton) \n10. Bean life cycle \n11. - \n12. Interface - bean life cycle"
					+ "\n13. Interface - bean life cycle \n14. BeanPostProcessor \n15. Uuse interfacesl \n16. Use required"
					+ "\nSelect an opction: "	
					);
			opc = keyboard.nextInt();
			switch (opc) {
			case 1: {
				// Hello world
				ApplicationContext appContext = new ClassPathXmlApplicationContext("com/example/demo/xml/beans.xml");
				World w = (World) appContext.getBean("world");
				System.out.println(w.getMsj());
				((ConfigurableApplicationContext) appContext).close(); // close resource meanwhile bean its destroyed
				break;
			}
			case 2: {
				// config with annotations
				ApplicationContext appContext2 = new AnnotationConfigApplicationContext(AppConfig.class);
				World w = (World) appContext2.getBean("world");
				System.out.println(w.getMsj());
				((ConfigurableApplicationContext) appContext2).close();
				break;
			}
			case 3: {
				// injection by constructor
				ApplicationContext appContext3 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans2.xml");
				Person p = (Person) appContext3.getBean("person");
				System.out.println(p.getId() + " " + p.getName() + " " + p.getLastName());
				Person p2 = (Person) appContext3.getBean("person1");
				System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getLastName());
				((ConfigurableApplicationContext) appContext3).close();
				break;
			}
			case 4: {
				// obj injection
				ApplicationContext appContext4 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans3.xml");
				Person1 p = (Person1) appContext4.getBean("person");
				System.out.println(p.getId() + " " + p.getName() + " " + p.getLastName() + " "
						+ p.getCountry().getName() + " " + p.getCountry().getCity().getName());
				((ConfigurableApplicationContext) appContext4).close();
				break;
			}
			case 5: {
				// nested beans
				ApplicationContext appContext4 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans3.xml");
				Person1 p = (Person1) appContext4.getBean("person2");
				System.out.println(p.getId() + " " + p.getName() + " " + p.getLastName() + " "
						+ p.getCountry().getName() + " " + p.getCountry().getCity().getName());
				((ConfigurableApplicationContext) appContext4).close();
				break;
			}
			case 6: {
				// referencing beans
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans4.xml");
				Person1 p = (Person1) appContext6.getBean("personBean");
				System.out.println(p.getId() + " " + p.getName() + " " + p.getLastName() + " "
						+ p.getCountry().getName() + " " + p.getCountry().getCity().getName());

				Person1 p2 = (Person1) appContext6.getBean("personBean2");
				System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getLastName() + " "
						+ p2.getCountry().getName() + " " + p2.getCountry().getCity().getName());
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 7: {
				// collections - list
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans5.xml");
				Person2 p = (Person2) appContext6.getBean("person");
				
				String citys = "";
				for (City city : p.getCountry().getCitys()) {
					citys += city.getName() + " - ";
				}
				
				System.out.println(p.getId() + " " + p.getName() + " " + p.getLastName() + " "
						+ p.getCountry().getName() + " " + citys);
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 8: {
				// autowired
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans6.xml");
				Person3 p = (Person3) appContext6.getBean("person");
			
				System.out.println(p.getId() + " " + p.getName() + " " + p.getLastName() + " "
						+ p.getCountry().getName() + " " + p.getCity().getName());
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 9: {
				// beans scope y lazy (just with singleton)
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans7.xml");
				Person3 p = (Person3) appContext6.getBean("person");
				Person3 p2 = (Person3) appContext6.getBean("person");
				System.out.println(p);
				System.out.println(p2);
				
				Person3 p3 = (Person3) appContext6.getBean("person2");
				Person3 p4 = (Person3) appContext6.getBean("person2");
				System.out.println(p3);
				System.out.println(p4);
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 10: {
				// bean life cycle
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans8.xml");
				Person4 p = (Person4) appContext6.getBean("person");
				City2 city = (City2) appContext6.getBean("city");
				
				System.out.println(p.getLastName());
				System.out.println(city.getName());
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 11: {
				// postConstruct preDestroy
				
				break;
			}
			case 12: {
				// interface - bean life cycle
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans9.xml");
				Person5 p = (Person5) appContext6.getBean("person");
				System.out.println(p.getLastName());
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 13: {
				// interface bean life cycle
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans9.xml");
				Person5 p = (Person5) appContext6.getBean("person");
				System.out.println(p.getLastName());
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 14: {
				// beanPostProcessor
				ApplicationContext appContext6 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans10.xml");
				Person5 p = (Person5) appContext6.getBean("person");
				System.out.println(p.getLastName());
				((ConfigurableApplicationContext) appContext6).close();
				break;
			}
			case 15: {
				// use interfaces
				ApplicationContext appContext2 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans11.xml");
				Player player = (Player) appContext2.getBean("messi");
				System.out.println(player.getName() + " - " + player.getTeam().showTeam());
				
				ITeam team = (ITeam) appContext2.getBean("barcelona");
				System.out.println(team.showTeam());
				
				team = (ITeam) appContext2.getBean("juventus");
				System.out.println(team.showTeam());
				((ConfigurableApplicationContext) appContext2).close();
				break;
			}			
			case 16: {
				// Required
				ApplicationContext appContext22 = new ClassPathXmlApplicationContext("com/example/demo/xml/beans12.xml");
				Player p22 = (Player) appContext22.getBean("messi");
				System.out.println(p22.getName() + " - " + p22.getTeam().showTeam());

				((ConfigurableApplicationContext) appContext22).close(); // close resource meanwhile bean its destroyed
				break;
			}
			case 17: {
				opc = 17;
				System.out.println("End program!");
				break;
			}
			default:
				System.out.println("Unexpected value: " + opc);
			}

		} while (opc != 17);
	}
}
