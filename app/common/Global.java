package common;

import com.avaje.ebean.Ebean;
import models.User;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.h2.jdbcx.JdbcDataSource;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

/**
 * Class responsible for bootstrapping the application
 */
public class Global extends GlobalSettings {

    protected static Application APPLICATION;

    @Override
    public void onStart(Application app) {
        System.out.println("Global.onStart");
        APPLICATION = app;
        //ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        InitialData.insert(app);
    }

    public static JdbcDataSource getJdbcDataSource() {
        String dbUrl = APPLICATION.configuration().getString("db.default.url");
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(dbUrl);
        return jdbcDataSource;
    }

    static class InitialData {

        public static void insert(Application app) {
            if(Ebean.find(User.class).findRowCount() == 0) {

                Map<String,List<Object>> all = (Map<String,List<Object>>) Yaml.load("init-data.yml");

                // Insert users first
                Ebean.save(all.get("users"));

                /*// Insert projects
                Ebean.save(all.get("projects"));
                for(Object project: all.get("projects")) {
                    // Insert the project/user relation
                    Ebean.saveManyToManyAssociations(project, "members");
                }

                // Insert tasks
                Ebean.save(all.get("tasks"));*/

            }

            RepositoryService repositoryService = ProcessEngine.getInstance().getRepositoryService();
            RuntimeService runtimeService = ProcessEngine.getInstance().getRuntimeService();

            // Deploy the process definition
            repositoryService.createDeployment()
                    .addClasspathResource("process-definition.bpmn20.xml")
                    .deploy();

            // Start a process instance
            runtimeService.startProcessInstanceByKey("financialReport");
        }

    }
}
