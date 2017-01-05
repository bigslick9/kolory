/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package porjekt1;


/**
 *
 * @author student
 */
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import static java.lang.Math.abs;
import static java.lang.Math.round;
import java.net.*;
import javax.imageio.*;

public class Porjekt1 extends JFrame {

    BufferedImage image;
    JLabel promptLabel;
    JTextField prompt;
    JButton promptButton;
    JFileChooser fileChooser;
    JButton loadButton;
    JButton processingButton;
    JScrollPane scrollPane;
    JLabel imgLabel;

    public Porjekt1() {
        super("Image processing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
        JPanel inputPanel = new JPanel();
        promptLabel = new JLabel("Filename:");
        inputPanel.add(promptLabel);
        prompt = new JTextField(20);
        inputPanel.add(prompt);
        promptButton = new JButton("Browse");
        inputPanel.add(promptButton);
        contentPane.add(inputPanel, BorderLayout.NORTH);
        fileChooser = new JFileChooser();
        promptButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int returnValue
                        = fileChooser.showOpenDialog(null);
                        if (returnValue
                        == JFileChooser.APPROVE_OPTION) {
                            File selectedFile
                            = fileChooser.getSelectedFile();
                            if (selectedFile != null) {
                                prompt.setText(selectedFile.getAbsolutePath());
                            }
                        }
                    }
                }
        );

        imgLabel = new JLabel();
        scrollPane = new JScrollPane(imgLabel);
        scrollPane.setPreferredSize(new Dimension(700, 500));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel();
        loadButton = new JButton("Load");
        outputPanel.add(loadButton);
        loadButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String name = prompt.getText();
                            File file = new File(name);
                            if (file.exists()) {
                                image = ImageIO.read(file.toURL());
                                if (image == null) {
                                    System.err.println("Invalid input file format");
                                } else {
                                    imgLabel.setIcon(new ImageIcon(image));
                                }
                            } else {
                                System.err.println("Bad filename");
                            }
                        } catch (MalformedURLException mur) {
                            System.err.println("Bad filename");
                        } catch (IOException ioe) {
                            System.err.println("Error reading file");
                        }
                    }
                }
        );

        processingButton = new JButton("Processing");
        outputPanel.add(processingButton);
        processingButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Processing(image);
                        imgLabel.setIcon(new ImageIcon(image));
                    }
                });

        contentPane.add(outputPanel, BorderLayout.SOUTH);
    }

    private static int getPixel(BufferedImage img, int x, int y) {
        int gray;
        int rgb = img.getRGB(x, y);
        int r = (rgb & 0x00ff0000) >>> 16;
        int g = (rgb & 0x0000ff00) >>> 8;
        int b = rgb & 0x000000ff;
        gray = (int) (0.299 * r + 0.587 * g + 0.114 * b); 
        return gray;
    }
    
    private static int Igrek(int r, int g, int b) {
        int Y;

        Y = (int) (0.299 * r + 0.587 * g + 0.114 * b); 
        return Y;
    }
    
    private static int Ju(int r, int g, int b) {
        int U;
        U = (int) (-0.147 * r - 0.289 * g + 0.436 * b); 
        return U;
    }
    
    private static int Fał(int r, int g, int b) {
        int V;
        V = (int) (0.615 * r - 0.515 * g - 0.1 * b); 
        return V;
    }
    
    private static int Igrek2(int Y, double alfa){
        int y;
        y=(int)round(Y/(22.5+alfa));
        return y;
    }
    
     private static int Ju2(int U, double alfa){
        int u;
        u=(int)round((U+111.18)/(22.236+alfa));
        return u;
    }
    
      private static int Fał2(int V, double alfa){
        int v;
        v=(int)round((V+156.825)/(31.365+alfa));
        return v;
    }
      
       private static void Processing(BufferedImage img)
 {
 int w=img.getWidth(null);
 int h=img.getHeight(null);
 for(int x=0;x<w;x++)
 for(int y=0;y<h;y++)
 {
 int rgb=img.getRGB(x, y);
 int a=(rgb&0xff000000)>>>24;
 int r=(rgb&0x00ff0000)>>>16;
 int g=(rgb&0x0000ff00)>>>8;
 int b=rgb&0x000000ff;

      
      int rr=121;
      int gg=67;
      int bb=116;
      int RGB=0;
      double alfa=1;
      if(Igrek2(Igrek(r,g,b),alfa)==Igrek2(Igrek(rr,gg,bb),alfa) && Ju2(Ju(r,g,b),alfa)==Ju2(Ju(rr,gg,bb),alfa) && Fał2(Fał(r,g,b),alfa)==Fał2(Fał(rr,gg,bb),alfa) )
        //zapis kanałów
      
 RGB=b|(g<<8)|(r<<16)|(a<<24);
 img.setRGB(x, y, RGB);
      
 }     
 }
       
       
    private static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(),
                source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    private static int filterPixel(BufferedImage img, int x, int y, int[][] maska) {
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        if (x > w || y > h || x < 0 || y < 0) {
            return 0;
        }

        int a = 0;
        int tempx = x - (maska.length / 2);
        int tempy = y - (maska[0].length / 2);

        for (int i = 0; i < maska.length; i++) {
            for (int j = 0; j < maska[0].length; j++) {
                a += getPixel(img, tempx + i, tempy + j) * maska[i][j];
            }
        }
        if (a > 255) {
            return 255;
        }
        if (a < 0) {
            return 0;
        }
        return a;
    }

//    private static void Processing(BufferedImage img) {
//        int w = img.getWidth(null);
//        int h = img.getHeight(null);
//
//        BufferedImage temp = copyImage(img);
//        int[][] maska = new int[][]{
//            {-5, 3, 3},
//            {-5, 0, 3},
//            {-5, 3, 3}};
//
//        for (int x = 1; x < w-1; x++) {
//            for (int y = 1; y < h-1; y++) {
//                int a = filterPixel(temp, x, y, maska);
//                int RGB = a | (a << 8) | (a << 16) | (a << 24);
//                img.setRGB(x, y, RGB);
//            }
//        }
//
//    }

    public static void main(String args[]) {
        JFrame frame = new Porjekt1();
        frame.pack();
        frame.show();
    }
}