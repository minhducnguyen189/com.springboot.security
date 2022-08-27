package com.springboot.security.custom.basic.spring.security.controller;

import com.springboot.security.custom.basic.spring.security.model.Contact;
import com.springboot.security.custom.basic.spring.security.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET, path = "/v1/contact")
    public ResponseEntity<String> getContactDetail() {
        return ResponseEntity.ok("This is the contact details");
    }

    @RequestMapping(method = RequestMethod.POST, path = "/v1/contact")
    @PreFilter("filterObject.contactName == 'Han'")
    @PostFilter("filterObject.subject == 'Hello'")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(this.contactService.createContact(contact));
    }

}
