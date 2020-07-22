package ru.zarudny.springapp.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class HandlerService {

  public List<String> sortLines(List<String> poem) {
    List<String> list = poem.stream()
        .map(Object::toString)
        .sorted(Comparator.comparingInt(String::length))
        .map(s -> "(" + s.length() + "): " + s)
        .collect(Collectors.toList());

    return list;
  }


  public String getMonth(int number) {
    String monthString = selectMonth(number);

    if (number > 0 && number < 13) {
      return stringConvert(monthString).toUpperCase();
    }
    return monthString.toUpperCase();
  }

  private String selectMonth(int number) {
    String monthString;
    switch (number) {
      case 1:
        monthString = "Январь";
        break;
      case 2:
        monthString = "Февраль";
        break;
      case 3:
        monthString = "Март";
        break;
      case 4:
        monthString = "Апрель";
        break;
      case 5:
        monthString = "Май";
        break;
      case 6:
        monthString = "Июнь";
        break;
      case 7:
        monthString = "Июль";
        break;
      case 8:
        monthString = "Август";
        break;
      case 9:
        monthString = "Сентябрь";
        break;
      case 10:
        monthString = "Октябрь";
        break;
      case 11:
        monthString = "Ноябрь";
        break;
      case 12:
        monthString = "Декабрь";
        break;
      default:
        monthString = "Incorrect input data";
        break;
    }
    return monthString;
  }

  private String stringConvert(String monthString) {
    char[] array = monthString.toCharArray();
    StringBuilder stringBuilder = new StringBuilder();

    for (int i = 0; i < array.length; i++) {
      stringBuilder.append(array[i]);

      if (i < array.length - 1) {
        stringBuilder.append("-");
      }
    }
    return stringBuilder.toString();
  }
}
