package ru.job4j.tdd;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Ignore;
import org.junit.Test;
import java.util.Calendar;
import java.util.List;

@Ignore
public class CinemaTest {

    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.AUGUST, 10, 23, 0);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void addSession() {
        Cinema3D cinema = new Cinema3D();
        cinema.add(new Session3D());
        cinema.add(new Session4D());
        assertThat(cinema.getListSession(), is(List.of(new Session3D(), new Session4D())));
    }

    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(List.of(new Session3D())));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIncorrectDate() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2005, Calendar.NOVEMBER, 10, 23, 10);
        Ticket ticket = cinema.buy(account, 1, 1, date);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenIncorrectPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.AUGUST, 10, 23, 0);
        Ticket ticket = cinema.buy(account, -1, 500, date);
    }
}