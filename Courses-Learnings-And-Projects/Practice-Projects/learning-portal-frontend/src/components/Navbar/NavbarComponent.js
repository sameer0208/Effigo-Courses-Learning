import React from "react";
import { Navbar, Nav, NavDropdown } from "react-bootstrap";
import { Link } from "react-router-dom";
import 'bootstrap-icons/font/bootstrap-icons.css';  // Import Bootstrap Icons CSS locally

const NavbarComponent = () => {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Navbar.Brand as={Link} to="/">Learning Portal</Navbar.Brand>
      <Navbar.Toggle aria-controls="navbarNav" />
      <Navbar.Collapse id="navbarNav">
        <Nav className="ml-auto">
          <NavDropdown title={<><i className="bi bi-person"></i> Users</>} id="basic-nav-dropdown">
            <NavDropdown.Item as={Link} to="/users/add-user"><i className="bi bi-person-plus"></i> Add User</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/users/all-users"><i className="bi bi-person-lines-fill"></i> All Users</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/users/search-user"><i className="bi bi-search"></i> Search User</NavDropdown.Item>
          </NavDropdown>

          <NavDropdown title={<><i className="bi bi-laptop"></i> Courses</>} id="basic-nav-dropdown">
            <NavDropdown.Item as={Link} to="/courses/add-course"><i className="bi bi-plus-square"></i> Add Course</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/courses/all-courses"><i className="bi bi-journal-bookmark"></i> All Courses</NavDropdown.Item>
            <NavDropdown.Item as={Link} to="/courses/search-course"><i className="bi bi-search"></i> Search Course</NavDropdown.Item>
          </NavDropdown>

          <NavDropdown title={<><i className="bi bi-mortarboard"></i> Registered Courses</>} id="basic-nav-dropdown">
  <NavDropdown.Item as={Link} to="/registered-courses/add-registered-course">
    <i className="bi bi-plus-circle"></i> Add Registered Course
  </NavDropdown.Item>
  <NavDropdown.Item as={Link} to="/registered-courses/all-registered-courses">
    <i className="bi bi-list-check"></i> View Registered Courses
  </NavDropdown.Item>
</NavDropdown>

<NavDropdown title={<><i className="bi bi-heart"></i> Favourite Courses</>} id="basic-nav-dropdown">
            <NavDropdown.Item as={Link} to="/favourite-courses/all-favourite-courses">
              <i className="bi bi-star"></i> View Favourite Courses
            </NavDropdown.Item>
          </NavDropdown>


        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default NavbarComponent;
