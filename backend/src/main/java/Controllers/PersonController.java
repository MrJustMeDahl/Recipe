package Controllers;

import DTO.PersonDTO;
import Exceptions.APIException;
import Model.Person;
import Security.DAO.PersonDAO;
import io.javalin.http.Handler;

import java.util.List;

public class PersonController implements IController{
    @Override
    public Handler getAllPersons() {

        return ctx -> {
            PersonDAO personDAO = PersonDAO.getInstance();
            List<Person> persons = personDAO.getAll(Person.class);
            if (persons.isEmpty()) {
                throw new APIException(404, "No persons in database");
            }
            ctx.json(persons);
        };
    }

    @Override
    public Handler getPersonById() {
        return ctx->{
            String idString = ctx.pathParam("id");
            int id = Integer.parseInt(idString);
            PersonDAO personDAO = PersonDAO.getInstance();
            Person person = personDAO.getById(id, Person.class);
            if(person == null){
                throw new APIException(404, "No person with id: " + id + " in database");
            }
            ctx.json(person);
        };
    }

    @Override
    public Handler getPersonByName() {
        return ctx->{
            String nameString = ctx.pathParam("name");
            PersonDAO personDAO = PersonDAO.getInstance();
            Person person = personDAO.getByName(nameString, Person.class);
            if(person == null){
                throw new APIException(404, "No person with name: " + nameString + " in database");
            }
            ctx.json(person);
        };
    }

    @Override
    public Handler createPerson() {
        return ctx->{
            PersonDTO personDTO = ctx.bodyAsClass(PersonDTO.class);
            PersonDAO personDAO = PersonDAO.getInstance();
            personDAO.create(personDTO);
            ctx.status(201);
            ctx.json(personDTO);
        };
    }

    @Override
    public Handler updatePerson() {
        return ctx->{
            PersonDTO personDTO = ctx.bodyAsClass(PersonDTO.class);
            PersonDAO personDAO = PersonDAO.getInstance();
            personDAO.update(personDTO);
            ctx.status(201);
            ctx.json(personDTO);
        };
    }

    @Override
    public Handler deletePerson() {
        return ctx->{
            String idString = ctx.pathParam("id");
            int id = Integer.parseInt(idString);
            PersonDAO personDAO = PersonDAO.getInstance();
            boolean deleted = personDAO.delete(id, Person.class);
            if(!deleted){
                throw new APIException(404, "No person with id: " + id + " in database");
            }
            ctx.status(204);
        };
    }
}
