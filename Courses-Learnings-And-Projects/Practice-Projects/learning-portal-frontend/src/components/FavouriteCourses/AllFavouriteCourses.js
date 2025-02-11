import React, { useState, useEffect } from "react";
import axios from "axios";
import { Card, Container, Row, Col, Alert, Button } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

const AllFavouriteCourses = () => {
  const [favouriteCourses, setFavouriteCourses] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");

  useEffect(() => {
    fetchFavouriteCourses();
  }, []);

  const fetchFavouriteCourses = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/learning-portal/favourite-courses/fetch-all-favourite-courses"
      );
      if (response.data.length === 0) {
        setErrorMessage("No favourite courses found.");
      } else {
        setErrorMessage("");
        setFavouriteCourses(response.data);
      }
    } catch (error) {
      console.error("Error fetching favourite courses:", error);
      setErrorMessage("Failed to fetch favourite courses.");
    }
  };

  const handleDelete = async (favouriteId, courseTitle) => {
    const confirmDelete = window.confirm(
      `🛑 Are you sure you want to remove "${courseTitle}" from your favourite list?`
    );

    if (!confirmDelete) return; // If user cancels, do nothing

    try {
      await axios.delete(
        `http://localhost:8080/learning-portal/favourite-courses/delete-favourite-course/${favouriteId}`
      );
      setFavouriteCourses(favouriteCourses.filter((fav) => fav.favouriteId !== favouriteId));
    } catch (error) {
      console.error("Error deleting favourite course:", error);
      alert("❌ Failed to delete the favourite course.");
    }
  };

  return (
    <Container className="mt-4">
      <h2 className="mb-4 text-center text-primary fw-bold">Favourite Courses</h2>

      {errorMessage ? (
        <Alert variant="danger" className="text-center">
          {errorMessage}
        </Alert>
      ) : (
        <Row className="g-4">
          {favouriteCourses.map((fav) => (
            <Col key={fav.favouriteId} xs={12} sm={6} md={4} lg={3}>
              <Card className="shadow-lg border-0 rounded-4">
                <Card.Body className="p-4">
                  {/* Course Title with Icon */}
                  <Card.Title className="text-dark fw-bold d-flex align-items-center">
                    <i className="bi bi-book text-primary me-2 fs-4"></i>
                    {fav.courseTitle}
                  </Card.Title>

                  {/* User Name with Icon */}
                  <Card.Subtitle className="mb-3 text-muted d-flex align-items-center">
                    <i className="bi bi-person-circle text-secondary me-2 fs-5"></i>
                    {fav.username}
                  </Card.Subtitle>

                  {/* Course Details */}
                  <Card.Text className="text-dark">
                    <span className="fw-bold text-secondary">Favourite ID:</span> {fav.favouriteId} <br />
                    <span className="fw-bold text-secondary">Registered Course ID:</span> {fav.registeredCourseId}
                  </Card.Text>

                  {/* Remove Button with Confirmation */}
                  <Button
                    variant="danger"
                    className="w-100 mt-2"
                    onClick={() => handleDelete(fav.favouriteId, fav.courseTitle)}
                  >
                    <i className="bi bi-trash"></i> Remove
                  </Button>
                </Card.Body>

                {/* Footer with Background */}
                <Card.Footer className="bg-primary text-white text-center rounded-bottom">
                  <small>Added to favourites ❤️</small>
                </Card.Footer>
              </Card>
            </Col>
          ))}
        </Row>
      )}
    </Container>
  );
};

export default AllFavouriteCourses;
