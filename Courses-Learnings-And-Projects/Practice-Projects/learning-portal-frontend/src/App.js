import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { Container } from "react-bootstrap";
import Home from "./components/Home";
import NavbarComponent from "./components/Navbar/NavbarComponent";
import AddUser from "./components/Users/AddUser";
import AllUsers from "./components/Users/AllUsers";
import SearchUser from "./components/Users/SearchUser";
import AddCourse from "./components/Courses/AddCourse";
import AllCourses from "./components/Courses/AllCourses";
import SearchCourse from "./components/Courses/SearchCourse";
import AllRegisteredCourses from "./components/RegisteredCourses/AllRegisteredCourses";
import AddRegisteredCourse from "./components/RegisteredCourses/AddRegisteredCourse";
import AllFavouriteCourses from "./components/FavouriteCourses/AllFavouriteCourses"; // ✅ Import Favourite Courses Component
import NotFound from "./components/NotFound";

function App() {
  return (
    <Router>
      <NavbarComponent />
      <Container className="mt-5">
        <Routes>
        <Route path="/" element={<Home />} /> 
          <Route path="/users/add-user" element={<AddUser />} />
          <Route path="/users/all-users" element={<AllUsers />} />
          <Route path="/users/search-user" element={<SearchUser />} />
          <Route path="/registered-courses/add-registered-course" element={<AddRegisteredCourse />} />
          <Route path="/registered-courses/all-registered-courses" element={<AllRegisteredCourses />} />
          <Route path="/courses/add-course" element={<AddCourse />} />
          <Route path="/courses/all-courses" element={<AllCourses />} />
          <Route path="/courses/search-course" element={<SearchCourse />} />
          <Route path="/favourite-courses/all-favourite-courses" element={<AllFavouriteCourses />} /> {/* ✅ Added Route */}
          <Route path="*" element={<NotFound />} /> {/* Catch-all route */}
        </Routes>
      </Container>
    </Router>
  );
}

export default App;
