package com.piankov.auctions.command.admin;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.exception.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VerifyAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {

    }
}
