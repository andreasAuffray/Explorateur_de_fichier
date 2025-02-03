package com.esiea.pootd2.interfaces;

import com.esiea.pootd2.controllers.IExplorerController;
import java.util.Scanner;

public class TextInterface implements IUserInterface {
    private final IExplorerController controller;

    public TextInterface(IExplorerController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            String output = controller.executeCommand(input);
            if (!output.isEmpty()) {
                System.out.println(output);
            }
        }
    }
}
