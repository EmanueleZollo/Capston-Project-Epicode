package UnitedGymsProject.GymBrofriendly.services;

import UnitedGymsProject.GymBrofriendly.entities.Product;
import UnitedGymsProject.GymBrofriendly.entities.Step;
import UnitedGymsProject.GymBrofriendly.exceptions.BadRequestException;
import UnitedGymsProject.GymBrofriendly.exceptions.NotFoundException;
import UnitedGymsProject.GymBrofriendly.payloads.entities.ProductDTO;
import UnitedGymsProject.GymBrofriendly.payloads.entities.StepDTO;
import UnitedGymsProject.GymBrofriendly.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<Product> getAllProducts(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return productRepository.findAll(pageable);
    }

    public Product findById(long id) throws NotFoundException {
        return productRepository.findById(id).orElseThrow(
                ()-> new NotFoundException("Questa scheda con id " + id + " non esiste"));
    }

    public Product findByTitle(String title){
        return productRepository.findByTitle(title).orElseThrow(
                ()-> new NotFoundException("La scheda con il titolo " + title + " è inesistente"));
    }

    public Product save(ProductDTO body) throws IOException {
        productRepository.findByTitle(body.title()).ifPresent(
                title -> {throw new BadRequestException("Titolo " + body.title() + " già esistente");
                });
        Product newProduct = new Product();
        newProduct.setTitle(body.title());
        newProduct.setDescription(body.description());
        newProduct.setProductImage(body.productImage());
        newProduct.setAmount(body.amount());
        return productRepository.save(newProduct);
    }

    public Product findByIdAndUpdate(long id, Product body) throws NotFoundException {
        Product productToModify = this.findById(id);
        productToModify.setTitle(body.getTitle());
        productToModify.setDescription(body.getDescription());
        productToModify.setProductImage(body.getProductImage());
        productToModify.setPrice(body.getPrice());
        productToModify.setAmount(body.getAmount());
        productToModify.setWeight(body.getWeight());
        productToModify.setSize(body.getSize());
        return productRepository.save(productToModify);
    }

    public Product findByTitleAndUpdate(String title, Product body) throws NotFoundException {
        Product productToModify = this.findByTitle(title);
        productToModify.setDescription(body.getDescription());
        productToModify.setProductImage(body.getProductImage());
        productToModify.setPrice(body.getPrice());
        productToModify.setAmount(body.getAmount());
        productToModify.setWeight(body.getWeight());
        productToModify.setSize(body.getSize());
        return productRepository.save(productToModify);
    }

    public void findByIdAndDelete(long id) throws NotFoundException{
        productRepository.deleteById(id);
    }

}
