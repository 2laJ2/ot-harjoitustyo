package jasentietokannanhallinta.ui;

import jasentietokannanhallinta.domain.JasentiedotService;
import jasentietokannanhallinta.domain.Jasentiedot;
import jasentietokannanhallinta.dao.FileJasentiedotDao;
import jasentietokannanhallinta.dao.FileUserDao;
import java.io.FileInputStream;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Graafinen käyttöliittymä
 */
public class JasentietokannanhallintaUi extends Application {
    private JasentiedotService jasentiedotService;
    
    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene newMemberScene;
    private Scene findMemberScene;
    
    private VBox jasentiedotNodes;
    private Label menuLabel = new Label();
    
    /**
     * Ohjelman käynnistyessä tehtävät toimenpiteet
     */
    @Override
    public void init() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("config.properties"));
        
        String userFile = properties.getProperty("userFile");
        String jasentiedotFile = properties.getProperty("jasentiedotFile");
        
        FileUserDao userDao = new FileUserDao(userFile);
        FileJasentiedotDao todoDao = new FileJasentiedotDao(jasentiedotFile, userDao);
        jasentiedotService = new JasentiedotService(todoDao, userDao);
    }
    
    /**
     * Graafisen käyttöliittymän sisältävä osio
     * @param primaryStage
     */
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
            String password = passwordInput.getText();
            menuLabel.setText(username + " logged in...");
            if (jasentiedotService.login(username)) {
                if (jasentiedotService.getLoggedUser().getPassword().matches(password)) {
                    loginMessage.setText("");
                    primaryStage.setScene(mainScene);  
                    usernameInput.setText("");
                    passwordInput.setText("");
                } else {
                    loginMessage.setText("password incorrect");
                }
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  

        createButton.setOnAction(e-> {
            usernameInput.setText("");
            passwordInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  

        loginSceneButtonPane.getChildren().addAll(loginButton, createButton, loginMessage);
        
        loginPane.getChildren().addAll(loginMessage, inputPane, passwordPane, loginSceneButtonPane);       

        loginScene = new Scene(loginPane, 500, 400);    

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
                newUsernameInput.setText("");
                newNameInput.setText("");
                newPasswordInput.setText("");
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }

        });  
        goBackNewUserButton.setOnAction(e-> {
            newUsernameInput.setText("");
            newNameInput.setText("");
            newPasswordInput.setText("");
            primaryStage.setScene(loginScene);
        });
                
        createNewUserButtonPane.getChildren().addAll(goBackNewUserButton, createNewUserButton);

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, newPasswordPane, createNewUserButtonPane); 

        newUserScene = new Scene(newUserPane, 500, 400);

        // main scene
        
        VBox mainPane = new VBox(10);
        
        HBox menuPane = new HBox(10);    
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, logoutButton);
        
        VBox findMemberPane = new VBox(10);

        HBox findMemberNamePane = new HBox(10);
        findMemberNamePane.setPadding(new Insets(10));
        TextField findMemberNameInput = new TextField(); 
        findMemberNameInput.setPrefWidth(200);
        Label findMemberNameLabel = new Label("name");
        findMemberNameLabel.setPrefWidth(100);
        findMemberNamePane.getChildren().addAll(findMemberNameLabel, findMemberNameInput);
        
        HBox findMemberAddressPane = new HBox(10);
        findMemberAddressPane.setPadding(new Insets(10));
        TextField findMemberAddressInput = new TextField(); 
        findMemberAddressInput.setPrefWidth(200);
        Label findMemberAddressLabel = new Label("address");
        findMemberAddressLabel.setPrefWidth(100);
        findMemberAddressPane.getChildren().addAll(findMemberAddressLabel, findMemberAddressInput);
        
        HBox findMemberPhonePane = new HBox(10);
        findMemberPhonePane.setPadding(new Insets(10));
        TextField findMemberPhoneInput = new TextField(); 
        findMemberPhoneInput.setPrefWidth(200);
        Label findMemberPhoneLabel = new Label("phone");
        findMemberPhoneLabel.setPrefWidth(100);
        findMemberPhonePane.getChildren().addAll(findMemberPhoneLabel, findMemberPhoneInput);
        
        findMemberPane.getChildren().addAll(findMemberNamePane, findMemberAddressPane, 
            findMemberPhonePane);
        
        Label memberCreationMessage = new Label();
        
        HBox findPane = new HBox(10);
        findPane.setPadding(new Insets(10));
        TextField findInput = new TextField(); 
        findInput.setPrefWidth(200);
        Label findLabel = new Label("find member");
        findLabel.setPrefWidth(100);
        Button findMemberButton = new Button("find");
        
        Label memberNotFoundLabel = new Label("");
        memberNotFoundLabel.setPrefWidth(400);
        
        findMemberButton.setOnAction(e-> { 
            memberCreationMessage.setText("");   
            String findmembername = findInput.getText();
            if (jasentiedotService.doesMemberNameExist(findmembername)) {
                memberNotFoundLabel.setText("");
                Jasentiedot jasentiedot = jasentiedotService.findMemberByName(findmembername);
                findMemberNameInput.setText(findmembername);
                findMemberAddressInput.setText(jasentiedot.getAddress());
                findMemberPhoneInput.setText(jasentiedot.getPhone());
            } else {
                memberNotFoundLabel.setText("member not found");
                memberNotFoundLabel.setTextFill(Color.RED); 
                findInput.setText("");
                findMemberNameInput.setText("");
                findMemberAddressInput.setText("");
                findMemberPhoneInput.setText("");
            }
        });
        
        findPane.getChildren().addAll(findLabel, findInput, findMemberButton, findMemberPane);
        
        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(10));
        
        Button createMemberButton = new Button("create new member");
        
        createMemberButton.setOnAction(e-> { 
            String newmembername = findMemberNameInput.getText();
            String newmemberaddress = findMemberAddressInput.getText();
            String newmemberphone = findMemberPhoneInput.getText();
            
            if (newmembername.length() < 3 || newmemberaddress.length() < 3 || newmemberphone.length() < 8) {
                memberCreationMessage.setText("name or address or phone too short");
                memberCreationMessage.setTextFill(Color.RED);              
            } else if (jasentiedotService.createNewMember(newmembername, newmemberaddress, newmemberphone)) {
                memberCreationMessage.setText("");                
                memberNotFoundLabel.setText("new member created");
                memberNotFoundLabel.setTextFill(Color.GREEN);
                memberCreationMessage.setText("");
                findMemberNameInput.setText("");
                findMemberAddressInput.setText("");
                findMemberPhoneInput.setText("");
            } else {
                memberCreationMessage.setText("username has to be unique");
                memberCreationMessage.setTextFill(Color.RED);        
            }

        }); 
        
        Button editMemberButton = new Button("edit");
        
        editMemberButton.setOnAction(e-> {
            memberCreationMessage.setText("");   
            String findmembername = findInput.getText();
            Jasentiedot jasentiedot = jasentiedotService.findMemberByName(findmembername);
            Jasentiedot uusi = jasentiedot;
            int idUusi = uusi.getId();
            
            String newname = findMemberNameInput.getText();
            boolean exists = jasentiedotService.doesMemberNameExist(newname);
            Jasentiedot check = jasentiedotService.findMemberByName(newname);
            int idCheck = check.getId();
            
            if (exists == false || idUusi == idCheck) {
                uusi.setName(newname);
                uusi.setAddress(findMemberAddressInput.getText());
                uusi.setPhone(findMemberPhoneInput.getText());

                memberNotFoundLabel.setText("member information changed");
                memberNotFoundLabel.setTextFill(Color.GREEN);

                findMemberNameInput.setText("");
                findMemberAddressInput.setText("");
                findMemberPhoneInput.setText("");

                jasentiedotService.jasentiedotList.remove(jasentiedot);
                jasentiedotService.jasentiedotList.add(uusi);
            } else {
                memberNotFoundLabel.setText("membername exists, choose another name and press create");
                memberNotFoundLabel.setTextFill(Color.RED);
            }
        });

        buttonPane.getChildren().addAll(createMemberButton, editMemberButton);
        
        HBox deleteMemberButtonPane = new HBox(10);
        deleteMemberButtonPane.setPadding(new Insets(10));
        Button deleteMemberButton = new Button("delete member");
        deleteMemberButton.setTextFill(Color.RED);
        
        deleteMemberButton.setOnAction(e-> {
            String delete = findMemberNameInput.getText();
            if (jasentiedotService.deleteMember(delete)) {
                memberNotFoundLabel.setText("member deleted");
                memberNotFoundLabel.setTextFill(Color.RED);

                findMemberNameInput.setText("");
                findMemberAddressInput.setText("");
                findMemberPhoneInput.setText("");
                findInput.setText("");
            }
        });
        
        deleteMemberButtonPane.getChildren().addAll(deleteMemberButton);
        
        logoutButton.setOnAction(e-> {
            memberNotFoundLabel.setText("");
            findMemberNameInput.setText("");
            findMemberAddressInput.setText("");
            findMemberPhoneInput.setText("");
            findInput.setText("");
            primaryStage.setScene(loginScene);
        });        
        
        
        mainPane.getChildren().addAll(menuPane, memberNotFoundLabel, findPane, findMemberPane, memberCreationMessage, buttonPane, deleteMemberButtonPane);
        
        mainScene = new Scene(mainPane, 500, 400);
        
        // setup primary stage

        primaryStage.setTitle("Jasentiedot");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e-> {
            System.out.println("close");
        });
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

}
    

