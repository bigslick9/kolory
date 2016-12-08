/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package zajecia08.pkg12;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

public class Main extends JFrame {
 BufferedImage image;
 JLabel promptLabel;
 JTextField prompt;
 JButton promptButton;
 JFileChooser fileChooser;
 JButton loadButton;
 JButton processingButton;
 JScrollPane scrollPane;
 JLabel imgLabel;

 public Main() {
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
 int returnValue =
 fileChooser.showOpenDialog(null);
 if (returnValue ==
 JFileChooser.APPROVE_OPTION) {
 File selectedFile =
 fileChooser.getSelectedFile();
 if (selectedFile != null) {
 prompt.setText(selectedFile.getAbsolutePath());
 }
 }
 }
 }
 );

 imgLabel = new JLabel();
 scrollPane = new JScrollPane(imgLabel);
 scrollPane.setPreferredSize(new Dimension(700,500));
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

 private static int Szarosc(BufferedImage img, int w, int h)
 {

 double gray;
 int rgb=img.getRGB(w, h);
 int a=(rgb&0xff000000)>>>24;
 int r=(rgb&0x00ff0000)>>>16;
 int g=(rgb&0x0000ff00)>>>8;
 int b=rgb&0x000000ff;
 gray=0.299*r + 0.587*g + 0.114*b;
   return (int)gray;
 }
 
 private static void Processing(BufferedImage img)
 {
 int w=img.getWidth(null);
 int h=img.getHeight(null);
 double gray;
 double color;
 double stala=1.2;
 for(int x=0;x<w;x++)
 for(int y=0;y<h;y++){
 int a = Szarosc(img,x,y);
 int r = Szarosc(img,x,y);;
 int g = Szarosc(img,x,y);;
 int b = Szarosc(img,x,y);;
     
// {
// int rgb=img.getRGB(x, y);
// int a=(rgb&0xff000000)>>>24;
// int r=(rgb&0x00ff0000)>>>16;
// int g=(rgb&0x0000ff00)>>>8;
// int b=rgb&0x000000ff;
 //zadanie2,3
// gray=0.299*r + 0.587*g + 0.114*b;
// r=(int)gray;
//    if(r>130)
//        r=255;
//    else
//        r=0;
// g=(int)gray;
//     if(g>130)
//        g=255;
//    else
//        g=0;
// b=(int)gray;
//     if(b>130)
//        b=255;
//    else
//        b=0;
 //zadanie 4
//    a=255-a;
//    r=255-r;
//    g=255-g;
//    b=255-b;
    //zadanie5
//    a=(int)((a-128)*stala)+128;
//    r=(int)((r-128)*stala)+128;
//    g=(int)((g-128)*stala)+128;
//    b=(int)((b-128)*stala)+128;
//        if(a<0)
//            a=0;
//        else if(a>255)
//            a=255;
//        if(r<0)
//            r=0;
//        else if(r>255)
//            r=255;
//        if(g<0)
//            g=0;
//        else if(g>255)
//            g=255;
//        if(b<0)
//            b=0;
//        else if(b>255)
//            b=255;
    //zadanie6
//    a=(int)(a*stala);
//    r=(int)(r*stala);
//    g=(int)(g*stala);
//    b=(int)(b*stala);
//        if(a<0)
//            a=0;
//        else if(a>255)
//            a=255;
//        if(r<0)
//            r=0;
//        else if(r>255)
//            r=255;
//        if(g<0)
//            g=0;
//        else if(g>255)
//            g=255;
//        if(b<0)
//            b=0;
//        else if(b>255)
//            b=255;
 //tu można modyfikować wartość kanałów


 //zapis kanałów
 int RGB=b|(g<<8)|(r<<16)|(a<<24);
 img.setRGB(x, y, RGB);
 }
 }
 
 public static void main(String args[]) {
 JFrame frame = new Main();
 frame.pack();
 frame.show();
 }
}

