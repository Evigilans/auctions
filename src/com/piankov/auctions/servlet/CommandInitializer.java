package com.piankov.auctions.servlet;

import com.piankov.auctions.command.Command;
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

import java.util.HashMap;
import java.util.Map;

//TODO: вынести в ENUM???
public class CommandInitializer {
    static Map<String, Command> initializeCommandMap() {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("link", new LinkCommand());
        commandMap.put("language", new ChangeLanguageCommand());
        commandMap.put("registration", new RegistrationCommand());
        commandMap.put("login", new LoginCommand());
        commandMap.put("logout", new LogoutCommand());
        commandMap.put("profile", new ProfileCommand());
        commandMap.put("list", new AuctionsListCommand());
        commandMap.put("auction", new ShowAuctionCommand());
        commandMap.put("createAuction", new CreateAuctionCommand());
        commandMap.put("bid", new MakeBidCommand());
        return commandMap;
    }
}