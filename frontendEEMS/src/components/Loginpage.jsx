import React, { useState } from 'react';
import Header from './Header';
import './styles/stylinglogin.css';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

const Loginpage = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: '',
    password: '',
    role: 'Employee'
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post('http://localhost:8080/api/auth/login', formData);
      const data = response.data;

      if (data.token) {
        localStorage.setItem('token', data.token);
        localStorage.setItem('email', formData.email);
        localStorage.setItem('role', formData.role);

        alert("Login successful!");

        // Navigate based on role
        if (formData.role === 'Employee') {
           navigate('/emplanding');
        } else {
           navigate('/managerlanding');
        }
      } else if (data.error) {
        alert(data.error); // e.g. Role mismatch or invalid credentials
      } else {
        alert("Unexpected response from server.");
      }
    } catch (error) {
      console.error("Login error:", error);
      alert("Login failed: " + (error.response?.data?.error || error.message));
    }
  };

  return (
    <>
      <Header />
      <div className="container">
        <h2 className="login-title">Login</h2>
        <form className="login-form" onSubmit={handleSubmit}>
          <label>Email:</label>
          <input
            type="email"
            placeholder="Enter your email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label>Password:</label>
          <input
            type="password"
            placeholder="Enter password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />

          <label>Choose Role:</label>
          <select name="role" value={formData.role} onChange={handleChange}>
            <option value="Employee">Employee</option>
            <option value="Manager">Manager</option>
          </select>

          <button type="submit" className="submit-btn">Submit</button>
        </form>

        <p className="register-text">If not registered, register yourself</p>
        <button className="register-btn">
          <Link to="/register" className="register-link">Register</Link>
        </button>
      </div>
    </>
  );
};

export default Loginpage;
