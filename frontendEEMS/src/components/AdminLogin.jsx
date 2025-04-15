import './styles/stylingheader.css';
import './styles/stylinglogin.css';
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AdminLogin = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: '',
    password: '',
    role: 'Admin'
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
        localStorage.setItem('role', 'Admin');

        alert("Login successful!");
        navigate('/adminlanding');
      } else if (data.error) {
        alert(data.error);
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
      <nav className='adminlogin-navbar'>
        <img src="/assets/svg" alt="logo" className='logo' />
        <h1 className='welcome-text'>Welcome Admin!</h1>
      </nav>
      <div className="container">
        <h2 className="login-title">Login</h2>
        <form className="login-form" onSubmit={handleSubmit}>
          <label>Email:</label>
          <input
            type="email"
            placeholder="Enter your email"
            name='email'
            value={formData.email}
            onChange={handleChange}
            required
          />

          <label>Password:</label>
          <input
            type="password"
            placeholder="Enter password"
            name='password'
            value={formData.password}
            onChange={handleChange}
            required
          />

          <input type="hidden" name="role" value="Admin" />

          <button type="submit" className="submit-btn">Submit</button>
        </form>
      </div>
    </>
  );
};

export default AdminLogin;
