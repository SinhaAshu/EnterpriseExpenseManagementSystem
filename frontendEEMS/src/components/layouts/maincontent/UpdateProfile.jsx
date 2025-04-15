import React from 'react'
import '@/components/styles/homelayout.css'

const UpdateProfile = () => {
    return (
        <>
            <h2 className='emp-updateprofile-heading'>Update Your Profile</h2>
            <div className='emp-updateprofile'>
                <form className='editprofile-form'>

                    <label>Full Name:</label>
                    <input type="text" placeholder='Enter full name'></input>

                    <label>Email:</label>
                    <input type="email" placeholder='Enter new email address'></input>

                    <label>Password:</label>
                    <input type="password" placeholder='Enter your new password'></input>

                    <label>Choose Role:</label>
                    <select>
                        <option value="">Employee</option>
                        <option value="">Manager</option>
                    </select>

                    <button className='saveupdate-btn' type='submit'>Save</button>

                </form>
            </div>
        </>
    )
}

export default UpdateProfile
