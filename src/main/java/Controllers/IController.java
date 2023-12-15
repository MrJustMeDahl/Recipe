package Controllers;


import io.javalin.http.Handler;

public interface IController {
    Handler getAllPersons();
    Handler getPersonById();
    Handler getPersonByName();
    Handler createPerson();
    Handler updatePerson();
    Handler deletePerson();
}
