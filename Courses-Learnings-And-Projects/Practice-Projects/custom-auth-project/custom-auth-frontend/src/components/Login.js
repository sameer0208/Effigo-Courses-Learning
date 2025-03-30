import { useState } from "react";
import axiosInstance from "./axiosInstance"; // Use the custom Axios instance
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [credentials, setCredentials] = useState({
    username: "",
    password: "",
  });

  const navigate = useNavigate();

  // Update credentials as user types
  const handleChange = (e) => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  // Handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      // Use axiosInstance to log in
      const response = await axiosInstance.post("/api/auth/login", credentials);

      // Store JWT token in localStorage
      localStorage.setItem("token", response.data.token);

      alert("Login successful!");
      navigate("/home"); // Redirect to home page
    } catch (error) {
      alert("Login failed: " + (error.response?.data?.message || error.message));
    }
  };

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">
        <div className="col-md-6">
          <div className="card shadow-lg p-4">
            <h2 className="text-center mb-4">Login</h2>
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label className="form-label">Username:</label>
                <input
                  type="text"
                  name="username"
                  placeholder="Enter username"
                  value={credentials.username}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>

              <div className="mb-3">
                <label className="form-label">Password:</label>
                <input
                  type="password"
                  name="password"
                  placeholder="Enter password"
                  value={credentials.password}
                  onChange={handleChange}
                  className="form-control"
                  required
                />
              </div>

              <button type="submit" className="btn btn-primary w-100">
                Login
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Login;
