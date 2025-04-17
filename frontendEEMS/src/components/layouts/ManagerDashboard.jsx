import React from 'react'
import { useState } from 'react';
import '@/components/styles/homelayout.css';
import ManagerExpenseRequests from '@/components/layouts/maincontent/ManagerExpenseRequests';
import UpdateProfile from '@/components/layouts/maincontent/UpdateProfile';
import Profile from '@/components/layouts/maincontent/Profile';
import { useNavigate } from 'react-router-dom';
import { logoutUser } from '../../utils/auth';
import ListofProcessedRequests from '@/components/layouts/maincontent/ListofProcessedRequests';

const ManagerDashboard = () => {

    const navigate = useNavigate();

    const [activeSection, setActiveSection] = useState("home");

    const renderContent = () => {
        switch (activeSection) {
            case "view-expense":
                return <ManagerExpenseRequests />;
            case "processedrequests":
                return <ListofProcessedRequests />;
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
    };

    return (
        <>
            {/* navigation bar */}
            <div className='home-navbar'>
                <nav className='home-nav-buttons'>
                    <button className='home-nav-btn' onClick={() => setActiveSection("home")}>Home</button>
                    <button className='home-nav-btn' onClick={() => setActiveSection("view-expense")}>PendingRequests</button>
                    <button className='home-nav-btn' onClick={() => setActiveSection("processedrequests")}>Approved/Rejected</button>
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
