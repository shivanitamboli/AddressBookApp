package com.example.addressbookapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddressBookAppApplicationTests<PersonRepository, PersonService, Person> {
    //@SpringBootTest
    //public class TestAddressBookService {

        @Mock
        private PersonRepository personRepository;

        @InjectMocks
        private PersonService personService;

        Person person =new Person();
        Person person1 =new Person();
        Person person2 =new Person();
        Person person3 =new Person();

        @BeforeEach
        public void setup() {

            person.setId(1);
            person.setName("shivani");
            person.setEmail("tambolishivani4@gmail.com");
            person.setCity("pune");
            person.setMobileNo("8888888888");

            person1.setId(2);
            person1.setName("jyots");
            person1.setEmail("jyots10@gmail.com");
            person1.setCity("mumbai");
            person1.setMobileNo("9999999999");

            person2.setId(3);
            person2.setName("rani");
            person2.setEmail("rani4@gmail.com");
            person2.setCity("nashik");
            person2.setMobileNo("7777777777");

            person3.setId(4);
            person3.setName("rani");
            person3.setEmail("rani4@gmail.com");
            person3.setCity("pune");
            person3.setMobileNo("9998765488");
        }

        @Test
        void getAllPersonDataTest() {
            doReturn(Arrays.asList(person,person1)).when(personRepository).findAll();
            List<Person> returnedValues = personService.getAllPerson();
            Assertions.assertEquals(returnedValues,Arrays.asList(person,person1), "The sizes should be the same");
        }

        @Test
        void PersonSizetest() {
            doReturn(Arrays.asList(person,person1)).when(personRepository).findAll();
            List<Person> returnedValues = personService.getAllPerson();
            Assertions.assertEquals(returnedValues.size(), 2, "The sizes should be the same");
        }

        @Test
        void getPersonByIDTest() {
            doReturn(Optional.of(person1)).when(personRepository).findById(2);
            Person returnedEntity = personService.getPersonById(2);

            Assertions.assertTrue(returnedEntity != null, "Record was not found");
            Assertions.assertEquals(returnedEntity, person1, " Records should be the same");
        }

        @Test
        void getPersonIDTest1() throws Exception{
            Mockito.when(personRepository.findById(1)).thenReturn(Optional.of(person));
            Assertions.assertEquals(person, personService.getPersonById(1));
        }

        @Test
        public void savePersonTest() {
            when(personRepository.save(person2)).thenReturn(person2);
            Person savedPerson = personService.savePerson(person2);
            assertThat(person2).isEqualTo(savedPerson);
        }

        @Test
        public void updatePersonByIdTest() {
            when(personRepository.save(person3)).thenReturn(person3);
            doReturn(Optional.of(person1)).when(personRepository).findById(2);
            Person updatePerson = personService.updatePerson(2,person3);
            assertThat(person3).isEqualTo(updatePerson);
        }

        @Test
        public void deletePersonByIdTest() {
            when(personRepository.existsById(1)).thenReturn(true);
            assertThat(true).isEqualTo(personRepository.existsById(1));

        }
}
