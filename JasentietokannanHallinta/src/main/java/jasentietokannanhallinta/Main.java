package jasentietokannanhallinta;

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

public class Main extends Application {
    private JasentiedotService jasentiedotService;
    
    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    
    private VBox jasentiedotNodes;
    private Label menuLabel = new Label();

    @Override
    public void init(){
        FileUserDao userDao = new FileUserDao("users.txt");
        FileJasentiedotDao todoDao = new FileJasentiedotDao("jasentiedotList.txt", userDao);
        jasentiedotService = new JasentiedotService(todoDao, userDao);
    }

    public Node createJasentiedotNode(Jasentiedot jasentiedot) {
        HBox box = new HBox(10);
        Label label  = new Label(jasentiedot.getName());
        label.setMinHeight(28);
        Button button = new Button("done");
        button.setOnAction(e->{
            jasentiedotService.markDone(jasentiedot.getId());
            redrawJasentiedotlist();
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        box.setPadding(new Insets(0,5,0,5));

        box.getChildren().addAll(label, spacer, button);
        return box;
    }

    public void redrawJasentiedotlist() {
        jasentiedotNodes.getChildren().clear();     

        List<Jasentiedot> undoneJasentiedotList = jasentiedotService.getUndone();
        undoneJasentiedotList.forEach(jasentiedot->{
            jasentiedotNodes.getChildren().add(createJasentiedotNode(jasentiedot));
        });     
    }

    @Override
    public void start(Stage primaryStage) {               
        // login scene

        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("username");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("login");
        Button createButton = new Button("create new user");
        loginButton.setOnAction(e->{
            String username = usernameInput.getText();
            menuLabel.setText(username + " logged in...");
            if ( jasentiedotService.login(username) ){
                loginMessage.setText("");
                redrawJasentiedotlist();
                primaryStage.setScene(mainScene);  
                usernameInput.setText("");
            } else {
                loginMessage.setText("user does not exist");
                loginMessage.setTextFill(Color.RED);
            }      
        });  

        createButton.setOnAction(e->{
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);   
        });  

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);       

        loginScene = new Scene(loginPane, 300, 250);    

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

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e->{
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();

            if ( username.length()==2 || name.length()<2 ) {
                userCreationMessage.setText("username or name too short");
                userCreationMessage.setTextFill(Color.RED);              
            } else if ( jasentiedotService.createUser(username, name) ){
                userCreationMessage.setText("");                
                loginMessage.setText("new user created");
                loginMessage.setTextFill(Color.GREEN);
                primaryStage.setScene(loginScene);      
            } else {
                userCreationMessage.setText("username has to be unique");
                userCreationMessage.setTextFill(Color.RED);        
            }

        });  

        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton); 

        newUserScene = new Scene(newUserPane, 300, 250);

        // main scene

        ScrollPane jasentiedotScollbar = new ScrollPane();       
        BorderPane mainPane = new BorderPane(jasentiedotScollbar);
        mainScene = new Scene(mainPane, 300, 250);

        HBox menuPane = new HBox(10);    
        Region menuSpacer = new Region();
        HBox.setHgrow(menuSpacer, Priority.ALWAYS);
        Button logoutButton = new Button("logout");      
        menuPane.getChildren().addAll(menuLabel, menuSpacer, logoutButton);
        logoutButton.setOnAction(e->{
            primaryStage.setScene(loginScene);
        });        

        HBox createForm = new HBox(10);    
        Button createJasentiedot = new Button("create");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField newJasentiedotInput = new TextField();
        createForm.getChildren().addAll(newJasentiedotInput, spacer, createJasentiedot);

        jasentiedotNodes = new VBox(10);
        jasentiedotNodes.setMaxWidth(280);
        jasentiedotNodes.setMinWidth(280);
        redrawJasentiedotlist();

        jasentiedotScollbar.setContent(jasentiedotNodes);
        mainPane.setBottom(createForm);
        mainPane.setTop(menuPane);

        createJasentiedot.setOnAction(e->{
            jasentiedotService.createJasentiedot(1, newJasentiedotInput.getText(), "address", "phone", false, jasentiedotService.getLoggedUser());
            newJasentiedotInput.setText("");       
            redrawJasentiedotlist();
        });

        // setup primary stage

        primaryStage.setTitle("Jasentiedot");
        primaryStage.setScene(loginScene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e->{
            System.out.println("close");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
    

