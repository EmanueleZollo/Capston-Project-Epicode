package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.Subscription;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.SubscriptionDTO;
import UnitedGymsProject.GymBrofriendly.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.time.LocalDate;

import static UnitedGymsProject.GymBrofriendly.enums.SubscriptionType.*;

public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Page<Subscription> getAllSubscriptions(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return subscriptionRepository.findAll(pageable);
    }

    public Subscription findById(long id) throws NotFoundException{
        return subscriptionRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Id " + id + " non trovato"));
    }

    public Subscription findByUser(User user) throws NotFoundException{
        return subscriptionRepository.findByUser(user).orElseThrow(
                ()-> new NotFoundException("Utente con Id " + user.getId() + " non trovato"));
    }

    public Subscription save(SubscriptionDTO body) throws IOException {
        subscriptionRepository.findByUser(body.user()).ifPresent(
                user -> {
                    throw new BadRequestException("Utente " + body.user() + " già esistente");
                });

        Subscription newSub = new Subscription();
        newSub.setActivationDate(LocalDate.now());
        newSub.setSubscriptionType(body.subscriptionType());
        switch (body.subscriptionType()){
            case MENSILE -> newSub.setExpirationDate(LocalDate.now().plusMonths(1));
            case TRIMESTRALE -> newSub.setExpirationDate(LocalDate.now().plusMonths(3));
            case SEMESTRALE -> newSub.setExpirationDate(LocalDate.now().plusMonths(6));
            case ANNUALE -> newSub.setExpirationDate(LocalDate.now().plusYears(1));
        }
        newSub.setUser(body.user());
        return subscriptionRepository.save(newSub);
    }

    public Subscription findByUserAndUpdate(User user, Subscription body) throws NotFoundException{
        Subscription oldSub = this.findByUser(user);
        oldSub.setActivationDate(body.getActivationDate());
        oldSub.setSubscriptionType(body.getSubscriptionType());
        switch (body.getSubscriptionType()) {
            case MENSILE -> oldSub.setExpirationDate(LocalDate.now().plusMonths(1));
            case TRIMESTRALE -> oldSub.setExpirationDate(LocalDate.now().plusMonths(3));
            case SEMESTRALE -> oldSub.setExpirationDate(LocalDate.now().plusMonths(6));
            case ANNUALE -> oldSub.setExpirationDate(LocalDate.now().plusYears(1));
        }
        return subscriptionRepository.save(oldSub);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        subscriptionRepository.deleteById(id);
    }
}
