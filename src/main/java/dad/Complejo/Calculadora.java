package dad.Complejo;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Calculadora extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		//ui
		
		TextField aText = new TextField();
		TextField bText = new TextField();
		TextField cText = new TextField();
		TextField dText = new TextField();
		
		TextField r1Text = new TextField();
		TextField r2Text = new TextField();
		
		Text s1Text = new Text();
		Text s2Text = new Text();
		Text s3Text = new Text();
		Separator separator = new Separator();
		
		ComboBox<String> operadorCombo = new ComboBox<String>();
		operadorCombo.getItems().addAll("+", "-", "x", "/");
		
		aText.setPrefWidth(50); bText.setPrefWidth(50);
		cText.setPrefWidth(50); dText.setPrefWidth(50);
		r1Text.setPrefWidth(50); r2Text.setPrefWidth(50);
	
		HBox root = new HBox();
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("CalculadoraView.fxml");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		VBox vbox1 = new VBox(operadorCombo);
		
		VBox vbox2 = new VBox();
		
		VBox vbox3 = new VBox();
		
		root.getChildren().addAll(vbox1, vbox2, vbox3);
		
		HBox hbox1 = new HBox(aText, s1Text, bText, new Label("  i"));
		
		HBox hbox2 = new HBox(cText, s2Text, dText, new Label("  i"));
		
		HBox hbox3 = new HBox(r1Text, s3Text, r2Text, new Label("  i"));
		
		vbox2.getChildren().addAll(hbox1, hbox2, separator,  hbox3);
		
		
		//binding
		
		DoubleProperty a = new SimpleDoubleProperty();
		DoubleProperty b = new SimpleDoubleProperty();
		DoubleProperty c = new SimpleDoubleProperty();
		DoubleProperty d = new SimpleDoubleProperty();
		DoubleProperty r1 = new SimpleDoubleProperty();
		DoubleProperty r2 = new SimpleDoubleProperty();
		StringProperty s = new SimpleStringProperty();
		
		r1.addListener((o, ov, nv)-> System.out.print("result = " + nv));
		r2.addListener((o, ov, nv)-> System.out.print("result = " + nv));
		
		//bindeamos las variables a los cuadros de texto
		//Conectar int con string
		aText.textProperty().bindBidirectional(a, new NumberStringConverter());
		bText.textProperty().bindBidirectional(b, new NumberStringConverter());
		cText.textProperty().bindBidirectional(c, new NumberStringConverter());
		dText.textProperty().bindBidirectional(d, new NumberStringConverter());
		s1Text.textProperty().bindBidirectional(s);
		s2Text.textProperty().bindBidirectional(s);
		s3Text.textProperty().bindBidirectional(s);
		
		//conectamos el resultado con el conector
		//Conectar string con int 
		r1Text.textProperty().bind(r1.asString());
		r2Text.textProperty().bind(r2.asString());
		
		s.set("  +  ");
		
		
		//Listener
		operadorCombo.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) ->{ 
		System.out.print("result = " + nv);
		
		switch (nv) {
		case "+":
			s.set("  +  ");
			r1.bind(a.add(c));
			r2.bind(b.add(d));
		break;
		case "-":
			s.set("  -  ");
			r1.bind(a.subtract(b));
			r2.bind(c.subtract(d));
		break;
		case "x":
			s.set("  x  ");
			r1.bind(a.multiply(c).subtract(b.multiply(d)));
			r2.bind(a.multiply(d).add(b.multiply(c)));
		break;
		case "/":
			s.set("  /  ");
			r1.bind((a.multiply(c).add(b.multiply(d))).divide(c.multiply(c).add(d.multiply(d))));
			r2.bind((b.multiply(c).subtract(a.multiply(d))).divide(c.multiply(c).add(d.multiply(d))));
		break;
		}
		
		});

		operadorCombo.getSelectionModel().selectFirst();
	}

}
