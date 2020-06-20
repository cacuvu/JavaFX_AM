import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Interface2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(initInterface(), 640, 480));
        primaryStage.show();
    }

    private HBox initInterface() {
        HBox hb = new HBox();

        //Left
        VBox vbLeft = new VBox();
        TextArea ta = new TextArea();

        HBox textBox = new HBox();
        Button b = new Button("SEND");
        TextField tf = new TextField();

        HBox.setHgrow(tf, Priority.ALWAYS);
        VBox.setVgrow(ta, Priority.ALWAYS);
        HBox.setHgrow(vbLeft, Priority.ALWAYS);
        textBox.getChildren().addAll(tf, b);
        vbLeft.getChildren().addAll(ta, textBox);

        //Get Text
        EventHandler<ActionEvent> sendText = actionEvent -> {
            ta.appendText(tf.getText() + "\n");
        };

        b.setOnAction(sendText);
        tf.setOnAction(sendText);

        //Right
        VBox vbRight = new VBox();
        Label contacts = new Label("Contacts");
        ListView contactList = new ListView();

        VBox.setVgrow(contactList, Priority.ALWAYS);
        vbRight.setMaxWidth(250);
        vbRight.setMinWidth(250);
        vbRight.getChildren().addAll(contacts, contactList);

        hb.getChildren().addAll(vbLeft, vbRight);




        return hb;
    }
}
