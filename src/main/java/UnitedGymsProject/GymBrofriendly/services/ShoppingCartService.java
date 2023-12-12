package UnitedGymsProject.GymBrofriendly.services;


import UnitedGymsProject.GymBrofriendly.entities.ShoppingCart;
import UnitedGymsProject.GymBrofriendly.entities.User;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.ShoppingCartDTO;
import UnitedGymsProject.GymBrofriendly.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    // LISTA DI TUTTI I CARRELLI CREDO SIA INUTILE

    public ShoppingCart findById(long id) throws NotFoundException {
        return shoppingCartRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Questa carrello con id " + id + " non esiste"));
    }

    public ShoppingCart findByUser(User user){
        return shoppingCartRepository.findByUser(user).orElseThrow(
                ()-> new NotFoundException("L'utente " + user.getEmail() + " non ha mai creato un carrello"));
    }

    public ShoppingCart save(ShoppingCartDTO body) throws IOException {
        shoppingCartRepository.findByUser(body.user()).ifPresent(
                title -> {throw new BadRequestException("Il carrello dell'utente " + body.user().getEmail() + " è già esistente");
                });
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setUser(body.user());
        newShoppingCart.setShoppingCartItems(body.shoppingCartItems());
        if (newShoppingCart.getShoppingCartItems() == null) {
            newShoppingCart.setAmountToBePaid(0);
        } else {
            newShoppingCart.getShoppingCartItems().forEach( shoppingCartItem -> {
                double sum = 0;
                double totalSum = sum += shoppingCartItem.getTotalPrice();
                newShoppingCart.setAmountToBePaid(totalSum);
            });
        }
        return shoppingCartRepository.save(newShoppingCart);
    }

}
