/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pdi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import modelo.Imagen;
import static sun.security.krb5.Confounder.bytes;
import vista.Frame;

/**
 *
 * @author AnthonyLA
 */
public class PDI {
    
    private int height = 0;
    private int width = 0;
    
    private int red;
    private int green;
    private int blue;
    private int gray;
    
    private int pixel;
    // Convierte la imagen negativa.
    public BufferedImage FotoNegativa(BufferedImage img)
    {
        Imagen.setImagenOrinal(img);
        
        height = img.getHeight();
        width = img.getWidth();
        
        for (int h = 0; h<height; h++)
        {
            for (int w = 0; w<width; w++)
            {
                pixel = img.getRGB(w, h);
                red = (pixel>>16)&0xff;
                green = (pixel>>8)&0xff;
                blue = pixel&0xff;
                
                red = 255 - red;
                green = 255 - green;
                blue = 255 -  blue;
                
                pixel =  (red<<16) | (green<<8) | blue;
                
                img.setRGB(w, h, pixel);
            }
        }
        Imagen.setImagenTemporal(img);
        return (img);
    }
    
    //Convierte la imagen a escala de grises
    public BufferedImage FotoEscalaGrises(BufferedImage img)
    {
        Imagen.setImagenOrinal(img);
        
        height = img.getHeight();
        width = img.getWidth();
        
        for (int h = 0; h<height; h++)
        {
            for (int w = 0; w<width; w++)
            {
                pixel = img.getRGB(w, h);
                red = (pixel>>16)&0xff;
                green = (pixel>>8)&0xff;
                blue = pixel&0xff;
                
                gray = (red + green + blue)/3;
                
                pixel =  (gray<<16) | (gray<<8) | gray;
                
                img.setRGB(w, h, pixel);
            }
        }
        Imagen.setImagenTemporal(img);
        return (img);
    }
    
    // Convierte la imagen en blanco y negro.
    public BufferedImage FotoBlancoNegro(BufferedImage img) throws IOException
    {
        Imagen.setImagenOrinal(img);
        
        final int BLACK = 0;
        final int WHITE = 255;
        
        height = img.getHeight();
        width = img.getWidth();
        
        for(int h=0; h<height; h++)
        {
            for(int w=0; w<width; w++)
            {
                pixel = img.getRGB(w, h);
                red = (pixel>>16)&0xff;
                green = (pixel>>8)&0xff;
                blue = pixel&0xff;
                
                if(red+green+blue < 128)
                {
                    red = BLACK;
                    green = BLACK;
                    blue = BLACK;
                }else
                {
                    red = WHITE;
                    green = WHITE;
                    blue = WHITE;
                }
                
                pixel =  (red<<16) | (green <<8) | blue;
                
                img.setRGB(w, h, pixel);
            }
        }
        
        Imagen.setImagenTemporal(img);
        return img;
    }
    
    public void GuardarImagen(BufferedImage imagenGuardar) throws IOException
    {
        
        boolean[] casoGuardado = Imagen.getTipoGuardado();
        int i = 0;
        while(casoGuardado[i] != true) i++;
        
        switch(i)
        {
            // Guardar imagen negativa.
            case 0:
                BufferedImage imagenNegativa = new BufferedImage(imagenGuardar.getWidth(),imagenGuardar.getHeight(),BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics_ = imagenNegativa.createGraphics();
                graphics_.drawImage(imagenGuardar, 0, 0, null);
                ImageIO.write(imagenNegativa, "BMP", new File("imagenNegativa.bmp"));
                break;
                
            // Guardar imagen escala de grises.
            case 1:
                BufferedImage imagenGris = new BufferedImage(imagenGuardar.getWidth(),imagenGuardar.getHeight(),BufferedImage.TYPE_BYTE_GRAY);
                Graphics2D graphics = imagenGris.createGraphics();
                graphics.drawImage(imagenGuardar, 0, 0, null);
                ImageIO.write(imagenGris, "BMP", new File("imagenEscalaGrises.bmp"));
                break;
            
            // Guardar imagen blanco y negro.    
            case 2:
                BufferedImage img = Imagen.getImagenOrinal();
                final int BLACK = 0;
                final int WHITE = 255;
                
                height = img.getHeight();
                width = img.getWidth();
                
                for(int h=0; h<height; h++)
                {
                    for(int w=0; w<width; w++)
                    {
                        pixel = img.getRGB(w, h);
                        red = (pixel>>16)&0xff;
                        green = (pixel>>8)&0xff;
                        blue = pixel&0xff;
                        
                        if(red+green+blue < 128)
                        {
                            red = BLACK;
                            green = BLACK;
                            blue = BLACK;
                        }else
                        {
                            red = WHITE;
                            green = WHITE;
                            blue = WHITE;
                        }
                        
                        pixel =  (red<<16) | (green <<8) | blue;
                        
                        img.setRGB(w, h, pixel);
                    }
                }
                
                BufferedImage blancoNegroByte = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
                Graphics2D grafica = blancoNegroByte.createGraphics();
                grafica.drawImage(img, 0, 0, null);
                ImageIO.write(blancoNegroByte, "BMP", new File("imagenBlancoNegro.bmp"));
                break;
                
        }
    }
    
    // Rotar imagen.
    public BufferedImage FotoRotacion(BufferedImage img)
    {
        //Imagen.setImagen(img);
        
        height = img.getHeight();
        width = img.getWidth();
        
        BufferedImage rotatedImage = new BufferedImage(height,width,img.getType());
        for (int w=0;w<width;w++)
        {
            for (int h=0;h<height;h++)
            {
                rotatedImage.setRGB(h,width-w-1,img.getRGB(w,h));
            }
        }
        Imagen.setImagenTemporal(rotatedImage);
        return rotatedImage;
    }
    // Cuenta colores unicos
    public int FotoColoresUnicos(BufferedImage img)
    {
        Set<Integer> pixels = new HashSet<>();
        //Imagen.setImagen(img);
        
        height = img.getHeight();
        width = img.getWidth();
        
        for(int h=0; h<height; h++)
        {
            for(int w=0; w<width; w++)
            {
                pixel = img.getRGB(w, h);
                if (!pixels.contains(pixel)) {
                    pixels.add(pixel);
                }
            }
        }
        System.out.println(pixels.size());
        return pixels.size();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Frame vista = new Frame();
        
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setVisible(true);
    }
    
}
