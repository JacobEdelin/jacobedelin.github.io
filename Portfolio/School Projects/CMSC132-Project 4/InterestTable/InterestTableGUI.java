package gui;

import java.text.NumberFormat;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.*;

//@Written by Jacob Edelin

public class InterestTableGUI extends Application {
	private TextArea displayArea;
	private TextField principalInput, rateInput;
	private double principalValue, rateValue, yearValue, simpleInt, compoundInt;
	private int yearCounter;

	@Override /* Method in Application class */
	public void start(Stage primaryStage) {

		int sceneWidth = 525, sceneHeight = 325;
		int verSpace = 8, horSpace = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;

		/* Setting pane properties */
		FlowPane fieldsPane = new FlowPane();
		fieldsPane.setHgap(horSpace);
		fieldsPane.setVgap(verSpace);
		fieldsPane.setPadding(new Insets(paneBorderTop, paneBorderRight, paneBorderBottom, paneBorderLeft));

		/* Creating the scrollable area */
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(displayArea);

		/* Adding GUI elements */
		Label principalLabel = new Label("Principal: ");
		principalInput = new TextField();
		fieldsPane.getChildren().addAll(principalLabel, principalInput);

		Label rateLabel = new Label("Rate(Percentage): ");
		rateInput = new TextField();
		fieldsPane.getChildren().addAll(rateLabel, rateInput);

		Label sliderLabel = new Label("Number of Years: ");

		/* Creating Slider */
		Slider yearSlider = new Slider();
		yearSlider.setMin(1);
		yearSlider.setMax(25);
		yearSlider.setMajorTickUnit(4);
		yearSlider.setShowTickMarks(true);
		yearSlider.setShowTickLabels(true);

		fieldsPane.getChildren().addAll(sliderLabel, yearSlider);

		BorderPane borderPane = new BorderPane();

		Interest simpleCalculation = new Interest();
		Interest compoundCalculation = new Interest();

		/* Creating Buttons */
		Button simpleButton = new Button("Simple Interest");
		Button compoundButton = new Button("Coumpound Interest");
		Button bothButton = new Button("Both Interests");
		fieldsPane.getChildren().addAll(simpleButton, compoundButton, bothButton);

		/* Using lambda expression */
		simpleButton.setOnAction(e -> {
			principalValue = Double.parseDouble(principalInput.getText());
			rateValue = Double.parseDouble(rateInput.getText());
			yearValue = yearSlider.getValue();
			String simpleResults = "";
			yearCounter = 1;

			for (double i = 1.0; i <= yearValue; i++) {
				simpleInt = simpleCalculation.simpleInterest(principalValue, rateValue, i);
				NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
				simpleResults += yearCounter + "-->" + currencyFormat.format(simpleInt) + "\n";
				yearCounter++;
			}

			displayArea.setText(("Principal: " + "$" + String.format("%.2f", principalValue) + ", " + "Rate: "
					+ (String.format("%.1f", rateValue))) + "\n" + "Year, Simple Interest Amount" + "\n"
					+ simpleResults);
		});

		/* Using anonymous inner class */
		compoundButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				principalValue = Double.parseDouble(principalInput.getText());
				rateValue = Double.parseDouble(rateInput.getText());
				yearValue = yearSlider.getValue();
				String compoundResults = "";
				yearCounter = 1;

				for (double i = 1.0; i <= yearValue; i++) {
					compoundInt = compoundCalculation.compoundInterest(principalValue, rateValue, i);
					NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
					compoundResults += yearCounter + "-->" + currencyFormat.format(compoundInt) + "\n";
					yearCounter++;
				}

				displayArea.setText(("Principal: " + "$" + String.format("%.2f", principalValue) + ", " + "Rate: "
						+ (String.format("%.1f", rateValue))) + "\n" + "Year, Compound Interest Amount" + "\n"
						+ compoundResults);
			}
		});

		/* For non-anonymous inner class */
		class ButtonHandler implements EventHandler<ActionEvent> {
			@Override
			public void handle(ActionEvent e) {
				principalValue = Double.parseDouble(principalInput.getText());
				rateValue = Double.parseDouble(rateInput.getText());
				yearValue = yearSlider.getValue();
				String bothResults = "";
				yearCounter = 1;

				for (double i = 1.0; i <= yearValue; i++) {
					simpleInt = simpleCalculation.simpleInterest(principalValue, rateValue, i);
					compoundInt = compoundCalculation.compoundInterest(principalValue, rateValue, i);
					NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
					bothResults += yearCounter + "-->" + currencyFormat.format(simpleInt) + "-->" 
								+ currencyFormat.format(compoundInt) + "\n";
					yearCounter++;
				}

				displayArea.setText(("Principal: " + "$" + String.format("%.2f", principalValue) + 
						", " + "Rate: " + (String.format("%.1f", rateValue))) + "\n"
						+ "Year, Simple Interest Amount, Compound Interest Amount" + "\n" + bothResults);
			}
		}
		;

		/* Using non-anonymous inner class */
		bothButton.setOnAction(new ButtonHandler());

		borderPane.setTop(scrollPane);
		borderPane.setBottom(fieldsPane);

		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}