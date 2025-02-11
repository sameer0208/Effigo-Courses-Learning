import React, { useState } from "react";
import axios from "axios";
import { Form, Button, Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom"; 

const AddCourse = () => {
  const navigate = useNavigate(); 
  const [courseData, setCourseData] = useState({
    title: "",
    courseCategory: "",
    description: "",
    price: "",
  });

  const handleChange = (e) => {
    setCourseData({ ...courseData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post("http://localhost:8080/learning-portal/courses/create-course", courseData);
      alert("Course added successfully!");
      setCourseData({ title: "", courseCategory: "", description: "", price: "" }); // Reset form
      navigate("/courses/all-courses");
    } catch (error) {
      alert("Error adding course");
      console.error("Error:", error);
    }
  };

  return (
    <Container>
      <h2>Add Course</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group>
          <Form.Label>Title</Form.Label>
          <Form.Control type="text" name="title" value={courseData.title} onChange={handleChange} required />
        </Form.Group>

        <Form.Group className="mt-3">
          <Form.Label>Course Category</Form.Label>
          <Form.Control type="number" name="courseCategory" value={courseData.courseCategory} onChange={handleChange} required />
        </Form.Group>

        <Form.Group className="mt-3">
          <Form.Label>Description</Form.Label>
          <Form.Control as="textarea" name="description" value={courseData.description} onChange={handleChange} required />
        </Form.Group>

        <Form.Group className="mt-3">
          <Form.Label>Price</Form.Label>
          <Form.Control type="number" name="price" value={courseData.price} onChange={handleChange} required />
        </Form.Group>

        <Button variant="primary" type="submit" className="w-100 mt-3">
          Add Course
        </Button>
      </Form>
    </Container>
  );
};

export default AddCourse;
