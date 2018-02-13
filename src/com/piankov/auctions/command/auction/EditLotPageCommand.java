package com.piankov.auctions.command.auction;

import com.piankov.auctions.action.LotAction;
import com.piankov.auctions.command.Command;
import com.piankov.auctions.constant.PageConstant;
import com.piankov.auctions.constant.ParameterConstant;
import com.piankov.auctions.entity.Lot;
import com.piankov.auctions.entity.User;
import com.piankov.auctions.exception.ActionPerformingException;
import com.piankov.auctions.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditLotPageCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(EditLotPageCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        try {
            LotAction lotAction = new LotAction();
            String lotId = request.getParameter(ParameterConstant.PARAMETER_LOT_ID);

            Lot lot = lotAction.findLotById(lotId);

            if (lot != null) {
                User user = (User) request.getSession().getAttribute(ParameterConstant.PARAMETER_USER);

                if (user.equals(lot.getOwner()) || user.isAdmin()) {
                    request.setAttribute(ParameterConstant.PARAMETER_LOT, lot);
                    request.getRequestDispatcher(PageConstant.PAGE_EDIT_LOT).forward(request, response);
                } else {
                    //
                }
            } else {
                //
            }
        } catch (ServletException | IOException | ActionPerformingException e) {
            throw  new CommandExecutionException("An exception occurred during 'Edit Lot Page' command execution.", e);        }
    }
}
