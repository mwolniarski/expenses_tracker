package pl.wolniarskim.expenses_tracker.service;

import org.springframework.stereotype.Service;
import pl.wolniarskim.expenses_tracker.model.Product;
import pl.wolniarskim.expenses_tracker.repository.ProductRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        Product returnedExpense = productRepository.save(product);
        return returnedExpense;
    }

    public void deleteProduct(long id){
        if(!productRepository.existsById(id))
            throw new EntityNotFoundException();
        productRepository.deleteById(id);
    }
}
