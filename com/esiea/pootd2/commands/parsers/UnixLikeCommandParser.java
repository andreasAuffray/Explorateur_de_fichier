package com.esiea.pootd2.commands.parsers;

import com.esiea.pootd2.commands.*;

import java.util.Arrays;
import java.util.List;

public class UnixLikeCommandParser implements ICommandParser {

    @Override
    public Command parse(String input) {
        if (input == null || input.isBlank()) {
            return new ErrorCommand("Empty command.");
        }

        List<String> args = splitArguments(input);
        if (args.isEmpty()) {
            return new ErrorCommand("Invalid command.");
        }

        return mapCommand(args);
    }

    private List<String> splitArguments(String input) {
        return Arrays.asList(input.trim().split("\\s+"));
    }

    private Command mapCommand(List<String> args) {
        String command = args.get(0);

        switch (command) {
            case "ls":
                return new ListCommand();
            case "mkdir":
                if (args.size() < 2) {
                    return new ErrorCommand("mkdir: Missing argument.");
                }
                return new MakeDirectoryCommand(args.get(1));
            case "touch":
                if (args.size() < 2) {
                    return new ErrorCommand("touch: Missing argument.");
                }
                return new TouchCommand(args.get(1));
            case "cd":
                if (args.size() < 2) {
                    return new ErrorCommand("cd: Missing argument.");
                }
                return new ChangeDirectoryCommand(args.get(1));
            default:
                return new ErrorCommand("Unknown command: " + command);
        }
    }
}
