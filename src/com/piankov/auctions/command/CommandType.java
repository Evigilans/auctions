package com.piankov.auctions.command;

import com.piankov.auctions.command.entity.CreateAuctionCommand;
import com.piankov.auctions.command.entity.MakeBidCommand;
import com.piankov.auctions.command.system.ChangeLanguageCommand;
import com.piankov.auctions.command.system.LinkCommand;
import com.piankov.auctions.command.user.LoginCommand;
import com.piankov.auctions.command.user.LogoutCommand;
import com.piankov.auctions.command.user.RegistrationCommand;
import com.piankov.auctions.command.view.AuctionsListCommand;
import com.piankov.auctions.command.view.ProfileCommand;
import com.piankov.auctions.command.view.ShowAuctionCommand;

public enum CommandType {
    LINK(new LinkCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE(new ProfileCommand()),
    AUCTIONS_LIST(new AuctionsListCommand()),
    SHOW_AUCTION(new ShowAuctionCommand()),
    CREATE_AUCTION(new CreateAuctionCommand()),
    MAKE_BID(new MakeBidCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
