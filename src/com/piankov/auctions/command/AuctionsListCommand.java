package com.piankov.auctions.command;

import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.entity.Auction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AuctionsListCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        AuctionDAO auctionDAO = new AuctionDAO();
        try {
            List<Auction> auctions = auctionDAO.findActiveAuctions();
            request.setAttribute("auctions", auctions);
            request.getRequestDispatcher("pages/list.jsp").forward(request, response);
        } catch (SQLException | IOException | ServletException e) {
            e.printStackTrace();
        }
    }
}
