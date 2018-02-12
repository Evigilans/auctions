package com.piankov.auctions.controller;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.command.entity.ShowVerifyingAuctionCommand;
import com.piankov.auctions.command.entity.VerifyAuctionCommand;
import com.piankov.auctions.command.list.VerifyingAuctionsListCommand;
import com.piankov.auctions.command.edit.EditUserCommand;
import com.piankov.auctions.command.page.EditUserPageCommand;
import com.piankov.auctions.command.entity.CreateAuctionCommand;
import com.piankov.auctions.command.entity.MakeBidCommand;
import com.piankov.auctions.command.system.ChangeLanguageCommand;
import com.piankov.auctions.command.page.LinkCommand;
import com.piankov.auctions.command.user.LoginCommand;
import com.piankov.auctions.command.user.LogoutCommand;
import com.piankov.auctions.command.user.RegisterCommand;
import com.piankov.auctions.command.list.ActiveAuctionsListCommand;
import com.piankov.auctions.command.user.ProfileCommand;
import com.piankov.auctions.command.entity.ShowActiveAuctionCommand;

import java.util.HashMap;
import java.util.Map;

//TODO: вынести в ENUM???
public class CommandInitializer {
    static Map<String, Command> initializeCommandMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("link", new LinkCommand());
        commandMap.put("language", new ChangeLanguageCommand());
        commandMap.put("registration", new RegisterCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("profile", new ProfileCommand());
        commandMap.put("activeAuctionsList", new ActiveAuctionsListCommand());
        commandMap.put("auction", new ShowActiveAuctionCommand());
        commandMap.put("createAuction", new CreateAuctionCommand());
        commandMap.put("bid", new MakeBidCommand());
        commandMap.put("verify", new VerifyAuctionCommand());
        commandMap.put("verifyingAuctionsList", new VerifyingAuctionsListCommand());
        commandMap.put("verifyingAuction", new ShowVerifyingAuctionCommand());
        commandMap.put("editUserPage", new EditUserPageCommand());
        commandMap.put("editUser", new EditUserCommand());
        return commandMap;
    }
}