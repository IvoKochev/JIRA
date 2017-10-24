/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jira.model;

import static org.testng.Assert.*;

/**
 *
 * @author slavi
 */
public class RoleNGTest {
    
    public RoleNGTest() {
    }

    @org.testng.annotations.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.testng.annotations.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.testng.annotations.BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @org.testng.annotations.AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of getId method, of class Role.
     */
    @org.testng.annotations.Test
    public void testGetId() {
        System.out.println("getId");
        Role instance = new Role();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Role.
     */
    @org.testng.annotations.Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Role instance = new Role();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRole method, of class Role.
     */
    @org.testng.annotations.Test
    public void testGetRole() {
        System.out.println("getRole");
        Role instance = new Role();
        String expResult = "";
        String result = instance.getRole();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRole method, of class Role.
     */
    @org.testng.annotations.Test
    public void testSetRole() {
        System.out.println("setRole");
        String role = "";
        Role instance = new Role();
        instance.setRole(role);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
