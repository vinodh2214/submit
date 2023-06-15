package com.assesment.University;

import com.assesment.University.entity.*;
import com.assesment.University.repsitory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@SpringBootApplication
public class UniversityApplication {

    static PersonRepository personRepository;
    static FacultyRepository facultyRepository;
    static GradStudentRepository gradStudentRepository;
    static GrantRepository grantRepository;
    static StudentRepository studentRepository;
    static DepartmentRepository departmentRepository;
    static CollegeRepository collegeRepository;
    static SectionRepository sectionRepository;
    static CourseRepository courseRepository;


    @Autowired
    public UniversityApplication(PersonRepository personRepository, FacultyRepository facultyRepository, GradStudentRepository gradStudentRepository,GrantRepository grantRepository,StudentRepository studentRepository,DepartmentRepository departmentRepository,CollegeRepository collegeRepository,SectionRepository sectionRepository,CourseRepository courseRepository) {
        this.personRepository = personRepository;
        this.facultyRepository = facultyRepository;
        this.gradStudentRepository = gradStudentRepository;
        this.grantRepository=grantRepository;
        this.studentRepository=studentRepository;
        this.departmentRepository=departmentRepository;
        this.collegeRepository=collegeRepository;
        this.sectionRepository=sectionRepository;
        this.courseRepository=courseRepository;


    }


    public UniversityApplication() {

    }

    public static void main(String[] args) {
        SpringApplication.run(UniversityApplication.class, args);
        UniversityApplication application = new UniversityApplication();
   application.personCrudOperation();
        application.facultyCrudOperation();
        application.gradStudentCrudOperation();
        application.grantCrudOperations();
        application.studentCrudOperation();
    application.departmentCrudOperations();
        application.collegeCrudOperations();
        application.sectionCrudOperation();
        application.courseCrudOperation();
    }
    @Transactional
    public void personCrudOperation() {
        Person person = new Person();
        person.setSsn(Arrays.asList("123456789"));
        person.setBDate(LocalDate.now());
        person.setSex("Male");
        person.setName(new Name("Jalagam", "vinodh", "kumar"));
        person.setAddress(new Address("2 cross", "bose", "21b", "City", "State", "12345"));
        Person savedPerson = personRepository.save(person);
        System.out.println("saved person"+person.getId()+" "+person.getName());


        // Update the person
        savedPerson.setName(new Name("Jalagam","vinodh", "Naidu"));
        savedPerson.setAddress(new Address("120","1st Street", "Apt 123", "City", "State", "98765"));

        Person updatedPerson = personRepository.save(savedPerson);
        System.out.println("Updated Person: " + person.getName());

        // Retrieve the person by ID
        Long personId = updatedPerson.getId();
        Optional<Person> retrievedPerson = personRepository.findById(personId);
        System.out.println("Retrieved Person: " + person.getName());

        // Delete the person
//        personRepository.delete(updatedPerson);
//        System.out.println("Deleted Person");
    }

    @Transactional
    public void facultyCrudOperation() {
        // Create a new faculty
        Faculty faculty = new Faculty();
        faculty.setRank("Professor");
        faculty.setFOffice("123A");
        faculty.setFPhone("1234567890");
        faculty.setSalary(100000.0);

        // Save the faculty to the database
        Faculty savedFaculty = facultyRepository.save(faculty);
        System.out.println("savedFaculty"+faculty.getId()+" "+faculty.getRank());

        // Update the faculty
        savedFaculty.setRank("Associate Professor");
        savedFaculty.setSalary(80000.0);

        Faculty updatedFaculty = facultyRepository.save(savedFaculty);
        System.out.println("Updated Faculty: " + faculty.getId()+" "+faculty.getRank());

        // Retrieve the faculty by ID
        Long facultyId = updatedFaculty.getId();
        Optional<Faculty> retrievedFaculty = facultyRepository.findById(facultyId);


        // Delete the faculty
//        facultyRepository.delete(updatedFaculty);
//        System.out.println("Deleted Faculty");
    }

