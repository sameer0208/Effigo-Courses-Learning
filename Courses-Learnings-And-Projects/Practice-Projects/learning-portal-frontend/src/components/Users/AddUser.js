import React, { useState } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import axios from "axios";
import { Form, Button, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap CSS locally
import "bootstrap-icons/font/bootstrap-icons.css"; // Import Bootstrap Icons CSS locally

const AddUser = () => {
  const navigate = useNavigate(); // Initialize navigate function

  const [userData, setUserData] = useState({
    username: "",
    password: "",
    userRole: "user", // default role as user
  });

  const handleChange = (e) => {
    setUserData({
      ...userData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const registrationDateTime = new Date().toISOString();

    try {
      const response = await axios.post(
        "http://localhost:8080/learning-portal/users/create-user",
        { ...userData, registrationDateTime }
      );
      console.log("User added successfully:", response.data);
      alert("User added successfully!");

      navigate("/users/all-users"); // Redirect to All Users page
    } catch (error) {
      console.error("Error adding user:", error);
      alert("Error adding user");
    }
  };

  return (
    <Container>
      <h2>Add User</h2>
      <Form onSubmit={handleSubmit}>
        <Form.Group controlId="formUsername">
          <Form.Label>Username</Form.Label>
          <Form.Control
            type="text"
            name="username"
            value={userData.username}
            onChange={handleChange}
            required
            placeholder="Enter Username"
          />
        </Form.Group>
        <Form.Group controlId="formPassword">
          <Form.Label>Password</Form.Label>
          <Form.Control
            type="password"
            name="password"
            value={userData.password}
            onChange={handleChange}
            required
            placeholder="Enter Password"
          />
        </Form.Group>
        <Form.Group controlId="formUserRole">
          <Form.Label>User Role</Form.Label>
          <Form.Control
            as="select"
            name="userRole"
            value={userData.userRole}
            onChange={handleChange}
          >
            <option value="user">User</option>
            <option value="admin">Admin</option>
          </Form.Control>
        </Form.Group>
        <Button variant="primary" type="submit" className="w-100 mt-3">
          <i className="bi bi-person-plus"></i> Add User
        </Button>
      </Form>
    </Container>
  );
};

export default AddUser;
