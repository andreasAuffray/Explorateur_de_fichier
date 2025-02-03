package com.esiea.pootd2.models;

import java.util.concurrent.ThreadLocalRandom;

public class FileInode extends Inode {
    public FileInode(String name) {
        super(name);
        setSize(ThreadLocalRandom.current().nextInt(1, 100001));
    }
}
