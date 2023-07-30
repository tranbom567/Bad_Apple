package com.example;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
       FFmpegFrameGrabber grabber=new FFmpegFrameGrabber("D:\\CPP Proj\\Bad Apple\\test\\test2.mp4");
       AudioInputStream inputStream=AudioSystem.getAudioInputStream(new File("D:\\CPP Proj\\Bad Apple\\test\\Audio.wav"));
       Clip clip=AudioSystem.getClip();
     
       grabber.start();
       Java2DFrameConverter converter=new Java2DFrameConverter();
       int frameCount=grabber.getLengthInFrames();
       Frame f;
        BufferedImage bi;
         StringBuilder sb=new StringBuilder();
       
       clip.open(inputStream);
        clip.start();
       for(int i=0;i<frameCount;i++){
     grabber.setFrameNumber(i);
    f=grabber.grab();
   bi=converter.convert(f);

    for(int rows=0;rows<bi.getHeight();rows++){
   
      for(int cols=0;cols<bi.getWidth();cols++){
        Color c=new Color(bi.getRGB(cols, rows));
       
       if(c.getRGB()>=(new Color(17, 17, 17,255)).getRGB())
       sb.append("*");
        if(c.getRGB()<(new Color(17, 17, 17,255)).getRGB())
       sb.append(".");
      }
     sb.append("\n");
    }
   
   
    System.out.print(sb.toString()); 
     Thread.sleep(Long.valueOf(args[0]));
    System.out.print("\033[H\033[2J"); 
  sb.replace(0, sb.length(), "");
       }
       grabber.close();
    }
}
