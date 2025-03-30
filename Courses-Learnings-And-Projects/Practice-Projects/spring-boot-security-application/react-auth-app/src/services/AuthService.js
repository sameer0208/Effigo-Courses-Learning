// services/AuthService.js
import axios from 'axios';

const API_URL = "http://localhost:8080/api/auth/";

const AuthService = {
  register(username, email, password, role) {
    console.log('Sending signup request:', { username, email, password, role });
    return axios.post(API_URL + "signup", {
      username,
      email,
      password,
      role
    }).then(response => {
      console.log("Signup success:", response.data);
      return response.data;
    }).catch(error => {
      console.log("Error in signup request:", error);
      throw error;
    });
  },  

  login(username, password) {
    return axios.post(API_URL + "signin", {
      username,
      password
    }).then(response => {
      if (response.data.accessToken) {
        localStorage.setItem("user", JSON.stringify(response.data));
      }
      return response.data;
    }).catch(error => {
      console.log("Error in login request:", error);
      throw error;
    });
  },  

  logout() {
    localStorage.removeItem("user");
  },

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
};

export default AuthService;
