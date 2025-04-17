import React from 'react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Profile from '@/components/layouts/maincontent/Profile';
import AllExpensesList from './maincontent/AllExpensesList';
import ListofAllEmployee from './maincontent/ListofAllEmployee';
import { logoutUser } from '../../utils/auth';
import AdminExpenseRequests from './maincontent/AdminExpenseRequests';
import ListofProcessedRequests from './maincontent/ListofProcessedRequests';


const AdminDashboard = () => {

    const navigate = useNavigate();

const[activeSection, setActiveSection] = useState("home");

 const renderContent = () => {
       switch(activeSection){
        case "employee":
        return <ListofAllEmployee />;
        case "processedrequests":
        return <ListofProcessedRequests />;
        case "allexpenselist":
            return <AllExpensesList />;
        case "requests":
        return <AdminExpenseRequests />;
        default:
            return (
                <Profile />
            );
       }
 };
    
    const handleLogout = () => {
        logoutUser();
        navigate('/adminlogin');
    }

  return (
    <>
     <div className='home-navbar'> 
        <nav className='home-nav-buttons'>
            <button className='home-nav-btn' onClick={()=> setActiveSection("home")}>Home</button>
            <button className='home-nav-btn' onClick={()=> setActiveSection("employee")}>Employee</button>
            <button className='home-nav-btn' onClick={()=> setActiveSection("processedrequests")}>Approved/Rejected</button>
            <button className='home-nav-btn' onClick={()=> setActiveSection("allexpenselist")}>ViewExpenses</button>
            <button className='home-nav-btn' onClick={()=> setActiveSection("requests")}>Requests</button>
        </nav>
        <button className='logout-btn' onClick={handleLogout}>LogOut</button>
     </div>
     <div>
        {renderContent()}
     </div>
      
    </>
  )
}

export default AdminDashboard
