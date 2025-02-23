import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Loginpage from './components/Loginpage'
import Registerpage from './components/Registerpage'
import AdminLogin from './components/AdminLogin';

const App = () => {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Loginpage />} />
          <Route path="/register" element={<Registerpage />} />
          <Route path="/adminlogin" element={<AdminLogin />} />
        </Routes>
      </Router>
    </>
  )
}

export default App

