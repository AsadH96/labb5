import Model.EntertainmentModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * NB! To run this application from a shell, e.g. Windows
 * command prompt, use
 * java -classpath ".;.\the_name_of_the_connector.jar" JDBCTest 
 * <username> <password>
 * In the above example, the jar is located in the current directory.
 * 
 * If you use Eclipse or NetBeans, read the documentation on where to 
 * put the jar file.
 * 
 * @Ulsbold Altangerel
 */
public class EntertainmentMain extends Application{
    private static EntertainmentModel model;
    @Override
    public void start(Stage primaryStage) {

        EntertainmentView view = new EntertainmentView(model);

        Scene scene = new Scene(view,400,400);

        primaryStage.setTitle("Cerberus");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public static void main(String[] args) throws Exception {
        model = new EntertainmentModel(args);
        launch();
        
    }
}
