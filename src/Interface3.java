import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Interface3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(initInterface(), 600, 600));
        primaryStage.show();
    }

    private GridPane initInterface() {
        GridPane gp = new GridPane();

        TextArea ta = new TextArea();
        TextField tf = new TextField();

        Button b = new Button("Отправить");
        b.setMinWidth(100);
        var b_width = b.getWidth();

        Label l = new Label("Контакты");

        ListView ls = new ListView();
        ls.setPrefHeight(0);

        gp.setGridLinesVisible(true);

        gp.add(ta, 0, 0, 2,2);
        gp.add(tf, 0,2);
        gp.add(b,1,2);
        gp.add(l,2,0);
        gp.add(ls, 2,1, 1,2);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setHgrow(Priority.ALWAYS);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setMinWidth(b_width);
        ColumnConstraints column3 = new ColumnConstraints(200);

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row2.setVgrow(Priority.ALWAYS);

        gp.getRowConstraints().addAll(row1, row2, row3);
        gp.getColumnConstraints().addAll(column1, column2, column3);




        return  gp;
    }
}
