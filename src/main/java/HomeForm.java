//بسم الله الرحمن الرحیم


import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.Position;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HomeForm extends JFrame {
    JButton status1Button,status2Button;
    JTextField getSizePopilation;
    JTextField getMinOfGenes,getMaxOfGenes;
    JLabel getMinOfGenesLabel,getMaxOfGenesLabel,getSizePopilationLabel,getMinOfGenesGuideLabel,getMaxOfGenesGuideLabel,getSizePopilationGuideLabel;
    JLabel abasBagheri;
    JTextField getMaxTime,getSutibleFitness,getMutationPropebly;
    JLabel getMaxTimeLabel,getSutibleFitnessLabel,getMutationPropeblyLabel;
    JLabel getMaxTimeGuideLabel,getSutibleFitnessGuideLabel,getMutationPropeblyGuideLabel;
    public HomeForm(){
        this.setLayout(null);
        this.setSize(900,600);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.white);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        status1Button = new JButton();
        status1Button.setText(" حالت ۱");
        status1Button.setBounds(300,450,140,50);
        status2Button = new JButton();
        status2Button.setText(" حالت ۲");
        status2Button.setBounds(460,450,140,50);
        status1Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        status2Button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        status1Button.setBackground(new Color(240,240,240));
        status2Button.setBackground(new Color(240,240,240));
        getSizePopilation = new JTextField();
        getSizePopilation.setBounds(475,100,225,50);
        this.add(getSizePopilation);

        getMaxOfGenes = new JTextField();
        getMaxOfGenes.setBounds(475,200,225,50);

        getMaxOfGenes.setBackground(new Color(251,251,251));

        getMinOfGenes = new JTextField();
        getMinOfGenes.setBounds(475,300,225,50);
        getMinOfGenes.setBackground(new Color(251,251,251));
        getSizePopilation.setBackground(new Color(251,251,251));


        this.add(getMaxOfGenes);
        this.add(getMinOfGenes);
        this.add(status1Button);
        this.add(status2Button);


        getMinOfGenesLabel = new JLabel("کمترین تعداد ژن :",SwingConstants.RIGHT);
        getMaxOfGenesLabel = new JLabel("بیشترین تعداد ژن :",SwingConstants.RIGHT);
        getSizePopilationLabel = new JLabel("تعداد جمعیت : ",SwingConstants.RIGHT);
        this.add(getMaxOfGenesLabel);
        this.add(getMinOfGenesLabel);
        this.add(getSizePopilationLabel);


        getMinOfGenesLabel.setBounds(700,315,120,20);
        getMaxOfGenesLabel.setBounds(700,215,120,20);
        getSizePopilationLabel.setBounds(700,115,120,20);
        //status1Button.setBorder(BorderUIResource.getBlackLineBorderUIResource());


        getMinOfGenesGuideLabel = new JLabel("*حداقل عدد ۴ و حداکثر ۳۲ را وارد کنید.",SwingConstants.RIGHT);
        getMaxOfGenesGuideLabel = new JLabel("*حداقل عدد ۸ و حداکثر ۲۵۶ را وارد کنید.",SwingConstants.RIGHT);
        getSizePopilationGuideLabel = new JLabel("* لطفا عدد زوج وارد کنید.",SwingConstants.RIGHT);

        this.add(getMinOfGenesGuideLabel);
        this.add(getMaxOfGenesGuideLabel);
        this.add(getSizePopilationGuideLabel);

        getSizePopilationGuideLabel.setBounds(475,160,225,12);
        getMaxOfGenesGuideLabel.setBounds(475,260,225,12);
        getMinOfGenesGuideLabel.setBounds(475,360,225,12);

        abasBagheri = new JLabel("عباس باقری",SwingConstants.CENTER);
        abasBagheri.setBounds(400,30,100,16);
        this.add(abasBagheri);


        getMaxTime = new JTextField();
        getMutationPropebly = new JTextField();
        getSutibleFitness = new JTextField();

        this.add(getMaxTime);
        this.add(getMutationPropebly);
        this.add(getSutibleFitness);

        getMutationPropebly.setBounds(80,100,225,50);
        getSutibleFitness.setBounds(80,200,225,50);
        getMaxTime.setBounds(80,300,225,50);

        getMutationPropebly.setBackground(new Color(251,251,251));
        getSutibleFitness.setBackground(new Color(251,251,251));
        getMaxTime.setBackground(new Color(251,251,251));

        getMutationPropebly.setMargin(new Insets(0,10,0,10));
        getSutibleFitness.setMargin(new Insets(0,10,0,10));
        getMaxTime.setMargin(new Insets(0,10,0,10));


        getMaxTimeLabel = new JLabel("حداکثر زمان اجرا : ",SwingConstants.RIGHT);
        getSutibleFitnessLabel = new JLabel("برازش مناسب : ",SwingConstants.RIGHT);
        getMutationPropeblyLabel = new JLabel("احتمال وقوع جهش : ",SwingConstants.RIGHT);

        this.add(getMaxTimeLabel);
        this.add(getMutationPropeblyLabel);
        this.add(getSutibleFitnessLabel);

        getMutationPropeblyLabel.setBounds(305,115,120,20);
        getSutibleFitnessLabel.setBounds(305,215,120,20);
        getMaxTimeLabel.setBounds(305,315,120,20);



        getMaxTimeGuideLabel = new JLabel("*زمان بر حسب میلی ثانیه وارد کنید.",SwingConstants.RIGHT);
        getSutibleFitnessGuideLabel =  new JLabel("*مقدار مورد نظر خود را وارد کنید.",SwingConstants.RIGHT);
        getMutationPropeblyGuideLabel =  new JLabel("*عددی بین صفر تا صد وارد کنید.",SwingConstants.RIGHT);

        this.add(getMaxTimeGuideLabel);
        this.add(getSutibleFitnessGuideLabel);
        this.add(getMutationPropeblyGuideLabel);

        getMutationPropeblyGuideLabel.setBounds(80,160,225,12);
        getSutibleFitnessGuideLabel.setBounds(80,260,225,12);
        getMaxTimeGuideLabel.setBounds(80,360,225,12);


        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Main.getPath("fonts/BNaznnBd.ttf"))).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            getMinOfGenesLabel.setFont(customFont);
            getMaxOfGenesLabel.setFont(customFont);
            getSizePopilationLabel.setFont(customFont);
            status1Button.setFont(customFont);
            status2Button.setFont(customFont);
            getMinOfGenes.setFont(customFont);
            getMaxOfGenes.setFont(customFont);
            getSizePopilation.setFont(customFont);
            abasBagheri.setFont(customFont);
            getMaxTime.setFont(customFont);
            getSutibleFitness.setFont(customFont);
            getMutationPropebly.setFont(customFont);
            getSutibleFitnessLabel.setFont(customFont);
            getMutationPropeblyLabel.setFont(customFont);
            getMaxTimeLabel.setFont(customFont);

            getMaxTimeLabel.setForeground(new Color(63,63,63));
            getSutibleFitnessLabel.setForeground(new Color(63,63,63));
            getMutationPropeblyLabel.setForeground(new Color(63,63,63));

            getSizePopilationLabel.setForeground(new Color(63,63,63));
            getMaxOfGenesLabel.setForeground(new Color(63,63,63));
            getMinOfGenesLabel.setForeground(new Color(63,63,63));


        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File(Main.getPath("fonts/BNazanin.ttf"))).deriveFont(13f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            getSizePopilationGuideLabel.setFont(customFont);
            getMaxOfGenesGuideLabel.setFont(customFont);
            getMinOfGenesGuideLabel.setFont(customFont);
            getMutationPropeblyGuideLabel.setFont(customFont);
            getMaxTimeGuideLabel.setFont(customFont);
            getSutibleFitnessGuideLabel.setFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }


        getMinOfGenes.setMargin(new Insets(0,10,0,10));
        getMaxOfGenes.setMargin(new Insets(0,10,0,10));
        getSizePopilation.setMargin(new Insets(0,10,0,10));

    }

    public JButton getStatus1Button() {
        return status1Button;
    }
}
