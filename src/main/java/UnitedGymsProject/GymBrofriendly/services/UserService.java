package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.UserSignUpDTO;
import UnitedGymsProject.GymBrofriendly.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*COLLEGARE CLOUDINARY*/

/*FUNZIONE CHE SALVA QUALSIASI UTENTE - LA DISTINZIONE PER RUOLO
        VERRA' GESTITA SOLO DAL TITOLARE E DALL'ADMIN */
    public User save(UserSignUpDTO body) throws IOException {
        userRepository.findByEmail(body.email()).ifPresent(user ->
        {throw new BadRequestException
                ("Esiste già un utente registrato con questa mail" + body.email());
        });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setPassword(body.password());
        return userRepository.save(newUser);
    }

    public Page<User> getUsers(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return userRepository.findAll(pageable);
    }

    public User findById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByEmail(String email) throws NotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }

    public User findByEmailAndUpdate(String email, User body) throws NotFoundException{
        User userFound = this.findByEmail(email);
        userFound.setName(body.getName());
        userFound.setSurname(body.getSurname());
        userFound.setEmail(body.getEmail());
        /*userFound.setPassword(body.getPassword());*/
        userFound.setAvatarUrl(body.getAvatarUrl());
        userFound.setRole(body.getRole());
        userFound.setSubscription(body.getSubscription());
        userFound.setHealthCertificate(body.getHealthCertificate());
        userFound.setEventList(body.getEventList());
        userFound.setPlanList(body.getPlanList());
        userFound.setShoppingCart(body.getShoppingCart());
        return userRepository.save(userFound);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        userRepository.deleteById(id);
    }

    /*FUNZIONE CHE CARICA IL FILE AVATAR*/
}
