package io.github.makbn.meempreprocessor;

import io.github.makbn.meemlocationgraph.LocationGraph;

import java.io.File;

public interface StarterEventListener {


    void FileCopied(File vertex, File edge);

    void queryExcuted(LocationGraph lg);
}
