/*
 * Created By Habibullah Bahar
 * Click https://github.com/iHabibullahBahar
 */
package flashcard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Habib
 */

public class Main extends Application{
    
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Flash Card - use and forget what to remember");
            
            //Use your Icons Path here
            Image logo =new Image("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\FlashCardAssignment\\src\\image\\icon.png");
            stage.getIcons().add(logo);
            stage.show();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
}
