package com.piankov.auctions.command;

import com.piankov.auctions.dao.AuctionDAO;
import com.piankov.auctions.dao.LotDAO;
import com.piankov.auctions.entity.Auction;
import com.piankov.auctions.entity.Client;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.validator.Validator;

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
        Client client = (Client) request.getSession().getAttribute("client");

        Validator validator = new Validator();

        if (validator.validateRegistrationData()) {
            try {
                Auction auction = new Auction();
                Lot lot = new Lot();

                lot.setName(name);
                lot.setDescription(description);
                lot.setOwnerId(client.getId());
                lot.setStartPrice(startPrice);

                LotDAO lotDAO = new LotDAO();
                long  generatedLotId = lotDAO.create(lot);
                lot.setId(generatedLotId);


                auction.setLot(lot);
                auction.setStateId(2);
                auction.setTypeId(1);

                AuctionDAO auctionDAO = new AuctionDAO();
                long  generatedAuctionId = auctionDAO.create(auction);
                auction.setId(generatedAuctionId);

                request.getRequestDispatcher("pages/home.jsp").forward(request, response);
            } catch (SQLException | ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
