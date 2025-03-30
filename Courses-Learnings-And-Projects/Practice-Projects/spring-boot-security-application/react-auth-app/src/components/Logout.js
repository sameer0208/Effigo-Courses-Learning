import React from "react";
import { useNavigate } from "react-router-dom";
import AuthService from "../services/AuthService";

const Logout = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    AuthService.logout();
    navigate("/login");
  };

  return (
    <div>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
};

export default Logout;
