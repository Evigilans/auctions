package com.piankov.auctions.servlet;

import com.piankov.auctions.command.*;

import java.util.HashMap;
import java.util.Map;

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