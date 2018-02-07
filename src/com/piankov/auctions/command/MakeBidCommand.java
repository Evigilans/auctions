package com.piankov.auctions.command;

import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.entity.Bid;
import com.piankov.auctions.entity.Client;
import com.piankov.auctions.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class MakeBidCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        int value = Integer.parseInt(request.getParameter("value"));
        String auctionId = request.getParameter("auctionId");
        Client client = (Client) request.getSession().getAttribute("client");

        Validator validator = new Validator();

        if (validator.validateRegistrationData()) {
            try {
                Bid bid = new Bid();
                bid.setClientId(client.getId());
                bid.setAuctionId(Long.parseLong(auctionId));
                bid.setValue(value);

                BidDAO bidDAO = new BidDAO();
                long generatedId = bidDAO.create(bid);

                bid.setId(generatedId);

                request.getRequestDispatcher("pages/home.jsp").forward(request, response);
            } catch (ServletException | IOException | SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
