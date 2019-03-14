//بسم الله الرحمن الرحیم

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SplashForm extends JFrame{
    private JPanel splashForm;
    private JLabel godName;

    public SplashForm(){
        add(splashForm);
        setSize(500,350);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setBackground(Color.white);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Main.getPath("fonts/BNaznnBd.ttf"))).deriveFont(28f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            godName.setFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
    }
}
