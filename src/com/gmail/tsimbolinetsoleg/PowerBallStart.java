package com.gmail.tsimbolinetsoleg;


import TicketLibrary.*;

import java.util.HashMap;
import java.util.List;

class PowerBallStart {

    static void run() {
        PowerBallManager powerBallManager = new PowerBallManager(new RandomTicketRegistration(), new ConsoleIOProvider(),
                new PowerBallStatistic(), new PowerBallChecker());
        System.out.println("How many tickets do you want to create?");
        int ticketsCount = powerBallManager.inputValue();
        List<Ticket> tickets = powerBallManager.createTickets(ticketsCount);
        Ticket ticketWinNumber = powerBallManager.registerTicket();
         tickets.add(powerBallManager.registerTicketYourself());  //if u want to add 1 ticket Yourself
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.checkForWinners(tickets, ticketWinNumber);
        powerBallManager.printWinners(winners);
        powerBallManager.statisticOfWinning(ticketsCount, winners);
    }
}