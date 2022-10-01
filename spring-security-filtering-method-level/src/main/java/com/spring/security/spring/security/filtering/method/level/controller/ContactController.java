package com.spring.security.spring.security.filtering.method.level.controller;

import com.spring.security.spring.security.filtering.method.level.model.Contact;
import com.spring.security.spring.security.filtering.method.level.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET, path = "/v1/contact")
    public ResponseEntity<String> getContactDetail() {
        return ResponseEntity.ok("This is the contact details");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/contact")
    @PreFilter("filterObject.subject == 'Hello'")
    public ResponseEntity<List<Contact>> createContact(@RequestBody List<Contact> contacts) {
        return ResponseEntity.ok(this.contactService.createContacts(contacts));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/v1/contacts")
    public ResponseEntity<List<Contact>> getContacts() {
        return ResponseEntity.ok(this.contactService.getContacts());
    }

}