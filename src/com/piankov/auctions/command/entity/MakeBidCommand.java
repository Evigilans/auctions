package com.piankov.auctions.command.entity;

import com.piankov.auctions.command.Command;
import com.piankov.auctions.dao.BidDAO;
import com.piankov.auctions.entity.Bid;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.validator.EntityValidator;

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
        User user = (User) request.getSession().getAttribute("user");

        EntityValidator entityValidator = new EntityValidator();

        if (entityValidator.validateRegistrationData()) {
            try {
                Bid bid = new Bid();
                bid.setClientId(user.getId());
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
