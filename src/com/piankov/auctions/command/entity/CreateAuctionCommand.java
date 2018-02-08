package com.piankov.auctions.command.entity;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.*;
import com.piankov.auctions.validator.EntityValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class CreateAuctionCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int startPrice = Integer.parseInt(request.getParameter("startPrice"));
        int days = Integer.parseInt(request.getParameter("days"));
        User user = (User) request.getSession().getAttribute("user");

        EntityValidator entityValidator = new EntityValidator();

        if (entityValidator.validateRegistrationData()) {
            try {
                LotDAO lotDAO = new LotDAO();
                Lot lot = new Lot();
                lot.setName(name);
                lot.setDescription(description);
                lot.setOwner(user);
                lot.setStartPrice(startPrice);
                long generatedLotId = lotDAO.create(lot);
                lot.setId(generatedLotId);

                AuctionDAO auctionDAO = new AuctionDAO();
                Auction auction = new Auction();
                auction.setLot(lot);
                auction.setState(AuctionState.IN_PROGRESS);
                auction.setType(AuctionType.DIRECT);
                long generatedAuctionId = auctionDAO.create(auction);
                auction.setId(generatedAuctionId);

                request.getRequestDispatcher("pages/home.jsp").forward(request, response);
            } catch (SQLException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
