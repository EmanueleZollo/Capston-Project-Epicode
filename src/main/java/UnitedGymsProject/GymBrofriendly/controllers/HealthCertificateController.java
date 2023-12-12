package UnitedGymsProject.GymBrofriendly.controllers;

import UnitedGymsProject.GymBrofriendly.entities.HealthCertificate;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.payloads.entities.HealthCertificateDTO;
import UnitedGymsProject.GymBrofriendly.services.HealthCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/certificatimedici")
public class HealthCertificateController {

    @Autowired
    private HealthCertificateService healthCertificateService;

    @GetMapping("")
    public Page<HealthCertificate> getHealthCertificate(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "25") int size,
                                                        @RequestParam(defaultValue = "id") String orderBy){
        return healthCertificateService.getCertificateSet(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public HealthCertificate findById(@PathVariable UUID id){
        return healthCertificateService.findById(id);
    }

    @GetMapping("/{user}") // mail?
    public HealthCertificate findByUser(@PathVariable User user){
        return healthCertificateService.findByUser(user);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public HealthCertificate saveHealthCertificate(@RequestBody HealthCertificateDTO body) throws IOException {
        return healthCertificateService.save(body);
    }

    /*@PostMapping("/upload")
    public String uploadExample(@RequestParam("avatar") MultipartFile body) throws IOException {
        // il nome del parametro "avatar" deve corrispondere ESATTAMENTE al nome dell'attributo del FormData che si è concordato col frontend per allegare il file
        System.out.println(body.getSize());
        System.out.println(body.getContentType());
        return usersService.uploadPicture(body);*/

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findByIdAndDelete(@PathVariable UUID id) {
        healthCertificateService.findByIdAndDelete(id);
    }


    }



