import React, { useState, useEffect } from "react";
import axios from "axios";
import { Container, Card, Row, Col, Button } from "react-bootstrap";

const AllRegisteredCourses = () => {
  const [registeredCourses, setRegisteredCourses] = useState([]);
  const [favouriteCourses, setFavouriteCourses] = useState([]);

  // Fetch all registered courses
  const fetchRegisteredCourses = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/learning-portal/registered-courses/fetch-registered-courses"
      );
      setRegisteredCourses(response.data);
    } catch (error) {
      console.error("Error fetching registered courses:", error);
      alert("Failed to fetch registered courses.");
    }
  };

  // Fetch favorite courses
  const fetchFavouriteCourses = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/learning-portal/favourite-courses/fetch-all-favourite-courses"
      );
      setFavouriteCourses(response.data || []);
    } catch (error) {
      console.error("Error fetching favourite courses:", error);
      setFavouriteCourses([]);
    }
  };

  // Check if a course is in favorites
  const isFavourite = (registrationId) => {
    return Array.isArray(favouriteCourses) && favouriteCourses.some((fav) => fav.registeredCourseId === registrationId);
  };

  // Handle adding/removing favorite
  const toggleFavourite = async (registrationId) => {
    const existingFavourite = favouriteCourses.find((fav) => fav.registeredCourseId === registrationId);

    if (existingFavourite) {
      // Ask for confirmation before removing
      const confirmDelete = window.confirm("Are you sure you want to remove this from favorites?");
      if (confirmDelete) {
        try {
          await axios.delete(
            `http://localhost:8080/learning-portal/favourite-courses/delete-favourite-course/${existingFavourite.favouriteId}`
          );
          alert("Removed from favorites!");
          fetchFavouriteCourses();
        } catch (error) {
          console.error("Error removing favourite course:", error);
          alert("Failed to remove from favorites.");
        }
      }
    } else {
      // Add to favorites
      try {
        await axios.post("http://localhost:8080/learning-portal/favourite-courses/add-favourite-course", {
          registeredCourseId: registrationId,
        });
        alert("Added to favorites!");
        fetchFavouriteCourses();
      } catch (error) {
        console.error("Error adding favourite course:", error);
        alert("Failed to add to favorites.");
      }
    }
  };

  // Handle unregistering a registered course
  const unregisterCourse = async (registrationId) => {
    const confirmUnregister = window.confirm("Are you sure you want to unregister from this course?");
    if (confirmUnregister) {
      try {
        await axios.delete(
          `http://localhost:8080/learning-portal/registered-courses/delete-registered-course/${registrationId}`
        );
        alert("Successfully unregistered from the course.");
        fetchRegisteredCourses(); // Refresh the list after deleting
        fetchFavouriteCourses(); // Refresh favorites in case the course was a favorite
      } catch (error) {
        console.error("Error unregistering from course:", error);
        alert("Failed to unregister from the course.");
      }
    }
  };

  useEffect(() => {
    fetchRegisteredCourses();
    fetchFavouriteCourses();
  }, []);

  return (
    <Container className="mt-4">
      <h2 className="text-center">All Registered Courses</h2>
      <Row>
        {registeredCourses.length > 0 ? (
          registeredCourses.map((reg) => (
            <Col key={reg.registrationId} md={4} sm={6} xs={12} className="mb-4">
              <Card className="shadow-lg">
                <Card.Body>
                  <Card.Title className="text-primary">
                    {reg.courseTitle || "Unknown Course"}
                  </Card.Title>
                  <Card.Subtitle className="mb-2 text-muted">
                    Registered by: {reg.username || "Unknown User"}
                  </Card.Subtitle>
                  <Card.Text>
                    <strong>Registration ID:</strong> {reg.registrationId}
                  </Card.Text>

                  {/* Heart Icon for Favorite using Bootstrap Icons */}
                  <Button variant="link" onClick={() => toggleFavourite(reg.registrationId)}>
                    <i
                      className={`bi ${isFavourite(reg.registrationId) ? "bi-heart-fill text-danger" : "bi-heart"} fs-4`}
                    ></i>
                  </Button>

                  {/* Unregister Button */}
                  <Button variant="danger" className="ms-2" onClick={() => unregisterCourse(reg.registrationId)}>
                    Unregister
                  </Button>
                </Card.Body>
              </Card>
            </Col>
          ))
        ) : (
          <Col className="text-center">
            <p>No registered courses found.</p>
          </Col>
        )}
      </Row>
    </Container>
  );
};

export default AllRegisteredCourses;
