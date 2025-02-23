import React from 'react'
import './styles/stylingheader.css'
import './styles/stylinglogin.css'

const AdminLogin = () => {
    return (
        <>
            <nav className='adminlogin-navbar'>
                <img src="/assets/svg" alt="logo" className='logo' />
                <h1 className='welcome-text'>Welcome Admin!</h1>
            </nav>
            <div className="container">
                <h2 className="login-title">Login</h2>
                <form className="login-form">
                    <label>Username:</label>
                    <input type="text" placeholder="Enter your email" required />

                    <label>Password:</label>
                    <input type="password" placeholder="Enter password" required />

                    <button type="submit" className="submit-btn">Submit</button>
                </form>
            </div>
        </>
    )
}

export default AdminLogin
