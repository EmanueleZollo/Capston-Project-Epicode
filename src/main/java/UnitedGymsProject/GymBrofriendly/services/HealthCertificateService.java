package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.HealthCertificate;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.HealthCertificateDTO;
import UnitedGymsProject.GymBrofriendly.repositories.HealthCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class HealthCertificateService {
    @Autowired
    private HealthCertificateRepository healthCertificateRepository;

    /*COLLEGARE CLOUDINARY*/


    public Page<HealthCertificate> getCertificateList(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(orderBy));
        return healthCertificateRepository.findAll(pageable);
    }

    public HealthCertificate findById(UUID id){
        return healthCertificateRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("ID " + id + " non trovato"));
    }

    public HealthCertificate findByUser(User user){
        return healthCertificateRepository.findByUser(user).orElseThrow(
                ()-> new NotFoundException("Utente " + user.getId() + " non trovato"));
    }

    public HealthCertificate save(HealthCertificateDTO body) throws IOException {
        healthCertificateRepository.findByUser(body.user()).ifPresent(user ->
        { throw new BadRequestException("Questo utente " + user.getId() + "è già stato creato");
        });

        HealthCertificate newHCert = new HealthCertificate();
        newHCert.setUrl(body.url());
        newHCert.setNewCertificate(LocalDate.now());
        newHCert.setExpiredCertificate(LocalDate.now().plusYears(1));
        newHCert.setUser(body.user());
        return healthCertificateRepository.save(newHCert);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException{
        healthCertificateRepository.deleteById(id);
    }


    /*FUNZIONE CHE CARICA IL FILE URL*/
}
