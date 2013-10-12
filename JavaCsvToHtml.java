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
                File fileIn = new File(txtFileIn.getText());

                FileHTML html = new FileHTML(txtFileOut.getText());
                html.createHTML(fileIn);
                html.close();

            }
        });
    }
}

class FileHTML{
    private PrintWriter fileOut;

    public FileHTML(String path){
        try{
            this.fileOut = new PrintWriter(path);
        }
        catch (FileNotFoundException e) {   }
    }

    public void createHTML(File input){
        HeaderHTML();
        BodyHTML(input);
        FooterHTML();
    }

    public void close(){
        this.fileOut.close();
    }

    private void HeaderHTML(){
        fileOut.println("<!DOCTYPE html><html><header><title>Tabella da CSV</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./base.css\"></header><body><table>");
        fileOut.flush();
    }

    private void FooterHTML(){
        fileOut.println("</table></body></html>");
        fileOut.flush();
    }

    private void BodyHTML(File input){
        Scanner scanner = null;
        try{
            scanner = new Scanner(input);
        }catch (FileNotFoundException e){   };

        while (scanner.hasNext()){
            String row = scanner.nextLine();
            fileOut.println("<tr>");
            String[] fields = row.split(";");
            for(String field:fields){
                fileOut.format("<td>%s</td>",field);
            }
            fileOut.println("</tr>");
        }
        scanner.close();
        fileOut.flush();
    }
}
