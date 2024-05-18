package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.NotFoundException;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.repository.CategoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void processTransaction(Transaction transaction) {
        Long transactionId = transaction.getId();
        Optional<Category> category = categoryRepository.findById(transactionId);
        if(category.isEmpty())
            throw new NotFoundException("Transaction: " + transaction.getId() + " not found");
    }

}
