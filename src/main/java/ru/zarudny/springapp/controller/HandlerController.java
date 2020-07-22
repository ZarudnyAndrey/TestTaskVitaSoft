package ru.zarudny.springapp.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.zarudny.springapp.service.HandlerService;

@RestController
public class HandlerController {

  @Autowired
  HandlerService handlerService;

  @PostMapping(value = "/poem")
  @ResponseStatus(HttpStatus.OK)
  public List<String> sortLines(@RequestBody List<String> poem) {
    return handlerService.sortLines(poem);
  }

  @GetMapping("/month/{number}")
  @ResponseStatus(HttpStatus.OK)
  public String getMonth(@Valid @PathVariable int number) {
    return handlerService.getMonth(number);
  }
}
