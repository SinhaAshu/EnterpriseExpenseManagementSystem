import React, { useEffect, useState } from 'react'
import '@/components/styles/homelayout.css'
import axios from 'axios';
import { istokenExpired } from '../../../utils/auth';
import { useNavigate } from 'react-router-dom';

const Profile = () => {
  const navigate = useNavigate();
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    const fetchProfile = async (e) => {
      const token = localStorage.getItem('token');

      if (!token || istokenExpired(token)) {
        alert('Please log in again!');
        navigate('/');
      }

      try {
        const response = await axios.get('http://localhost:8080/api/dashboard/profile', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });

        setProfile(response.data);
      } catch (error) {
        console.error('Failed to fetch profile:', error);
      }
    };

    fetchProfile();
  }, [navigate]);

  if (!profile) return <p>Loading profile...</p>;
  return (
    <>
      <h1 className='dashboard-heading'>DASHBOARD</h1>
      <div className='emp-profile-content'>
        <p className='emp-profile-fields'><strong>Name:</strong> {profile.full_name}</p>
        <p className='emp-profile-fields'><strong>Email:</strong> {profile.email}</p>
        <p className='emp-profile-fields'><strong>Role:</strong> {profile.role}</p>
      </div>
    </>
  )
}

export default Profile

