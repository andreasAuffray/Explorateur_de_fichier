package com.esiea.pootd2.controllers;

import com.esiea.pootd2.commands.*;
import com.esiea.pootd2.commands.parsers.UnixLikeCommandParser;
import com.esiea.pootd2.models.FileInode;
import com.esiea.pootd2.models.FolderInode;
import com.esiea.pootd2.models.Inode;

public class ExplorerController implements IExplorerController {
    private final FolderInode root;
    private FolderInode current;
    private final UnixLikeCommandParser parser;

    public ExplorerController() {
        this.root = new FolderInode("/");
        this.current = root;
        this.parser = new UnixLikeCommandParser();
    }

    @Override
    public String executeCommand(String commandStr) {
        Command command = parser.parse(commandStr);
        return doCommand(command);
    }

    private String doCommand(Command command) {
        if (command instanceof ListCommand) {
            return doCommand((ListCommand) command);
        } else if (command instanceof MakeDirectoryCommand) {
            return doCommand((MakeDirectoryCommand) command);
        } else if (command instanceof TouchCommand) {
            return doCommand((TouchCommand) command);
        } else if (command instanceof ChangeDirectoryCommand) {
            return doCommand((ChangeDirectoryCommand) command);
        } else if (command instanceof ErrorCommand errorCommand) {
            return "Error: " + errorCommand.getMessage();
        }
        return "";
    }

    private String doCommand(ListCommand command) {
        StringBuilder output = new StringBuilder();
        for (var inode : current.getChildren()) {
            output.append(inode.getName()).append(" ").append(inode.getSize()).append("\n");
        }
        return output.toString().strip();
    }

    private String doCommand(MakeDirectoryCommand command) {
        String name = command.getDirectoryName();
        current.addInode(new FolderInode(name));
        return "";
    }

    private String doCommand(TouchCommand command) {
        String name = command.getFileName();
        current.addInode(new FileInode(name));
        return "";
    }

    private String doCommand(ChangeDirectoryCommand command) {
        String name = command.getDirectoryName();

        if (name.equals("..")) {
            if (current.getParent() != null) {
                current = current.getParent();
                return "";
            } else {
                return "Unable to go up : you are at the root";
            }
        }

        for (Inode child : current.getChildren()) {
            if (child instanceof FolderInode && child.getName().equals(name)) {
                current = (FolderInode) child;
                return "";
            }
        }
        return null;
    }
}
