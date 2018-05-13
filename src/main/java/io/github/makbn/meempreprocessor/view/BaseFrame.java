package io.github.makbn.meempreprocessor.view;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {

    public BaseFrame() throws HeadlessException {
        super();
        init();
    }

    public BaseFrame(GraphicsConfiguration gc) {
        super(gc);
        init();
    }

    public BaseFrame(String title) throws HeadlessException {
        super(title);
        init();
    }

    public BaseFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        init();
    }

    protected abstract void init();

    public JFrame addAll(Component...components){
        for(Component c: components){
            super.add(c);
        }
        return this;
    }
}
