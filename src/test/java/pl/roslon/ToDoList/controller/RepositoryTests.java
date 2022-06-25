package pl.roslon.ToDoList.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.roslon.ToDoList.entities.Item;
import pl.roslon.ToDoList.repository.ItemRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositoryTests {

    @Autowired
    ItemRepository itemRepository;

    @DisplayName("Should save repository witch id 1L")
    @Test
    public void shouldSaveRepositoryWithId1L() {
        Item savedItem = itemRepository.save(new Item(1L, "Example item"));
        assertAll(
                () -> assertEquals(1, itemRepository.count())
        );
    }

    @DisplayName("Should find entity by id")
    @Test
    public void shouldFindById() {
        Item firstItemSaved = itemRepository.save(new Item(1L, "first item"));
        Item secondItemSaved = itemRepository.save(new Item(2L, "second item"));

        assertAll(
                () -> assertEquals(firstItemSaved, itemRepository.findById(1L).get())
        );
        itemRepository.deleteAll();
    }
}
