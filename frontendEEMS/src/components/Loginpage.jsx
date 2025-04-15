import React, { useState } from 'react'
import Header from './Header'
import './styles/stylinglogin.css'
import { Link, useNavigate } from 'react-router-dom'
import axios from 'axios'

const Loginpage = () => {

  const navigate = useNavigate();

  const [formData, setformData] = useState({
    username: '',
    password: '',
    role: 'Employee'
  });

  const handlechange = (e) => {
    setformData({
      ...formData, [e.target.name]: e.target.value
    });
  };

  const handlesubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/login', formData);
      if (response.data === 'Login successful!') {
        if (formData.role === 'Employee') {
          navigate('/emplanding'); // Navigate to Employee landing page
        } else {
          navigate('/managerlanding'); // Navigate to Manager landing page
        }
      } else if (response.data === 'Login unsuccessful!') {
        alert('Login failed');
      }
      else {
        alert(response.data);
      }
    } catch (error) {
      console.error("Error:", error);
      alert(`Login failed: ${error.message}`);
    }
  };

  return (
    <>
      <Header />
      <div className="container">
        <h2 className="login-title">Login</h2>
        <form className="login-form" onSubmit={handlesubmit} >
          <label>Username:</label>
          <input type="text" placeholder="Enter your email" name='username' value={formData.username} onChange={handlechange} required />

          <label>Password:</label>
          <input type="password" placeholder="Enter password" name='password' value={formData.password} onChange={handlechange} required />

          <label>Choose Role:</label>
          <select name='role' value={formData.role} onChange={handlechange}>
            <option value="Employee">Employee</option>
            <option value="Manager">Manager</option>
          </select>

          <button type="submit" className="submit-btn">Submit</button>
        </form>

        <p className="register-text">If not registered, register yourself</p>
        <button className="register-btn" >
          <Link to="/register" className="register-link">Register</Link></button>
      </div>
    </>
  )
}

export default Loginpage
