import React from "react";
import { Container, Button, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css"; // Import Bootstrap CSS locally

const UserSections = () => {
  return (
    <Container>
      <h3>User Management</h3>
      <Row>
        <Col sm={12} md={4}>
          <Button variant="primary" href="/users/add-user" className="w-100">
            <i className="fas fa-user-plus"></i> Add User
          </Button>
        </Col>
        <Col sm={12} md={4}>
          <Button variant="info" href="/users/all-users" className="w-100">
            <i className="fas fa-users"></i> All Users
          </Button>
        </Col>
        <Col sm={12} md={4}>
          <Button variant="secondary" href="/users/search-user" className="w-100">
            <i className="fas fa-search"></i> Search User
          </Button>
        </Col>
      </Row>
    </Container>
  );
};

export default UserSections;
