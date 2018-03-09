package com.gmail.tsimbolinetsoleg;


import TicketLibrary.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class PowerBallStart {

    static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many tickets do you want to create?");
        int ticketsCount = sc.nextInt();
        PowerBallManager powerBallManager = new PowerBallManager(new RandomTicketRegistration(), new ConsoleOutputProvider(),
                new PowerBallStatistic(), new PowerBallChecker());
        List<Ticket> tickets = powerBallManager.createTickets(ticketsCount);
        Ticket ticketWinNumber = powerBallManager.registerTicket();
         tickets.add(powerBallManager.registerTicketYourself());  //if u want to add 1 ticket Yourself
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.checkForWinners(tickets, ticketWinNumber);
        powerBallManager.printWinners(winners);
    }
}