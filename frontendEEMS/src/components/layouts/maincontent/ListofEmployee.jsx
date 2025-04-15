import React from 'react'
import { FaTrash } from 'react-icons/fa'

const ListofEmployee = () => {
  return (
    <>
    <h2 style={{textAlign:"center"}}>List of Employee</h2>
    <div className='list-container'>
      <table className='list-table'>
        <thead>
          <tr>
            <th>#ID</th>
            <th>Employee Name</th>
            <th>Employee Email</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>John Doe</td>
            <td>johndun@gmail.com</td>
            <td className='delete-btn'><FaTrash /></td>
           </tr>
        </tbody>
      </table>
      </div>
    </>
  )
}

export default ListofEmployee
