import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class JavaCsvToHtml{

    public static void main(String[] args){
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}

class MainFrame extends JFrame{

    public MainFrame(){
        InitUI();
    }

    private void InitUI(){
        final JTextField txtFileIn = new JTextField("/home/generic/prova.csv",10);
        final JTextField txtFileOut = new JTextField("/home/generic/index.html",10);
        JButton btnEsegui = new JButton("Esegui");

        JPanel panel = new JPanel();
        panel.add(txtFileIn);
        panel.add(txtFileOut);
        panel.add(btnEsegui);

        this.setContentPane(panel);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(150,150);

        btnEsegui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PrintWriter fileOut = new null;
                Scanner scanner = null;
                try{
                    scanner = new Scanner(new File(txtFileIn.getText()));
                    fileOut = new PrintWriter(txtFileOut.getText());
                }catch (FileNotFoundException e){   };
        
                while (scanner.hasNext()){
                    String row = scanner.nextLine();
                    fileOut.println(row+"\n");
                }
                scanner.close();
            }
        }
    }
}
