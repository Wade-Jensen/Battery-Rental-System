package au.com.battery.rental.persistence.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import au.com.battery.rental.persistence.model.Battery;
import au.com.battery.rental.persistence.model.BatteryUser;
import au.com.battery.rental.persistence.model.Machine;
import au.com.battery.rental.persistence.model.RentalLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ConfigTest.class, PersistenceJPAConfig.class }, loader = AnnotationConfigContextLoader.class)
public class BatteryTest {

	//Create Variables to test against (And also write to the database)
	private Integer machineId = 1;
	private Double soc = 35;
	private Timestamp = 
	
	
	@Test // Test object instantiation and basic getter - setter
	public void testProject()
	{
		Company company = new Company(companyName);
		Project project = new Project(projectName, company);
		
		assertEquals(project.getName(), projectName);
		assertEquals(project.getCompany(), company);
	}
	
	@Test
	public void testProjectGetSet() // Test object instantiation and more basic getter - setters
	{
		// create test objects
		Company company = new Company(companyName);
		Project project = new Project();
		
		// check Project.id accessors
		project.setProjectId(idNumber);
		assertEquals(idNumber,project.getProjectId());
		
		// check Project.projectName accessors
		project.setName(projectName);
		assertEquals(projectName, project.getName());
		
		// check Project.company accessors
		project.setCompany(company);
		assertEquals(company,project.getCompany());
	}
	
	@Test
	public void testProjectAddRemoveProjectMetal() {
		//Project 
	
	// Create the test object
	Project project = new Project();
	
	// Create member variables
	Metal metal = new Metal();
	metal.setName(metalName);
	
	Metal metal1 = new Metal();
	metal1.setName(metalName1);
	
	Company company = new Company(companyName);
	
	ProjectMetal projectMetal = new ProjectMetal();
	projectMetal.setMetal(metal);
	
	ProjectMetal projectMetal1 = new ProjectMetal();
	projectMetal.setMetal(metal1);
	
	List<ProjectMetal> projectMetals = new ArrayList<ProjectMetal>();
	projectMetals.add(projectMetal);
	projectMetals.add(projectMetal1);
	
	// Set test object member variables
	project.setProjectId(idNumber);
	project.setName(projectName);
	project.setProjectMetals(projectMetals);
	project.setCompany(company);
	
	// Remove metal from the list in order to see if the 
	// model logic behaves correctly
	project.removeProjectMetal(projectMetal1);
	projectMetals.remove(projectMetal1);
	assertEquals(project.getProjectMetals(), projectMetals );
	
	// Add metal to the list in order to see if the 
	// model logic behaves correctly
	project.addProjectMetal(projectMetal1);
	projectMetals.add(projectMetal1);
	assertEquals(project.getProjectMetals(), projectMetals );
	}
	
}
