package sample;

import AntiUI.FileOperator;
import AntiUI.PageTable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {
    private PageTable pageTable = new PageTable();
    private FileOperator operator = FileOperator.getInstance();
    private ComboBox<Integer> comboBox;
    private TextField fileName;
    private TextField pageStack;
    private TextArea textArea;
    private Button openFile;
    private Button addPage;
    private Button read;
    private Button save;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        initAllControllers(root);
        primaryStage.setTitle("PageHandle");
        primaryStage.setScene(new Scene(root, 630, 550));
        primaryStage.show();
    }

    private void initAllControllers(Parent root) {
        comboBox = (ComboBox<Integer>) root.lookup("#com_box");
        fileName = (TextField) root.lookup("#file_name");
        textArea = (TextArea) root.lookup("#text_area");
        pageStack = (TextField) root.lookup("#page_stack");
        openFile = (Button) root.lookup("#open_file");
        openFile.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                operator.initialFile(fileName.getText());
                refreshComboBox();
            }
        });

        read = (Button) root.lookup("#read");
        read.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textArea.clear();
                int pageNumber = comboBox.getValue();
                pageTable.readText(pageNumber);
                byte[] textContent = pageTable.getTextContent();
                byte[] line = new byte[128];
                for (int i = 0; i * 128 < 4 * 1024; i++) {
                    System.arraycopy(textContent, i * 128, line, 0, 128);
                    String tempContent = new String(line);
                    textArea.appendText(tempContent + "\n");
                }
                refreshStack();


            }
        });

        save = (Button) root.lookup("#save");
        save.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        addPage = (Button) root.lookup("#add_page");
        addPage.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

    }

    private void refreshComboBox() {
        for (int i = 0; i < operator.getPages().size(); i++) {
            comboBox.getItems().addAll(i);
        }

    }

    private void refreshStack() {
        pageStack.clear();
        for (int i = 0; i < pageTable.getSize(); i++) {
            pageStack.appendText(pageTable.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
