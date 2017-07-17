package com.epicodus.ak.hairsalon.db;

import com.epicodus.ak.hairsalon.model.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.converters.Convert;

import java.time.LocalDate;
import java.util.List;

public class PgDatabase implements Database {

    private Sql2o connectionWrapper;

    public PgDatabase(String url, String user, String password) {
        connectionWrapper = new Sql2o(url, user, password);
        Convert.registerConverter(LocalDate.class, new LocalDateConverter());
    }

    @Override
    public List<Stylist> getStylists() {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,lastName,firstName FROM stylists")
                .executeAndFetch(Stylist.class);
        }
    }

    @Override
    public Stylist getStylistById(int id) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,lastName,firstName FROM stylists WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(Stylist.class);
        }
    }

    @Override
    public void updateStylist(Stylist stylist) {
        try (Connection connection = connectionWrapper.open()) {
            if (stylist.getId() > 0) {
                connection
                    .createQuery("UPDATE stylists SET lastName=:lastName, firstName=:firstName WHERE id=:id")
                    .bind(stylist)
                    .executeUpdate();
            } else {
                stylist.setId(connection
                    .createQuery("INSERT INTO stylists(lastName,firstName) VALUES (:lastName,:firstName)", true)
                    .bind(stylist)
                    .executeUpdate()
                    // make sure [id] is the first defined column in [stylists] table
                    .getKey(int.class));
            }
        }
    }

    @Override
    public boolean anyAppointmentByStylist(int stylistId) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT EXISTS(SELECT id FROM appointments WHERE stylistId=:stylistId)")
                .addParameter("stylistId", stylistId)
                .executeScalar(boolean.class);
        }
    }

    @Override
    public void deleteStylist(int id) {
        try (Connection connection = connectionWrapper.beginTransaction()) {
            connection
                .createQuery("UPDATE clients SET stylistId=NULL WHERE stylistId=:stylistId")
                .addParameter("stylistId", id)
                .executeUpdate();

            connection
                .createQuery("DELETE FROM stylists WHERE id=:id")
                .addParameter("id", id)
                .executeUpdate();

            connection.commit();
        }
    }

    @Override
    public List<Client> getClients() {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,lastName,firstName,gender,dateOfBirth,stylistId FROM clients")
                .executeAndFetch(Client.class);
        }
    }

    @Override
    public List<Client> getClientsByStylist(int stylistId) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,lastName,firstName,gender,dateOfBirth,stylistId FROM clients WHERE stylistId=:stylistId")
                .addParameter("stylistId", stylistId)
                .executeAndFetch(Client.class);
        }
    }

    @Override
    public Client getClientById(int id) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,lastName,firstName,gender,dateOfBirth,stylistId FROM clients WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(Client.class);
        }
    }

    @Override
    public void updateClient(Client client) {
        try (Connection connection = connectionWrapper.open()) {
            if (client.getId() > 0) {
                connection
                    .createQuery("UPDATE clients SET lastName=:lastName,firstName=:firstName,gender=:gender,dateOfBirth=:dateOfBirth,stylistId=:stylistId WHERE id=:id")
                    .bind(client)
                    .executeUpdate();
            } else {
                client.setId(connection
                    .createQuery("INSERT INTO clients(lastName,firstName,gender,dateOfBirth,stylistId) VALUES (:lastName,:firstName,:gender,:dateOfBirth,:stylistId)", true)
                    .bind(client)
                    .executeUpdate()
                    // make sure [id] is the first defined column in [clients] table
                    .getKey(int.class));
            }
        }
    }

    @Override
    public void deleteClient(int id) {
        try (Connection connection = connectionWrapper.beginTransaction()) {
            connection
                .createQuery("DELETE FROM appointments WHERE clientId=:clientId")
                .addParameter("clientId", id)
                .executeUpdate();

            connection
                .createQuery("DELETE FROM clients WHERE id=:id")
                .addParameter("id", id)
                .executeUpdate();

            connection.commit();
        }
    }

    @Override
    public List<Appointment> getAppointments() {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,clientId,stylistId,dateAndTime FROM appointments")
                .executeAndFetch(Appointment.class);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByClient(int clientId) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,clientId,stylistId,dateAndTime FROM appointments WHERE clientId=:clientId")
                .addParameter("clientId", clientId)
                .executeAndFetch(Appointment.class);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByStylist(int stylistId) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,clientId,stylistId,dateAndTime FROM appointments WHERE stylistId=:stylistId")
                .addParameter("stylistId", stylistId)
                .executeAndFetch(Appointment.class);
        }
    }

    @Override
    public Appointment getAppointmentById(int id) {
        try (Connection connection = connectionWrapper.open()) {
            return connection
                .createQuery("SELECT id,clientId,stylistId,dateAndTime FROM appointments WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(Appointment.class);
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        try (Connection connection = connectionWrapper.open()) {
            if (appointment.getId() > 0) {
                connection
                    .createQuery("UPDATE appointments SET clientId=:clientId,stylistId=:stylistId,dateAndTime=:dateAndTime WHERE id=:id")
                    .bind(appointment)
                    .executeUpdate();
            } else {
                appointment.setId(connection
                    .createQuery("INSERT INTO appointments(clientId,stylistId,dateAndTime) VALUES (:clientId,:stylistId,:dateAndTime)", true)
                    .bind(appointment)
                    .executeUpdate()
                    // make sure [id] is the first defined column in [appointments] table
                    .getKey(int.class));
            }
        }
    }

    @Override
    public void deleteAppointment(int id) {
        try (Connection connection = connectionWrapper.open()) {
            connection
                .createQuery("DELETE FROM appointments WHERE id=:id")
                .addParameter("id", id)
                .executeUpdate();
        }
    }
}