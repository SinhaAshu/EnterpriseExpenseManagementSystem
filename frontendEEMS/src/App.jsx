import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Loginpage from './components/Loginpage'
import Registerpage from './components/Registerpage'
import AdminLogin from './components/AdminLogin';
import EmployeeDashboard from './components/layouts/EmployeeDashboard';
import ManagerDashboard from './components/layouts/ManagerDashboard';
import AdminDashboard from './components/layouts/AdminDashboard';
import ProtectedRoute from './components/ProtectedRoute';

const App = () => {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Loginpage />} />
          <Route path="/register" element={<Registerpage />} />
          <Route path="/adminlogin" element={<AdminLogin />} />
          <Route path="/emplanding" element={<ProtectedRoute allowedRoles={['Employee']}><EmployeeDashboard /> </ProtectedRoute> } />
          <Route path="/managerlanding" element={<ProtectedRoute allowedRoles={['Manager']}><ManagerDashboard /></ProtectedRoute> }/>
          <Route path="/adminlanding" element={<ProtectedRoute allowedRoles={['Admin']}><AdminDashboard /></ProtectedRoute> }/>
        </Routes>
      </Router>
    </>
  )
}

export default App

