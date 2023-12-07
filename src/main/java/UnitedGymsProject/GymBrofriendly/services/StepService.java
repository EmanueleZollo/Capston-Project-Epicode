package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.Step;

import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;

import UnitedGymsProject.GymBrofriendly.payloads.entities.StepDTO;
import UnitedGymsProject.GymBrofriendly.repositories.StepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class StepService {

    @Autowired
    private StepRepository stepRepository;

    public Page<Step> getAllSteps(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return stepRepository.findAll(pageable);
    }

    public Step findById(long id) throws NotFoundException {
        return stepRepository.findById(id).orElseThrow(
        ()-> new NotFoundException("Questa scheda con id " + id + " non esiste"));
    }

    public Step findByTitle(String title){
        return stepRepository.findByTitle(title).orElseThrow(
                ()-> new NotFoundException("La scheda con il titolo " + title + " è inesistente"));
    }

    public Step save(StepDTO body) throws IOException {
        stepRepository.findByTitle(body.title()).ifPresent(
                title -> {throw new BadRequestException("Titolo " + body.title() + " già esistente");
                });
        Step newStep = new Step();
        newStep.setTitle(body.title());
        newStep.setDescription(body.description());
        newStep.setExerciseImageUrl(body.exerciseImageUrl());
        newStep.setExerciseSet(body.exerciseSet());
        return stepRepository.save(newStep);
    }

    public Step findByIdAndUpdate(long id, Step body) throws NotFoundException {
        Step stepToModify = this.findById(id);
        stepToModify.setTitle(body.getTitle());
        stepToModify.setDescription(body.getDescription());
        stepToModify.setAdditionalInfo(body.getAdditionalInfo());
        stepToModify.setExerciseImageUrl(body.getExerciseImageUrl());
        stepToModify.setExerciseSet(body.getExerciseSet());
        stepToModify.setRestTime(body.getRestTime());
        stepToModify.setWeight(body.getWeight());
        stepToModify.setPlan(body.getPlan());
        return stepRepository.save(stepToModify);
    }

    public Step findByTitleAndUpdate(String title, Step body) throws NotFoundException {
        Step stepToModify = this.findByTitle(title);
        stepToModify.setDescription(body.getDescription());
        stepToModify.setAdditionalInfo(body.getAdditionalInfo());
        stepToModify.setExerciseImageUrl(body.getExerciseImageUrl());
        stepToModify.setExerciseSet(body.getExerciseSet());
        stepToModify.setRestTime(body.getRestTime());
        stepToModify.setWeight(body.getWeight());
        stepToModify.setPlan(body.getPlan());
        return stepRepository.save(stepToModify);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        stepRepository.deleteById(id);
    }
}

