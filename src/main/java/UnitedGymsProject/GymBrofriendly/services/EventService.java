package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.Event;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.NewEventDTO;
import UnitedGymsProject.GymBrofriendly.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Page<Event> getAllEvents(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return eventRepository.findAll(pageable);
    }

    public Event findById(long id) throws NotFoundException{
        return eventRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("L'evento con id " + id + " non esiste"));
    }

    public Page<Event> findByUserList(User user, int page, int size, String orderBy){
            Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
            return eventRepository.findByUserList(user, pageable).orElseThrow(
                    ()-> new NotFoundException("L'utente " + user.getId() + " non è presente in alcun evento"));
    }

    public Event save(NewEventDTO body) throws IOException{
        eventRepository.findByTitle(body.title()).ifPresent(
                title -> {throw new BadRequestException("Titolo " + body.title() + " già esistente");
                });
        Event newEvent = new Event();
        newEvent.setEventDate(body.eventDate());
        newEvent.setTitle(body.title());
        newEvent.setUserList(body.userList());
        newEvent.setPlace(body.place());
        newEvent.setMaxAttendance(body.maxAttendance());
        return eventRepository.save(newEvent);
    }

    public Event findByIdAndUpdate(long id, Event body) throws NotFoundException {
        Event eventToModify = this.findById(id);
        eventToModify.setTitle(body.getTitle());
        eventToModify.setEventDate(body.getEventDate());
        eventToModify.setPlace(body.getPlace());
        eventToModify.setMaxAttendance(body.getMaxAttendance());
        eventToModify.setUserList(body.getUserList());
        return eventRepository.save(eventToModify);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        eventRepository.deleteById(id);
    }
}
