package com.effigocourses.springboot.learn_jpa_and_hibernate.course.jdbc;

import com.effigocourses.springboot.learn_jpa_and_hibernate.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository {

    @Autowired
    private JdbcTemplate springJdbcTemplate;

//    private static String INSERT_QUERY =
//            """
//                    insert into course(id, name, author)
//                    values(1,'spring boot course','udemy');
//
//                    """;

    private static String INSERT_QUERY =
            """
                    insert into course(id, name, author)
                    values(?,?,?);
                    """;

    private static String DELETE_QUERY = """
            
            delete from course where id  = ?
            
            """;

    private static String SELECT_QUERY = """
            SELECT * FROM COURSE WHERE ID =?
            """;

    public void insert(Course course)
    {
        springJdbcTemplate.update(INSERT_QUERY, course.getId(), course.getName(), course.getAuthor());
    }

    public void deleteByQuery(long id)
    {
        springJdbcTemplate.update(DELETE_QUERY, id);
    }

    public Course selectById(long id)
    {
        return springJdbcTemplate.queryForObject(SELECT_QUERY,new BeanPropertyRowMapper<>(Course.class),id);

    }

}
