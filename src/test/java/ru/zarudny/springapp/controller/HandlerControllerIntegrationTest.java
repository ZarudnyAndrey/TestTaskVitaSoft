package ru.zarudny.springapp.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.zarudny.springapp.utils.IntegrationTest;

@IntegrationTest
public class HandlerControllerIntegrationTest {

  @Autowired
  TestRestTemplate testRestTemplate;

  @Test
  public void sortLines_withCorrectData() {
    List<String> requestArray = new ArrayList<>();

    requestArray.add("Тихо струится река серебристая");
    requestArray.add("В царстве вечернем зеленой весны.");
    requestArray.add("Солнце садится за горы лесистые.");
    requestArray.add("Рог золотой выплывает луны.");

    List<String> responseArray = new ArrayList<>();

    responseArray.add("(27): Рог золотой выплывает луны.");
    responseArray.add("(30): Тихо струится река серебристая");
    responseArray.add("(32): Солнце садится за горы лесистые.");
    responseArray.add("(33): В царстве вечернем зеленой весны.");

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<List<String>> entity = new HttpEntity<>(requestArray, headers);

    ResponseEntity<List> response = testRestTemplate
        .postForEntity("/poem", entity, List.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(responseArray, response.getBody());
  }

  @Test
  public void getMonth_withIncorrectInputValue() {
    ResponseEntity<String> response = testRestTemplate
        .exchange("/month/0", HttpMethod.GET, null, String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("INCORRECT INPUT DATA", response.getBody());
  }

  @Test
  public void getMonth_withCorrectInputValue() {
    ResponseEntity<String> response = testRestTemplate
        .exchange("/month/6", HttpMethod.GET, null, String.class);

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals("И-Ю-Н-Ь", response.getBody());
  }
}
