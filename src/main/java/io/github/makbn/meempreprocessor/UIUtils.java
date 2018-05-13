package io.github.makbn.meempreprocessor;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class UIUtils {


    public static JFrame getFrame(int w,int h,String title){
        JFrame f=new JFrame();
        f.setTitle(title);
        f.setSize(w,h);
        f.setLayout(null);
        return f;
    }

    public static JFrame getFrame(int w,int h,String title,int posX,int posY){
        JFrame f=getFrame(w,h,title);
        f.setBounds(posX,posY,w,h);

        return f;
    }

    public static JLabel createLabel(String text){
        JLabel label  = new JLabel("", JLabel.CENTER);
        label.setText(text);
        label.setOpaque(true);
        label.setForeground(Color.BLACK);
        return label;
    }

    public static JLabel getLabel(String text,int posX,int posY,int w,int h){
        JLabel l=createLabel(text);
        l.setBounds(posX,posY,w,h);
        return l;
    }

    public static JLabel getLabel(String text,int posX,int posY,int w,int h,Color color){
        JLabel l=getLabel(text,w,h,posX,posY);
        l.setBackground(color);
        return l;
    }


    public static  JButton getButton(String text,int posX,int posY,int w,int h){
        JButton button=new JButton(text);//creating instance of JButton
        button.setBounds(posX,posY,w, h);//x axis, y axis, width, height
        return button;
    }

    public static JTextArea getTextArea(String hint,int posX,int posY,int w,int h){

        JTextArea editTextArea = new JTextArea(hint);
        editTextArea.setRows(1);
        editTextArea.setColumns(4);
        editTextArea.setBounds(posX,posY,w,h);

        return editTextArea;
    }

    public static JButton createFileBrowserBtn(Frame parent , String name,JLabel label,File[] file,int posX,int posY,int w,int h){
        JButton selectVertex=getButton(name,posX,posY,w, h);
        final JFileChooser fc = new JFileChooser();
        selectVertex.addActionListener(e -> {
            FileNameExtensionFilter fi=new FileNameExtensionFilter("csv","csv");
            fc.setFileFilter(fi);
            fc.showOpenDialog(parent);

            try {
                // Open an input stream
                File selFile = fc.getSelectedFile();
                label.setText("File: "+selFile.getAbsolutePath());
                file[0]=selFile;

            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        return selectVertex;
    }

}
