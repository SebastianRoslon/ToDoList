package pl.roslon.ToDoList.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.roslon.ToDoList.entities.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
}
