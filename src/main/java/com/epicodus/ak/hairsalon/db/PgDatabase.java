package com.epicodus.ak.hairsalon.db;

import com.epicodus.ak.hairsalon.model.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

public class PgDatabase implements Database {

    private Sql2o connectionWrapper;

    public PgDatabase(String url, String user, String password) {
        connectionWrapper = new Sql2o(url, user, password);
    }

    @Override
    public List<Stylist> getStylists() {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,lastname,firstname FROM stylists")
                .executeAndFetch(Stylist.class);
        }
    }

    @Override
    public Stylist getStylistById(int id) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,lastname,firstname FROM stylists WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(Stylist.class);
        }
    }

    @Override
    public void updateStylist(Stylist stylist) {
        try(Connection connection = connectionWrapper.open()){
            if (stylist.getId() > 0) {
               connection
                   .createQuery("UPDATE stylists SET lastname=:lastname, firstname=:firstname WHERE id=:id")
                   .bind(stylist)
                   .executeUpdate();
            }
            else {
                stylist.setId(connection
                    .createQuery("INSERT INTO stylists(lastname,firstname) VALUES (:lastname,:firstname)",true)
                    .bind(stylist)
                    .executeUpdate()
                    // make sure [id] is the first defined column in [stylists] table
                    .getKey(int.class));
            }
        }
    }

    @Override
    public boolean canDeleteStylist(int id) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT NOT EXISTS(SELECT id FROM appointments WHERE stylistid=:id)")
                .addParameter("id", id)
                .executeScalar(boolean.class);
        }
    }

    @Override
    public void deleteStylist(int id) {
        try(Connection connection = connectionWrapper.beginTransaction()){
            connection
                .createQuery("UPDATE clients SET stylistid=NULL WHERE stylistid=:id")
                .addParameter("id", id)
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
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,lastname,firstname,gender,dateofbirth,stylistid FROM clients")
                .executeAndFetch(Client.class);
        }
    }

    @Override
    public List<Client> getClientsByStylist(int stylistId) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,lastname,firstname,gender,dateofbirth,stylistid FROM clients WHERE stylistid=:stylistid")
                .addParameter("stylistid", stylistId)
                .executeAndFetch(Client.class);
        }
    }

    @Override
    public Client getClientById(int id) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,lastname,firstname,gender,dateofbirth,stylistid FROM clients WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(Client.class);
        }
    }

    @Override
    public void updateClient(Client client) {
        try(Connection connection = connectionWrapper.open()){
            if (client.getId() > 0) {
                connection
                    .createQuery("UPDATE clients SET lastname=:lastname,firstname=:firstname,gender=:gender,dateofbirth=:dateofbirth,stylistid=:stylistid WHERE id=:id")
                    .bind(client)
                    .executeUpdate();
            }
            else {
                client.setId(connection
                    .createQuery("INSERT INTO clients(lastname,firstname,gender,dateofbirth,stylistid) VALUES (:lastname,:firstname,:gender,:dateofbirth,:stylistid)",true)
                    .bind(client)
                    .executeUpdate()
                    // make sure [id] is the first defined column in [clients] table
                    .getKey(int.class));
            }
        }
    }

    @Override
    public void deleteClient(int id) {
        try(Connection connection = connectionWrapper.beginTransaction()){
            connection
                .createQuery("DELETE FROM appointments WHERE clientid=:id")
                .addParameter("id", id)
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
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,clientid,stylistid,dateandtime FROM appointments")
                .executeAndFetch(Appointment.class);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByClient(int clientId) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,clientid,stylistid,dateandtime FROM appointments WHERE clientid=:clientId")
                .addParameter("clientId", clientId)
                .executeAndFetch(Appointment.class);
        }
    }

    @Override
    public List<Appointment> getAppointmentsByStylist(int stylistId) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,clientid,stylistid,dateandtime FROM appointments WHERE stylistid=:stylistId")
                .addParameter("stylistId", stylistId)
                .executeAndFetch(Appointment.class);
        }
    }

    @Override
    public Appointment getAppointmentById(int id) {
        try(Connection connection = connectionWrapper.open()){
            return connection
                .createQuery("SELECT id,clientid,stylistid,dateandtime FROM appointments WHERE id=:id")
                .addParameter("id", id)
                .executeAndFetchFirst(Appointment.class);
        }
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        try(Connection connection = connectionWrapper.open()){
            if (appointment.getId() > 0) {
                connection
                    .createQuery("UPDATE appointments SET clientid=:clientid,stylistid=:stylistid,dateandtime=:dateandtime WHERE id=:id")
                    .bind(appointment)
                    .executeUpdate();
            }
            else {
                appointment.setId(connection
                    .createQuery("INSERT INTO appointments(clientid,stylistid,dateandtime) VALUES (:clientid,:stylistid,:dateandtime)",true)
                    .bind(appointment)
                    .executeUpdate()
                    // make sure [id] is the first defined column in [appointments] table
                    .getKey(int.class));
            }
        }
    }

    @Override
    public void deleteAppointment(int id) {
        try(Connection connection = connectionWrapper.open()){
            connection
                .createQuery("DELETE FROM appointments WHERE id=:id")
                .addParameter("id", id)
                .executeUpdate();
        }
    }
}