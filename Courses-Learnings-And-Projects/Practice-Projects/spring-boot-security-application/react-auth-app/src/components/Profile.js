import React, { useState, useEffect } from "react";
import AuthService from "../services/AuthService";
import { useNavigate } from "react-router-dom"; // Import useNavigate

const Profile = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate(); // Use useNavigate instead of useHistory

  useEffect(() => {
    const currentUser = AuthService.getCurrentUser();

    if (!currentUser) {
      navigate("/login"); // Use navigate instead of history.push
    } else {
      setUser(currentUser);
    }
  }, [navigate]); // Dependency array should include 'navigate'

  return user ? (
    <div className="profile">
      <h2>Profile</h2>
      <p><strong>Username:</strong> {user.username}</p>
      <p><strong>Email:</strong> {user.email}</p>
      <p><strong>Roles:</strong> {user.roles.join(", ")}</p>
    </div>
  ) : null;
};

export default Profile;
