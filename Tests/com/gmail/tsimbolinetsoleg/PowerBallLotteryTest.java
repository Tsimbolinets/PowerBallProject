package com.gmail.tsimbolinetsoleg;

import TicketLibrary.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PowerBallLotteryTest {

    private PowerBallLottery powerB;
    private PowerBallManager powerBallManager = new PowerBallManager(new RandomTicketRegistration(),
            new ConsoleOutputProvider(), new PowerBallStatistic(), new PowerBallChecker());
    private int ticketsCount = 3000000;

    @Before
    public void setUp() throws Exception {
        powerB = new PowerBallLottery();
    }

    @Test
    public void registrationTicketTest() throws Exception {
        System.out.println();
        System.out.println("Registration Ticket Test:");
        Ticket ticket = powerBallManager.registerTicket();
        assertEquals(6, ticket.getGameFilledBalls().size());
        System.out.println(ticket.getGameFilledBalls());
    }

    @Test
    public void testForWinners() throws Exception {
        System.out.println();
        System.out.println(" Winners Test:");
        List<Ticket> tickets = powerBallManager.createTickets(ticketsCount / 10);
        Ticket ticketWinNumber = powerBallManager.registerTicket();
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.checkForWinners(tickets, ticketWinNumber);
        powerBallManager.printWinners(winners);
    }

    @Test
    public void testForJackpotWinners() throws Exception {
        System.out.println();
        System.out.println("Test for Jackpot Winner:");
        List<Ticket> tickets = powerBallManager.createTickets(10);
        Ticket ticketWinNumber = powerBallManager.registerTicket();
        tickets.add(ticketWinNumber);
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.checkForWinners(tickets, ticketWinNumber);
        powerBallManager.printWinners(winners);
    }

    @Test
    public void statisticTest() throws Exception {
        System.out.println();
        System.out.println("Statistic Test:");
        List<Ticket> tickets = powerBallManager.createTickets(ticketsCount);
        Ticket ticketWinNumber = powerBallManager.registerTicket();
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.checkForWinners(tickets, ticketWinNumber);
        powerBallManager.printWinners(winners);
        powerBallManager.statisticOfWinning(ticketsCount, winners);
    }
}
