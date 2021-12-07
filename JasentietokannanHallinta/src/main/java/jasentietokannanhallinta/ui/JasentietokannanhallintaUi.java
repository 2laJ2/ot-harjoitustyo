package jasentietokannanhallinta.ui;

import java.util.*;
import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class JasentietokannanhallintaUi extends Application {
    private JasentiedotService jasentiedotService;
    
    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene newMemberScene;
    private Scene findMemberScene;
    
    private VBox jasentiedotNodes;
    private Label menuLabel = new Label();

    @Override
    public void init() {
        FileUserDao userDao = new FileUserDao("users.txt");
        FileJasentiedotDao todoDao = new FileJasentiedotDao("jasentiedotList.txt", userDao);
        jasentiedotService = new JasentiedotService(todoDao, userDao);
    }

    public Node createJasentiedotNode(Jasentiedot jasentiedot) {
        HBox box = new HBox(10);
        Label label  = new Label(jasentiedot.getName());
        label.setMinHeight(28);
        Button button = new Button("done");
        button.setOnAction(e-> {
            jasentiedotService.markDone(jasentiedot.getId());
            redrawJasentiedotlist();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0, 5, 0, 5));

        box.getChildren().addAll(label, spacer, button);
        return box;
    }

    public void redrawJasentiedotlist() {
        jasentiedotNodes.getChildren().clear();     

        List<Jasentiedot> undoneJasentiedotList = jasentiedotService.getUndone();
        undoneJasentiedotList.forEach(jasentiedot-> {
            jasentiedotNodes.getChildren().add(createJasentiedotNode(jasentiedot));
        });     
    }

    @Override
    public void start(Stage primaryStage) {               
        // login scene

        VBox loginPane = new VBox(10);
        
        HBox inputPane = new HBox(10);
        inputPane.setPadding(new Insets(10));
        TextField usernameInput = new TextField();
        Label usernameLabel = new Label("username");
        usernameLabel.setPrefWidth(100);
        inputPane.getChildren().addAll(usernameLabel, usernameInput);
        
        HBox passwordPane = new HBox(10);
        passwordPane.setPadding(new Insets(10));
        TextField passwordInput = new TextField();
        Label passwordLabel = new Label("password");
        passwordLabel.setPrefWidth(100);
        passwordPane.getChildren().addAll(passwordLabel, passwordInput);
        
        HBox loginSceneButtonPane = new HBox(10);
        loginSceneButtonPane.setPadding(new Insets(10));
        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        Label loginMessage = new Label();
        
        loginButton.setOnAction(e-> {
            String username = usernameInput.getText();
            String password = passwordInput.getText(); //add password checking function
            menuLabel.setText(username + " logged in...");
            if (jasentiedotService.login(username)) {
                loginMessage.setText("");
                primaryStage.setScene(mainScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  

        createButton.setOnAction(e-> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  

        loginSceneButtonPane.getChildren().addAll(loginButton, createButton, loginMessage);
        
        loginPane.getChildren().addAll(loginMessage, inputPane, passwordPane, loginSceneButtonPane);       

        loginScene = new Scene(loginPane, 900, 500);    

        // new user scene

        VBox newUserPane = new VBox(10);

        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField(); 
        Label newUsernameLabel = new Label("username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);        

        HBox newPasswordPane = new HBox(10);
        newPasswordPane.setPadding(new Insets(10));
        TextField newPasswordInput = new TextField();
        Label newPasswordLabel = new Label("password");
        newPasswordLabel.setPrefWidth(100);
        newPasswordPane.getChildren().addAll(newPasswordLabel, newPasswordInput);        

        Label userCreationMessage = new Label();

        HBox createNewUserButtonPane = new HBox(10);
        createNewUserButtonPane.setPadding(new Insets(10));
        Button createNewUserButton = new Button("create");
        Button goBackNewUserButton = new Button("back");
        
        createNewUserButton.setOnAction(e-> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();
            String password = newPasswordInput.getText();
            
            if (username.length() < 3 || name.length() < 3 || password.length() < 10) {
                userCreationMessage.setText("username or name or password too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if (jasentiedotService.createUser(username, name, password)) {
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }

        });  
        goBackNewUserButton.setOnAction(e-> {
            primaryStage.setScene(loginScene);
        });
        
        createNewUserButtonPane.getChildren().addAll(goBackNewUserButton, createNewUserButton);

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, newPasswordPane, createNewUserButtonPane); 

        newUserScene = new Scene(newUserPane, 900, 500);

        VBox mainPane = new VBox(10);
        
        HBox menuPane = new HBox(10);    
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, logoutButton);
        logoutButton.setOnAction(e-> {
            primaryStage.setScene(loginScene);
        });        
       
        HBox findPane = new HBox(10);
        findPane.setPadding(new Insets(10));
        TextField findInput = new TextField(); 
        Label findLabel = new Label("find member");
        findLabel.setPrefWidth(100);
        Button findMemberButton = new Button("find");
        
        findMemberButton.setOnAction(e-> { //add find member function
            primaryStage.setScene(findMemberScene);   
        });
        
        findPane.getChildren().addAll(findLabel, findInput, findMemberButton);
        
        Button createMemberButton = new Button("create new member");
        
        createMemberButton.setOnAction(e-> {
            primaryStage.setScene(newMemberScene);   
        });
        
        mainPane.getChildren().addAll(menuPane, findPane, createMemberButton);
        
        mainScene = new Scene(mainPane, 900, 500);
        
        // new member scene

        VBox newMemberPane = new VBox(10);

        HBox createNewMemberNamePane = new HBox(10);
        createNewMemberNamePane.setPadding(new Insets(10));
        TextField createNewMemberNameInput = new TextField(); 
        Label createNewMemberNameLabel = new Label("name");
        createNewMemberNameLabel.setPrefWidth(100);
        createNewMemberNamePane.getChildren().addAll(createNewMemberNameLabel, createNewMemberNameInput);
        
        HBox createNewMemberAddressPane = new HBox(10);
        createNewMemberAddressPane.setPadding(new Insets(10));
        TextField createNewMemberAddressInput = new TextField(); 
        Label createNewMemberAddressLabel = new Label("address");
        createNewMemberAddressLabel.setPrefWidth(100);
        createNewMemberAddressPane.getChildren().addAll(createNewMemberAddressLabel, createNewMemberAddressInput);
        
        HBox createNewMemberPhonePane = new HBox(10);
        createNewMemberPhonePane.setPadding(new Insets(10));
        TextField createNewMemberPhoneInput = new TextField(); 
        Label createNewMemberPhoneLabel = new Label("phone");
        createNewMemberPhoneLabel.setPrefWidth(100);
        createNewMemberPhonePane.getChildren().addAll(createNewMemberPhoneLabel, createNewMemberPhoneInput);
        
        HBox createNewMemberButtonPane = new HBox(10);
        createNewMemberButtonPane.setPadding(new Insets(10));
        Button createNewMemberButton = new Button("create"); //add create function
        Button goBackCreateNewMemberButton = new Button("back");
        
        createNewMemberButton.setOnAction(e-> {
            primaryStage.setScene(mainScene);   
        });
        goBackCreateNewMemberButton.setOnAction(e-> {
            primaryStage.setScene(mainScene);
        });
        
        createNewMemberButtonPane.getChildren().addAll(goBackCreateNewMemberButton, createNewMemberButton);
        
        
        newMemberPane.getChildren().addAll(createNewMemberNamePane, createNewMemberAddressPane, 
            createNewMemberPhonePane, createNewMemberButtonPane);
        
        /*

        Label memberCreationMessage = new Label();

        
        createNewMemberButton.setOnAction(e-> {
            String newmembername = newMemberNameInput.getText();
            String newmemberaddress = newMemberAddressInput.getText();
            String newmemberphone = newMemberPhoneInput.getText();
            int newmemberid = 
            
            if (newmembername.length() == 2 || newmemberaddress.length() < 2 || newmemberphone.length() < 8) {
                userCreationMessage.setText("name or address or phone too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if (jasentiedotService.createJasentiedot(membername, memberaddress, memberphone)) {//
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }

        });  
        */
        
        newMemberScene = new Scene(newMemberPane, 900, 500);
        
        // find member scene
        
        VBox findMemberPane = new VBox(10);

        HBox findMemberNamePane = new HBox(10);
        findMemberNamePane.setPadding(new Insets(10));
        TextField findMemberNameInput = new TextField(); 
        Label findMemberNameLabel = new Label("name");
        findMemberNameLabel.setPrefWidth(100);
        findMemberNamePane.getChildren().addAll(findMemberNameLabel, findMemberNameInput);
        
        HBox findMemberAddressPane = new HBox(10);
        findMemberAddressPane.setPadding(new Insets(10));
        TextField findMemberAddressInput = new TextField(); 
        Label findMemberAddressLabel = new Label("address");
        findMemberAddressLabel.setPrefWidth(100);
        findMemberAddressPane.getChildren().addAll(findMemberAddressLabel, findMemberAddressInput);
        
        HBox findMemberPhonePane = new HBox(10);
        findMemberPhonePane.setPadding(new Insets(10));
        TextField findMemberPhoneInput = new TextField(); 
        Label findMemberPhoneLabel = new Label("phone");
        findMemberPhoneLabel.setPrefWidth(100);
        findMemberPhonePane.getChildren().addAll(findMemberPhoneLabel, findMemberPhoneInput);
        
        HBox findMemberButtonPane = new HBox(10);
        findMemberButtonPane.setPadding(new Insets(10));
        Button goBackButton = new Button("back");
        Button editMemberButton = new Button("edit");
        
        goBackButton.setOnAction(e-> {
            primaryStage.setScene(mainScene);   
        });
        editMemberButton.setOnAction(e-> { //add edit function
            primaryStage.setScene(mainScene);   
        });
        
        findMemberButtonPane.getChildren().addAll(goBackButton, editMemberButton);
        
        findMemberPane.getChildren().addAll(findMemberNamePane, findMemberAddressPane, 
            findMemberPhonePane, findMemberButtonPane);
        
        findMemberScene = new Scene(findMemberPane, 900, 500);
        
        // setup primary stage

        primaryStage.setTitle("Jasentiedot");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> {
            System.out.println("close");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
    

