package com.keepthink;
import com.keepthink.person.Person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
        EntityManager em = emf.createEntityManager();

        Person.create(em, "hello", 28);
        Person.create(em, "world", 28);

        List<Person> persons = Person.get(em);
        for(Person person: persons){
            System.out.printf("Name: %s, age: %d%n", person.getName(), person.getAge());
        }
    }
}
