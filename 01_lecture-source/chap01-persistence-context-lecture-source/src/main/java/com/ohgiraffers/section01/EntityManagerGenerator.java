package com.ohgiraffers.section01;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerGenerator {
    public static EntityManager getInstance() {

        EntityManagerFactory factory = EntitymanagerFactoryGenerator.getInstance();
        EntityManager manager = factory.createEntityManager();

        return manager;
    }
}
