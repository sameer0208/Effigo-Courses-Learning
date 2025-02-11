import React from "react";
import { Container, Button, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const CourseSections = () => {
  return (

<Container>
<h3>Course Management</h3>
<Row>
  <Col sm={12} md={4}>
    <Button variant="primary" href="/courses/add-user" className="w-100">
      <i className="fas fa-user-plus"></i> Add Course
    </Button>
  </Col>
  <Col sm={12} md={4}>
    <Button variant="info" href="/courses/all-courses" className="w-100">
      <i className="fas fa-users"></i> All Courses
    </Button>
  </Col>
  <Col sm={12} md={4}>
    <Button variant="secondary" href="/courses/search-course" className="w-100">
      <i className="fas fa-search"></i> Search User
    </Button>
  </Col>
</Row>
</Container>
  );
};

export default CourseSections;
