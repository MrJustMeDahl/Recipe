package Security.DAO;

import DTO.PersonDTO;
import Model.Person;
import jakarta.persistence.EntityManager;

public class PersonDAO extends DAO<PersonDTO, Person> {

    //Singleton
    private static PersonDAO instance;
    private PersonDAO(){}
    public static PersonDAO getInstance(){
        if(instance == null){
            instance = new PersonDAO();
        }
        return instance;
    }

    @Override
    public Person create(PersonDTO personDTO) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Person person = new Person(personDTO);
            em.persist(person);
            em.getTransaction().commit();
            return person;
        }    }

    @Override
    public Person update(PersonDTO personDTO) {
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            Person person = em.find(Person.class, personDTO.id);
            person.setName(personDTO.name);
            person.setEmail(personDTO.email);
            person.setRole(personDTO.role);
            em.merge(person);
            em.getTransaction().commit();
            return person;
        }    }
}
