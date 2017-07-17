package com.epicodus.ak.hairsalon.db;

import com.epicodus.ak.hairsalon.model.*;

import java.util.List;

public interface Database {
    List<Stylist> getStylists();
    Stylist getStylistById(int id);
    void updateStylist(Stylist stylist);
    boolean anyAppointmentByStylist(int stylistId);
    void deleteStylist(int id);

    List<Client> getClients();
    List<Client> getClientsByStylist(int stylistId);
    Client getClientById(int id);
    void updateClient(Client client);
    void deleteClient(int id);

    List<Appointment> getAppointments();
    List<Appointment> getAppointmentsByClient(int clientId);
    List<Appointment> getAppointmentsByStylist(int stylistId);
    Appointment getAppointmentById(int id);
    void updateAppointment(Appointment appointment);
    void deleteAppointment(int id);
}
