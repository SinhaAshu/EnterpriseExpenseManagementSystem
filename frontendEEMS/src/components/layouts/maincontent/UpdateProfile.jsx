import React, { useState } from 'react'
import '@/components/styles/homelayout.css'
import { toast } from 'react-toastify';
import axios from 'axios';
import { logoutUser } from '../../../utils/auth';
import { useNavigate } from 'react-router-dom'; 

const UpdateProfile = () => {
    const navigate = useNavigate();

    const token = localStorage.getItem('token');
    if(!token){
        navigate('/');
    }

   const [formData, setFormData] = useState(
    { full_name: ''});

   const [email, setEmail] = useState(
    { email: ''});

   const [password, setPassword] = useState(
    { password: ''});

   const handleemail = (e) => {
    setEmail({
      ...email, [e.target.name]: e.target.value
    });
  };

  const handlepassword = (e) => {
    setPassword({
      ...password, [e.target.name]: e.target.value
    });
  };

  const handlechange = (e) => {
    setFormData({
      ...formData, [e.target.name]: e.target.value
    });
  };

   const handleSubmit = async (e) =>{
    e.preventDefault();
    try{
        const response = await axios.put('http://localhost:8080/api/dashboard/profile/update', formData, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        if(response.data){
            toast.success("Updated Successfully!");
            setFormData({
                full_name: ''
            });
        }
    }catch(error){
        toast.error("Something unsual happened! Please try again!");
        console.error("Error:", error);
    }
   };

   const handleChangeEmail = async (e) =>{
    e.preventDefault();
    if (!email.email.trim()) {
        toast.error("Email is required");
        return;
    }
    const confirmEmail = window.confirm("Are you sure you want to change your email?");
    if (!confirmEmail) return;
    try{
        const response = await axios.put('http://localhost:8080/api/dashboard/profile/update-email', email, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        if(response.data){
            toast.success("Updated Successfully!");
            setEmail({
                email: ''
            });
            logoutUser();
        }
    }catch(error){
        toast.error("Something unsual happened! Please try again!");
        console.error("Error:", error);
    }
   };

   const handleChangePassword = async (e) =>{
    e.preventDefault();
    if (!password.password.trim()) {
        toast.error("Password is required");
        return;
    }
    const confirmPassword = window.confirm("Are you sure you want to change your password?");
    if (!confirmPassword) return;
    try{
        const response = await axios.put('http://localhost:8080/api/dashboard/profile/update-password', password, {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
        if(response.data){
            toast.success("Updated Successfully!");
            setPassword({
                password: ''
            });
            logoutUser();
        }
    }catch(error){
        toast.error("Something unsual happened! Please try again!");
        console.error("Error:", error);
    }
   };
   

    return (
        <>
            <h2 className='emp-updateprofile-heading'>Update Your Profile</h2>
            <div className='emp-updateprofile'>
                <form className='editprofile-form' onSubmit={handleSubmit}>

                    <label>Full Name:</label>
                    <input type="text" placeholder='Enter full name' name = "full_name" value={formData.full_name} 
                     onChange={handlechange} required></input>
                     
                    <button className='saveupdate-btn' type='submit'>Save</button>

                    <label>Email:</label>
                    <input type="email" placeholder='Enter new email address' name= "email" value={email.email}
                    onChange={handleemail} required></input>
                    <button className='submit-btn' onClick={handleChangeEmail}>Change Email</button>

                    <label>Password:</label>
                    <input type="password" placeholder='Enter your new password' name= "password" value={password.password}
                    onChange={handlepassword} required></input>
                    <button className='submit-btn' onClick={handleChangePassword}>Change Password</button>
                </form>
            </div>
        </>
    )
}

export default UpdateProfile
