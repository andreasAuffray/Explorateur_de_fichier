package com.esiea.pootd2.models;

public abstract class Inode {
    private final String name;
    private int size;
    private FolderInode parent;

    protected Inode(String name) {
        this.name = name;
        this.size = 0;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    public FolderInode getParent(){
        return this.parent;
    }
}
