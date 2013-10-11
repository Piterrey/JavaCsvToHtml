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
        final JTextField txtFileIn = new JTextField("/home/generic/input",10);
        final JTextField txtFileOut = new JTextField("/home/generic/input",10);
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

                PrintWriter fileOut = null;
                try{
                fileOut = new PrintWriter(txtFileOut.getText());
                }
                catch (FileNotFoundException e) {   }


                HeaderHTML(fileOut);
                BodyHTML(fileIn,fileOut);
                FooterHTML(fileOut);

                fileOut.close();
            }

            private void HeaderHTML(PrintWriter output){
                //TODO: <!DOCTYPE html><html><header><title>Tabella da CSV</title></header><body><table border="1">
                output.println("<!DOCTYPE html><html><header><title>Tabella da CSV</title><link rel=\"stylesheet\" type=\"text/css\" href=\"./base.css\"></header><body><table>");
            }

            private void FooterHTML(PrintWriter output){
                //TODO: </table></body></html>
                output.println("</table></body></html>");
            }

            private void BodyHTML(File input, PrintWriter output){
                Scanner scanner = null;
                try{
                    scanner = new Scanner(input);
                }catch (FileNotFoundException e){   };

                while (scanner.hasNext()){
                    String row = scanner.nextLine();
                    //todo: <tr>
                    output.println("<tr>");
                    String[] fields = row.split(";");
                    for(String field:fields){
                        //todo: <td>field</td>
                        output.format("<td>%s</td>",field);
                    }
                    //todo: </tr>
                    output.println("</tr>");
                }
                scanner.close();
            }
        });
    }
}
