package com.ohgiraffers.section01;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntitymanagerFactoryGenerator {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("ohgiraffers");
    public static EntityManagerFactory getInstance(){

        return factory;
    }
}
