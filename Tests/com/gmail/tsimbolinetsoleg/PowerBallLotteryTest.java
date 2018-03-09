package com.gmail.tsimbolinetsoleg;

import TicketLibrary.IUserOutputProvider;
import TicketLibrary.PowerBallManager;
import TicketLibrary.RandomTicketRegistration;
import TicketLibrary.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PowerBallLotteryTest {

    private PowerBallLottery powerB;
    private PowerBallManager powerBallManager = new PowerBallManager(new RandomTicketRegistration(), new IUserOutputProvider());

    private int ticketsCount = 3000000;

    @Before
    public void setUp() throws Exception {
        powerB = new PowerBallLottery();
    }

    @Test
    public void registrationTicketTest() throws Exception {
        System.out.println();
        System.out.println("Registration Ticket Test:");
        Ticket ticket = powerBallManager.getRegistrar().registerTicket();
        assertEquals(6, ticket.getGameFilledBalls().size());
        System.out.println(ticket.getGameFilledBalls());
    }

    @Test
    public void testForWinners() throws Exception {
        System.out.println();
        System.out.println(" Winners Test:");
        List<Ticket> tickets = powerBallManager.getRegistrar().createTickets(ticketsCount / 10);
        Ticket ticketWinNumber = powerBallManager.getRegistrar().registerTicket();
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.CheckForWinners(tickets, ticketWinNumber);
        powerBallManager.getIUserOutputProvider().printWinners(winners);
    }

    @Test
    public void testForJackpotWinners() throws Exception {
        System.out.println();
        System.out.println("Test for Jackpot Winner:");
        List<Ticket> tickets = powerBallManager.getRegistrar().createTickets(10);
        Ticket ticketWinNumber = powerBallManager.getRegistrar().registerTicket();
        tickets.add(ticketWinNumber);
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.CheckForWinners(tickets, ticketWinNumber);
        powerBallManager.getIUserOutputProvider().printWinners(winners);
    }

    @Test
    public void statisticTest() throws Exception {
        System.out.println();
        System.out.println("Statistic Test:");
        List<Ticket> tickets = powerBallManager.getRegistrar().createTickets(ticketsCount);
        Ticket ticketWinNumber = powerBallManager.getRegistrar().registerTicket();
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = powerBallManager.CheckForWinners(tickets, ticketWinNumber);
        powerBallManager.getIUserOutputProvider().printWinners(winners);
        powerBallManager.statisticOfWinning(ticketsCount, winners);
    }
}
