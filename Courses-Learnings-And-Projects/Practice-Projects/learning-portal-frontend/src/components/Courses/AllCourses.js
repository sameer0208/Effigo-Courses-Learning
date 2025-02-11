import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, Button, Container, Modal, Form, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const AllCourses = () => {
  const [courses, setCourses] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentCourse, setCurrentCourse] = useState({
    courseId: "",
    title: "",
    courseCategory: "",
    description: "",
    price: "",
  });

  useEffect(() => {
    fetchCourses();
  }, []);

  const fetchCourses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/learning-portal/courses/fetch-all-courses");
      setCourses(response.data || []); // Ensuring it is always an array
    } catch (error) {
      console.error("Error fetching courses:", error);
      setCourses([]); // Set empty array in case of an error
    }
  };

  const handleDelete = async (courseId) => {
    if (window.confirm("Are you sure you want to delete this course?")) {
      try {
        await axios.delete(`http://localhost:8080/learning-portal/courses/delete-course/${courseId}`);
        setCourses(courses.filter((course) => course.courseId !== courseId));
        alert("Course deleted successfully!");
      } catch (error) {
        alert("Error deleting course");
      }
    }
  };
  
  const handleUpdate = async () => {
    try {
      await axios.put(
        `http://localhost:8080/learning-portal/courses/update/course-details/${currentCourse.courseId}`,
        currentCourse
      );
  
      setCourses(courses.map((course) => (course.courseId === currentCourse.courseId ? currentCourse : course)));
      alert("Course updated successfully!");
      setShowModal(false);
    } catch (error) {
      alert("Error updating course");
    }
  };
  const handleShowModal = (course) => {
    setCurrentCourse(course);
    setShowModal(true);
  };
  
  return (
    <Container className="mt-4">
      <h2 className="text-center mb-4">All Courses</h2>
      {courses.length > 0 ? ( // Check if courses array is not empty
        <Row>
          {courses.map((course) => (
            <Col md={4} key={course.courseId} className="mb-4">
              <Card className="shadow-sm border-0 rounded">
                <Card.Body>
                  <Card.Title className="fw-bold text-primary">{course.title}</Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">Course ID: {course.courseId}</Card.Subtitle>
                  <Card.Subtitle className="mb-2 text-muted">{course.courseCategory}</Card.Subtitle>
                  <Card.Text>{course.description}</Card.Text>
                  <Card.Text>
                    <strong>Price:</strong> ${course.price}
                  </Card.Text>
                  <div className="d-flex justify-content-between">
                    <Button variant="outline-warning" onClick={() => handleShowModal(course)}>
                      <i className="bi bi-pencil"></i> Edit
                    </Button>
                    <Button variant="outline-danger" onClick={() => handleDelete(course.courseId)}>
                      <i className="bi bi-trash"></i> Delete
                    </Button>
                  </div>
                </Card.Body>
              </Card>
            </Col>
          ))}
        </Row>
      ) : (
        <p className="text-center text-muted fs-5 mt-4">No courses added.</p>
      )}

      {/* Update Course Modal */}
      <Modal show={showModal} onHide={() => setShowModal(false)}>
        <Modal.Header closeButton>
          <Modal.Title>Update Course</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Title</Form.Label>
              <Form.Control
                type="text"
                value={currentCourse.title}
                onChange={(e) => setCurrentCourse({ ...currentCourse, title: e.target.value })}
              />
            </Form.Group>

            <Form.Group className="mt-3">
              <Form.Label>Category</Form.Label>
              <Form.Control
                type="text"
                value={currentCourse.courseCategory}
                onChange={(e) => setCurrentCourse({ ...currentCourse, courseCategory: e.target.value })}
              />
            </Form.Group>

            <Form.Group className="mt-3">
              <Form.Label>Description</Form.Label>
              <Form.Control
                as="textarea"
                value={currentCourse.description}
                onChange={(e) => setCurrentCourse({ ...currentCourse, description: e.target.value })}
              />
            </Form.Group>

            <Form.Group className="mt-3">
              <Form.Label>Price</Form.Label>
              <Form.Control
                type="number"
                value={currentCourse.price}
                onChange={(e) => setCurrentCourse({ ...currentCourse, price: e.target.value })}
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={() => setShowModal(false)}>
            Close
          </Button>
          <Button variant="primary" onClick={handleUpdate}>
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default AllCourses;