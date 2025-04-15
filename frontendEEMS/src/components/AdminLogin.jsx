import './styles/stylingheader.css'
import './styles/stylinglogin.css'
import React, { useState } from 'react'
import axios from 'axios'
import { useNavigate } from 'react-router-dom'

const AdminLogin = () => {

  const navigate = useNavigate();

    const [formData, setformData] = useState({
        username: '',
        password: '',
        role:'Admin'
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
          if(response.data === 'Login successful!'){
            navigate('/adminlanding');
          }else if (response.data === 'Login unsuccessful!') {
            alert('Login failed');
        }
        else {
          alert(response.data);
        }
        } catch (error) {
          console.error("Error:", error); // Log the actual error in console
          alert(`Registration failed: ${error.message}`); // Show a proper error message
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
                <form className="login-form" onSubmit={handlesubmit}>
                    <label>Username:</label>
                    <input type="text" placeholder="Enter your email" name='username' value={formData.username} onChange={handlechange} required />

                    <label>Password:</label>
                    <input type="password" placeholder="Enter password" name='password' value={formData.password} onChange={handlechange} required />

                    <input type="hidden" name="role" value="Admin" />

                    <button type="submit" className="submit-btn">Submit</button>
                </form>
            </div>
        </>
    )
}

export default AdminLogin
