package kr.pe.ihoney.springboot.jpandsecurity.domain.group;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectTest {

    @Test(expected=IllegalArgumentException.class)
    public void 이름중복확인() {
        Project project = new Project("Test project");
        project.addTeam("Team1");
 
        project.addTeam("Team1");
        fail();
    }
}
