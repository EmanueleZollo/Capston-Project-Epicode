package UnitedGymsProject.GymBrofriendly.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(long id){
        super("L'utente con id " + id + " non è stato trovato. Tenta con un id diverso!");
    }

    public NotFoundException(String email){
        super("L'utente con l'email " + email + " non è stato trovato. Tenta con un'email diversa!");
    }
}
