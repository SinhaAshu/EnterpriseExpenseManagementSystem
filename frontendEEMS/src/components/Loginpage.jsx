import React, { useState } from 'react'
import Header from './Header'
import './styles/stylinglogin.css'
import { Link } from 'react-router-dom'
import axios from 'axios'

const Loginpage = () => {

  const [formData, setformData] = useState({
    username:'',
    password:'',
    role:'Employee'
  });

  const handlechange = (e) =>{
    setformData({
     ...formData, [e.target.name]: e.target.value
    });
   };
 
   const handlesubmit = async (e) =>{
     e.preventDefault();
     try {
       const response = await axios.post('http://localhost:8080/login', formData);
       alert(response.data);
     } catch (error) {
       console.error("Error:", error); // Log the actual error in console
       alert(`Registration failed: ${error.message}`); // Show a proper error message
     }
   };

  return (
    <>
      <Header />
      <div className="container">
        <h2 className="login-title">Login</h2>
        <form className="login-form" onSubmit={handlesubmit}>
          <label>Username:</label>
          <input type="text" placeholder="Enter your email" name='username' value={formData.username} onChange={handlechange} required />

          <label>Password:</label>
          <input type="password" placeholder="Enter password" name='password' value={formData.password} onChange={handlechange} required />

          <label>Choose Role:</label>
          <select name='role' value={formData.role} onChange={handlechange}>
            <option value="employee">Employee</option>
            <option value="manager">Manager</option>
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
