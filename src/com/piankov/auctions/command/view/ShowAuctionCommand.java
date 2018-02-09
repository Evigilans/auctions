package com.piankov.auctions.command.view;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.entity.Auction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ShowAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        try (AuctionDAO auctionDAO = new AuctionDAO();) {
            Auction auction = auctionDAO.findById(request.getParameter("id"));
            request.setAttribute("auction", auction);
            request.getRequestDispatcher("pages/auction.jsp").forward(request, response);
        } catch (IOException | ServletException | SQLException e) {
            e.printStackTrace();
        }
    }
}
