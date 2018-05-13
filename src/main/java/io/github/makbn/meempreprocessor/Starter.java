package io.github.makbn.meempreprocessor;
import io.github.makbn.meemlocationgraph.LocationGraph;
import io.github.makbn.meempreprocessor.view.FileTransferFrame;
import io.github.makbn.meempreprocessor.view.MainFrame;
import io.github.makbn.meempreprocessor.view.SQLFrame;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.io.File;
import java.net.URISyntaxException;


class Starter {

    private static String RUNNING_PATH;
    private static String RUNNING_PATH_PARENT;
    private static StarterEventListener eventListener;
    private static final String MAIN_TAG="main";
    private static final String SQL_TAG="sql";
    private static final String FILE_TAG="file";


    public static void main(String[] a){
        main(a, new StarterEventListener() {
            @Override
            public void FileCopied(File vertex, File edge) {

            }

            @Override
            public void queryExcuted(LocationGraph lg) {

            }
        });
    }


    public static void main(String[] args,StarterEventListener starterEventListener) {
        try {
            UIManager.setLookAndFeel (new NimbusLookAndFeel());

            RUNNING_PATH = Starter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if(args.length>0)
                RUNNING_PATH_PARENT=args[0];
            eventListener=starterEventListener;
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace ();
        }catch (URISyntaxException e) {
            e.printStackTrace();
        }
        ViewStack.init();
        ViewStack.addView(SQL_TAG,createSQlFrame());
        ViewStack.addView(FILE_TAG,copyFileFrame());
        ViewStack.addView(MAIN_TAG,createMainFrame());
        ViewStack.getViewByTag(MAIN_TAG).setVisible(true);
    }

    private static JFrame copyFileFrame() {
        JFrame f=new FileTransferFrame(RUNNING_PATH_PARENT,eventListener);
        return f;
    }

    private static JFrame createSQlFrame() {
        JFrame f=new SQLFrame(RUNNING_PATH_PARENT,eventListener);
        return f;
    }

    public static JFrame createMainFrame(){
        JFrame f=new MainFrame();
        return f;

    }

}
