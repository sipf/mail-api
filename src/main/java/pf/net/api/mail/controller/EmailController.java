package pf.net.api.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pf.net.api.mail.model.Email;
import pf.net.api.mail.service.EmailService;

@RestController
@RequestMapping(value = "/mail")
public class EmailController {

    @Autowired
    EmailService emailService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Email> sendEmail(@RequestBody Email eParams) {
        emailService.send(eParams);
        return new ResponseEntity<>(eParams, HttpStatus.CREATED);
    }
}
