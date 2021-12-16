package com.sameetasadullah.i180479_i180531.logicLayer;

import java.time.LocalDate;
import java.util.*;

public class Reservation {
    private Vector<Room> reservedRooms;
    private Customer customer;
    private LocalDate checkInDate, checkOutDate;

    public Vector<Room> getReservedRooms() {
        return reservedRooms;
    }

    public void setReservedRooms(Vector<Room> reservedRooms) {
        this.reservedRooms = reservedRooms;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Reservation(Vector<Room> rooms, Customer c, LocalDate inDate, LocalDate outDate) {
        reservedRooms = rooms;
        customer = c;
        checkInDate = inDate;
        checkOutDate = outDate;
    }

}
