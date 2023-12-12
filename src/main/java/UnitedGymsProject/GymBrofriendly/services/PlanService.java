package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.Plan;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.PlanDTO;
import UnitedGymsProject.GymBrofriendly.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public Page<Plan> getAllPlans(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return planRepository.findAll(pageable);
    }

    public Plan findById(long id) throws NotFoundException {
        return planRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Id " + id + " non trovato"));
    }

    public Page<Plan> findByUser(User user, int page, int size, String orderBy) throws NotFoundException{
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return planRepository.findByUserList(user, pageable).orElseThrow(
                ()-> new NotFoundException("Non esiste alcun piano dell'utente " + user));
    }

    public Plan save(PlanDTO body) throws IOException {
        
        Plan newPlan = new Plan();
       newPlan.setUserList(body.userList());
       newPlan.setStepList(body.stepList());
        switch (body.planType()){
            case NUTRITION -> newPlan.setPlanType(body.planType()); //AUTORIZZAZIONE DA Nutrizionista se è presente nella USERLIST?
            case WORKOUT -> newPlan.setPlanType(body.planType()); //AUTORIZZAZIONE DA PERSONAL_TRAINER se è presente nella USERLIST
        }
        return planRepository.save(newPlan);
    }

    public Plan findByUserAndUpdate(Long id, Plan body) throws NotFoundException{
        Plan oldPlan = this.findById(id);
        oldPlan.setPlanType(body.getPlanType());
        switch (body.getPlanType()) {
            case NUTRITION -> oldPlan.setPlanType(body.getPlanType()); //AUTORIZZAZIONE DA Nutrizionista se è presente nella USERLIST?
            case WORKOUT -> oldPlan.setPlanType(body.getPlanType()); //AUTORIZZAZIONE DA PERSONAL_TRAINER se è presente nella USERLIST

        }
        return planRepository.save(oldPlan);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        planRepository.deleteById(id);
    }
}
