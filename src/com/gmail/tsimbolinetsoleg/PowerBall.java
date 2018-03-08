package com.gmail.tsimbolinetsoleg;


import TicketLibrary.Ticket;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PowerBall {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Scanner sc = new Scanner(System.in);
        System.out.println("How many tickets do u want to createTickets?");
        int ticketsCount = sc.nextInt();
        List<Ticket> tickets = ticket.createTickets(ticketsCount);
        Ticket ticketWinNumber = ticket.registerTicket();
        tickets.add(ticket.registerTicketYourself());  //if u want to add 1 ticket Yourself
        HashMap<Ticket.possiblePrize, List<Ticket>> winners = ticket.CheckForWinners(tickets,ticketWinNumber);
        ticket.printWinners(winners);
    }
}
