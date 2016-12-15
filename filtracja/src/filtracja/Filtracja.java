/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package filtracja;

import java.awt.*;
 import java.awt.event.*;
import java.awt.image.*;
 import javax.swing.*;
 import java.io.*;
 import java.net.*;
 import javax.imageio.*;
 
 public class Filtracja extends JFrame {
  BufferedImage image;
  JLabel promptLabel;
  JTextField prompt;
  JButton promptButton;
  JFileChooser fileChooser;
  JButton loadButton;
  JButton processingButton;
  JScrollPane scrollPane;
  JLabel imgLabel;
 
  public Filtracja() {
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
  
  private static BufferedImage copyImage(BufferedImage source) {
 BufferedImage b = new BufferedImage(source.getWidth(),
source.getHeight(), source.getType());
 Graphics g = b.getGraphics();
 g.drawImage(source, 0, 0, null);
 g.dispose();
 return b;
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
      BufferedImage temp = copyImage(img);
  int[][] maska = new int[][]{
    {3, 3, 3},
    {-5, 0, 3},
    {-5, -5, 3},
    };
  
  int w=img.getWidth(null);
  int h=img.getHeight(null);
  for(int x=1;x<w-1;x++)
  for(int y=1;y<h-1;y++){
  int a = filterPixel(temp,x,y,maska);
    int RGB = a | (a << 8) | (a << 16) | (255 << 24);
    img.setRGB(x,y,RGB);  
      
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

  img.setRGB(x, y, RGB);
  }
  }
  private static int getPixel(BufferedImage img, int x, int y){
    double gray;
  int rgb=img.getRGB(x, y);
  int a=(rgb&0xff000000)>>>24;
  int r=(rgb&0x00ff0000)>>>16;
  int g=(rgb&0x0000ff00)>>>8;
  int b=rgb&0x000000ff;
  gray=0.299*r + 0.587*g + 0.114*b;
    return (int)gray; 
  }
  
  private static int filterPixel(BufferedImage img, int x, int y, int[][] maska){
    int w=img.getWidth(null);
    int h=img.getHeight(null);
    if((x>w || y>h || x<0 || y<0 )){
        return 0;
    }
      int f=0;
      int xtemp=x-(maska.length/2);
      int ytemp=y-(maska[0].length/2);
  for(int i=0;i<maska.length;i++)
    for(int j=0;j<maska[0].length;j++){
       f+=getPixel(img,xtemp+i,ytemp+j)*maska[i][j];
//+getPixel(img,x-1,y)*maska[i][j+1]+getPixel(img,x-1,y+1)*maska[i][j+2]+getPixel(img,x,y-1)*maska[i+1][j]+getPixel(img,x,y)*maska[i+1][j+1]+getPixel(img,x,y+1)*maska[i+1][j+2]+getPixel(img,x+1,y-1)*maska[i+2][j]+getPixel(img,x+1,y)*maska[x+2][y+1]+getPixel(img,x+1,y+1)*maska[x+2][y+2];
  }
  if(f>255)
      return 255;
  if(f<0)
      return 0;
  return f;
  }
  
  public static void main(String args[]) {
  JFrame frame = new Filtracja();
  frame.pack();
  frame.show();
  }
 }
 