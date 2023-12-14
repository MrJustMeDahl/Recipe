package Config;

import Controllers.AccessManagerController;
import Routing.Routes;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.plugin.bundled.RouteOverviewPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServerConfig {

    private static AccessManagerController accessManager = new AccessManagerController();

    public static void startJavalinServer(Javalin app, int port){
        app.updateConfig(ServerConfig::javalinConfiguration);
        Routes routes = new Routes();
        app.routes(routes.getRoutes(app));
        app.start(port);
    }

    private static void javalinConfiguration(JavalinConfig config){
        config.routing.contextPath = "/api";
        config.http.defaultContentType = "application/json";
        config.plugins.register(new RouteOverviewPlugin("/"));
        config.accessManager(accessManager::accessManagerHandler);
    }
}