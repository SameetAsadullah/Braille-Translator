package com.sameetasadullah.i180479_i180531.logicLayer;

import java.time.LocalDate;
import java.util.*;

public class Reservation {
    private Vector<Room> reservedRooms;
    private Customer customer;
    private LocalDate checkInDate, checkOutDate;

    public Reservation(Vector<Room> rooms, Customer c, LocalDate inDate, LocalDate outDate) {
        reservedRooms = rooms;
        customer = c;
        checkInDate = inDate;
        checkOutDate = outDate;
    }
}
