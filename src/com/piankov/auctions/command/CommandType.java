package com.piankov.auctions.command;

import com.piankov.auctions.command.entity.VerifyAuctionCommand;
import com.piankov.auctions.command.entity.CreateAuctionCommand;
import com.piankov.auctions.command.entity.MakeBidCommand;
import com.piankov.auctions.command.system.ChangeLanguageCommand;
import com.piankov.auctions.command.page.LinkCommand;
import com.piankov.auctions.command.user.LoginCommand;
import com.piankov.auctions.command.user.LogoutCommand;
import com.piankov.auctions.command.user.RegisterCommand;
import com.piankov.auctions.command.list.ActiveAuctionsListCommand;
import com.piankov.auctions.command.user.ProfileCommand;
import com.piankov.auctions.command.entity.ShowAuctionCommand;

public enum CommandType {
    LINK(new LinkCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    REGISTRATION(new RegisterCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE(new ProfileCommand()),
    AUCTIONS_LIST(new ActiveAuctionsListCommand()),
    SHOW_AUCTION(new ShowAuctionCommand()),
    CREATE_AUCTION(new CreateAuctionCommand()),
    MAKE_BID(new MakeBidCommand()),
    VERIFY(new VerifyAuctionCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
