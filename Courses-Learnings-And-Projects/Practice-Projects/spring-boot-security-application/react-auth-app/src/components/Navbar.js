import React from "react";
import { Link } from "react-router-dom";
import AuthService from "../services/AuthService";

const Navbar = () => {
  const currentUser = AuthService.getCurrentUser();

  const logOut = () => {
    AuthService.logout();
    window.location.reload();
  };

  return (
    <nav className="navbar navbar-light bg-light shadow-sm">
      <div className="container">
        <Link className="navbar-brand fw-bold text-primary" to="/home">
          Secure Security App | Sameer
        </Link>
        <div>
          <ul className="nav">
            <li className="nav-item">
              <Link className="nav-link text-dark" to="/home">
                Home
              </Link>
            </li>
            {currentUser ? (
              <>
                <li className="nav-item">
                  <Link className="nav-link text-dark" to="/profile">
                    Profile
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link text-danger" to="/" onClick={logOut}>
                    Logout
                  </Link>
                </li>
              </>
            ) : (
              <>
                <li className="nav-item">
                  <Link className="nav-link text-success" to="/login">
                    Login
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link text-primary" to="/signup">
                    Sign Up
                  </Link>
                </li>
              </>
            )}
          </ul>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
