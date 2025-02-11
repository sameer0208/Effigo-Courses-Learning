import React, { useState, useEffect } from "react";
import axios from "axios";
import { Container, Form, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const AddRegisteredCourse = () => {
  const [users, setUsers] = useState([]);
  const [courses, setCourses] = useState([]);
  const [selectedUserId, setSelectedUserId] = useState("");
  const [selectedCourseId, setSelectedCourseId] = useState("");

  // Fetch all users
  const fetchUsers = async () => {
    try {
      const response = await axios.get("http://localhost:8080/learning-portal/users/fetch-all-users");
      setUsers(response.data);
    } catch (error) {
      console.error("Error fetching users:", error);
      alert("Error fetching users");
    }
  };

  // Fetch all courses
  const fetchCourses = async () => {
    try {
      const response = await axios.get("http://localhost:8080/learning-portal/courses/fetch-all-courses");
      setCourses(response.data);
    } catch (error) {
      console.error("Error fetching courses:", error);
      alert("Error fetching courses");
    }
  };

  useEffect(() => {
    fetchUsers();
    fetchCourses();
  }, []);

  // Register User to Course
  const handleRegisterToCourse = async () => {
    if (!selectedUserId || !selectedCourseId) {
      alert("Please select both a user and a course.");
      return;
    }

    try {
      await axios.post("http://localhost:8080/learning-portal/registered-courses/course-registration", {
        userId: selectedUserId,
        courseId: selectedCourseId,
      });

      alert("User registered to course successfully!");
      setSelectedUserId("");
      setSelectedCourseId("");
    } catch (error) {
      console.error("Error registering user to course:", error.response ? error.response.data : error);
      alert("Failed to register user to course. Check console for details.");
    }
  };

  return (
    <Container>
      <h2>Register User to Course</h2>

      {/* Select User */}
      <Form.Group className="mb-3">
        <Form.Label>Select User</Form.Label>
        <Form.Select value={selectedUserId} onChange={(e) => setSelectedUserId(e.target.value)}>
          <option value="">-- Select a User --</option>
          {users.length > 0 ? (
            users.map((user) => (
              <option key={user.userId} value={user.userId}>
                {user.username} (ID: {user.userId})
              </option>
            ))
          ) : (
            <option disabled>Loading users...</option>
          )}
        </Form.Select>
      </Form.Group>

      {/* Select Course */}
      <Form.Group className="mb-3">
        <Form.Label>Select Course</Form.Label>
        <Form.Select value={selectedCourseId} onChange={(e) => setSelectedCourseId(e.target.value)}>
          <option value="">-- Select a Course --</option>
          {courses.length > 0 ? (
            courses.map((course) => (
              <option key={course.courseId} value={course.courseId}>
                {course.title} (ID: {course.courseId})
              </option>
            ))
          ) : (
            <option disabled>Loading courses...</option>
          )}
        </Form.Select>
      </Form.Group>

      {/* Register Button */}
      <Button variant="primary" onClick={handleRegisterToCourse}>
        Register
      </Button>
    </Container>
  );
};

export default AddRegisteredCourse;
