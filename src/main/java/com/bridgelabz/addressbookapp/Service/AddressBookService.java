package com.example.addressbookapplication.service;

import com.example.addressbookapplication.dto.ContactDTO;
import com.example.addressbookapplication.model.Contact;
import com.example.addressbookapplication.repository.ContactRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class AddressBookService implements IAddressBookService {
    private List<Contact> contactList = new ArrayList<>();

    @Autowired
    ContactRepository contactRepository;

    @Override
    public List<Contact> getContact() {

        return contactList;
    }

    @Override
    public Contact getContactById(int contactId) {
        return contactList.get(contactId - 1);
    }

    @Override
    public Contact createContact(ContactDTO contactDTO) {
        Contact contact=null;
        Contact contactData =new Contact();
        contactData.setContactId(contactDTO.getContactId());
        contactData.setFirstName(contactDTO.getFirstName());
        contactData.setLastName(contactDTO.getLastName());
        contactData.setAddress(contactDTO.getAddress());
        contactData.setState(contactDTO.getState());
        contactData.setCity(contactDTO.getCity());
        contactData.setZip(contactDTO.getZip());
        contactData.setPhone(contactDTO.getPhone());
        contactList.add(contactData);
        contactRepository.save(contactData);
        return contactData;
    }

    @Override

    public Contact updateContact(int contactId, ContactDTO contactDTO) {
        Contact contact = this.getContactById(contactId);
        if (!contactList.isEmpty()){
            contact.setFirstName(contactDTO.firstName);
            contact.setLastName(contactDTO.lastName);
            contact.setAddress(contactDTO.address);
            contact.setState(contactDTO.state);
            contact.setCity(contactDTO.city);
            contact.setZip(contactDTO.zip);
            contact.setPhone(contactDTO.phone);
            contactList.set(contactId - 1, contact);
            return contact;
        }
        else {
            return contact=null;
        }
    }
}