import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Created by Brent on 19/11/2016.
 */
public class Main extends Application{

    Stage window;

    public static void main(String [ ] args){ launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("view/prompt.fxml")));
        window.setTitle("Hangman Word Chooser");
        window.setScene(new Scene(root, 400, 90));
        window.setResizable(false);
        window.show();
    }



}
