package javafx.numberguessinggame;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class PrimaryController {
    @FXML
    private TextField guess;
    @FXML
    private Label dialog;
    @FXML
    private Label turns;
    @FXML
    private Label attemptsComp;
    @FXML
    private Label congrats;
    @FXML
    private Button computer;
    @FXML
    private Button submitbtn;
    @FXML
    private Button st;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
     
    int num, mid;
    static int attempts=0, attempts_pc=0;
    static int n=(int)(Math.random()*101);
    int low=0, high=100;
            
    public void initialize(){
        computer.setDisable(true);
        //restart.setDisable(true);
    }
    
   
    public void submit (ActionEvent e){
        try{
            num=Integer.parseInt(guess.getText());
            attempts++;
            guess.clear();
            if(num==n){
                dialog.setText("Congratulations! You guessed the correct number in "+attempts+" attempts."); 
                computer.setDisable(false);
                submitbtn.setDisable(true);
            }
            else if(num<0||num>100)
                dialog.setText("Please enter a number between 0 to 100.\nAttempt(s) used: "+attempts);
            else if(num<n)
                dialog.setText("Increase the number.\nAttempt(s) used: "+attempts); 
            else
                dialog.setText("Decrease the number.\nAttempt(s) used: "+attempts);
        }
        catch(NumberFormatException exp){
            guess.clear();
            dialog.setText("Enter an integer only.");
        }
        catch(Exception exp){
            dialog.setText("Error!");
        }
    }
    
    public void compTurn(ActionEvent e){
        Thread thread=new Thread(()->{
            while(true){
                mid=(low+high)/2;
                Platform.runLater(()->turns.setText(Integer.toString(mid)+"\nAttempt(s) used: "+(attempts_pc)));
                if(mid==n){
                    attempts_pc++;
                    Platform.runLater(()->{
                    st.setDisable(true);
                    attemptsComp.setText("Computer guessed the correct number in "+attempts_pc+" attempts.");
        
                    if(attempts>attempts_pc)
                        congrats.setText("Your attempt(s) "+attempts+" - "+attempts_pc+" Computer's attempt(s)\n\nComputer wins! Better luck next time.");
                    else if(attempts<attempts_pc)
                        congrats.setText("Your attempt(s) "+attempts+" - "+attempts_pc+" Computer's attempt(s)\n\nCONGRATULATIONS! YOU WIN.");
                    else
                        congrats.setText("Your attempt(s) "+attempts+" - "+attempts_pc+" Computer's attempt(s)\n\nThe game is tied.");  
                    });
                    break;
                }
                else if(mid>n){
                    attempts_pc++;
                    high=mid;
                }
                else if(mid<n){
                    attempts_pc++;
                    low=mid;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        thread.setDaemon(true); 
        thread.start();                
    }
    
    public void playCompter(ActionEvent event) throws IOException{
        root=FXMLLoader.load(getClass().getResource("secondary.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();      
    }
    
    public void restart(ActionEvent event) throws IOException{
        attempts=0;
        attempts_pc=0;
        n=(int)(Math.random()*101);
        root=FXMLLoader.load(getClass().getResource("primary.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();      
    }
    
    
    public void quit (ActionEvent e){
        Platform.exit();
    }
}
