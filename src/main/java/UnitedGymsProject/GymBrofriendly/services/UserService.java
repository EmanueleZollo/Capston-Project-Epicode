package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.UserSignUpDTO;
import UnitedGymsProject.GymBrofriendly.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

/*FUNZIONE CHE SALVA QUALSIASI UTENTE - LA DISTINZIONE PER RUOLO
        VERRA' GESTITA SOLO DAL TITOLARE E DALL'ADMIN */
    public User save(UserSignUpDTO body) throws IOException {
        userRepository.findByEmail(body.email()).ifPresent(user ->
        {throw new BadRequestException("Esiste già un utente registrato con questa mail" + body.email())
        });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        newUser.setPassword(body.password());
        return userRepository.save(newUser);
    }

    
}
