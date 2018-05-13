package io.github.makbn.meempreprocessor.view;

import io.github.makbn.meempreprocessor.UIUtils;
import io.github.makbn.meempreprocessor.ViewStack;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    public MainFrame() throws HeadlessException {

    }

    @Override
    protected void init() {
        setTitle("MEEM Preprocessor");
        setSize(640,200);
        setLayout(null);
        JButton sqlQuery=UIUtils.getButton("SQL Query",210,100,100, 40);//x axis, y axis, width, height
        sqlQuery.addActionListener(e -> {
            ViewStack.getViewByTag("sql").setVisible(true);
            ViewStack.getViewByTag("sql").setLocation(getLocation());
            setVisible(false);
        });


        JButton selectBtn=UIUtils.getButton("Select Files",310,100,100, 40);//x axis, y axis, width, height
        selectBtn.addActionListener(e -> {
            ViewStack.getViewByTag("file").setVisible(true);
            ViewStack.getViewByTag("file").setLocation(getLocation());
            setVisible(false);
        });

        addAll(selectBtn,sqlQuery);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }
}
