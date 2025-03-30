import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";
import Login from "./components/Login";
import Signup from "./components/Signup";
import Profile from "./components/Profile";
import Home from "./components/Home";
const App = () => {
  return (
    <Router>
      <Navbar />
      <div className="container">
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/home" element={<Home />} />
          <Route path="/" element={<Login />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
