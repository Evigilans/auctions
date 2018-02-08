package com.piankov.auctions.command.view;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.CommandExecutionException;
import com.piankov.auctions.exception.UnauthorizedAccessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AuctionsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws UnauthorizedAccessException, CommandExecutionException {
        AuctionDAO auctionDAO = new AuctionDAO();
        try {
            int stateId;

            switch (request.getParameter("auctionsState")) {
                case "active":
                    stateId = 2;
                    break;
                case "verification":
                    User user = (User) request.getSession().getAttribute("user");
                    if (user != null && user.isAdmin()) {
                        stateId = 1;
                    } else {
                        throw new UnauthorizedAccessException("This user has no access to this command.");
                    }
                    break;
                default:
                    throw new CommandExecutionException("Unable to find suitable command for your request.");
            }

            List<Auction> auctions = auctionDAO.findAuctionsByState(stateId);
            request.setAttribute("auctions", auctions);
            request.getRequestDispatcher("pages/list.jsp").forward(request, response);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}