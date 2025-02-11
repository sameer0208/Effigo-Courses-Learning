import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, Button, Container, Modal, Form, Row, Col, Image } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-icons/font/bootstrap-icons.css";

const AllUsers = () => {
  const [users, setUsers] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [currentUser, setCurrentUser] = useState({ userId: "", username: "", userRole: "user", password: "" });
  const [roleFilter, setRoleFilter] = useState("ALL");
  const [showPassword, setShowPassword] = useState(false);
  const [noUsersMessage, setNoUsersMessage] = useState("");

  // 🔥 Fetch users from the API
  const fetchUsers = async (role = "ALL") => {
    try {
      let url =
        role === "ALL"
          ? "http://localhost:8080/learning-portal/users/fetch-all-users"
          : `http://localhost:8080/learning-portal/users/fetch-by-role?role=${role.toLowerCase()}`;

      const response = await axios.get(url);

      if (response.data.length === 0) {
        setNoUsersMessage(`No users with role '${role}' found.`);
        setUsers([]);
      } else {
        setNoUsersMessage("");
        setUsers(response.data);
      }
    } catch (error) {
      console.error("Error fetching users:", error);
      alert("Error fetching users");
    }
  };

  useEffect(() => {
    fetchUsers(roleFilter);
  }, [roleFilter]);

  const handleRoleFilterChange = (event) => {
    const selectedRole = event.target.value;
    setRoleFilter(selectedRole);
    fetchUsers(selectedRole);
  };

  const handleDelete = async (userId, username) => {
    const confirmDelete = window.confirm(`Are you sure you want to delete user: ${username}?`);
    if (!confirmDelete) return;

    try {
      await axios.delete(`http://localhost:8080/learning-portal/users/delete-user/${userId}`);
      fetchUsers(roleFilter);
      alert("User deleted successfully!");
    } catch (error) {
      console.error("Error deleting user:", error);
      alert("Error deleting user");
    }
  };

  const handleShowModal = (user) => {
    setCurrentUser({ ...user }); // 🔥 No uppercase conversion here!
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleUpdate = async () => {
    try {
      const prevRole = currentUser.userRole; // 🔥 Store previous role
      const updatedRole = currentUser.userRole.toLowerCase(); // 🔥 Ensure lowercase role

      await axios.put(
        `http://localhost:8080/learning-portal/users/update/user-details/${currentUser.userId}`,
        { ...currentUser, userRole: updatedRole } // 🔥 Store role in lowercase
      );

      alert("User updated successfully!");

      handleCloseModal();

      // 🔥 If role changed, update filter & fetch new users
      if (prevRole !== updatedRole) {
        setRoleFilter(updatedRole);
        fetchUsers(updatedRole);
      } else {
        // 🔥 If role didn't change, just refresh the current list
        fetchUsers(roleFilter);
      }
      
    } catch (error) {
      console.error("Error updating user:", error);
      alert("Error updating user");
    }
  };

  return (
    <Container>
      <h2 className="mb-4">User Profiles</h2>

      {/* Role Filter */}
      <div className="mb-3">
        <strong>Filter by Role:</strong>
        <Form.Check inline label="Show All" type="radio" name="roleFilter" value="ALL" checked={roleFilter === "ALL"} onChange={handleRoleFilterChange} />
        <Form.Check inline label="User" type="radio" name="roleFilter" value="user" checked={roleFilter === "user"} onChange={handleRoleFilterChange} />
        <Form.Check inline label="Admin" type="radio" name="roleFilter" value="admin" checked={roleFilter === "admin"} onChange={handleRoleFilterChange} />
      </div>

      <Row>
        {noUsersMessage ? (
          <Col className="text-center">
            <p className="text-danger">{noUsersMessage}</p>
          </Col>
        ) : (
          users.map((user) => (
            <Col md={4} key={user.userId} className="mb-4">
              <Card className="text-center">
                <Card.Body>
                  {/* Generate unique avatar using RoboHash */}
                  <Image
                    src={`https://robohash.org/${user.username}.png?size=100x100`}
                    roundedCircle
                    className="mb-3"
                  />
                  <Card.Title>{user.username}</Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">{user.userRole}</Card.Subtitle>
                  <Card.Text><strong>User ID:</strong> {user.userId}</Card.Text>
                  <Button variant="warning" onClick={() => handleShowModal(user)} className="me-2">
                    <i className="bi bi-pencil"></i> Update
                  </Button>
                  <Button variant="danger" onClick={() => handleDelete(user.userId, user.username)}>
                    <i className="bi bi-trash"></i> Delete
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          ))
        )}
      </Row>

      {/* Update User Modal */}
      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Update User</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group>
              <Form.Label>Username</Form.Label>
              <Form.Control type="text" value={currentUser.username} onChange={(e) => setCurrentUser({ ...currentUser, username: e.target.value })} />
            </Form.Group>

            <Form.Group className="mt-3">
              <Form.Label>Password</Form.Label>
              <div className="d-flex">
                <Form.Control type={showPassword ? "text" : "password"} value={currentUser.password} onChange={(e) => setCurrentUser({ ...currentUser, password: e.target.value })} />
                <Button variant="outline-secondary" onClick={() => setShowPassword(!showPassword)}>
                  <i className={showPassword ? "bi bi-eye-slash" : "bi bi-eye"}></i>
                </Button>
              </div>
            </Form.Group>

            <Form.Group className="mt-3">
              <Form.Label>User Role</Form.Label>
              <Form.Select value={currentUser.userRole} onChange={(e) => setCurrentUser({ ...currentUser, userRole: e.target.value.toLowerCase() })}>
                <option value="user">User</option>
                <option value="admin">Admin</option>
              </Form.Select>
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>Close</Button>
          <Button variant="primary" onClick={handleUpdate}>Save Changes</Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
};

export default AllUsers;
