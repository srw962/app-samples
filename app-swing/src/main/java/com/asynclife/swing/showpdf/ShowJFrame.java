package com.asynclife.swing.showpdf;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.MediaSizeName;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class ShowJFrame extends javax.swing.JFrame {


    CustomImgPanel imgPanel;
    int level;
    String pdfFilePath = "src/main/resources/Java-Abstraction.pdf";


    /**
     * Creates new form ShowJFrame
     */
    public ShowJFrame() {
        initComponents();
        level = 3;
        imgPanel = new CustomImgPanel(714, 1011, pdfToImg(pdfFilePath));
        imgPanel.setPreferredSize(new Dimension(714, 1011));
        mainScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        mainScrollPanel.setViewportView(imgPanel);
        this.setLayout(null);
        this.setVisible(true);
        this.Enlarge.setVisible(true);
        this.narrow.setVisible(true);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {


        narrow = new javax.swing.JButton();
        Enlarge = new javax.swing.JButton();
        mainScrollPanel = new javax.swing.JScrollPane();
        print = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);


        narrow.setText("缩小");
        narrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                narrowActionPerformed(evt);
            }
        });


        Enlarge.setText("放大");
        Enlarge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnlargeActionPerformed(evt);
            }
        });


        print.setText("打印");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(narrow)
                .addGap(128, 128, 128)
                .addComponent(Enlarge)
                .addGap(103, 103, 103)
                .addComponent(print)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(narrow)
                    .addComponent(Enlarge)
                    .addComponent(print))
                .addContainerGap())
        );


        pack();
    }// </editor-fold>                        


    private void narrowActionPerformed(java.awt.event.ActionEvent evt) {                                       
        mainScrollPanel.remove(imgPanel);
        if (level > 1) {
            level--;
            Map<String, Integer> map = getWidthHeight(level);
            imgPanel = new CustomImgPanel(map.get("width").intValue(), map.get("height").intValue(), pdfToImg(pdfFilePath));
            imgPanel.setPreferredSize(new Dimension(map.get("width").intValue(), map.get("height").intValue()));
            imgPanel.repaint();
            mainScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            mainScrollPanel.setViewportView(imgPanel);
        } else {
            JOptionPane.showMessageDialog(this, "已经到最小了！");
        }


    }                                      


    private void EnlargeActionPerformed(java.awt.event.ActionEvent evt) {                                        
        mainScrollPanel.remove(imgPanel);
        if (level < 5) {
            level++;
            Map<String, Integer> map = getWidthHeight(level);
            imgPanel = new CustomImgPanel(map.get("width").intValue(), map.get("height").intValue(), pdfToImg(pdfFilePath));
            imgPanel.setPreferredSize(new Dimension(map.get("width").intValue(), map.get("height").intValue()));
            imgPanel.repaint();
            mainScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            mainScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            mainScrollPanel.setViewportView(imgPanel);
        } else {
            JOptionPane.showMessageDialog(this, "已经到最大了！");
        }
    }                                       


    private void printActionPerformed(java.awt.event.ActionEvent evt) {                                      
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PDF;
        PrintService printService[] = PrintServiceLookup.lookupPrintServices(
                flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        PrintService service = ServiceUI.printDialog(null, 200, 200,
                printService, defaultService, flavor, pras);
        if (service != null) {
            FileInputStream fis = null;
            try {
                DocPrintJob job = service.createPrintJob();
                fis = new FileInputStream(pdfFilePath);
                pras.add(MediaSizeName.ISO_A4);
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(fis, flavor, das);
                job.print(doc, pras);
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ShowJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (PrintException ex) {
                Logger.getLogger(ShowJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ShowJFrame.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(ShowJFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }                                     


   


    public Map<String, Integer> getWidthHeight(int level) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (level == 1) {
            map.put("width", new Integer("238"));
            map.put("height", new Integer("337"));
        } else if (level == 2) {
            map.put("width", new Integer("476"));
            map.put("height", new Integer("674"));
        } else if (level == 3) {
            map.put("width", new Integer("714"));
            map.put("height", new Integer("1011"));
        } else if (level == 4) {
            map.put("width", new Integer("952"));
            map.put("height", new Integer("1439"));
        } else if (level == 5) {
            map.put("width", new Integer("1190"));
            map.put("height", new Integer("1684"));
        }


        return map;
    }


    public static Image pdfToImg(String pdfFileName) {
        ImageIcon imageIcon = null;
        try {
            PDDocument doc = PDDocument.load(pdfFileName);
            int pageCount = doc.getNumberOfPages();
            System.out.println(pageCount);
            PDPage page = (PDPage) doc.getDocumentCatalog().getAllPages().get(0);
            BufferedImage bufferedImage = page.convertToImage();
            if (bufferedImage != null) {
                imageIcon = new ImageIcon(Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource()));
            }
            doc.close();
            System.out.println("over");
        } catch (IOException ex) {
            Logger.getLogger(ShowJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imageIcon.getImage();
    }
    // Variables declaration - do not modify                     
    private javax.swing.JButton Enlarge;
    private javax.swing.JScrollPane mainScrollPanel;
    private javax.swing.JButton narrow;
    private javax.swing.JButton print;
    // End of variables declaration        
    
    
    
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
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ShowJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowJFrame().setVisible(true);
            }
        });
    }
}
