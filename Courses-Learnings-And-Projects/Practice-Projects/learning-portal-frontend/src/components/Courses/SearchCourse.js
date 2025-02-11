import React, { useState } from "react";
import axios from "axios";
import { Form, Button, Container, Table } from "react-bootstrap";

const SearchCourse = () => {
  const [courseId, setCourseId] = useState("");
  const [course, setCourse] = useState(null);
  const [error, setError] = useState(null);

  const handleSearch = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/learning-portal/courses/fetch-course/${courseId}`);
      setCourse(response.data);
      setError(null); // Clear error message if course is found
    } catch (error) {
      setError("Course not found");
      setCourse(null);
    }
  };

  return (
    <Container>
      <h2>Search Course</h2>
      <Form>
        <Form.Group>
          <Form.Label>Enter Course ID</Form.Label>
          <Form.Control type="text" value={courseId} onChange={(e) => setCourseId(e.target.value)} />
        </Form.Group>
        <Button variant="primary" className="mt-3" onClick={handleSearch}>
          Search
        </Button>
      </Form>

      {error && <p className="text-danger mt-3">{error}</p>}

      {course && (
        <Table striped bordered hover className="mt-3">
          <thead>
            <tr>
              <th>Course ID</th>
              <th>Title</th>
              <th>Category</th>
              <th>Description</th>
              <th>Price</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{course.courseId}</td>
              <td>{course.title}</td>
              <td>{course.courseCategory}</td>
              <td>{course.description}</td>
              <td>{course.price}</td>
            </tr>
          </tbody>
        </Table>
      )}
    </Container>
  );
};

export default SearchCourse;