    @Transactional
    public void gradStudentCrudOperation() {
        GradStudent gradStudent = new GradStudent();


        gradStudent.addDegree(new Degree("JNTUA","Bachelor of Science",2020));
        gradStudent.addDegree(new Degree("JNTUA","Master of Science",2022));


        Faculty advisor = new Faculty();

        advisor.setRank("Professor");
        advisor.setFOffice("123A");
        advisor.setFPhone("1234567890");
        advisor.setSalary(100000.0);
        facultyRepository.save(advisor);

        gradStudent.setAdvisor(advisor);

        // Set the committee members for the graduate student
        List<Faculty> committees = new ArrayList<>();
        Faculty committeeMember1 = new Faculty();
        committeeMember1.setRank("Associate Professor");
        committeeMember1.setFOffice("456B");
        committeeMember1.setFPhone("9876543210");
        committeeMember1.setSalary(80000.0);
        facultyRepository.save(committeeMember1);
        committees.add(committeeMember1);

        Faculty committeeMember2 = new Faculty();
        committeeMember2.setRank("Assistant Professor");
        committeeMember2.setFOffice("789C");
        committeeMember2.setFPhone("5678901234");
        committeeMember2.setSalary(60000.0);
        facultyRepository.save(committeeMember2);
        committees.add(committeeMember2);

        gradStudent.setCommittees(committees);
        gradStudentRepository.save(gradStudent);
        System.out.println("Saved GradStudent: " + gradStudent.getId() + " - " + gradStudent.getName());

        // Update the GradStudent
        gradStudent.setId(20l);
        GradStudent updatedGradStudent = gradStudentRepository.save(gradStudent);
        System.out.println("Updated GradStudent: " + updatedGradStudent.getId() + " - " + updatedGradStudent.getName());

        // Retrieve the GradStudent by ID
        Long gradStudentId = updatedGradStudent.getId();
        Optional<GradStudent> retrievedGradStudent = gradStudentRepository.findById(gradStudentId);
        System.out.println("Retrieved GradStudent: " +updatedGradStudent.getId()  );

        // Delete the GradStudent
//        gradStudentRepository.delete(updatedGradStudent);
//        System.out.println("Deleted GradStudent");
    }


    @Transactional
    public void grantCrudOperations() {

        Grant grant = new Grant();
        grant.setTitle("Research Grant");
        grant.setNo("12345");
        grant.setAgency("National Science Foundation");
        grant.setStartedDate("2023-01-01");
        grant.setSupport(new Support("2012", "3years", "2015"));

        Faculty principalInvestigator = new Faculty();
        principalInvestigator.setName(new Name("Jalagam", "vinodh", "kumar"));
        principalInvestigator.setRank("Professor");
        principalInvestigator.setFOffice("123A");
        principalInvestigator.setFPhone("1234567890");
        principalInvestigator.setSalary(100000.0);
        grant.setPrincipalInvestigator(principalInvestigator);

        // Save the grant to the database
        Grant savedGrant = grantRepository.save(grant);
        System.out.println("Saved Grant: " + grant.getTitle());


        // Update the grant
        savedGrant.setTitle("Updated Research Grant");
        savedGrant.setNo("67890");
        savedGrant.setSupport(new Support("2012", "3years", "2015"));

        Grant updatedGrant = grantRepository.save(savedGrant);
        System.out.println("Updated Grant: " +updatedGrant.getTitle());

        // Retrieve the grant by ID
        Optional<Grant> retrievedGrant = grantRepository.findById(updatedGrant.getId());
        System.out.println("Retrieved Grant: " + grant.getTitle());

        // Delete the grant
//        grantRepository.delete(updatedGrant);
//        System.out.println("Deleted Grant");

    }
    @Transactional
    public void studentCrudOperation() {

        Student student = new Student();
        student.setName(new Name("Jalagam", "vinodh", "kumar"));




        // Save the student to the database
        Student savedStudent = studentRepository.save(student);
        System.out.println("Saved Student: " + student.getName());

        // Update the student
        student.setName(new Name("virat", "kohli", "king"));


        Student updatedStudent = studentRepository.save(student);
        System.out.println("Updated Student: " + updatedStudent.getName());

        // Retrieve the student by ID
        Long studentId = updatedStudent.getId();
        Optional<Student> retrievedStudent = studentRepository.findById(studentId);
        System.out.println("Retrieved Student: " + updatedStudent.getName() );

        // Delete the student
//        studentRepository.delete(updatedStudent);
//        System.out.println("Deleted Student");
    }
    @Transactional
    public void collegeCrudOperations() {
        // Create a new college
        College college = new College();
        college.setCollege_name("Engineering College");
        college.setCollege_dean("Nagaraj");
        college.setCollege_office("Building C");

        // Save the college to the database
        College savedCollege = collegeRepository.save(college);
        System.out.println("Saved College: " + college.getCollege_dean());

        // Update the college
        savedCollege.setCollege_name("Science College");
        savedCollege.setCollege_dean("vinodh");
        savedCollege.setCollege_office("Building D");

        College updatedCollege = collegeRepository.save(savedCollege);
        System.out.println("Updated College: " + updatedCollege.getCollege_dean());

        // Retrieve the college by ID
        Long collegeId = updatedCollege.getId();
        Optional<College> retrievedCollege = collegeRepository.findById(collegeId);
        System.out.println("Retrieved College: " + updatedCollege.getCollege_dean());

        // Delete the college
//        collegeRepository.delete(updatedCollege);
//        System.out.println("Deleted College");
    }
    @Transactional

