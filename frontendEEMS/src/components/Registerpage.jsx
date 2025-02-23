import React, { useState } from 'react'
import Header from './Header'
import { Link } from 'react-router-dom'
import './styles/stylingregister.css'
import axios from 'axios'

const Registerpage = () => {

  const [formData, setformData] = useState({
    full_name: '',
    email: '',
    password: '',
    role: 'Emloyee'
  });

  const handlechange = (e) => {
    setformData({
      ...formData, [e.target.name]: e.target.value
    });
  };

  const handlesubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/addEmp', formData);
      if (response.data) {
        alert("Successfully Registered!");
      } else {
        alert("Registration failed. No response from server.");
      }
    } catch (error) {
      console.error("Error:", error); // Log the actual error in console
      alert(`Registration failed: ${error.message}`); // Show a proper error message
    }
  };


  return (
    <>
      <Header />
      <div className="container">
        <h2 className="register-title">Register</h2>
        <form className="register-form" onSubmit={handlesubmit}>
          <label>Full Name:</label>
          <input type="text" placeholder="Enter full name" name='full_name' value={formData.full_name} onChange={handlechange} required />

          <label>Email:</label>
          <input type="email" placeholder="Enter eamil" name='email' value={formData.email} onChange={handlechange} required />

          <label>Password:</label>
          <input type="password" placeholder="Enter password" name='password' value={formData.password} onChange={handlechange} required />

          <label>Choose Role:</label>
          <select name='role' value={formData.role} onChange={handlechange}>
            <option value="Employee">Employee</option>
            <option value="Manager">Manager</option>
          </select>

          <button type="submit" className="submit-btn">Submit</button>
        </form>

        <p className="redirectlogin-text">Go back to login page</p>
        <button className="redirectlogin-btn" >
          <Link to="/" className="redirectlogin-link">Login</Link></button>
      </div>

    </>
  )
}

export default Registerpage
