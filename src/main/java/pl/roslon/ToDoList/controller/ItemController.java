package pl.roslon.ToDoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.roslon.ToDoList.entities.Item;
import pl.roslon.ToDoList.repository.ItemRepository;

import javax.validation.Valid;
import java.net.URI;

//@RestController
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

//    @GetMapping("/index")
//    public ResponseEntity<Void> save(Model model, Item item) {
//        model.addAttribute("item", item);
//        model.addAttribute("items", itemRepository.findAll());
//        return new ResponseEntity<Void>(HttpStatus.OK);
//    }




    @PostMapping("/save")
    public String addItem(@Valid Item item, BindingResult result) {
        if (result.hasErrors()) {
            return "index";
        }
        itemRepository.save(item);
        return "redirect:/index";
    }

//    @PostMapping("/save")
//    public ResponseEntity<String> addItem(@Valid Item item, BindingResult result) {
//
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/save")
//                .buildAndExpand()
//                .toUri();
//
//        if (result.hasErrors()) {
//           return ResponseEntity.created(location).body("index");
//        }
//        itemRepository.save(item);
//        return ResponseEntity.created(location).body("index");
//    }



    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable("id") Item item) {
        itemRepository.delete(item);
        return "redirect:/index";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        itemRepository.deleteAll();
        return "redirect:/index";
    }
}