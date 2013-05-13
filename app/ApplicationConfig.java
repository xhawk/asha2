import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@ComponentScan({"controllers", "services"})
public class ApplicationConfig {

    @Bean
    public ProcessEngine processEngine() {
        System.out.println("Creating processEngine");
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .setDataSource(Global.getJdbcDataSource())
                .setDatabaseSchemaUpdate("true")
                .buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // Deploy the process definition
        repositoryService.createDeployment()
                .addClasspathResource("process-definition.bpmn20.xml")
                .deploy();

        // Start a process instance
        runtimeService.startProcessInstanceByKey("financialReport");

        return processEngine;
    }
}
