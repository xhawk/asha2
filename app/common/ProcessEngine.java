package common;

import org.activiti.engine.ProcessEngineConfiguration;

/**
 *
 */
public class ProcessEngine {
    private static org.activiti.engine.ProcessEngine processEngine;

    public static org.activiti.engine.ProcessEngine getInstance() {
        if (processEngine == null) {
            processEngine = ProcessEngineConfiguration
                    .createStandaloneProcessEngineConfiguration()
                    .setDataSource(Global.getJdbcDataSource())
                    .setDatabaseSchemaUpdate("true")
                    .buildProcessEngine();
        }
        return processEngine;
    }

    private ProcessEngine() {}
}
