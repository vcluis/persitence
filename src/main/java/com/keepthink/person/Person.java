package com.keepthink.person;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.EntityManager;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import jakarta.persistence.TypedQuery;

import java.util.List;

@Entity
public class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getAge(){
        return age;
    }

    public static void create(EntityManager em, String name, int age){
        em.getTransaction().begin();
        Person person = new Person(name, age);
        em.persist(person);
        em.getTransaction().commit();
    }

    public static List<Person> get(EntityManager em){
        TypedQuery<Person> query = em.createQuery("SELECT p FROM PERSON p", Person.class);
        return query.getResultList();
    }

    public static void update(EntityManager em, int id, String name, int age){
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        if(person != null){
            person.setName(name);
            person.setAge(age);
            em.getTransaction().commit();
        }
        else em.getTransaction().rollback();
    }

    public static void delete(EntityManager em, int id){
        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        if(person != null){
            em.remove(person);
            em.getTransaction().commit();
        }
        else em.getTransaction().rollback();
    }

}
