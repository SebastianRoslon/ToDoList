package pl.roslon.ToDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.roslon.ToDoList.entities.Item;
import pl.roslon.ToDoList.repository.ItemRepository;

import javax.validation.Valid;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/index")
    public String save(Model model, Item item) {
        model.addAttribute("item", item);
        model.addAttribute("items", itemRepository.findAll());
        return "index";
    }

    @PostMapping("/save")
    public String addUser(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        itemRepository.save(item);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Item item) {
        itemRepository.delete(item);
        return "redirect:/index";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        itemRepository.deleteAll();
        return "redirect:/index";
    }
}
