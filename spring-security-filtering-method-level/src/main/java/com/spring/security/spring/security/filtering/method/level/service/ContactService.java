package com.spring.security.spring.security.filtering.method.level.service;

import com.spring.security.spring.security.filtering.method.level.entity.ContactEntity;
import com.spring.security.spring.security.filtering.method.level.model.Contact;
import com.spring.security.spring.security.filtering.method.level.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> createContacts(List<Contact> contacts) {
        List<ContactEntity> contactEntities = this.toContactEntities(contacts);
        contactEntities = this.contactRepository.saveAll(contactEntities);
        return this.toContacts(contactEntities);
    }

    private List<ContactEntity> toContactEntities(List<Contact> contacts) {
        List<ContactEntity> contactEntities = new ArrayList<>();
        contacts.forEach(c -> {
            ContactEntity contactEntity = new ContactEntity();
            contactEntity.setContactEmail(c.getContactEmail());
            contactEntity.setContactName(c.getContactName());
            contactEntity.setSubject(c.getSubject());
            contactEntity.setMessage(c.getMessage());
            contactEntities.add(contactEntity);
        });
        return contactEntities;
    }

    @PostFilter("filterObject.contactName == 'Han'")
    public List<Contact> getContacts() {
        List<ContactEntity> contactEntities = this.contactRepository.findAll();
        return this.toContacts(contactEntities);
    }

    private List<Contact> toContacts(List<ContactEntity> contactEntities) {
        List<Contact> contacts = new ArrayList<>();
        contactEntities.forEach(c -> {
            Contact contact = new Contact();
            contact.setId(c.getId());
            contact.setContactName(c.getContactName());
            contact.setContactEmail(c.getContactEmail());
            contact.setSubject(c.getSubject());
            contact.setMessage(c.getMessage());
            contacts.add(contact);
        });
        return contacts;
    }

}