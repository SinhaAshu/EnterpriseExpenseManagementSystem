import React from 'react'
import Header from './Header'
import './styles/stylinglogin.css'
import { Link } from 'react-router-dom'

const Loginpage = () => {
  return (
    <>
      <Header />
      <div className="container">
        <h2 className="login-title">Login</h2>
        <form className="login-form">
          <label>Username:</label>
          <input type="text" placeholder="Enter your email" required />

          <label>Password:</label>
          <input type="password" placeholder="Enter password" required />

          <label>Choose Role:</label>
          <select>
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
