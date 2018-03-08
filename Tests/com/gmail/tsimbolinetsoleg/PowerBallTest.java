package com.gmail.tsimbolinetsoleg;


import TicketLibrary.Ticket;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PowerBallTest {

    private PowerBall powerB;

    @Before
    public void setUp() throws Exception {
        powerB = new PowerBall();
    }

     @Test
     public void registrationTicketTest() throws Exception {
        Ticket ticket = new Ticket();
        ticket = ticket.registerTicket();
        assertEquals(6, ticket.getGameFilledBalls().size());
        System.out.println(ticket.getGameFilledBalls());
     }

    @Test
    public void testForWinners() throws Exception {
        Ticket ticket = new Ticket();
         List<Ticket> tickets = ticket.createTickets(100000);
         Ticket ticketWinNumber = ticket.registerTicket();
         HashMap<Ticket.possiblePrize, List<Ticket>> winners = ticket.CheckForWinners(tickets,ticketWinNumber);
         ticket.printWinners(winners);
     }

    @Test
    public void testForJackpotWinners() throws Exception {
        Ticket ticket = new Ticket();
        List<Ticket> tickets = ticket.createTickets(10);
        Ticket ticketWinNumber = ticket.registerTicket();
        tickets.add(ticketWinNumber);
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = ticket.CheckForWinners(tickets,ticketWinNumber);
        ticket.printWinners(winners);
    }

    @Test
    public void statisticTest() throws Exception {
        Ticket ticket = new Ticket();
        HashMap<Ticket.possiblePrize,Double> chanceToWin = new HashMap<>();

        chanceToWin.put(Ticket.possiblePrize.Four , 0.03718771815); // Chance to get red ball
                                                // is 1/26 * 64/69 * 63/68 * 62/67 *61/66 * 60/65 + 1/26 * 1/69 * 64/68 * 63/67 *62/66 * 61/65;

        chanceToWin.put(Ticket.possiblePrize.Seven , 0.00315071395); // Chance to get 3 white balls or 2 white and red ball
                                                // is 3/69 * 2/68 * 1/68 * 65/67 * 64/66 * 25/26 + 2/69 * 1/68 * 1/26 * 64/67 *63/66 * 62/65;

        chanceToWin.put(Ticket.possiblePrize.Hundred , 0.00009637192); // Chance to get 4 white balls or 3 white and red ball
                                                // is 4/69 * 3/68 *2/68 * 1/67 * 65/66 * 25/26 + 3/69 * 2/68 * 1/67 * 1/26  *64/66 * 63/65;

        chanceToWin.put(Ticket.possiblePrize.FiftyThouth , 0.00000109513); // Chance to get 4 white balls and red ball
                                                // is 4/69 * 3/68 *2/68 * 1/67 * 65/66 * 1/26;

        chanceToWin.put(Ticket.possiblePrize.Million , 8.55574453e-8); // Chance to get 5 white balls
                                                         // is 5/69 * 4/68 *3/68 * 2/67 * 1/66 * 25/26;

        chanceToWin.put(Ticket.possiblePrize.Jackpot , 3.42229781e-9); // Chance to get 5 white balls and red ball
                                                         // is 5/69 * 4/68 *3/68 * 2/67 * 1/66 * 1/26;
        int ticketsCount=3000000;
        List<Ticket> tickets = ticket.createTickets(ticketsCount);
        Ticket ticketWinNumber = ticket.registerTicket();
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = ticket.CheckForWinners(tickets,ticketWinNumber);
        ticket.printWinners(winners);

        List<Integer> peopleWon = new ArrayList<>();

        peopleWon.add(winners.get(Ticket.possiblePrize.Four).size()*4);
        peopleWon.add(winners.get(Ticket.possiblePrize.Seven).size()*7);
        peopleWon.add(winners.get(Ticket.possiblePrize.Hundred).size()*100);
        peopleWon.add(winners.get(Ticket.possiblePrize.FiftyThouth).size()*50000);
        peopleWon.add(winners.get(Ticket.possiblePrize.Million).size()*1000000);
        peopleWon.add(winners.get(Ticket.possiblePrize.Jackpot).size()*150000000);

        List<Double> statisticPeopleWon = new ArrayList<>();

        statisticPeopleWon.add(chanceToWin.get(Ticket.possiblePrize.Four)*ticketsCount*4);
        statisticPeopleWon.add(chanceToWin.get(Ticket.possiblePrize.Seven)*ticketsCount*7);
        statisticPeopleWon.add(chanceToWin.get(Ticket.possiblePrize.Hundred)*ticketsCount*100);
        statisticPeopleWon.add(chanceToWin.get(Ticket.possiblePrize.FiftyThouth)*ticketsCount*50000);
        statisticPeopleWon.add(chanceToWin.get(Ticket.possiblePrize.Million)*ticketsCount*1000000);
        statisticPeopleWon.add(chanceToWin.get(Ticket.possiblePrize.Jackpot)*ticketsCount*150000000);
        for(int i=0;i<statisticPeopleWon.size();i++) {
            System.out.println("People statistic won : " + String.format("%.2f",statisticPeopleWon.get(i)) + " $"
                    +  ";    People won : " + peopleWon.get(i) + " $");
        }
        int sumPeopleWon = 0;
        double sumStatisticPeopleWon = 0;
        for(int i=0;i<statisticPeopleWon.size();i++) {
            sumPeopleWon += peopleWon.get(i);
            sumStatisticPeopleWon += statisticPeopleWon.get(i);
        }
        System.out.println();
        System.out.println("People spend : " + ticketsCount*2 + " $");
        System.out.println("People statistic won : " + String.format("%.2f",sumStatisticPeopleWon) + " $" + ";     People won : " + sumPeopleWon + " $");
        }
    }
