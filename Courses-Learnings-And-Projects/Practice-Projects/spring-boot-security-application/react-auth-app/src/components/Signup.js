import React, { useState } from "react";
import AuthService from "../services/AuthService";
import { useNavigate } from "react-router-dom";

const Signup = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("ROLE_USER"); // Default role is 'user'
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  const handleRoleChange = (e) => {
    setRole(e.target.value);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    // Send roles as an array
    const signupRequest = {
      username,
      email,
      password,
      roles: [role],  // Send roles as an array of strings (matching the backend roles enum)
    };

    try {
      await AuthService.register(signupRequest);  // Assuming AuthService handles the API call correctly
      navigate("/login"); // Navigate to login page after successful signup
    } catch (error) {
      setMessage(error.response?.data?.message || "An error occurred during sign-up");
    }
  };

  return (
    <div className="container d-flex justify-content-center align-items-center vh-100">
      <div className="card p-4 shadow-lg" style={{ width: "400px" }}>
        <h2 className="text-center mb-4">Sign Up</h2>
        {message && <div className="alert alert-danger">{message}</div>}
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label className="form-label">Username</label>
            <input
              type="text"
              className="form-control"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Email</label>
            <input
              type="email"
              className="form-control"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Password</label>
            <input
              type="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label className="form-label">Role</label>
            <select
              className="form-select"
              value={role}
              onChange={handleRoleChange}
            >
              <option value="ROLE_USER">User</option>
              <option value="ROLE_ADMIN">Admin</option>
              <option value="ROLE_MODERATOR">Moderator</option>
            </select>
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Sign Up
          </button>
        </form>
      </div>
    </div>
  );
};

export default Signup;
