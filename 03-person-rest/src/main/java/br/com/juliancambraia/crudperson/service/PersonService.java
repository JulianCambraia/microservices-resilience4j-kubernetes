package br.com.juliancambraia.crudperson.service;

import br.com.juliancambraia.crudperson.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private AtomicLong counter = new AtomicLong();

    public Person findById(String id) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstname("Julian Fernando");
        person.setLastname("Almeida");
        person.setAge("50");
        person.setAddress("Rua Mayrink, 139 Caiçara, Belo Horizonte MG");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();
        Collection<Integer> itens = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        itens.forEach(i -> {
            long id = counter.incrementAndGet();
            personList.add(new Person(id, "Nome_" + i, "Sobrenome_" + i, i+"30", "Endereço_" + i, (id %2)==0?"Male":"Female"));
        });

        return personList;
    }

    public Optional<Person> create(Person person) {
        return Optional.ofNullable(person);
    }

    public void delete(String id) {
    }
}
