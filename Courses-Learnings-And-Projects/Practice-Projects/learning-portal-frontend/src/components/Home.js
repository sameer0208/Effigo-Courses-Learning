import React from "react";
import { Link } from "react-router-dom";
import { Button, Container, Row, Col } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "./Home.css";
import logo from "../images/logo-img.jpg"; 
const Home = () => {
  return (
    <div className="home-container">
      {/* Background Video */}
      <video autoPlay loop muted className="background-video">
        <source src="/videos/background-video.mp4" type="video/mp4" />
        Your browser does not support the video tag.
      </video>

      {/* Overlay Content */}
      <div className="overlay">
        <Container>
          <Row className="text-center">
            <Col>
            <img src={logo} alt="Logo" className="logo-img" />
              <h1 className="title">Welcome to the Learning Portal (Developed By Sameer)</h1>
              <p className="subtitle">
                Enhance your skills with top-notch courses from experts!
              </p>
              <Link to="/courses/all-courses">
                <Button variant="primary" size="lg" className="mt-3">
                  Get Started 🚀
                </Button>
              </Link>
            </Col>
          </Row>
          
          {/* Features Section */}
          <Row className="mt-5 features">
            <Col md={4} className="feature">
              <i className="bi bi-book-half feature-icon"></i>
              <h3>Wide Course Selection</h3>
              <p>Choose from hundreds of courses across various domains.</p>
            </Col>
            <Col md={4} className="feature">
              <i className="bi bi-person-check feature-icon"></i>
              <h3>Expert Instructors</h3>
              <p>Learn from industry experts with real-world experience.</p>
            </Col>
            <Col md={4} className="feature">
              <i className="bi bi-award feature-icon"></i>
              <h3>Get Certified</h3>
              <p>Earn certificates that help boost your career.</p>
            </Col>
          </Row>
        </Container>
      </div>
    </div>
  );
};

export default Home;