    public void departmentCrudOperations() {
        // Create a new department
        Department department = new Department();
        department.setDepartment_name("Computer Science");
        department.setDepartment_office("Building A");
        department.setDepartment_no(12345);

        // Save the department to the database
        Department savedDepartment = departmentRepository.save(department);
        System.out.println("Saved Department: " + department.getDepartment_name());

        // Update the department
        savedDepartment.setDepartment_name("Information Technology");
        savedDepartment.setDepartment_office("Building B");

        Department updatedDepartment = departmentRepository.save(savedDepartment);
        System.out.println("Updated Department: " + updatedDepartment.getDepartment_name());

        // Retrieve the department by ID
        Long departmentId = updatedDepartment.getId();
        Optional<Department> retrievedDepartment = departmentRepository.findById(departmentId);
        System.out.println("Retrieved Department: " + updatedDepartment.getDepartment_name());

        // Delete the department
//        departmentRepository.delete(updatedDepartment);
//        System.out.println("Deleted Department");
    }
    @Transactional
    public void sectionCrudOperation() {
        // Create a new section
        Section section = new Section();
        section.setCurrent_year("2023");
        section.setCurrent_qtr(3);


        Section savedSection = sectionRepository.save(section);
        System.out.println("Saved Section: " + section.getSection_no());

        // Update the section
        savedSection.setCurrent_year("2024");
        savedSection.setCurrent_qtr(1);


        Section updatedSection = sectionRepository.save(savedSection);
        System.out.println("Updated Section: " + updatedSection.getSection_no());

        // Retrieve the section by ID
        Long sectionId = updatedSection.getSection_no();
        Section retrievedSection = sectionRepository.findById(sectionId).orElse(null);
        System.out.println("Retrieved Section: " + updatedSection.getSection_no());

        // Delete the section
//        sectionRepository.delete(updatedSection);
//        System.out.println("Deleted Section");
    }
    @Transactional
    public void courseCrudOperation() {

        Course course = new Course();
        course.setCourse_name("Computer Science");


        Course savedCourse = courseRepository.save(course);
        System.out.println("Saved Course: " + course.getCourse_name());


        savedCourse.setCourse_name("Electrical Engineering");


        Course updatedCourse = courseRepository.save(savedCourse);
        System.out.println("Updated Course: " + updatedCourse.getCourse_name());

        // Retrieve the course by ID
        Long courseId = updatedCourse.getId();
        Course retrievedCourse = courseRepository.findById(courseId).orElse(null);
        System.out.println("Retrieved Course: " + updatedCourse.getCourse_name());

        // Delete the course
//        courseRepository.delete(updatedCourse);
//        System.out.println("Deleted Course");
    }

}



