package com.piankov.auctions.controller;

import com.piankov.auctions.command.*;
import com.piankov.auctions.command.auction.*;
import com.piankov.auctions.command.bid.CancelBidCommand;
import com.piankov.auctions.command.list.*;
import com.piankov.auctions.command.bid.MakeBidCommand;
import com.piankov.auctions.command.user.*;
import com.piankov.auctions.command.system.LinkCommand;
import com.piankov.auctions.command.system.ChangeLanguageCommand;
import com.piankov.auctions.command.view.ProfileCommand;
import com.piankov.auctions.command.view.ShowActiveAuctionCommand;
import com.piankov.auctions.command.view.ShowEndedAuctionCommand;
import com.piankov.auctions.command.view.ShowVerifyingAuctionCommand;

public enum CommandType {
    EDIT_USER(new EditUserCommand()),
    EDIT_AUCTION(new EditAuctionCommand()),
    CREATE_AUCTION(new CreateAuctionCommand()),
    MAKE_BID(new MakeBidCommand()),
    CANCEL_BID(new CancelBidCommand()),
    SHOW_ACTIVE_AUCTION(new ShowActiveAuctionCommand()),
    SHOW_VERIFYING_AUCTION(new ShowVerifyingAuctionCommand()),
    SHOW_ENDED_AUCTION(new ShowEndedAuctionCommand()),
    VERIFY_AUCTION(new VerifyAuctionCommand()),
    ACTIVE_AUCTIONS_LIST(new ActiveAuctionsListCommand()),
    ENDED_AUCTIONS_LIST(new EndedAuctionsListCommand()),
    VERIFYING_AUCTIONS_LIST(new VerifyingAuctionsListCommand()),
    EDIT_USER_PAGE(new EditUserPageCommand()),
    EDIT_AUCTION_PAGE(new EditAuctionPageCommand()),
    LINK(new LinkCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    PROFILE(new ProfileCommand()),
    REGISTER(new RegisterCommand()),
    PROMOTE_USER(new PromoteUserCommand()),
    USER_LIST(new UserListCommand()),
    USER_AUCTIONS_LIST(new UserAuctionsList()),
    WITHDRAW_AUCTION(new WithdrawAuctionCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
