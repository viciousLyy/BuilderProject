package guet.yongyu.Factory;

import guet.yongyu.Impl.Project;
import guet.yongyu.Specify.CProject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectFactoryTest {

    @Test
    void getInstance() {

    }

    @Test
    void getProject() {
        Project project = ProjectFactory.getInstance().getProject("E:\\IdealProject\\CTest","c");
        Project project1 = new CProject("E:\\IdealProject\\CTest","c");
        System.out.println(project.getClass());
        assertEquals(project1.getClass(),project.getClass());
    }
}