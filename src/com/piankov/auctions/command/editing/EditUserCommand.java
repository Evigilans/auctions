package com.piankov.auctions.command.editing;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException, UnauthorizedAccessException {

    }
}
