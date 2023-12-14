package Routing;

import Exceptions.APIException;
import Exceptions.ExceptionHandler;
import io.javalin.Javalin;
import io.javalin.apibuilder.EndpointGroup;

public class Routes {

    ExceptionHandler exceptionHandler = new ExceptionHandler();

    public EndpointGroup getRoutes(Javalin app){
        return () -> {
            app.routes(() -> {
                //path("/", ); TODO
                    });
            app.exception(APIException.class, exceptionHandler::apiExceptionHandler);
            app.exception(NumberFormatException.class, exceptionHandler::numberFormatExceptionHandler);
            app.exception(Exception.class, exceptionHandler::exceptionHandler);
        };
    }
}
