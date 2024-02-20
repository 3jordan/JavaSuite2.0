package org.example.javasuitejavafx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

import static org.example.javasuitejavafx.Contact.createContact;

public class Home extends Application {
    Stage Window;
    Scene sceneHome, scenePersonalOrganization, sceneDataManagement, sceneGames, sceneContactBook, sceneExpenseTracker, sceneSiteConnectivityChecker, sceneConversionCalculator, sceneHangman, sceneTicTacToe;
    private ExpenseTracker expenseTracker = new ExpenseTracker();

    ListView<String> expenseListView = getExpenseListView();
    ListView<String> incomeListView = getIncomeListView();

    public ListView<String> getExpenseListView() {
        return expenseListView;
    }

    public ListView<String> getIncomeListView() {
        return incomeListView;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Window = primaryStage;
        expenseTracker = new ExpenseTracker();

        // UI Components
        Label siteLabel = new Label("Site Connectivity Checker");
        Label resultLabel = new Label();
        TextField urlField = new TextField();
        urlField.setPromptText("Enter URL here");
        Button checkButton = new Button("Check");

        //Event handling for the check button
        checkButton.setOnAction(e -> {
            String url = urlField.getText().trim();
            if (!url.isEmpty()) {
                String result = SiteChecker.isSiteUp(url);
                resultLabel.setText(result);
            } else {
                resultLabel.setText("Please enter a URL");
            }
        });

        // Home Buttons
        Button homeButton = new Button("Home");
        Button homeButton2 = new Button("Home");
        Button homeButton3 = new Button("Home");
        Button homeButton4 = new Button("Home");
        Button homeButton5 = new Button("Home");
        Button homeButton6 = new Button("Home");
        Button homeButton7 = new Button("Home");
        Button homeButton8 = new Button("Home");
        Button homeButton9 = new Button("Home");
        Button homeButton10 = new Button("Home");


        // Home Button Action
        homeButton.setOnAction(e -> Window.setScene(sceneHome));
        homeButton2.setOnAction(e -> Window.setScene(sceneHome));
        homeButton3.setOnAction(e -> Window.setScene(sceneHome));
        homeButton4.setOnAction(e -> Window.setScene(sceneHome));
        homeButton5.setOnAction(e -> Window.setScene(sceneHome));
        homeButton6.setOnAction(e -> Window.setScene(sceneHome));
        homeButton7.setOnAction(e -> Window.setScene(sceneHome));
        homeButton8.setOnAction(e -> Window.setScene(sceneHome));
        homeButton9.setOnAction(e -> Window.setScene(sceneHome));
        homeButton10.setOnAction(e-> Window.setScene(sceneHome));

        // Personal Organization Content
        Button personalButton = new Button("Personal Organization");
        Label personalLabel = new Label("Personal Organization");
        personalButton.setOnAction(e -> Window.setScene(scenePersonalOrganization));

        // Fetch contacts (for testing/debugging)
        System.out.println("Fetching contacts...");
        List<Contact> contacts = Contact.allContacts();
        System.out.println("Fetched " + contacts.size() + " contacts.");

        // Contact Book Section
        Button contactButton = new Button("Contact Book");
        contactButton.setOnAction(e -> {
            System.out.println("Switching to Contact Book scene...");
            Window.setScene(sceneContactBook);
        });
        Label contactLabel = new Label("Contact Book");

        // Create ListView to display contacts
        ListView<String> contactListView = new ListView<>();
        for (Contact contact : contacts) {
            contactListView.getItems().add(contact.toString());
        }

        // Create layout for the contact book section
        VBox contactPage = new VBox(20);
        contactPage.getChildren().addAll(contactLabel, contactListView);
        centerAlign(contactPage);
        sceneContactBook = new Scene(contactPage, 400, 400);



        // Expense Tracker
        Button expenseButton = new Button("Expense Tracker");
        Label expenseLabel = new Label("Expense Tracker");
        expenseButton.setOnAction(e -> Window.setScene(sceneExpenseTracker));

        // Data Management Content
        Label dataLabel = new Label("Data Management");
        Button dataButton = new Button("Data Management");
        dataButton.setOnAction(e -> Window.setScene(sceneDataManagement));

        // Site Connectivity Checker
        Button siteButton = new Button("Site Connectivity Checker");

        // Conversion Calculator Layout
        VBox conversionCalculatorPage = new VBox(20);
        conversionCalculatorPage.setAlignment(Pos.CENTER);
        Label conversionLabel = new Label("Conversion Calculator");
        TextField inputField = new TextField();
        TextField resultField = new TextField();

        //ChoiceBox for selecting the input type
        ChoiceBox<String> inputChoice = new ChoiceBox<>();
        inputChoice.getItems().addAll("Inch", "Feet", "Yard", "Centimeter", "Meter");
        inputChoice.setValue("Inch");

        //ChoiceBox for selecting the output type
        ChoiceBox<String> outputChoice = new ChoiceBox<>();
        outputChoice.getItems().addAll("Inch", "Feet", "Yard", "Centimeter", "Meter");
        outputChoice.setValue("Inch");


        Button calculateButton = new Button("Calculate");
        Button backButton = new Button("Back");

        calculateButton.setOnAction(e -> {
            String inputType = inputChoice.getValue();
            String outputType = outputChoice.getValue();
            double input = Double.parseDouble(inputField.getText());
            String result = GeometryCalculator.convert(inputType, input, outputType);
            resultField.setText(result);
        });

        backButton.setOnAction(e -> Window.setScene(sceneHome));

        conversionCalculatorPage.getChildren().addAll(conversionLabel, inputField, inputChoice, outputChoice, calculateButton, resultField, backButton);
        sceneConversionCalculator = new Scene(conversionCalculatorPage, 400, 400);
        centerAlign(conversionCalculatorPage);



        // Games Content
        Label gameLabel = new Label("Games");
        Button button3 = new Button("Games");
        button3.setOnAction(e -> Window.setScene(sceneGames));

        // Hangman
        Button hangmanButton = new Button("Hangman");
        Label hangmanLabel = new Label("Hangman");
        hangmanButton.setOnAction(e -> Window.setScene(sceneHangman));


        // Tic-Tac-Toe
        Button tttButton = new Button("Tic-Tac-Toe");
        Label tttLabel = new Label("Tic-Tac-Toe");
        tttButton.setOnAction(e -> Window.setScene(sceneTicTacToe));


        // Home Page Layout
        Label homeLabel = new Label("Home Page");
        VBox homePage = new VBox(20);
        homePage.getChildren().addAll(homeLabel, personalButton, dataButton);
        sceneHome = new Scene(homePage, 400, 400);
        centerAlign(homePage);


        // Personal Organization Layout
        VBox personalOrganizationPage = new VBox(20);
        personalOrganizationPage.getChildren().addAll(personalLabel, contactButton, expenseButton, homeButton);
        scenePersonalOrganization = new Scene(personalOrganizationPage, 200, 200);
        centerAlign(personalOrganizationPage);

        // Contact Book Layout
        VBox contactBookPage = new VBox(20);
        contactBookPage.getChildren().addAll(contactLabel, homeButton4);
        sceneContactBook = new Scene(contactBookPage, 200, 200);
        centerAlign(contactBookPage);

        // Expense Tracker Page Layout
        VBox expenseTrackerPage = new VBox(20);
        expenseTrackerPage.getChildren().add(expenseLabel);
        centerAlign(expenseTrackerPage);

        // Logic for adding an expense
        addButton(expenseTrackerPage, "Add Expense", () -> {
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setHeaderText("Add Expense");
            nameDialog.setContentText("Enter the name of the new expense:");
            Optional<String> nameResult = nameDialog.showAndWait();
            if (nameResult.isPresent()) {
                String name = nameResult.get();
                TextInputDialog valueDialog = new TextInputDialog();
                valueDialog.setHeaderText("Add Expense");
                valueDialog.setContentText("Enter the value of the new expense:");
                Optional<String> valueResult = valueDialog.showAndWait();
                if (valueResult.isPresent()) {
                    try {
                        double value = Double.parseDouble(valueResult.get());
                        expenseTracker.addExpense(name, value);
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("Please enter a valid numerical value.");
                        alert.showAndWait();
                    }
                }
            }
            updateExpenseListView(expenseListView);
        });

        // Logic for removing an expense
        addButton(expenseTrackerPage, "Remove Expense", () -> {
            TextInputDialog indexDialog = new TextInputDialog();
            indexDialog.setHeaderText("Remove Expense");
            indexDialog.setContentText("Enter the index of the expense to remove:");
            Optional<String> indexResult = indexDialog.showAndWait();
            if (indexResult.isPresent()) {
                try {
                    int index = Integer.parseInt(indexResult.get());
                    expenseTracker.removeExpense(index);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please enter a valid integer value.");
                    alert.showAndWait();
                }
            }
            updateExpenseListView(expenseListView);
        });

        // Logic for adding an income
        addButton(expenseTrackerPage, "Add Income", () -> {
            TextInputDialog nameDialog = new TextInputDialog();
            nameDialog.setHeaderText("Add Income");
            nameDialog.setContentText("Enter the name of the new income:");
            Optional<String> nameResult = nameDialog.showAndWait();
            if (nameResult.isPresent()) {
                String name = nameResult.get();
                TextInputDialog valueDialog = new TextInputDialog();
                valueDialog.setHeaderText("Add Income");
                valueDialog.setContentText("Enter the value of the new income:");
                Optional<String> valueResult = valueDialog.showAndWait();
                if (valueResult.isPresent()) {
                    try {
                        double value = Double.parseDouble(valueResult.get());
                        expenseTracker.addIncome(name, value);
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("Invalid Input");
                        alert.setContentText("Please enter a valid numerical value.");
                        alert.showAndWait();
                    }
                }
            }
            updateIncomeListView(incomeListView);
        });

        // Logic for removing an income
        addButton(expenseTrackerPage, "Remove Income", () -> {
            TextInputDialog indexDialog = new TextInputDialog();
            indexDialog.setHeaderText("Remove Income");
            indexDialog.setContentText("Enter the index of the income to remove:");
            Optional<String> indexResult = indexDialog.showAndWait();
            if (indexResult.isPresent()) {
                try {
                    int index = Integer.parseInt(indexResult.get());
                    expenseTracker.removeIncome(index);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Invalid Input");
                    alert.setContentText("Please enter a valid integer value.");
                    alert.showAndWait();
                }
            }
            updateIncomeListView(incomeListView);
        });


        backButton.setOnAction(e -> primaryStage.setScene(sceneHome));
        expenseTrackerPage.getChildren().add(backButton);

        // Create ListViews to display expenses and incomes
        expenseListView = new ListView<>();
        incomeListView = new ListView<>();

        // Refresh lists with existing expenses and incomes
        updateExpenseListView(expenseListView);
        updateIncomeListView(incomeListView);

        // Add list views to layout
        expenseTrackerPage.getChildren().addAll(new Label("Expenses:"), expenseListView);
        expenseTrackerPage.getChildren().addAll(new Label("Incomes:"), incomeListView);


        sceneExpenseTracker = new Scene(expenseTrackerPage, 400, 400);


        // Data Management Layout
        VBox dataManagementPage = new VBox(20);
        Button conversionButton = new Button("Conversion Calculator");
        conversionButton.setOnAction(e -> Window.setScene(sceneConversionCalculator));
        dataManagementPage.getChildren().addAll(dataLabel, siteButton, conversionButton, homeButton2);
        sceneDataManagement = new Scene(dataManagementPage, 200, 200);
        centerAlign(dataManagementPage);

        // Site Connectivity Checker Layout
        VBox siteConnectivityPage = new VBox(20);
        siteConnectivityPage.getChildren().addAll(siteLabel, urlField, checkButton, resultLabel, homeButton10);
        siteConnectivityPage.setAlignment(Pos.CENTER);
        Scene sceneSiteConnectivityChecker = new Scene(siteConnectivityPage, 300, 200);
        siteButton.setOnAction(e -> Window.setScene(sceneSiteConnectivityChecker));


        // Games Layout
        VBox gamesPage = new VBox(20);
        gamesPage.getChildren().addAll(gameLabel, hangmanButton, tttButton, homeButton3);
        sceneGames = new Scene(gamesPage, 200, 200);
        centerAlign(gamesPage);

        // Hangman Layout
        VBox hangmanPage = new VBox(20);
        hangmanPage.getChildren().addAll(hangmanLabel, homeButton8);
        sceneHangman = new Scene(hangmanPage, 200, 200);
        centerAlign(hangmanPage);

        // Tic Tac Toe Layout
        VBox ticTacToePage = new VBox(20);
        ticTacToePage.getChildren().addAll(tttLabel, homeButton9);
        sceneTicTacToe = new Scene(ticTacToePage, 200, 200);
        centerAlign(ticTacToePage);


        Window.setScene(sceneHome);
        Window.setTitle("Scene Changer");
        Window.show();
    }

    private void centerAlign(VBox vbox) {
        vbox.setAlignment(Pos.CENTER);
        vbox.setFillWidth(true);
    }

    private void updateExpenseListView(ListView<String> listView) {
        listView.getItems().clear();
        for (Expense expense : ExpenseTracker.Expenses) {
            listView.getItems().add(expense.toString());
        }
    }

    private void updateIncomeListView(ListView<String> listView) {
        listView.getItems().clear();
        for (Income income : ExpenseTracker.IncomeList) {
            listView.getItems().add(income.toString());
        }
    }

    private void addButton(VBox parent, String buttonText, Runnable action) {
        Button button = new Button(buttonText);
        button.setOnAction(e -> action.run());
        parent.getChildren().add(button);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
