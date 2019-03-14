//بسم الله الرحمن الرحیم

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ShowResultForm extends JFrame {
    int counter = 0;
    JLabel timer;
    Font customFont;
    public ShowResultForm(){
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);
        this.setSize(500,360);
        this.setResizable(false);
        timer = new JLabel("0s : انجام شده در",SwingConstants.RIGHT);
        this.add(timer);
        timer.setBounds(290,16,200,14);
        try {
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Main.getPath("fonts/BNaznnBd.ttf"))).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            timer.setFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void addResult(long result){
        JLabel newResult = new JLabel(   " نتیجه برای اجرای " + counter +  " ام : " +  result, SwingConstants.RIGHT);
        newResult.setBounds(20,40+(counter*20), 460, 20);
        this.add(newResult);
        if (customFont!=null){
            newResult.setFont(customFont);
        }
        counter++;

    }
}
