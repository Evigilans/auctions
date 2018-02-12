package com.piankov.auctions.controller;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.command.edit.EditUserCommand;
import com.piankov.auctions.command.entity.*;
import com.piankov.auctions.command.list.ActiveAuctionsListCommand;
import com.piankov.auctions.command.list.VerifyingAuctionsListCommand;
import com.piankov.auctions.command.page.EditUserPageCommand;
import com.piankov.auctions.command.page.LinkCommand;
import com.piankov.auctions.command.system.ChangeLanguageCommand;
import com.piankov.auctions.command.user.LoginCommand;
import com.piankov.auctions.command.user.LogoutCommand;
import com.piankov.auctions.command.user.ProfileCommand;
import com.piankov.auctions.command.user.RegisterCommand;

public enum CommandType {
    EDIT_USER(new EditUserCommand()),
    CREATE_AUCTION(new CreateAuctionCommand()),
    MAKE_BID(new MakeBidCommand()),
    SHOW_ACTIVE_AUCTION(new ShowActiveAuctionCommand()),
    SHOW_VERIFYING_AUCTION(new ShowVerifyingAuctionCommand()),
    VERIFY_AUCTION(new VerifyAuctionCommand()),
    ACTIVE_AUCTIONS_LIST(new ActiveAuctionsListCommand()),
    VERIFYING_AUCTIONS_LIST(new VerifyingAuctionsListCommand()),
    EDIT_USER_PAGE(new EditUserPageCommand()),
    LINK(new LinkCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE(new ProfileCommand()),
    REGISTER(new RegisterCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
