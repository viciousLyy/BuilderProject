package guet.yongyu.Factory;

import guet.yongyu.Builder.Builder;
import guet.yongyu.Impl.Project;
import guet.yongyu.Specify.CProject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderFactoryTest {

    @Test
    void getInstance() {
    }

    @Test
    void getProjectBuilder() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Builder builder = BuilderFactory.getInstance().getProjectBuilder("c");
        Project project = new CProject("E:\\codeblockProject\\BuilderTest","c");
        builder.run(project);

    }
}