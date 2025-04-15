import React from 'react'
import { useState } from 'react';
import '@/components/styles/homelayout.css';
import ManagerViewExpense from '@/components/layouts/maincontent/ManagerViewExpense';
import UpdateProfile from'@/components/layouts/maincontent/UpdateProfile';
import Profile from '@/components/layouts/maincontent/Profile';
import { useNavigate } from 'react-router-dom';

const ManagerDashboard = () => {

    const navigate = useNavigate();
    
    const[activeSection, setActiveSection] = useState("home");

    const renderContent = () => {
        switch (activeSection) {
            case "view-expense":
                return <ManagerViewExpense />;
            case "update-profile":
                return <UpdateProfile />;
            default:
                return (
                        <Profile />
                    );
        }
    };

const handleLogout = () => {
    // Clear user session or token (example: localStorage)
    localStorage.removeItem('userToken');
    // Use useNavigate to redirect to login page
    navigate('/');
};

return (
    <>
    {/* navigation bar */}
    <div className='home-navbar'>
            <nav className='home-nav-buttons'>
                    <button className='home-nav-btn' onClick={() => setActiveSection("home")}>Home</button>
                    <button className='home-nav-btn' onClick={() => setActiveSection("view-expense")}>ViewExpense</button>
                    <button className='home-nav-btn' onClick={() => setActiveSection("update-profile")}>EditProfile</button>
            </nav>
            <button className='logout-btn' onClick={handleLogout}>LogOut</button>
    </div>
    <div>
            {renderContent()}
    </div>
    </>
);
}

export default ManagerDashboard
