import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class PingTool {

  public static void main(String[] arguments) {

    JFrame frame = new JFrame("Ping tool by charilog v0.1");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    final JProgressBar bar = new JProgressBar(0, 100);
    final JButton ping = new JButton("ping");
    final JTextField text = new JTextField(20);
    final JTextArea results = new JTextArea();
    text.setText("www.ce.teiep.gr");
    bar.setVisible(false);
    bar.setBackground(new Color(15, 68, 18));
    ping.addActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        Thread t = new Thread() {
          public void run() {
            try {
              Process p = Runtime.getRuntime().exec("ping " + text.getText());
              BufferedReader inputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
              String s = "", r = "";
              int n = 0;
              ping.setEnabled(false);
              bar.setVisible(true);
              while ((s = inputStream.readLine()) != null) {
                r += s + "\n";
                //System.out.println(s);
                Thread.sleep(1000);
                bar.setValue(n);
                results.setText(r);
                n += 10;
              }
              ping.setEnabled(true);
              bar.setVisible(false);
              ping.setFocusPainted(true);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        };
        t.start();
      }
    });
    frame.getContentPane().setBackground(new Color(15, 68, 18));
    frame.setLayout(new FlowLayout());
    frame.add(text);
    frame.add(ping);
    results.setRows(12);
    results.setColumns(30);
    results.setForeground(new Color(255, 255, 255));
    results.setBackground(new Color(15, 68, 18));
    frame.add(results);
    frame.add(bar);
    frame.pack();
    frame.setSize(360, 300);
    frame.setResizable(false);
    frame.setVisible(true);
  }
}
