package com.springboot.security.custom.basic.spring.security.service;

import com.springboot.security.custom.basic.spring.security.entity.ContactEntity;
import com.springboot.security.custom.basic.spring.security.model.Contact;
import com.springboot.security.custom.basic.spring.security.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact createContact(Contact contact) {
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setContactEmail(contact.getContactEmail());
        contactEntity.setContactName(contact.getContactName());
        contactEntity.setSubject(contact.getSubject());
        contactEntity.setMessage(contact.getMessage());
        contactEntity = this.contactRepository.save(contactEntity);
        contact.setId(contactEntity.getId());
        return contact;
    }

}
