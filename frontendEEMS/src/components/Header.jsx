import React from 'react'
import './styles/stylingheader.css'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <>
    <nav className='navbar'>      
           <img src="/assets/svg" alt="logo" className='logo'/>
            <h1 className='title'>Welcome to our company</h1>
           <button className='admin-btn'>
            <Link to="/adminlogin" className='adminlogin-text'>ADMIN</Link></button>        
    </nav>
    </> 
  )
}

export default Header
