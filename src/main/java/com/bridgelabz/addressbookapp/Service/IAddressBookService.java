package com.example.addressbookapplication.service;


import com.example.addressbookapplication.dto.ContactDTO;
import com.example.addressbookapplication.model.Contact;

import java.util.List;

public interface IAddressBookService {
    List<Contact> getContact();

    Contact getContactById(int contactId);

    Contact createContact(ContactDTO contactDTO);

    Contact updateContact(int contactId, ContactDTO contactDTO);


}