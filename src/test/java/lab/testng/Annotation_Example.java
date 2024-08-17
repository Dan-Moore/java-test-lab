package lab.testng;

import org.testng.annotations.*;


public class Annotation_Example {
    /**
     *  @BeforeSuite: The annotated method will be run before all tests in this suite have run.
     */
    @BeforeSuite
    public void runBeforeSuite(){
        System.out.println("runBeforeSuite");
    }
    /**
     * @AfterSuite: The annotated method will be run after all tests in this suite have run.
     */
    @AfterSuite
    public void runAfterSuite(){
        System.out.println("runAfterSuite");
    }


    /**
     *   @BeforeClass: The annotated method will be run before the first test method in the current class is invoked.
     */
    @BeforeClass
    public void runBeforeClass(){
        System.out.println("runBeforeClass");
    }
    /**
     *  @AfterClass: The annotated method will be run after all the test methods in the current class have been run.
     */
    @AfterClass
    public void runAfterClass(){
        System.out.println("runAfterClass");
    }

    @BeforeGroups
    public void runBeforeGroups(){
        System.out.println("runBeforeGroups");
    }
    @AfterGroups
    public void runAfterGroups(){
        System.out.println("runAfterGroups");
    }

    /**
     *  @BeforeMethod: The annotated method will be run before each test method.
     */
    @BeforeMethod
    public void runBeforeMethod(){
        System.out.println("runBeforeMethod");
    }
    /**
     *  @AfterMethod: The annotated method will be run after each test method.
     */
    @AfterMethod
    public void runAfterMethod(){
        System.out.println("runAfterMethod");
    }

    /**
     * @BeforeTest: The annotated method will be run before any test method belonging to the classes inside the <test> tag is run.
     */
    @BeforeTest
    public void runBeforeTest(){
        System.out.println("runBeforeTest");

    }

    /**
     * @AfterTest: The annotated method will be run after all the test methods belonging to the classes inside the <test> tag have run.
     */
    @AfterTest
    public void runAfterTest(){
        System.out.println("runAfterTest");

    }

    @Test
    public void testOne(){
        System.out.println("------------------------");
        System.out.println("Running First Test");
        System.out.println("------------------------");

    }

    @Test
    public void testTwo(){
        System.out.println("------------------------");
        System.out.println("Running Second Test");
        System.out.println("------------------------");

    }

    @Test
    public void testThree(){
        System.out.println("------------------------");
        System.out.println("Running Third Test");
        System.out.println("------------------------");
    }
}