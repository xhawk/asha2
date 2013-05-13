import org.h2.jdbcx.JdbcDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;

/**
 * Class responsible for bootstrapping the application
 */
public class Global extends GlobalSettings {

    private ApplicationContext ctx;
    protected static Application APPLICATION;

    @Override
    public void onStart(Application app) {
        APPLICATION = app;
        ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        System.out.println(clazz.toString());
        return ctx.getBean(clazz);
    }

    public static JdbcDataSource getJdbcDataSource() {
        String dbUrl = APPLICATION.configuration().getString("db.default.url");
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(dbUrl);
        return jdbcDataSource;
    }
}
