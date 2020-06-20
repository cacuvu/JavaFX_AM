import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

    public class Circleinterface extends Application {
        public Circleinterface() {
        }

        public void start(Stage primaryStage) {
            primaryStage.setScene(new Scene(this.initInterface(), 600.0D, 600.0D));
            primaryStage.show();
        }

        private GridPane initInterface() {
            GridPane gp = new GridPane();
            Label l_1 = new Label("Цвет");
            Label l_2 = new Label("Фон");
            Label l_3 = new Label("Радиус");
            ColorPicker c_1 = new ColorPicker();
            c_1.setValue(Color.web("red"));
            ColorPicker c_2 = new ColorPicker();
            Slider s = new Slider(10.0D, 200.0D, 50.0D);
            double cir_r = 50.0D;
            Circle cir = new Circle(50.0D, 50.0D, cir_r);

            Pane p = new Pane();
            p.getChildren().add(cir);

            gp.add(l_1, 0, 0);
            gp.add(c_1, 0, 1);
            gp.add(l_2, 0, 2);
            gp.add(c_2, 0, 3);
            gp.add(l_3, 0, 4);
            gp.add(s, 0, 5);
            gp.add(p, 1, 0, 1, 7);

            ColumnConstraints column1 = new ColumnConstraints();
            column1.setMinWidth(200.0D);
            column1.setMaxWidth(200.0D);
            ColumnConstraints column2 = new ColumnConstraints();
            column2.setHgrow(Priority.ALWAYS);

            RowConstraints row = new RowConstraints();
            RowConstraints row7 = new RowConstraints();
            row7.setVgrow(Priority.ALWAYS);
            gp.getRowConstraints().addAll(row, row, row, row, row, row, row7);
            gp.getColumnConstraints().addAll(column1, column2);

            cir.radiusProperty().bind(s.valueProperty());
            cir.fillProperty().bind(c_1.valueProperty());
            cir.centerXProperty().bind(Bindings.divide(p.widthProperty(), 2));
            cir.centerYProperty().bind(Bindings.divide(p.heightProperty(), 2));
            s.maxProperty().bind(Bindings.min(p.heightProperty(), p.widthProperty()).divide(2));

            p.setBackground(new Background(new BackgroundFill(Paint.valueOf("green"), (CornerRadii)null, (Insets)null)));
            p.backgroundProperty().bind(Bindings.createObjectBinding(() -> {
                return new Background(new BackgroundFill((Paint)c_2.getValue(), (CornerRadii)null, (Insets)null));
            }, c_2.valueProperty()));

            return gp;
        }
    }
