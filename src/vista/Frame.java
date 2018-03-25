/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Imagen;
import pdi.PDI;

/**
 *
 * @author AnthonyLA
 */
public class Frame extends javax.swing.JFrame {

    private PDI pdi = new PDI();

    /**
     * Creates new form Frame
     */
    public Frame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Cuadro = new javax.swing.JPanel();
        imagen = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        opcion = new javax.swing.JComboBox<>();
        guardarImagen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        imagen.setText("jLabel1");

        javax.swing.GroupLayout CuadroLayout = new javax.swing.GroupLayout(Cuadro);
        Cuadro.setLayout(CuadroLayout);
        CuadroLayout.setHorizontalGroup(
            CuadroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CuadroLayout.createSequentialGroup()
                .addGap(196, 196, 196)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CuadroLayout.setVerticalGroup(
            CuadroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CuadroLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(imagen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jToggleButton1.setText("Cargar");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jToggleButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jToggleButton1KeyPressed(evt);
            }
        });

        opcion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Original", "Negativo", "Escala de grises", "Blanco y negro", "Colores únicos", "Rotación", "Compresión RLE" }));
        opcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionActionPerformed(evt);
            }
        });

        guardarImagen.setText("Guardar");
        guardarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(guardarImagen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToggleButton1)
                .addGap(87, 87, 87)
                .addComponent(opcion, 0, 165, Short.MAX_VALUE)
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cuadro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cuadro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opcion, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1)
                    .addComponent(guardarImagen))
                .addGap(83, 83, 83))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "bpm", "gif", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            
            BufferedImage img = null;
            Image imagenFinal = null;
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            int tamannoExt = path.length();
            String ext = path.substring(tamannoExt-3); // Se lee la extensión.
            
            // Si se carga una imagen netbmp se trata de otra forma.
            if(!ext.equalsIgnoreCase("bmp")) try {
                img = pdi.Netbmp(ext, path);            // Buffer de la imagen Netbmp.
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                URL url = new File(path).toURI().toURL();
                if (img == null) img = ImageIO.read(url);   // Si se leyó una Netbmp se obvia esta asignación.
                imagenFinal = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
            } catch (MalformedURLException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Imagen.setImagenOrinal(img); // Se guarda la imagen en el modelo
            imagen.setIcon(new ImageIcon(imagenFinal));
            Cuadro.add(imagen);
            Cuadro.setVisible(true);
            
        } //if the user click on save in Jfilechooser
        else if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("No File Select");
        }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jToggleButton1KeyPressed
        // 
    }//GEN-LAST:event_jToggleButton1KeyPressed

    private void opcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionActionPerformed
        String caso = opcion.getSelectedItem().toString().toLowerCase(); // Obtengo la opción del combo box.
        PDI controlador = new PDI();
        BufferedImage img = null;
        Image imagenFinal;
        boolean[] tipoGuardado = {false,false,false};
        switch(caso)
        {
            case "original":
                img = Imagen.getImagenOrinal();
                imagenFinal = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
                imagen.setIcon(new ImageIcon(imagenFinal));
                Cuadro.add(imagen);
            break;
            case "negativo":
                tipoGuardado[0]=true;       // bool para saber qué imagen guardaré en la opción "Guardar".
                tipoGuardado[1]=false;
                tipoGuardado[2]=false;
                Imagen.setTipoGuardado(tipoGuardado);
                img = controlador.FotoNegativa(Imagen.getImagenOrinal()); // Se accede a la imagen desde el modelo.
                imagenFinal = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
                imagen.setIcon(new ImageIcon(imagenFinal));
                Cuadro.add(imagen);
            break;
            case "escala de grises":
                tipoGuardado[1]=true;       // bool para saber qué imagen guardaré en la opción "Guardar".
                tipoGuardado[0]=false;
                tipoGuardado[2]=false;
                Imagen.setTipoGuardado(tipoGuardado);
                img = controlador.FotoEscalaGrises(Imagen.getImagenOrinal()); // Se accede a la imagen desde el modelo.
                imagenFinal = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
                imagen.setIcon(new ImageIcon(imagenFinal));
                Cuadro.add(imagen);
            break;
            case "blanco y negro":
                tipoGuardado[2]=true;       // bool para saber qué imagen guardaré en la opción "Guardar".
                tipoGuardado[0]=false;
                tipoGuardado[1]=false;
                Imagen.setTipoGuardado(tipoGuardado);
                {
                    try {
                        img = controlador.FotoBlancoNegro(Imagen.getImagenOrinal()); // Se accede a la imagen desde el modelo.
                    } catch (IOException ex) {
                        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                imagenFinal = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
                imagen.setIcon(new ImageIcon(imagenFinal));
                Cuadro.add(imagen);
            break;
            case "colores únicos":
                controlador.FotoColoresUnicos((Imagen.getImagenTemporal() == null) ? (Imagen.getImagenOrinal()) : (Imagen.getImagenTemporal())); // Se accede a la imagen desde el modelo.
            break;
            case "rotación":
                BufferedImage imgTemp;
                imgTemp = (Imagen.getImagenTemporal() == null) ? (Imagen.getImagenOrinal()) : (Imagen.getImagenTemporal());
                img = controlador.FotoRotacion(imgTemp);
                imagenFinal = img.getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
                imagen.setIcon(new ImageIcon(imagenFinal));
                Cuadro.add(imagen);
            break;
            case "compresión rle":
                int c = 2;
                String format = "ppm";
                int height = 2;
                int width = 2;
                int max_value;
                if (format.equals("ppm")) {
                    width = width*3;
                }
                    if (c==1) {
                        max_value = 255;
                        int arrayy[] = {1,60,0,0,1,0,60,0,1,0,0,60,3,120,120,120};
                        try {
                            controlador.CargarRLE(format, arrayy, height, width, max_value);
                        } catch (FileNotFoundException ex) {
                            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
                    }else{
                        int[][] mat = new int[height][width];
                        max_value = 120;
                        
                        mat[0][0] = 120;
                        mat[0][1] = 0;
                        mat[0][2] = 0;
                        mat[0][3] = 120;
                        mat[0][4] = 0;
                        mat[0][5] = 0;
                        mat[1][0] = 0;
                        mat[1][1] = 0;
                        mat[1][2] = 120;
                        mat[1][3] = 0;
                        mat[1][4] = 0;
                        mat[1][5] = 0;
                                               
                        try {
                            controlador.CompresionRLE(format, mat, width, height, max_value);
                        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            break;
            default:
                System.out.println("defecto");
        }
            
    }//GEN-LAST:event_opcionActionPerformed

    private void guardarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarImagenActionPerformed
        PDI controlador = new PDI();
        try {
            controlador.GuardarImagen(Imagen.getImagenTemporal());
        } catch (IOException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_guardarImagenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cuadro;
    private javax.swing.JButton guardarImagen;
    private javax.swing.JLabel imagen;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JComboBox<String> opcion;
    // End of variables declaration//GEN-END:variables
}
