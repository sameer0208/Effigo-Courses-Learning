import React, { useState } from "react";
import axios from "axios";
import { Container, Form, Button, Alert, Table } from "react-bootstrap";  // Importing Bootstrap components

// import './SearchUser.css';

const SearchUser = () => {
  const [userId, setUserId] = useState("");
  const [user, setUser] = useState(null);
  const [error, setError] = useState(null); // To handle errors

  const handleChange = (e) => {
    setUserId(e.target.value);
  };

  const handleSearch = async () => {
    setError(null); // Reset error on new search
    try {
      const response = await axios.get(
        `http://localhost:8080/learning-portal/users/fetch-user/${userId}`
      );
      setUser(response.data);
    } catch (error) {
      console.error("Error fetching user:", error);
      setError("User not found");
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="mb-4 text-center">Search User</h2>

      {/* Error alert */}
      {error && <Alert variant="danger">{error}</Alert>}

      <Form className="mb-4">
        <Form.Group controlId="userId">
          <Form.Label>Enter User ID</Form.Label>
          <Form.Control
            type="text"
            value={userId}
            onChange={handleChange}
            placeholder="User ID"
            isInvalid={error}  // Highlight input if there's an error
          />
          <Form.Control.Feedback type="invalid">
            Please enter a valid User ID.
          </Form.Control.Feedback>
        </Form.Group>
        <Button variant="primary" onClick={handleSearch} className="mt-3">
          Search
        </Button>
      </Form>

      {/* Display user info if found */}
      {user && (
        <Table striped bordered hover className="mt-3">
        <thead>
          <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>User Role</th>
            <th>Registration DateTime</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{user.userId}</td>
            <td>{user.username}</td>
            <td> {user.userRole}</td>
            <td>{new Date(user.registrationDateTime).toLocaleString()}</td>
          </tr>
        </tbody>
      </Table>
      )}
    </Container>
  );
};

export default SearchUser;
