package io.github.makbn.meempreprocessor.view;

import io.github.makbn.meempreprocessor.IOUtils;
import io.github.makbn.meempreprocessor.StarterEventListener;
import io.github.makbn.meempreprocessor.UIUtils;
import io.github.makbn.meempreprocessor.ViewStack;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FileTransferFrame extends BaseFrame {
    private String RUNNING_PATH_PARENT;
    private StarterEventListener eventListener;

    public FileTransferFrame(String RUNNING_PATH_PARENT,StarterEventListener eventListener) throws HeadlessException {
        this.RUNNING_PATH_PARENT=RUNNING_PATH_PARENT;
        this.eventListener=eventListener;
    }

    @Override
    protected void init() {
        File[] vertexFile=new File[1];
        File[] edgeFile=new File[1];
        setTitle("Select Files");
        setLayout(null);
        setSize(640,200);
        JLabel title=UIUtils.getLabel("select Vertex.csv and edge.csv file by click on buttons!",10,10,640,15);
        JLabel vertexTxt=UIUtils.getLabel("vertex data file not selected!",10,45,520,15);
        JLabel edgeTxt=UIUtils.getLabel("edge data file not selected!",10,95,520,15);
        JLabel divider=UIUtils.getLabel("",0,30,640,1,Color.GRAY);
        JButton back=UIUtils.getButton("back",10,120,100, 40);
        JButton ok=UIUtils.getButton("ok",120,120,100, 40);

        JButton selectVertex=UIUtils.createFileBrowserBtn(this,"Select vertex.csv",vertexTxt,vertexFile,520,40,100, 50);

        JButton selectEdge=UIUtils.createFileBrowserBtn(this,"Select edge.csv",edgeTxt,edgeFile,520,90,100, 50);

        back.addActionListener(e -> {
            ViewStack.getViewByTag("main").setVisible(true);
            ViewStack.getViewByTag("main").setLocation(getLocation());
            setVisible(false);
        });
        ok.addActionListener(e -> {
            if(vertexFile[0] !=null && edgeFile[0]!=null){
                if(vertexFile[0].isFile() && edgeFile[0].isFile()){
                    File vt=IOUtils.saveToFile(edgeFile[0], RUNNING_PATH_PARENT+ "/data/data_edges.csv");
                    File ed=IOUtils.saveToFile(vertexFile[0], RUNNING_PATH_PARENT + "/data/data_vertex.csv");

                    eventListener.FileCopied(vt,ed);
                    ViewStack.getViewByTag("main").setVisible(false);
                }
            }

        });

        addAll(back,selectEdge,selectVertex,title,vertexTxt,edgeTxt,divider,ok);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
