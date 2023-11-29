package UnitedGymsProject.GymBrofriendly.exceptions;

import org.springframework.validation.ObjectError;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){super(message);};
}
