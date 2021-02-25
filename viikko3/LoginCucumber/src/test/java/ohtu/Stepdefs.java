package ohtu;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import ohtu.io.*;
import ohtu.data_access.*;
import ohtu.services.*;

public class Stepdefs {
    App app;
    StubIO io;
    UserDao userDao;
    AuthenticationService auth;
    List<String> inputLines;
    
    @Before
    public void setup(){
        userDao = new InMemoryUserDao();
        auth = new AuthenticationService(userDao);
        inputLines = new ArrayList<>();      
    }
    @Given("user \"eero\" with password \"salainen1\" is created")
    public void createEeero(){
       String username = "eero";
       String password = "salainen1";
        
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    
    @Given("command login is selected")
    public void commandLoginSelected() throws Throwable {
        inputLines.add("login");
    }

    @When("username {string} and password {string} are entered")
    public void usernameAndPasswordAreEntered(String username, String password) {
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }    
    
    @Then("system will respond with {string}")
    public void systemWillRespondWith(String expectedOutput) {
        assertTrue(io.getPrints().contains(expectedOutput));
    } 
    @Given("command new is selected")
    public void commandNewSelected() throws Throwable {
        inputLines.add("new");
    }
    
    @When ("used username {string} and a valid password {string} are entered")
    public void usedUsernameAndValidPasswordEnetered(String username, String password){
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
        
    }
    @Then("error message {string}")
    public void errorToCreateUser(String error){
        assertTrue(io.getPrints().contains(error));
       
    }
    
    @When ("new username {string} and too short username {string} are entered")
    public void tooShortUsername(String username , String password){
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
        
    }
    @Then("error message tooShort {string}")
    public void errorToCreateUserTooShortUsername(String error){
        assertTrue(io.getPrints().contains(error));
       
    }
    
    @When ("new username {string} and password with only letters {string} are entered")
    public void passwordWithOnlyLetters(String username, String password){
        inputLines.add(username);
        inputLines.add(password);
        
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
    @Then ("error message onlyLettersInPW {string}")
    public void errorOnlyLettersInPassword(String error) {
         assertTrue(io.getPrints().contains(error));
    }
     @When ("new username {string} and too short password {string} are entered")
      public void tooShortPassword(String username , String password){
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
        
    }
        @Then("error message tooShortPW {string}")
    public void errorToCreateUserTooShortPW(String error){
        assertTrue(io.getPrints().contains(error));
       
    }
    @When("username eero \"eero\" with password salainen1 \"salainen1\" are entered")
    public void validLogin(){
       String username = "eero";
       String password = "salainen1";
       inputLines.add(username);
       inputLines.add(password);
       
       io = new StubIO(inputLines); 
       app = new App(io, auth);
       app.run();
    }
        @Then("system will respond again with eero \"logged in\"")
    public void systemWillResponseWithMessage() {
            io.getPrints();
       
    } 

}
    
 
    

    



