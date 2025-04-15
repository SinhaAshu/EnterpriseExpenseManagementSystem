import React from 'react'
import '@/components/styles/homelayout.css'

const Profile = () => {
  return (
    <>
      <h1 className='dashboard-heading'>DASHBOARD</h1>
      <div className='emp-profile-content'>
        <p className='emp-profile-fields'><strong>Name:</strong> Ashutosh Sinha</p>
        <p className='emp-profile-fields'><strong>Email:</strong> ashusinha1999@gmail.com</p>
        <p className='emp-profile-fields'><strong>Role:</strong> Employee</p>
      </div>
    </>
  )
}

export default Profile

