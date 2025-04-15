import React from 'react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '@/components/styles/homelayout.css'
import Profile from '@/components/layouts/maincontent/Profile';
import EmployeeAdExpense from './maincontent/EmployeeAdExpense';
import EmployeeViewExpense from './maincontent/EmployeeViewExpense';
import EmployeeExpenseStatus from './maincontent/EmployeeExpenseStatus';
import UpdateProfile from './maincontent/UpdateProfile';  
import { logoutUser } from '../../utils/auth';

const EmployeeDashboard = () => {
  
  const navigate = useNavigate();

    const[activeSection, setActiveSection] = useState("home");

    const renderContent = () => {
        switch (activeSection) {
            case "add-expense":
                return <EmployeeAdExpense />;
            case "view-expense":
                return <EmployeeViewExpense />;
            case "expense-status":
                return <EmployeeExpenseStatus />;
            case "update-profile":
                return <UpdateProfile />;
            default:
                return (
                        <Profile />
                    );
        }
    };

    const handleLogout = () => {
      logoutUser();
      navigate('/');
    }

  return (
    <>
      {/* navigation bar */}
      <div className='home-navbar'>
        <nav className='home-nav-buttons'>
            <button className='home-nav-btn'  onClick={() => setActiveSection("home")}>Home</button>
            <button className='home-nav-btn' onClick={() => setActiveSection("add-expense")}>AddExpense</button>
            <button className='home-nav-btn' onClick={() => setActiveSection("view-expense")}>ViewExpense</button>
            <button className='home-nav-btn' onClick={() => setActiveSection("expense-status")}>ExpenseStatus</button>
            <button className='home-nav-btn' onClick={() => setActiveSection("update-profile")}>EditProfile</button>
        </nav>
        <button className='logout-btn' onClick={handleLogout}>
            LogOut
        </button>
      </div>
      <div>
        {renderContent()}
      </div>
    </>
  )
}

export default EmployeeDashboard


