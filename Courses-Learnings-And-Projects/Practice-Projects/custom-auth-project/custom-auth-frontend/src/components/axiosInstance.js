// axiosInstance.js
import axios from "axios";

// Create a custom Axios instance
const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
});

// Add an interceptor to include the Bearer token
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default axiosInstance;
