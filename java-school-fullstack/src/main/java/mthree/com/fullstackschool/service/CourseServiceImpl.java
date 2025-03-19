package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {

    //YOUR CODE STARTS HERE

    private final CourseDao courseDao;

    @Autowired
    CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseDao.getAllCourses();

        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE
        Course course = null;
        try{
            course = courseDao.findCourseById(id);
        }catch (DataAccessException e){
            course.setCourseDesc("Course Not Found");
            course.setCourseName("Course Not Found");
        }
        return course;

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        if (course.getCourseName().isEmpty() || course.getCourseDesc().isEmpty()) {
            course.setCourseName("Name blank, course NOT added");
            course.setCourseDesc("Description blank, course NOT added");
        }
        else {
            course = courseDao.createNewCourse(course);
        }
        return course;

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE
        if (id != course.getCourseId()){
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
        }
        else{
            courseDao.updateCourse(course);
            course = getCourseById(id);
        }
        return course;

        //YOUR CODE ENDS HERE
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE

        try {
            courseDao.deleteCourse(id);
            System.out.println("Course ID : " + id + " deleted");
        }catch (DataAccessException e){
            throw e;
        }

        //YOUR CODE ENDS HERE
    }
}
