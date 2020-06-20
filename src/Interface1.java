import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interface1 extends Application {
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setScene(new Scene(initInterface(), 640, 480));
//        primaryStage.setScene(new Scene(initInterface()));
        primaryStage.show();
    }

    private VBox initInterface() {
        Button b = new Button("hello");
        Label l = new Label("some text");
        TextField tf = new TextField();

        VBox vb = new VBox(b, l, tf);
//        vb.getChildren().add(b);
//        vb.getChildren().addAll(b, l, tf);

        return vb;
    }



}
