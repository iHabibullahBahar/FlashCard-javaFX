package flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller implements Initializable
{
    private Scene scene;
    private Stage stage;
    private Parent root;
    
    //Home page 
    @FXML
    AnchorPane myScene;
    
    //Create New Note Page
    @FXML
    TextArea myNewTextField;
    @FXML
    TextArea myNewTextField1;
    @FXML
    Label mylabel;
    
    //Open Previus File Page
    FileChooser fileChooser = new FileChooser();
    @FXML 
    TextArea myPrevTextFiled;
    @FXML
    TextArea myPrevTextFiled1;
    @FXML
    Label savedPartLabel;
    @FXML
    Button savePartNextBtn;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //fileChooser.setInitialDirectory(new File("src\\allFLash\\newfile.txt"));
        
    }
    
    //All The Button Of Home will Perform By This Action
    public void goToHome(ActionEvent event) throws IOException
    {
        root=FXMLLoader.load(getClass().getResource("home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void createNew(ActionEvent event) throws IOException
    {
        root=FXMLLoader.load(getClass().getResource("NewFlashCard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void playFlashCardBtn(ActionEvent event) throws IOException
    {
        root=FXMLLoader.load(getClass().getResource("savedFlashCards.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void aboutBtn(ActionEvent event) throws IOException
    {
        root=FXMLLoader.load(getClass().getResource("about.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exitBtn(ActionEvent event) throws IOException{	
        Alert alert = new Alert(AlertType.CONFIRMATION);
        //alert.setTitle("Logout");
        alert.setHeaderText("You're about to Exit The Application!");
        alert.setContentText("Do you really want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK){
            System.out.println("You successfully Exit The Applicaton");
            stage = (Stage)myScene.getScene().getWindow();
            stage.close();
        }
    }
    
    
    //****Create New File page's action are started here
    public void resetBtn(ActionEvent event)throws IOException
    {
        myNewTextField.setText("");
        myNewTextField1.setText("");
    }
    //next button's action started here
    int a=2;
    File file1;
    String allString="";
    public void nextBtn(ActionEvent event)throws IOException
    {
        
        try{
            
            allString= allString+" Q: "+ myNewTextField.getText();
            allString=allString+" A: "+  myNewTextField1.getText();
            myNewTextField.setText("");
            myNewTextField1.setText("");
            mylabel.setText(Integer.toString(a));
            a++;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
    }
    public void saveNote(ActionEvent event)throws IOException
    {
        file1 = fileChooser.showSaveDialog(new Stage());
        if(file1!=null)
        {
            try (PrintWriter writer = new PrintWriter(file1)) {
            writer.write(allString);
            }
        }
    }
    

    //*****Load perivious flash card started here
    
    String loadedString;
    int i=1;
    String str[];
    //this action will Open Previous Note
    public void openFile(ActionEvent event) throws FileNotFoundException
    {
        i=1;
        File file =fileChooser.showOpenDialog(new Stage());
        try{
            Scanner scan =new Scanner(file);
            myPrevTextFiled.setText("");
            
            
            while(scan.hasNextLine())
            {
                loadedString=loadedString+(scan.nextLine()+"\n");
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        str=loadedString.split("\\s");
        qPrint();
        savePartNextBtn.setVisible(true);
    }
    
    //this will print question of flash card
    public void qPrint()
    {
        for(;i<str.length;i++)
        {
            if(str[i].equals("Q:"))continue;
            if(str[i].equals("A:"))break;
            myPrevTextFiled.appendText(str[i]+" ");
        } 
    }
    
    //this will print answer of flash card
    public void aPrint()
    {
        for(;i<str.length;i++)
        {
            if(str[i].equals("A:"))continue;
            if(str[i].equals("Q:"))break;
            myPrevTextFiled.appendText(str[i]+" ");
            savePartNextBtn.setText("See Answer");
        } 
    }
    
    //this action will perform to load perivious file
    int count=1;
    public void savedNextBtn(ActionEvent event)
    {
        if(count%2==0)
        {
            myPrevTextFiled.setText("");
            savedPartLabel.setText("Question");
            qPrint();
            savePartNextBtn.setText("See Answer");
        }
        else
        {
            myPrevTextFiled.setText("");
            savedPartLabel.setText("Answer");
            aPrint();
            savePartNextBtn.setText("See Next Question");
        }
        if(i>=str.length)
        {
            myPrevTextFiled.appendText("\n\n"+"End of Flash Card!");
            savePartNextBtn.setVisible(false);
            i=1;
        }
        count++;
    }
    
}