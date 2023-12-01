package com.zaurtregulov.spring.rest.exeption_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // поимка и обработка исключений
public class EmployeeGlobalExeptionHandler {

    @ExceptionHandler
    // обрабатывает исключения при вводе неверного адреса числами пойманное в кл MyRestController, код 400 тк вместо объекта передается null
    public ResponseEntity<EmployeeIncorrectData> handException(
            NoSuchEmployeeExeption exeption) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exeption.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler // обрабатывает исключения при вводе неверного адреса буквами пойманное в кл MyRestController, код 404 bad reqvest, тк несоотвествие типов в методе int, передается String
    public ResponseEntity<EmployeeIncorrectData> handException(
            Exception exeption) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exeption.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
