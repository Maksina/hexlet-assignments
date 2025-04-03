package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import exercise.model.Contact;
import exercise.repository.ContactRepository;
import exercise.dto.ContactDTO;
import exercise.dto.ContactCreateDTO;

import java.time.LocalDate;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactRepository contactRepository;

    // BEGIN
    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ContactDTO create(@RequestBody ContactCreateDTO contactData){
        var contact = toEntity(contactData);
        contactRepository.save(contact);
        return toDTO(contact);
    }

    private Contact toEntity(ContactCreateDTO data){
        var contact = new Contact();
        contact.setFirstName(data.getFirstName());
        contact.setLastName(data.getLastName());
        contact.setPhone(data.getPhone());
        return contact;
    }
    private ContactDTO toDTO(Contact contact){
        var dto = new ContactDTO();
        dto.setPhone(contact.getPhone());
        dto.setId(contact.getId());
        dto.setLastName(contact.getLastName());
        dto.setFirstName(contact.getFirstName());
        dto.setUpdatedAt(contact.getUpdatedAt());
        dto.setCreatedAt(contact.getCreatedAt());
        return dto;
    }
    // END
}
