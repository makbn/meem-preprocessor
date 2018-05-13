package io.github.makbn.meempreprocessor.view;

import io.github.makbn.meemlocationgraph.GraphFactory;
import io.github.makbn.meemlocationgraph.LocationGraph;
import io.github.makbn.meempreprocessor.StarterEventListener;
import io.github.makbn.meempreprocessor.UIUtils;
import io.github.makbn.meempreprocessor.ViewStack;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SQLFrame extends BaseFrame {
    private String RUNNING_PATH_PARENT;
    private StarterEventListener eventListener;
    public SQLFrame(String runningPath, StarterEventListener listener) throws HeadlessException {
        super();
        this.RUNNING_PATH_PARENT=runningPath;
        this.eventListener=listener;
    }

    public SQLFrame(GraphicsConfiguration gc) {
        super(gc);

    }

    public SQLFrame(String title) throws HeadlessException {
        super(title);

    }

    public SQLFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);

    }


    @Override
    protected void init() {
        setTitle("SQL Query");
        setSize(640,200);

        JButton back=UIUtils.getButton("Back",10,120,100, 40);

        back.addActionListener(e -> {
            ViewStack.getViewByTag("main").setVisible(true);
            ViewStack.getViewByTag("main").setLocation(getLocation());
            setVisible(false);
        });

        JTextArea host=UIUtils.getTextArea("127.0.0.1",10,10,300,30);
        JTextArea port=UIUtils.getTextArea("3316",320,10,300,30);
        JTextArea db=UIUtils.getTextArea("ExMdatabase",10,50,300,30);
        JTextArea table=UIUtils.getTextArea("Table Name",320,50,300,30);
        JTextArea user=UIUtils.getTextArea("DBUser3",10,90,300,30);
        JTextArea pass=UIUtils.getTextArea("DBPass3",320,90,300,30);
        JButton ok=UIUtils.getButton("ok",120,120,100, 40);
        ok.addActionListener(e -> {
            String hostTxt=host.getText();
            String portTxt=port.getText();
            String dbNameTxt=db.getText();
            String userTxt=user.getText();
            String passwordTxt=pass.getText();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    LocationGraph lg = null;
                    try {
                        showLoading();
                        lg = GraphFactory.createFromSQL(hostTxt!=null ? hostTxt:"127.0.0.1",
                                dbNameTxt,
                                userTxt,
                                passwordTxt,
                                null,
                                null,
                                null,
                                null,
                                RUNNING_PATH_PARENT);
                                eventListener.queryExcuted(lg);
                                ViewStack.getViewByTag("main").setVisible(false);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }).start();
        });
        addAll(back,host,pass,user,port,db,ok,table);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);//using no layout managers
    }

    private void showLoading() {

        Window win = SwingUtilities.getWindowAncestor(ViewStack.getViewByTag("sql"));
        final JDialog dialog = new JDialog(win, "Dialog", Dialog.ModalityType.APPLICATION_MODAL);

        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(progressBar, BorderLayout.CENTER);
        panel.add(new JLabel("Please wait......."), BorderLayout.PAGE_START);
        dialog.add(panel);
        dialog.pack();
        dialog.setLocationRelativeTo(win);
        dialog.setVisible(true);
    }
}
