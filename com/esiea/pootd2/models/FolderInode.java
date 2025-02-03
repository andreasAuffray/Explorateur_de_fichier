package com.esiea.pootd2.models;

import java.util.ArrayList;
import java.util.List;

public class FolderInode extends Inode {
    private final List<Inode> children = new ArrayList<>();
    private FolderInode parent;

    public FolderInode(String name) {
        super(name);
    }

    public void addInode(Inode inode) {
        if (inode instanceof FolderInode folderInode) {
            folderInode.setParent(this);
        }
        children.add(inode);
        recalculateSize();
    }

    public List<Inode> getChildren() {
        return children;
    }

    private void recalculateSize() {
        int totalSize = 0;
        for (Inode inode : children) {
            totalSize += inode.getSize();
        }
        setSize(totalSize);
    }

    @Override
    public FolderInode getParent() {
        return parent;
    }

    public void setParent(FolderInode parent) {
        this.parent = parent;
    }
}
