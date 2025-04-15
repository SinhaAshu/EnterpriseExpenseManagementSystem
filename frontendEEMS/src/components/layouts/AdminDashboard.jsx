import React from 'react'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import Profile from '@/components/layouts/maincontent/Profile';
import ManagerViewExpense from './maincontent/ManagerViewExpense';
import AllExpensesList from './maincontent/AllExpensesList';
import ListofManger from './maincontent/ListofManger';
import ListofEmployee from './maincontent/ListofEmployee';


const AdminDashboard = () => {

    const navigate = useNavigate();

const[activeSection, setActiveSection] = useState("home");

 const renderContent = () => {
       switch(activeSection){
        case "manager":
        return <ListofManger />;
        case "employee":
        return <ListofEmployee />;
        case "allexpenselist":
            return <AllExpensesList />;
        case "requests":
        return <ManagerViewExpense />;
        default:
            return (
                <Profile />
            );
       }
 };
    
    const handleLogout = () => {
        localStorage.removeItem('userToken');
        navigate('/adminlogin');
    }

  return (
    <>
     <div className='home-navbar'> 
        <nav className='home-nav-buttons'>
            <button className='home-nav-btn' onClick={()=> setActiveSection("home")}>Home</button>
            <button className='home-nav-btn' onClick={()=> setActiveSection("manager")}>Mangers</button>
            <button className='home-nav-btn' onClick={()=> setActiveSection("employee")}>Employee</button>
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
