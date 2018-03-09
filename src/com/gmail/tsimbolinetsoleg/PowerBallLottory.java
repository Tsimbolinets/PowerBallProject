package com.gmail.tsimbolinetsoleg;


import TicketLibrary.*;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PowerBallLottory {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("How many tickets do you want to create?");
        int ticketsCount = sc.nextInt();
        PowerBallManager powerBallManager = new PowerBallManager(new RandomTicketRegistration(), new IUserOutputProvider());
          List<Ticket> tickets = powerBallManager.getRegistrar().createTickets(ticketsCount);
          Ticket ticketWinNumber = powerBallManager.getRegistrar().registerTicket();
       // tickets.add(powerBallManager.getRegistrar().registerTicketYourself());  //if u want to add 1 ticket Yourself
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.CheckForWinners(tickets,ticketWinNumber);
        powerBallManager.getIUserOutputProvider().printWinners(winners);
    }
}
