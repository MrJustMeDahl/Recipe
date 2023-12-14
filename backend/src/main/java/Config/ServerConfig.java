package Config;

import Routing.Routes;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.plugin.bundled.RouteOverviewPlugin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class ServerConfig {

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
    }

    private static void MockDB(){
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        try (EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.createNativeQuery("TRUNCATE TABLE public.persons RESTART IDENTITY cascade;").executeUpdate();
            em.createNativeQuery("INSERT INTO public.persons (name, email, role) VALUES ('navn1','email1','role1');").executeUpdate();
            em.createNativeQuery("INSERT INTO public.persons (name, email, role) VALUES ('navn2','email2','role2');").executeUpdate();
            em.createNativeQuery("INSERT INTO public.persons (name, email, role) VALUES ('navn3','email3','role3');").executeUpdate();
            em.getTransaction().commit();
        }
    }

    public static void main(String[] args) {

        Javalin app = Javalin.create();
        startJavalinServer(app, 7070);
        JavalinConfig config = new JavalinConfig();
        javalinConfiguration(config);
        MockDB();

    }
}