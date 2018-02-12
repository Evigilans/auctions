package com.piankov.auctions.controller;

import com.piankov.auctions.command.Command;

public class CommandDefiner {
    public Command defineCommand(String commandName) {
        if (commandName != null) {
            commandName = commandName.replaceAll("-", "_").toUpperCase();
            return CommandType.valueOf(commandName).getCommand();
        }
        return null;
    }
}
