package Routing;

import Controllers.PersonController;
import DTO.PersonDTO;
import Exceptions.APIException;
import Exceptions.ExceptionHandler;
import Security.DAO.PersonDAO;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    ExceptionHandler exceptionHandler = new ExceptionHandler();
    PersonController personController = new PersonController();
    public static final String PERSONS = "/persons";

    public EndpointGroup getRoutes(Javalin app){
        return () -> {
            app.routes(() -> {
                //path("/", ); TODO
                path("/persons", () -> {
                    get(PERSONS, personController.getAllPersons());
                    get(PERSONS + "/{id}", personController.getPersonById());
                    get(PERSONS + "/name/{name}", personController.getPersonByName());
                   // get(PERSONS + "/email/{email}", personController.getPersonByEmail()); todo
                    put(PERSONS + "/{id}", personController.updatePerson());
                    post(PERSONS, personController.createPerson());
                    delete(PERSONS + "/{id}", personController.deletePerson());

                });
                    });
            app.exception(APIException.class, exceptionHandler::apiExceptionHandler);
            app.exception(NumberFormatException.class, exceptionHandler::numberFormatExceptionHandler);
            app.exception(Exception.class, exceptionHandler::exceptionHandler);
        };
    }
}
