package com.esiea.pootd2.commands;

public class ChangeDirectoryCommand extends Command {
    private final String directoryName;

    public ChangeDirectoryCommand(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getDirectoryName() {
        return directoryName;
    }
}
